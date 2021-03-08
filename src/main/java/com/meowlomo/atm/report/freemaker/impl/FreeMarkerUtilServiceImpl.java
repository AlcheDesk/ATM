package com.meowlomo.atm.report.freemaker.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.report.freemaker.FreeMakerDBTemplateLoader;
import com.meowlomo.atm.report.freemaker.FreeMarkerUtilService;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class FreeMarkerUtilServiceImpl implements FreeMarkerUtilService {

    private final Logger logger = LoggerFactory.getLogger(FreeMarkerUtilServiceImpl.class);

    @Autowired
    private FreeMakerDBTemplateLoader freeMakerTemplateLoader;

    public Template getTemplate(String name)
            throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException {
        try {
            // 通过Freemaker的Configuration读取相应的ftl
            Configuration cfg = new Configuration(Configuration.getVersion());
            // 设定去哪里读取相应的ftl模板文件
            logger.info("#######    getTemplate  begin    ########");
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("templates/ftl/").getFile());
            cfg.setDirectoryForTemplateLoading(file);
            // 在模板文件目录中找到名称为name的文件
            Template temp = cfg.getTemplate(name);
            logger.info("#######    getTemplate  end    ########");
            return temp;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 控制台输出
     * 
     * @param name
     * @param root
     */
    public void print(String name, Map<String, Object> root) {
        try {
            // 通过Template可以将模板文件输出到相应的流
            Template temp = this.getTemplate(name);
            temp.process(root, new PrintWriter(System.out));
        }
        catch (TemplateException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 输出HTML文件字符串，在本地保存文件
     * 
     * @param name
     * @param root
     * @param outFile
     */
    public String fprint(String name, Map<String, Object> root, String outFile) {
        FileWriter out = null;
        try {
            // 通过一个文件输出流，就可以写到相应的文件中，此处用的是绝对路径
            out = new FileWriter(new File("src/main/resources/templates/page/" + outFile));
            Template temp = this.getTemplate(name);
            temp.process(root, out);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (TemplateException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (out != null)
                    out.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return out.toString();
    }

    /**
     * 输出HTML文件字符串，在本地不保存文件
     * 
     * @param name
     * @param root
     * @param outFile
     */
    public String fprintWithoutFile(Map<String, Object> root, String templateName) {
        StringWriter out = new StringWriter();
        try {
            Configuration cfg = new Configuration(Configuration.getVersion());
            cfg.setDefaultEncoding("UTF-8");
            // Template t = this.getTemplate(name);
            Template t = freeMakerTemplateLoader.loadTempalte(templateName);
            t.process(root, out);
            out.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (TemplateException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (out != null)
                    out.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        // return out.toString();
        return out.getBuffer().toString();
    }

}
