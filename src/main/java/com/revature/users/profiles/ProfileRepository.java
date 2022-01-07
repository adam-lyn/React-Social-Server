package com.revature.users.profiles;

import java.util.Optional;

import com.revature.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {
	Optional<Profile> getProfileByUser(User user);

	@Modifying
	@Query(value = "update user_profile up set up.profile_img = ':url', up.user_id_fk = :user_id where up.id = ':profile_id'", nativeQuery = true)
	void updatePicUrl(String picCate, String url, String profile_id, String user_id);
}
