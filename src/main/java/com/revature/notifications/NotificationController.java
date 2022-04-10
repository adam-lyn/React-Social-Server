package com.revature.notifications;

import com.revature.groups.GroupService;
import com.revature.groups.dtos.GroupCreationRequest;
import com.revature.groups.dtos.GroupResponse;
import com.revature.notifications.dtos.NewNotificationRequest;
import com.revature.notifications.dtos.NewNotificationResponse;
import com.revature.notifications.dtos.NotificationResponse;
import com.revature.users.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/notification")
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
    public NewNotificationResponse createNotification(
            @RequestBody NewNotificationRequest newNotificationRequest) {
        System.out.println("whole thing" + newNotificationRequest);
        System.out.println("Type Id" + newNotificationRequest.getType_id());
        System.out.println("other user" + newNotificationRequest.getOtherUser());
        return notificationService.createNotification(newNotificationRequest);
    }

    // delete notification
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public void deleteNotification(@PathVariable String id) {
        notificationService.deleteNotification(id);
    }
}
