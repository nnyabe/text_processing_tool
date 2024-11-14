package com.example.library_management_system.exceptions;

public class InvalidRoleExeption extends RuntimeException {
    public InvalidRoleExeption(String message) {
        super(message);
    }

    public InvalidRoleExeption(String message, Throwable cause){
        super(message, cause);
    }
}


