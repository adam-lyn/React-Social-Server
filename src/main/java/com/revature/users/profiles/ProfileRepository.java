package com.revature.users.profiles;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.revature.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, UUID> {
	Optional<Profile> getProfileByUser(User user);

	List<Profile> findByFirstNameIgnoreCaseContaining(String firstName);
//	List<Profile> findByLastNameIgnoreCaseContaining(String lastName);
//	List<Profile> findByFirstNameOrLastNameIgnoreCaseContaining(String name1,
//													  String name2 = null);
}
