package com.example.library_management_system.exceptions;

public class MySQLUserNotFound extends Exception {
    public MySQLUserNotFound(String message) {
        super(message);
    }

    public MySQLUserNotFound(String message, Throwable cause){
      super(message, cause);
    }
}
