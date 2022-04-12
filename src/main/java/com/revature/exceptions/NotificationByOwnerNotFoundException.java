package com.revature.exceptions;

public class NotificationByOwnerNotFoundException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Could not find notification with this owner!";
    }
}