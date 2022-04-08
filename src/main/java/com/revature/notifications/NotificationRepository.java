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
//    @Query("from notifications")
//    public List<Post> findPostsByGroupId(Group group);
@Query(value = "select * from notifications where notification_id = 'd5ERictvfpdyQ791YQ3dmnG2jQP2'", nativeQuery = true)
    Optional<Notification> findNotificationById(String id);
}
