package com.revature.search;

import com.revature.users.User;
import com.revature.users.UserRepository;
import com.revature.users.profiles.Profile;
import com.revature.users.profiles.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    private final ProfileRepository profileRepository;
//    private final GroupRepository groupRepository;

    @Autowired
    public SearchService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public String formatString(String string) {
        return string.trim().toUpperCase();
    }

    public List<Profile> getUsersBySearch(String string) {
        return profileRepository.findByFirstNameIgnoreCaseContaining(formatString(string));
    }
}