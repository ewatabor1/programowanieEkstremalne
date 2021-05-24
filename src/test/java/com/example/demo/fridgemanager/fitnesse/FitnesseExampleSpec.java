package com.example.demo.fridgemanager.fitnesse;

import com.example.demo.fridgemanager.dto.ProductDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.stream.Collectors;

public class FitnesseExampleSpec {
    private WebClient client;
    private ObjectMapper mapper;

    public FitnesseExampleSpec() {
        client = WebClient.create("http://localhost:8080/api");
        mapper = new ObjectMapper();
    }

    public int numberOfProducts() {
        try {

            Mono<Object[]> response = client.get()
                    .uri("/products")
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(Object[].class).log();
            Object[] objects = response.block();
            return Arrays.stream(objects)
                    .map(object -> mapper.convertValue(object, ProductDTO.class))
                    .collect(Collectors.toList())
                    .size();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
}
