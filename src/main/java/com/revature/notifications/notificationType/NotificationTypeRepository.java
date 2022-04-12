package com.revature.notifications.notificationType;

import com.revature.notifications.Notification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationTypeRepository extends CrudRepository<NotificationType, String> {

}
