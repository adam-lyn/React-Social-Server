package com.revature.notifications;

import com.revature.exceptions.*;
import com.revature.notifications.dtos.NewNotificationRequest;
import com.revature.notifications.dtos.NotificationResponse;
import com.revature.notifications.dtos.SetNotificationsReadRequest;
import com.revature.users.User;
import com.revature.users.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public NotificationService(NotificationRepository notificationRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    Predicate<String> notNullOrEmpty = str -> str != null && !str.equals("");

    /**
     * @return Returns all groups in database
     */
    public List<NotificationResponse> getAllNotifications() {
        List<NotificationResponse> notificationResponseList = new ArrayList<>();

        notificationRepository.findAll().iterator()
                .forEachRemaining(n -> notificationResponseList.add(new NotificationResponse(n)));

        return notificationResponseList;
    }

    /**
     * @param id - Id of notification to be fetched
     * @return - Response entity representing notification
     */
    public NotificationResponse getNotification(String id) {
//        UUID uuid = UUID.fromString(id);
        Notification notification = notificationRepository.findNotificationById(id)
                .orElseThrow(NotificationNotFoundException::new);
        return new NotificationResponse(notification);
    }


    /**
     * @param ownerId - Id of notification to be fetched
     * @return - Response entity representing notification
     */
    public List<NotificationResponse> getNotificationByOwner(String ownerId) {
        List<NotificationResponse> notificationResponseList = new ArrayList<>();

        notificationRepository.findNotificationByOwnerId(ownerId).iterator()
                .forEachRemaining(n -> notificationResponseList.add(new NotificationResponse(n)));

        return notificationResponseList;
    }



    /**
     * @param newNotificationRequest - contains notification Type_id of new notification and Other User.
     *
     */
    public void createNotification(NewNotificationRequest newNotificationRequest, User user) {

        if (!notNullOrEmpty.test(newNotificationRequest.getType_id().toString()))
            throw new InvalidRequestException("Invalid notification type entered");
        if (!notNullOrEmpty.test(newNotificationRequest.getOtherUserId()))
            throw new InvalidRequestException("Invalid notification Other User entered");
        Notification newNotification = new Notification();

        newNotification.setId(UUID.randomUUID().toString());
        newNotification.setIsRead(false);

        User owner = userRepository.getById(user.getId());
        newNotification.setOwner(owner);

        newNotification.setType_id(newNotificationRequest.getType_id());

        User otherUser = userRepository.getById(newNotificationRequest.getOtherUserId());
        newNotification.setOtherUser(otherUser);

        newNotification.setDate(LocalDateTime.now());

        notificationRepository.save(newNotification);
    }

    public void setNotificationToRead(SetNotificationsReadRequest request) {
        for (String id : request.getNotificationIds()) {
            notificationRepository.setAsRead(id);
        }
    }

    public void deleteNotification(String notificationId) {
//        UUID uuid = UUID.fromString(notificationId);
        Notification notification = notificationRepository.findNotificationById(notificationId).get();

        notificationRepository.delete(notification);
    }
}

