package com.revature.notifications;

import com.revature.groups.Group;
import com.revature.posts.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, String> {

    Optional<Notification> findNotificationById(String id);

    Iterable<Notification> findNotificationByOwnerId(String owner_id_fk);

}
