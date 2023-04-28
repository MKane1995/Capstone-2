package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.*;
import com.techelevator.util.BasicLogger;
import org.springframework.http.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.security.Principal;

public class ApiService {
    private String API_BASE_URL = "http://localhost:8080/";
    private RestTemplate restTemplate;
    private AuthenticatedUser currentUser;

    public ApiService(String url, AuthenticatedUser currentUser){
        this.restTemplate = new RestTemplate();
        this.currentUser = currentUser;
        this.API_BASE_URL = url;
    }

    public double getBalance() {
        Double balance = null;
        try {
            balance = restTemplate.exchange(API_BASE_URL + "balance/" + currentUser.getUser().getId(), HttpMethod.GET, makeAuthEntity(), Double.class).getBody();
            System.out.println("Your current account balance is: $" + balance);
        } catch (RestClientException e) {
            System.out.println(e.getMessage());
        }
        return balance;
//        Double balance = 0.00;
//        try {
//            balance = restTemplate.getForObject(BASE_API + "/accounts/{id}", double.class);
//        }
//        catch (RestClientResponseException e) {
//            System.out.println(e.getMessage());
//        }
//        return balance;
    }

    private HttpEntity makeAuthEntity(){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(currentUser.getToken());
        HttpEntity entity = new HttpEntity(headers);
        return entity;
    }

}


