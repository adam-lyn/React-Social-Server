package com.revature.exceptions;

public class NotificationNotFoundException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Could not find notification!";
    }
}