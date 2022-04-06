package com.revature.notifications;

import com.revature.groups.Group;
import com.revature.posts.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, UUID> {
//    @Query("from notifications")
//    public List<Post> findPostsByGroupId(Group group);

}
