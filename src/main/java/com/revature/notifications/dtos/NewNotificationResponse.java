package com.revature.notifications.dtos;

import com.revature.notifications.Notification;
import com.revature.notifications.notificationType.NotificationType;
import com.revature.users.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class NewNotificationResponse {
    private String id;
    private User owner;
    private NotificationType type_id;
    private LocalDateTime date;
    private User otherUser;

    public NewNotificationResponse(Notification notification) {
        this.id = notification.getId();
        this.owner = notification.getOwner();
        this.type_id = notification.getType_id();
        this.date = notification.getDate();
        this.otherUser = notification.getOtherUser();

    }

    @Override
    public String toString() {
        return "NotificationResponse{" +
                "id='" + id + '\'' +
                ", owner=" + owner +
                ", type_id=" + type_id +
                ", date=" + date +
                ", otherUser=" + otherUser +
                '}';
    }
}

