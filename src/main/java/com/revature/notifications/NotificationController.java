package com.revature.notifications;

import com.revature.notifications.dtos.NewNotificationRequest;
import com.revature.notifications.dtos.NotificationResponse;
import com.revature.notifications.dtos.SetNotificationsReadRequest;
import com.revature.users.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin
public class NotificationController {


    private final NotificationService notificationService;

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
    @GetMapping(value = "/{id}", produces = "application/json")
    public NotificationResponse getOneNotification(@PathVariable String id) {
        return notificationService.getNotification(id);
    }

    //Get all Notification by Owner
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/owner/{ownerId}", produces = "application/json")
    public List<NotificationResponse> getNotificationByOwner(@PathVariable String ownerId) {
        return notificationService.getNotificationByOwner(ownerId);
    }

    // Create Notification
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/create", consumes = "application/json")
    public void createNotification(@RequestBody NewNotificationRequest newNotificationRequest, @AuthenticationPrincipal User user) {

        notificationService.createNotification(newNotificationRequest, user);
    }

    // Set notification to read
    @PutMapping(value="/read")
    public void setNotificationsToRead(@RequestBody SetNotificationsReadRequest request) {
        notificationService.setNotificationToRead(request);
    }

    // delete notification
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public void deleteNotification(@PathVariable String id) {
        notificationService.deleteNotification(id);
    }
}
