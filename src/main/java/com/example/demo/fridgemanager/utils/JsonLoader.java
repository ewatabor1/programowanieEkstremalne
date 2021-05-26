package com.example.demo.fridgemanager.utils;

import com.example.demo.fridgemanager.controller.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class JsonLoader {
    public static <T> List<T> readJsonListFromFile(String file, Class<T> clazz, ObjectMapper objectMapper) throws JsonProcessingException {
        String contentAsString = getResourceFileAsString(file);
        JavaType nestedListType = objectMapper.getTypeFactory().constructParametricType(List.class, clazz);
        List<T> response =  objectMapper.readValue(contentAsString,nestedListType);

        return response;
    }
    public static String getResourceFileAsString(String fileName) {
        InputStream is = getResourceFileAsInputStream(fileName);
        if (is != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            return (String)reader.lines().collect(Collectors.joining(System.lineSeparator()));
        } else {
            throw new RuntimeException("resource not found");
        }
    }

    public static InputStream getResourceFileAsInputStream(String fileName) {
        ClassLoader classLoader = JsonLoader.class.getClassLoader();
        return classLoader.getResourceAsStream(fileName);
    }
}
