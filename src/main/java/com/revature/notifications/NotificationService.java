package com.revature.notifications;

import com.revature.exceptions.*;
import com.revature.notifications.dtos.NewNotificationRequest;
import com.revature.notifications.dtos.NewNotificationResponse;
import com.revature.notifications.dtos.NotificationResponse;
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
    public NotificationResponse getNotificationByOwner(String ownerId) {
        Notification notification = notificationRepository.findNotificationByOwnerId(ownerId)
                .orElseThrow(NotificationByOwnerNotFoundException::new);
        return new NotificationResponse(notification);
    }



    /**
     * @param newNotificationRequest - contains notification Type_id of new notification and Other User.
     *
     */
    public NewNotificationResponse createNotification(NewNotificationRequest newNotificationRequest) {

        if (!notNullOrEmpty.test(newNotificationRequest.getType_id().toString()))
            throw new InvalidRequestException("Invalid notification type entered");
        if (!notNullOrEmpty.test(newNotificationRequest.getOtherUser().toString()))
            throw new InvalidRequestException("Invalid notification Other User entered");
        System.out.println("here 1");
        Notification newNotification = new Notification();
        System.out.println("here 2");

        newNotification.setId(UUID.randomUUID().toString());
        newNotification.setOwner(newNotificationRequest.getOwner());
        newNotification.setType_id(newNotificationRequest.getType_id());
        newNotification.setOtherUser(newNotificationRequest.getOtherUser());
        newNotification.setDate(LocalDateTime.now());
        //TODO: maybe generate timestamp here?
        NewNotificationResponse tasdt = new NewNotificationResponse(notificationRepository.save(newNotification));
        System.out.println(tasdt);
        System.out.println("here 3");
        return new NewNotificationResponse(notificationRepository.save(newNotification));
    }

    public void deleteNotification(String notificationId) {
//        UUID uuid = UUID.fromString(notificationId);
        Notification notification = notificationRepository.findNotificationById(notificationId).get();

        notificationRepository.delete(notification);
    }
}

