package com.revature.notifications.dtos;

import java.util.List;
import lombok.Data;

@Data
public class SetNotificationsReadRequest {
    private List<String> notificationIds;
}
