package com.example.CustomerManagementPortal.service;

import com.example.CustomerManagementPortal.dto.ServerObj;
import com.example.CustomerManagementPortal.transformer.CustomerTransformer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class RemoteApiService {

    @Autowired
    CustomerService customerService;

    public String callApi(String apiUrl, String requestBody) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                apiUrl,
                HttpMethod.POST,  // Change this based on your API's HTTP method
                requestEntity,
                String.class
        );

        // You can handle the response as needed
        String responseBody = responseEntity.getBody();
        System.out.println("Token is" + responseBody);
        return responseBody;
    }

    public void getCustomers(String token, String apiUrl) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<ServerObj[]> responseEntity = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,  // Change this based on your API's HTTP method
                requestEntity,
                ServerObj[].class
        );

        ServerObj[] jsonResponse = responseEntity.getBody();

        for(ServerObj x : jsonResponse){
            System.out.println(x.getUuid());
            String res = customerService.addCustomer(CustomerTransformer.getCustomerDtoFromServerObj(x));
            System.out.println(res);
        }


    }

}
