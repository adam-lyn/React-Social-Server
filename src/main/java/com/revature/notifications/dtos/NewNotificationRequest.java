package com.revature.notifications.dtos;

import com.revature.notifications.notificationType.NotificationType;
import com.revature.users.User;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class NewNotificationRequest {
    //user getting notified, other user, type, timestamp
    private User owner;
    private User otherUser;
    //TODO is this supposed to be of type string instead of NotificationType?
    private NotificationType type_id;
    private Timestamp timestamp;

}
