//package com.meowlomo.atm.core.service.base.congen;
//
//import java.io.Serializable;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.util.Arrays;
//import java.util.Calendar;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//import java.util.TreeMap;
//import java.util.stream.Collectors;
//
//import javax.ws.rs.core.MultivaluedMap;
//import javax.ws.rs.core.UriInfo;
//
//import org.apache.commons.lang3.BooleanUtils;
//import org.apache.ibatis.session.RowBounds;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.stereotype.Component;
//
//import com.github.pagehelper.PageRowBounds;
//import com.google.common.base.CaseFormat;
//import com.meowlomo.atm.config.RuntimeVariables;
//
//@Component
//public class ExampleGenerator<T> {
//
//    private final Logger logger = LoggerFactory.getLogger(ExampleGenerator.class);
//    
//    @Autowired
//    private MappingJackson2HttpMessageConverter jacksonConverter;
//
//    private T example;
//
//    public ExampleGenerator(Class<T> example) {
//        try {
//            this.example = example.newInstance();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//    }
//    
//    public  T generateExample(Map<String, ? extends Serializable> condition) {
//        //convert the map to case insensitive order
//        Map<String, Object> conditionMap = new TreeMap<String, Object>(String.CASE_INSENSITIVE_ORDER);
//        //load the data 
//        conditionMap.putAll(condition);
//        try {
//
//            // generate the example object and then create the criteria instance
//            Object criteriaObject = null;
//            if (criteria == null) {
//                // get the create Criteria method
//                Method exampleCriteriaMethod = example.getClass().getDeclaredMethod("createCriteria");
//                criteriaObject = exampleCriteriaMethod.invoke(example);
//            }
//            else {
//                // initialize the criteria
//                criteriaObject = criteria;
//            }
//            // get the methods
//            Map<String, Method> methods = this.getMathods(criteriaObject.getClass().getDeclaredMethods());
//            Set<String> methodNames = methods.keySet();
//
//            // check the field from the query string map
//            String ids = queryParams.getFirst("ids");
//            String orderIndexs = queryParams.getFirst("orderIndex");
//            String name = queryParams.getFirst("name");
//            String names = queryParams.getFirst("names");
//            String comment = queryParams.getFirst("comment");
//            String startDateString = queryParams.getFirst("startDate");
//            String endDateString = queryParams.getFirst("endDate");
//            String orderBy = queryParams.getFirst("orderBy");
//            String status = queryParams.getFirst("status");
//            String type = queryParams.getFirst("type");
//            String runType = queryParams.getFirst("runType");
//            String mode = queryParams.getFirst("mode");
//            String target = queryParams.getFirst("target");
//            String message = queryParams.getFirst("message");
//            String values = queryParams.getFirst("values");
//            String log = queryParams.getFirst("log");
//            String isDriver = queryParams.getFirst("isDriver");
//            String isDefault = queryParams.getFirst("isDefault");
//            String isActive = queryParams.getFirst("active");
//            String isDeleted = queryParams.getFirst("deleted");
//
//            if (ids != null && methodNames.contains("andIdIn")) {
//                String[] idsStringArray = ids.trim().split(",");
//                List<Long> idsList = Arrays.asList(idsStringArray).stream().mapToLong(Long::parseLong).boxed()
//                        .collect(Collectors.toList());
//                Method method = methods.get("andIdIn");
//                method.invoke(criteriaObject, idsList);
//            }
//
//            if (orderIndexs != null && methodNames.contains("andOrderIndexIn")) {
//                String[] orderIndexsStringArray = orderIndexs.trim().split(",");
//                List<Long> orderIndexsList = Arrays.asList(orderIndexsStringArray).stream().mapToLong(Long::parseLong)
//                        .boxed().collect(Collectors.toList());
//                Method method = methods.get("andOrderIndexIn");
//                method.invoke(criteriaObject, orderIndexsList);
//            }
//
//            if (names != null && methodNames.contains("andNameIn")) {
//                String[] idsStringArray = names.trim().split(",");
//                List<String> namesList = Arrays.asList(idsStringArray);
//                Method method = methods.get("andNameIn");
//                method.invoke(criteriaObject, namesList);
//            }
//
//            if (values != null && methodNames.contains("andValueIn")) {
//                String[] idsStringArray = values.trim().split(",");
//                List<String> valuesList = Arrays.asList(idsStringArray);
//                Method method = methods.get("andValueIn");
//                method.invoke(criteriaObject, valuesList);
//            }
//
//            if (name != null && methodNames.contains("andNameLikeInsensitive")) {
//                Method method = methods.get("andNameLikeInsensitive");
//                method.invoke(criteriaObject, name);
//            }
//
//            if (log != null && methodNames.contains("andLogLikeInsensitive")) {
//                Method method = methods.get("andLogLikeInsensitive");
//                method.invoke(criteriaObject, log);
//            }
//
//            if (status != null && methodNames.contains("andStatusEqualTo")) {
//                Method method = methods.get("andStatusEqualTo");
//                method.invoke(criteriaObject, status);
//            }
//
//            if (type != null && methodNames.contains("andTypeEqualTo")) {
//                Method method = methods.get("andTypeEqualTo");
//                method.invoke(criteriaObject, type);
//            }
//
//            if (target != null && methodNames.contains("andTargetLikeInsensitive")) {
//                Method method = methods.get("andTargetLikeInsensitive");
//                method.invoke(criteriaObject, target);
//            }
//
//            if (message != null && methodNames.contains("andMessageLikeInsensitive")) {
//                Method method = methods.get("andMessageLikeInsensitive");
//                method.invoke(criteriaObject, message);
//            }
//
//            if (comment != null && methodNames.contains("andCommentLikeInsensitive")) {
//                Method method = methods.get("andCommentLikeInsensitive");
//                method.invoke(criteriaObject, comment);
//            }
//
//            if (isActive != null && methodNames.contains("andActiveEqualTo")) {
//                Method method = methods.get("andActiveEqualTo");
//                method.invoke(criteriaObject, BooleanUtils.toBooleanObject(isActive));
//            }
//
//            // default action for active filter
//            if (isActive == null && methodNames.contains("andActiveEqualTo")) {
//                Method method = methods.get("andActiveEqualTo");
//                method.invoke(criteriaObject, Boolean.TRUE);
//            }
//
//            // default delete is set to false
//            if (isDeleted == null && methodNames.contains("andDeletedEqualTo")) {
//                Method method = methods.get("andDeletedEqualTo");
//                method.invoke(criteriaObject, BooleanUtils.toBooleanObject("false"));
//            }
//
//            if (isDeleted != null && methodNames.contains("andDeletedEqualTo") && !isDeleted.equalsIgnoreCase("all")) {
//                Method method = methods.get("andDeletedEqualTo");
//                method.invoke(criteriaObject, BooleanUtils.toBooleanObject(isDeleted));
//            }
//
//            if (isDeleted != null && isDeleted.equalsIgnoreCase("all") && methodNames.contains("andDeletedIsNotNull")) {
//                Method method = methods.get("andDeletedIsNotNull");
//                method.invoke(criteriaObject);
//            }
//
//            if (runType != null && methodNames.contains("andRunTypeEqualTo")) {
//                Method method = methods.get("andRunTypeEqualTo");
//                method.invoke(criteriaObject, runType);
//            }
//            else if (mode != null && mode.equalsIgnoreCase("DEVELOPMENT")
//                    && methodNames.contains("andRunTypeEqualTo")) {
//                Method method = methods.get("andRunTypeEqualTo");
//                method.invoke(criteriaObject, mode);
//            }
//
//            Calendar startCal = Calendar.getInstance();
//            Calendar endCal = Calendar.getInstance();
//            if (startDateString != null && org.apache.commons.lang3.StringUtils.isNumeric(startDateString)
//                    && methodNames.contains("andCreatedAtGreaterThanOrEqualTo")) {
//                startCal.setTimeInMillis(Long.valueOf(startDateString));
//                Method method = methods.get("andCreatedAtGreaterThanOrEqualTo");
//                method.invoke(criteriaObject, startCal.getTime());
//            }
//
//            if (startDateString != null && org.apache.commons.lang3.StringUtils.isNumeric(startDateString)
//                    && endDateString != null && org.apache.commons.lang3.StringUtils.isNumeric(endDateString)
//                    && methods.keySet().contains("andCreatedAtBetween")) {
//                startCal.setTimeInMillis(Long.valueOf(startDateString));
//                endCal.setTimeInMillis(Long.valueOf(endDateString));
//                Method method = methods.get("andCreatedAtBetween");
//                method.invoke(criteriaObject, startCal.getTime(), endCal.getTime());
//            }
//
//            if (endDateString != null && org.apache.commons.lang3.StringUtils.isNumeric(endDateString)
//                    && methods.keySet().contains("andCreatedAtLessThanOrEqualTo")) {
//                endCal.setTimeInMillis(Long.valueOf(endDateString));
//                Method method = methods.get("andCreatedAtLessThanOrEqualTo");
//                method.invoke(criteriaObject, endCal.getTime());
//            }
//
//            if (orderBy != null) {
//                orderBy = orderBy + ",id asc";
//                Method exampleOrderMethod = example.getClass().getDeclaredMethod("setOrderByClause",
//                        String.class);
//                exampleOrderMethod.invoke(example,
//                        CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, orderBy));
//            }
//
//            // is driver
//            if (isDriver != null && methods.keySet().contains("andIsDriverEqualTo")
//                    && BooleanUtils.toBooleanObject(isDriver) != null) {
//                Method method = methods.get("andIsDriverEqualTo");
//                method.invoke(criteriaObject, BooleanUtils.toBooleanObject(isDriver));
//            }
//
//            // is default
//            if (isDefault != null && methods.keySet().contains("andIsDefaultEqualTo")
//                    && BooleanUtils.toBooleanObject(isDefault) != null) {
//                Method method = methods.get("andIsDefaultEqualTo");
//                method.invoke(criteriaObject, BooleanUtils.toBooleanObject(isDefault));
//            }
//            Method exampleOrMethod = example.getClass().getDeclaredMethod("or", criteriaObject.getClass());
//            exampleOrMethod.invoke(example, criteriaObject);
//            return (T) example;
//        }
//        catch (InstantiationException e) {
//            logger.error("could not create example object instance", e);
//        }
//        catch (IllegalAccessException e) {
//            logger.error("could not create example object instance", e);
//        }
//        catch (IllegalArgumentException e) {
//            logger.error("Error on invoking method", e);
//        }
//        catch (InvocationTargetException e) {
//            logger.error("Error on invoking method", e);
//        }
//        catch (NoSuchMethodException e) {
//            logger.error("Error on creating method object", e);
//        }
//        catch (SecurityException e) {
//            logger.error("Error on creating method object", e);
//        }
//        return null;
//    }
//
//    private Map<String, Method> getMathods(Method[] methodList) {
//        // loop through the method
//        Map<String, Method> methods = new HashMap<String, Method>();
//        for (int i = 0; i < methodList.length; i++) {
//            Method method = methodList[i];
//            methods.put(method.getName(), method);
//        }
//        return methods;
//    }
//
//    public RowBounds generateSearchRowBounds(UriInfo uriInfo) {
//        String queryString = uriInfo.getQueryParameters();
//        // check id default, no page size
//        if (queryParams.getFirst("pageSize") == null) {
//            return new PageRowBounds(0, RuntimeVariables.DEFAULT_LIMIT);
//        }
//        // check not default , and all
//        else if (queryParams.getFirst("pageSize") != null && (queryParams.getFirst("pageSize").equalsIgnoreCase("all")
//                || queryParams.getFirst("pageSize").equalsIgnoreCase("ALL")
//                || queryParams.getFirst("pageSize").equalsIgnoreCase("All"))) {
//            return new RowBounds();
//        }
//        // check not default , and all
//        else {
//            String pageSize = queryParams.getFirst("pageSize");
//            String pageNumber = queryParams.getFirst("pageNumber");
//            if (pageSize != null && pageNumber != null && org.apache.commons.lang3.StringUtils.isNumeric(pageSize)
//                    && org.apache.commons.lang3.StringUtils.isNumeric(pageNumber)) {
//
//                int limit = Integer.parseInt(pageSize);
//                int offect = limit * (Integer.parseInt(pageNumber) - 1);
//                return new PageRowBounds(offect, limit);
//            }
//            else {
//                return new PageRowBounds(0, RuntimeVariables.DEFAULT_LIMIT);
//            }
//        }
//    }
//}
