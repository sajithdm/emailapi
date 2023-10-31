package com.java.airnz.emailapi.util;

public class Validator {

    public static Boolean authenticate(String bearerToken){
        // ToDo: implement token validation
        if (bearerToken == null || bearerToken.isEmpty()) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
