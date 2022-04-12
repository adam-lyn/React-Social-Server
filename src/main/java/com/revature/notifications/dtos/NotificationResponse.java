package com.revature.notifications.dtos;

import com.revature.notifications.Notification;
import com.revature.notifications.notificationType.NotificationType;
import com.revature.users.User;
import com.revature.users.dtos.UserDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class NotificationResponse {
    private String id;
    private Boolean isRead;
    private UserDto owner;
    private NotificationType type_id;
    private LocalDateTime date;
    private UserDto otherUser;

    public NotificationResponse(Notification notification) {
        this.id = notification.getId();
        this.isRead = notification.getIsRead();
        this.owner = new UserDto(notification.getOwner());
        this.type_id = notification.getType_id();
        this.date = notification.getDate();
        this.otherUser = new UserDto(notification.getOtherUser());
    }
}
