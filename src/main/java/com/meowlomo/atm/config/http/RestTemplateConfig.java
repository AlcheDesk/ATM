package com.meowlomo.atm.config.http;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import okhttp3.ConnectionPool;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.Response;

@Configuration
public class RestTemplateConfig {

	@Value("${meowlomo.security.authentication.header.name:Authorization}")
	private String authenticationHeaderName;

	@Value("${meowlomo.http.connection-pool-size:100}")
	private int HTTP_CONNECTION_POOL_SIZE;

	@Value("${meowlomo.http.connection-pool-keep-alive-duration-in-seconds:60}")
	private int HTTP_CONNECTION_POOL_KEEP_ALIVE_DURATION_IN_SECONDS;

	@Value("${meowlomo.http.timeout-in-seconds:2}")
	private int HTTP_TIMEOUT_IN_SECONDS;

	@Value("${meowlomo.http.trust-all:false}")
	private boolean HTTP_TRUST_ALL;

	@Value("${meowlomo.http.jwt-token:#{null}}")
	private String jwtToken;

	class JwtTokenInterceptor implements Interceptor {

		private String authenticationHeaderName;

		private String authToken;

		public JwtTokenInterceptor(String authenticationHeaderName, String token) {
			this.authToken = token;
			this.authenticationHeaderName = authenticationHeaderName;
		}

		@Override
		public Response intercept(Chain chain) throws IOException {
			Request original = chain.request();
			Request.Builder builder = original.newBuilder().header(authenticationHeaderName, authToken);
			Request request = builder.build();
			return chain.proceed(request);
		}
	}

	private X509TrustManager x509TrustManager() {
		return new X509TrustManager() {

			@Override
			public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
			}

			@Override
			public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
			}

			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return new X509Certificate[0];
			}
		};
	}

	private SSLSocketFactory sslSocketFactory() {
		try {
			SSLContext sslContext = SSLContext.getInstance("TLS");
			sslContext.init(null, new TrustManager[] { x509TrustManager() }, new SecureRandom());
			return sslContext.getSocketFactory();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ConnectionPool okhttpConnectionPool() {
		return new ConnectionPool(HTTP_CONNECTION_POOL_SIZE, HTTP_CONNECTION_POOL_KEEP_ALIVE_DURATION_IN_SECONDS,
				TimeUnit.SECONDS);
	}

	@Bean
	public OkHttpClient okHttpClient() {
		Builder clientBuilder = new OkHttpClient.Builder().retryOnConnectionFailure(false)
				.connectionPool(okhttpConnectionPool()).readTimeout(HTTP_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
				.writeTimeout(HTTP_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
				.connectTimeout(HTTP_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);
		if (HTTP_TRUST_ALL) {
			clientBuilder.sslSocketFactory(sslSocketFactory(), x509TrustManager());
		}
		OkHttpClient client = clientBuilder.build();
		return client;
	}

	@Bean
	public OkHttp3ClientHttpRequestFactory okHttp3ClientHttpRequestFactory(OkHttpClient okHttpClient) {
		OkHttp3ClientHttpRequestFactory factory = new OkHttp3ClientHttpRequestFactory(okHttpClient);
		return factory;
	}

	@Bean
	public RestTemplate restTemplate(OkHttp3ClientHttpRequestFactory okHttp3ClientHttpRequestFactory) {
	    ClientHttpRequestFactory factory = new BufferingClientHttpRequestFactory(okHttp3ClientHttpRequestFactory);
		RestTemplate restTemplate = new RestTemplate(factory);
		List<ClientHttpRequestInterceptor> interceptorList = new ArrayList<ClientHttpRequestInterceptor>();
	    interceptorList.add(new HeaderRequestInterceptor(authenticationHeaderName));
		interceptorList.add(new JsonMimeInterceptor());
		interceptorList.add(new LoggingRequestInterceptor());
		restTemplate.setInterceptors(interceptorList);
		//use utf 8 as default for string
		restTemplate.getMessageConverters().add(1, new StringHttpMessageConverter(Charset.forName("UTF-8")));
		restTemplate.setErrorHandler(new RestTemplateErrorHandler());
		return restTemplate;
	}
}
