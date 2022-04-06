package com.revature.notifications;

import com.revature.exceptions.*;
import com.revature.groups.Group;
import com.revature.groups.GroupRepository;
import com.revature.groups.dtos.GroupCreationRequest;
import com.revature.groups.dtos.GroupResponse;
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
     * @param notificationName - name of notification to be fetched
     * @return - Response entity representing notification
     */
    public NotificationResponse getNotification(String id) {
        Notification notification = notificationRepository.findNotificationByName(id)
                .orElseThrow(NotificationNotFoundException::new);
        return new NotificationResponse(notification);
    }

    /**
     * @param notificationCreationRequest - contains notification Type_id of new notification and Other User.
     * @param owner - Currently logged in user gets set as owner.
     */
    public NotificationResponse createNotification(NotificationCreationRequest notificationCreationRequest, User owner) {

        if (!notNullOrEmpty.test(notificationCreationRequest.getType_id()))
            throw new InvalidRequestException("Invalid notification type entered");

        Notification newNotification = new Notification();

        newNotification.setOwner(owner);
        newNotification.setType_id(notificationCreationRequest.getType_id());
        newNotification.setOtherUser(notificationCreationRequest.getOtherUser());
        //TODO: maybe generate timestamp here?


        return new NotificationResponse(notificationRepository.save(newNotification));
    }

    public void deleteNotification(String notificationId) {
        Notification notification = notificationRepository.findNotificationById(notificationId).get();

        notificationRepository.delete(notification);
    }
}

