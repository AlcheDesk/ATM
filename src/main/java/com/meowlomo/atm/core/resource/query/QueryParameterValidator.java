package com.meowlomo.atm.core.resource.query;

public class QueryParameterValidator {
    
    public static boolean idsValidator(String idsString) {
        if (idsString == null) {
            return false;
        }
        else if (idsString.trim().isEmpty()) {
            return true;
        }
        else {
            return idsString.trim().matches("^([1-9]\\d*)?(,[1-9]\\d*)*$");
        }
    }
}
