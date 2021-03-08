package com.meowlomo.atm.core.service.util.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.CaseFormat;
import com.google.common.collect.Lists;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.meowlomo.atm.core.service.util.CommonUtil;

@Component
public class CommonUtilImpl implements CommonUtil {

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonConverter;

    private final Logger logger = LoggerFactory.getLogger(CommonUtilImpl.class);

    @Override
    public Map<String, String> convertJsonToMap(JsonNode jsonNode) {
        if (jsonNode == null) { return null; }
        TypeReference<HashMap<String, String>> typeRef = new TypeReference<HashMap<String, String>>() {
        };
        return jacksonConverter.getObjectMapper().convertValue(jsonNode, typeRef);
    }

    @Override
    public JsonNode convertMapToJson(Map<?, ?> map) {
        return jacksonConverter.getObjectMapper().valueToTree(map);
    }

    @Override
    public Map<String, String> convertMutiMapToMap(Map<String, List<String>> mutiMap) {
        Map<String, String> returnMap = new HashMap<String, String>();
        if (mutiMap == null) { return returnMap; }
        for (Entry<String, List<String>> entry : mutiMap.entrySet()) {
            returnMap.put(entry.getKey(), String.join(";", entry.getValue()));
        }
        return returnMap;
    }

    @Override
    public JsonNode convertNameToCamelCase(JsonNode jsonNode) {
        if (jsonNode == null) { return null; }
        ObjectMapper objectMapper = jacksonConverter.getObjectMapper();
        try {
            String jsonString = objectMapper.writeValueAsString(jsonNode);
            return objectMapper.readTree(this.convertNameToCamelCase(jsonString));
        }
        catch (JsonProcessingException e) {
            logger.error("Error converting json naming", e);
            return null;
        }
    }

    @Override
    public String convertNameToCamelCase(String jsonString) {
        JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();
        // get the fields
        Set<String> keyInterator = jsonObject.keySet();
        List<String> keyList = Lists.newArrayList(keyInterator);
        for (int i = 0; i < keyList.size(); i++) {
            String key = keyList.get(i);
            JsonElement value = jsonObject.get(key);
            // check underscore
            if (key.contains("_") && StringUtils.isAllUpperCase(key.replaceAll("_", ""))) {
                String newKey = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, key);
                jsonObject.remove(key);
                jsonObject.add(newKey, value);
            }
            else if (key.contains("_") && StringUtils.isAllLowerCase(key.replaceAll("_", ""))) {
                String newKey = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, key);
                jsonObject.remove(key);
                jsonObject.add(newKey, value);
            }
        }
        return jsonObject.toString();
    }
}
