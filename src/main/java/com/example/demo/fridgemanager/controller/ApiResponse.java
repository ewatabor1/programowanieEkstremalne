package com.example.demo.fridgemanager.controller;

import java.util.Collections;
import java.util.List;

public class ApiResponse<T> {
    private T data;
private List<Error> errors;

    public ApiResponse() {
    }

    public ApiResponse(T data) {
        this.data = data;
        this.errors= Collections.emptyList();
    }

    public ApiResponse(List<Error> errors) {
        this.data=null;
        this.errors = errors;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
