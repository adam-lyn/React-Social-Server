package com.revature.notifications;

import com.revature.exceptions.*;
import com.revature.groups.Group;
import com.revature.groups.GroupRepository;
import com.revature.groups.dtos.GroupCreationRequest;
import com.revature.groups.dtos.GroupResponse;
import com.revature.notifications.dtos.NewNotificationRequest;
import com.revature.notifications.dtos.NotificationResponse;
import com.revature.users.User;
import com.revature.users.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
        System.out.println("checking the Id:" + id);
        Notification notification = notificationRepository.findNotificationById(id)
                .orElseThrow(NotificationNotFoundException::new);
        System.out.println(notification);
        System.out.println(new NotificationResponse(notification));
        return new NotificationResponse(notification);
    }

    /**
     * @param newNotificationRequest - contains notification Type_id of new notification and Other User.
     * @param owner - Currently logged in user gets set as owner.
     */
    public NotificationResponse createNotification(NewNotificationRequest newNotificationRequest, User owner) {

        if (!notNullOrEmpty.test(newNotificationRequest.getType_id().toString()))
            throw new InvalidRequestException("Invalid notification type entered");

        Notification newNotification = new Notification();

        newNotification.setOwner(owner);
        newNotification.setType_id(newNotificationRequest.getType_id());
        newNotification.setOtherUser(newNotificationRequest.getOtherUser());
        //TODO: maybe generate timestamp here?


        return new NotificationResponse(notificationRepository.save(newNotification));
    }

    public void deleteNotification(String notificationId) {
        Notification notification = notificationRepository.findNotificationById(notificationId).get();

        notificationRepository.delete(notification);
    }
}

