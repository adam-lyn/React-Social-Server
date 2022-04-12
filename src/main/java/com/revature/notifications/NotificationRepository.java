package com.revature.notifications;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, String> {

    Optional<Notification> findNotificationById(String id);

    @Query("SELECT n From Notification n WHERE n.isRead = false")
    Iterable<Notification> findNotificationByOwnerId(String owner_id_fk);

    @Modifying
    @Transactional
    @Query("UPDATE Notification SET isRead = true WHERE id = ?1")
    void setAsRead(String id);

}
