package com.revature.notifications;

import com.revature.groups.GroupService;
import com.revature.groups.dtos.GroupCreationRequest;
import com.revature.groups.dtos.GroupResponse;
import com.revature.notifications.dtos.NewNotificationRequest;
import com.revature.notifications.dtos.NotificationResponse;
import com.revature.users.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
//    @GetMapping(value = "/d5ERictvfpdyQ791YQ3dmnG2jQP2", produces = "application/json")
    @GetMapping(value = "/d5ERictvfpdyQ791YQ3dmnG2jQP2", produces = "application/json")
    public ResponseEntity<NotificationResponse> getOneNotification() {
        System.out.println("Aidan smells");
        return ResponseEntity.ok(notificationService.getNotification("d5ERictvfpdyQ791YQ3dmnG2jQP2"));
    }

    // Create Notification
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/create", consumes = "application/json")
    public NotificationResponse createNotification(
            @RequestBody NewNotificationRequest newNotificationRequest,
            @AuthenticationPrincipal User owner) {
        return notificationService.createNotification(newNotificationRequest, owner);
    }

    // delete notification
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public void deleteNotification(@PathVariable String id) {
        notificationService.deleteNotification(id);
    }
}
