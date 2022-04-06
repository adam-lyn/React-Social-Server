package com.revature.notifications.dtos;

import com.revature.users.User;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class NewNotificationRequest {
    //user getting notified, other user, type, timestamp
    private String owner;
    private String otherUser;
    private String type;
    private Timestamp timestamp;

}
