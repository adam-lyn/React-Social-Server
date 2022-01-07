package com.revature.users.profiles;

import com.revature.exceptions.ProfileNotFoundException;
import com.revature.exceptions.WrongUserException;
import com.revature.users.User;
import com.revature.users.UserRepository;
import com.revature.users.dtos.PicUrlDto;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProfileService {

    // dependency injection

    private final ProfileRepository profileRepo;
    private final UserRepository userRepository;

    public ProfileService(ProfileRepository profileRepository, UserRepository userRepository) {
        this.profileRepo = profileRepository;
        this.userRepository = userRepository;
    }


    /*  Parameters: Profile object, User object
        Updates profile in database if User is owner
        Returns the modified Profile
     */
    public Profile updateProfile(Profile profile, User user) throws WrongUserException {
        if (profile.getUser() == null) {
            profile.setUser(user);
        }

        if (profile.getUser().equals(user)) {
            return profileRepo.saveAndFlush(profile);
        } else {
            throw new WrongUserException();
        }
    }

    /*  test
     */
    public void test() {
        profileRepo.testCRUD();
    }

    /*  update pic url
     */
    public PicUrlDto updatePicUrl(String picCate, String savedURL, String s_profile_id, User user) throws WrongUserException {
        UUID profile_id;
        if (s_profile_id.equals("0")) {
            Profile profile2 = new Profile();
            Profile profile = profileRepo.saveAndFlush(profile2);
            profile_id = profile.getId();
//            profile.setUser(user);
            s_profile_id = profile_id.toString();

            Optional<User> dbUser = userRepository.findById(user.getId());
            if (!dbUser.isPresent()) {
                userRepository.save(user);
            }

        } else {
            profile_id = UUID.fromString(s_profile_id);
        }
        int rtn;
        if (picCate.equals("header")) {
            rtn = profileRepo.updateHeaderPicUrl(savedURL, s_profile_id, user.getId());
        } else {
            rtn = profileRepo.updateProfilePicUrl(savedURL, s_profile_id, user.getId());
        }
        if (rtn > 0) {
            return new PicUrlDto(picCate, s_profile_id, savedURL);
        } else {
            throw new WrongUserException();
        }
    }

    /*  Parameter: profileID
        Returns the specified Profile
     */
    public Profile findProfileById(UUID profileId) throws ProfileNotFoundException {
        Optional<Profile> profile = profileRepo.findById(profileId);

        if (profile.isPresent()) {
            return profile.get();
        } else {
            throw new ProfileNotFoundException();
        }
    }

    /*  Parameter: User object
        Returns the Profile of the provided User
     */
    public Profile findUsersProfile(User user) throws ProfileNotFoundException {
        Optional<Profile> profile = profileRepo.getProfileByUser(user);
        if (profile.isPresent()) {
            return profile.get();
        } else {
            throw new ProfileNotFoundException();
        }
    }

    public Boolean checkProfileOwnership(UUID id, User user) throws ProfileNotFoundException {
        Optional<Profile> profile = profileRepo.findById(id);

        if (profile.isPresent()) {
            return profile.get().getUser().equals(user);
        } else {
            throw new ProfileNotFoundException();
        }
    }
}
