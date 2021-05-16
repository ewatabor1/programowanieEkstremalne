package com.example.demo.fridgemanager.controller;

public class Error {
    private String field;
    private String message;

    public Error(String field, String message) {
        this.setField(field);
        this.setMessage(message);
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
