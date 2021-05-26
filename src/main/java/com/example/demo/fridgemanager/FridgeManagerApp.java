package com.example.demo.fridgemanager;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.io.IOException;
import java.util.Map;

@SpringBootApplication
@PropertySource(value = {"classpath:/config.json"}, factory = FridgeManagerApp.JsonLoader.class)
public class FridgeManagerApp {

    public static void main(String[] args) {
        SpringApplication.run(FridgeManagerApp.class, args);
    }

    public static class JsonLoader implements PropertySourceFactory {

        @Override
        public org.springframework.core.env.PropertySource<?> createPropertySource(String name,
                                                                                   EncodedResource resource) throws IOException {
            Map readValue = new ObjectMapper().readValue(resource.getInputStream(), Map.class);
            return new MapPropertySource("json-source", readValue);
        }

    }
}
