package com.revature.users.profiles;

import com.revature.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, UUID> {
    Optional<Profile> getProfileByUser(User user);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Modifying
    @Query(value = "update user_profile up set up.header_img = :url, up.user_id_fk = :user_id where up.id = :profile_id", nativeQuery = true)
    int updateHeaderPicUrl(String url, String profile_id, String user_id);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Modifying
    @Query(value = "update user_profile up set up.profile_img = :url, up.user_id_fk = :user_id where up.id = :profile_id", nativeQuery = true)
    int updateProfilePicUrl(String url, String profile_id, String user_id);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Modifying
    @Query(value = "update user_profile up set up.profile_img = ':url' where up.id = 'd921e5f2-86cb-4a0f-abd9-b9ec4aafa3c5';", nativeQuery = true)
    void testCRUD();
}
