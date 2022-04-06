package com.revature.notifications;

import com.revature.groups.GroupService;
import com.revature.groups.dtos.GroupCreationRequest;
import com.revature.groups.dtos.GroupResponse;
import com.revature.users.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {


    private NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // Get all Notifications
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = "application/json")
    public List<NotificationResponse> getAllNotifications() {
        return notificationService.getAllNotifications();
    }

    //Get One Notification
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/{notificationName}", produces = "application/json")
    public NotificationResponse getOneNotification(@PathVariable String id) {
        return notificationService.getNotification(id);
    }

    // Create Notification
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/create", consumes = "application/json")
    public GroupResponse createNotification(
            @RequestBody NotificationCreationRequest notificationCreationRequest,
            @AuthenticationPrincipal User owner) {
        return notificationService.createNotification(notificationCreationRequest, owner);
    }

    // delete notification
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public void deleteNotification(@PathVariable String id) {
        notificationService.deleteNotification(id);
    }
}
