package com.myProject.expectionhandling;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
    }
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
