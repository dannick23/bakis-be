package com.example.bakis.utils;

import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    public static String getEmail(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
