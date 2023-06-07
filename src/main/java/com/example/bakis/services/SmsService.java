package com.example.bakis.services;

import com.example.bakis.apis.model.SmsRequest;
import com.example.bakis.apis.model.UserDTO;
import com.example.bakis.database.repository.SystemRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@Slf4j
@RequiredArgsConstructor
public class SmsService {
    public void sendSms(LocalDateTime from, String phoneNumber) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        var request = new SmsRequest("189c0e169f21077fa5b1e1c0afeaccdf",
                "a19731001@gmail.com",
                "Minecraft0family",
                "Hello! Don't forget your upcoming visit on " + from.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")),
                new String[]{phoneNumber.replace("+", "")},
                "JSON"
                );

        ResponseEntity<String> response = restTemplate.postForEntity("https://api.sendberry.com/SMS/SEND", request, String.class, headers);

        System.out.println(response.getBody());
    }
}