package com.revature;

import com.revature.notifications.notificationType.NotificationType;
import com.revature.notifications.notificationType.NotificationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ReverbAppDummy implements CommandLineRunner {
    private final NotificationTypeRepository notificationTypeRepository;

    @Autowired
    public ReverbAppDummy(NotificationTypeRepository notificationTypeRepository){
        this.notificationTypeRepository = notificationTypeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        NotificationType like = new NotificationType();
        like.setId("1");
        like.setTypeName("like");

        NotificationType comment = new NotificationType();
        comment.setId("2");
        comment.setTypeName("comment");
        System.out.println("AHHHHHHHHHHHHHHHHHHHHHHH: " + notificationTypeRepository.existsById("1"));
        if (!notificationTypeRepository.existsById("1")){
            notificationTypeRepository.save(like);
            notificationTypeRepository.save(comment);

        }
    }
}
