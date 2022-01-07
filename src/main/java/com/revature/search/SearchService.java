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

    private final UserRepository userRepository;
//    private final GroupRepository groupRepository;

    @Autowired
    public SearchService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String formatString(String string) {
        return string.trim();
    }

    public List<User> getUsersBySearch(String string) {
        return userRepository.findUsersByEmailContains(formatString(string));
    }
}