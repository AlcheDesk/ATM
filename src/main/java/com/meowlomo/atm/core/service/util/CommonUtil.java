package com.meowlomo.atm.core.service.util;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

public interface CommonUtil {

    Map<String, String> convertJsonToMap(JsonNode jsonNode);

    public JsonNode convertMapToJson(Map<?, ?> map);

    Map<String, String> convertMutiMapToMap(Map<String, List<String>> mutiMap);

    JsonNode convertNameToCamelCase(JsonNode jsonNode);

    String convertNameToCamelCase(String jsonString);

}
