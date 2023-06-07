package com.example.bakis.apis.model;

public record SmsRequest(String key,
                         String name,
                         String password,
                         String content,
                         String[] to,
                         String responseformat) {
}
