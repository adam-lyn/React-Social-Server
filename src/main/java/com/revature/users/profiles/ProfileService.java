package com.revature.users.profiles;

import com.revature.exceptions.ProfileNotFoundException;
import com.revature.exceptions.WrongUserException;
import com.revature.users.User;
import com.revature.users.dtos.PicUrlDto;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProfileService {

    // dependency injection

    private final ProfileRepository profileRepo;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepo = profileRepository;
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

    /*  update pic url
     */
    public PicUrlDto updatePicUrl(String picCate, String savedURL, String s_profile_id, User user) throws ProfileNotFoundException {
        UUID profile_id;
        if (s_profile_id.equals("0")) {
            Profile profile2 = new Profile();
            Profile  profile = profileRepo.saveAndFlush(profile2);
            profile_id = profile.getId();
//            profile.setUser(user);
            s_profile_id = profile_id.toString();
        }else {
            profile_id = UUID.fromString(s_profile_id);
        }
        int rtn = profileRepo.updatePicUrl(savedURL, s_profile_id, user.getId());

//        if (rtnUrl.isPresent()) {
        return new PicUrlDto(s_profile_id, savedURL);
//        } else {
//            throw new ProfileNotFoundException();
//        }
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
