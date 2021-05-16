package com.example.demo.fridgemanager.exceptions;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(Class cls,Long id) {
        super("Entity " + cls.getSimpleName() + " with id = " + id + " not found");
    }
}
