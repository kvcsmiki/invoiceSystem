package com.task.invoice.core.services;

import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class CaptchaService {

    public boolean verifyCaptcha(String captchaResponse) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://www.google.com/recaptcha/api/siteverify";
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("secret", "6LeSFU8lAAAAAGqplMQkL4YKeJT-QnUZYimsqECM");
        parameters.add("response", captchaResponse);
        parameters.add("remoteip", "");
        ResponseEntity<Map> response = restTemplate.postForEntity(url, parameters, Map.class);
        Map<String, Object> responseBody = response.getBody();
        boolean success = (Boolean) responseBody.get("success");
        return success;
    }
}
