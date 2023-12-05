package com.ensueno.ensueno.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.ensueno.ensueno.model.*;

@Service
public class LoginService {

    private final String apiUrl = "http://127.0.0.1:8000/login/"; // Ajusta la URL según tu configuración

    public String login(String email, String password) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("email", email);
        map.add("password", password);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<LoginResponse> response = restTemplate.postForEntity(apiUrl, request, LoginResponse.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            LoginResponse loginResponse = response.getBody();
            return loginResponse.getToken();
        } else {
            return null;
        }
    }
}
