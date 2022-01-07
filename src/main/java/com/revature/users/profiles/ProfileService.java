package com.revature.users.profiles;

import com.revature.exceptions.ProfileNotFoundException;
import com.revature.exceptions.WrongUserException;
import com.revature.users.User;
import com.revature.users.dtos.PicUrlDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public PicUrlDto updatePicUrl(String picCate, String savedURL, String profile_id, User user) throws ProfileNotFoundException {
        profileRepo.updatePicUrl(picCate, savedURL, profile_id,user.getId());
        if (profile_id.equals("0")) {
            Profile profile = new Profile();
            profile = profileRepo.saveAndFlush(profile);
            profile_id = profile.getId();
        }

//        if (rtnUrl.isPresent()) {
            return new PicUrlDto(profile_id, savedURL);
//        } else {
//            throw new ProfileNotFoundException();
//        }
    }

    /*  Parameter: profileID
        Returns the specified Profile
     */
    public Profile findProfileById(int profileId) throws ProfileNotFoundException {
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

    public Boolean checkProfileOwnership(int id, User user) throws ProfileNotFoundException {
        Optional<Profile> profile = profileRepo.findById(id);

        if (profile.isPresent()) {
            return profile.get().getUser().equals(user);
        } else {
            throw new ProfileNotFoundException();
        }
    }
}
