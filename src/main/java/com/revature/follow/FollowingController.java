package com.revature.follow;

import com.revature.users.User;
import com.revature.users.UserRepository;
import com.revature.users.dtos.ProfileResponse;
import com.revature.users.profiles.Profile;
import com.revature.users.profiles.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/user")
public class FollowingController {

    private final FollowingService followingService;
    private final UserRepository userRepository;
    private final ProfileService profileService;
//    private User currentUser;

    @Autowired
    public FollowingController(FollowingService followingService, UserRepository userRepository, ProfileService profileService) {
        this.followingService = followingService;
        this.userRepository = userRepository;
        this.profileService = profileService;
    }

    //get user id from profile id
    @GetMapping(path = "/profile/{profileId}")
    public String getUserFromProfileId(@PathVariable String profileId) {
        User user = followingService.getUserFromProfile(profileId);
        return user.getId();
    }

    // Gets the current user's followed users.
    @GetMapping(path = "/get-followings")
    public ResponseEntity<List<User>> getListOfFollowings(@AuthenticationPrincipal User currentUser) {
        return ResponseEntity.ok(followingService.getFollowings(currentUser));
    }

    // Get the followers of the current user
    @GetMapping(path = "/get-owner-followers")
    public ResponseEntity<List<User>> getListOfFollowers(@AuthenticationPrincipal User currentUser) {
        //User followers = userRepository.getById(currentUser.getId());
        return ResponseEntity.ok(followingService.getFollowers(currentUser));
    //    return ResponseEntity.ok(followingService.getFollowers(currentUser));
    }

    // Get the number of followers of the user in the path variable
    @GetMapping(path = "/get-followers/{userId}")
    public Integer getFollowerNumber(@PathVariable String userId){
        User user = userRepository.getById(userId);
        return user.getFollower().size();
    }

    // Gets the number of followed accounts of the given user
    @GetMapping(path = "/get-following-num/{userId}")
    public Integer getFollowingNumber(@PathVariable String userId){
        User user = userRepository.getById(userId);
        return user.getFollowing().size();
    }


    // Get the number of followers for the logged in user
    @GetMapping(path = "/get-follower-number")
    public ResponseEntity<Integer> getNumberOfFollowers(@AuthenticationPrincipal User currentUser) {
        return ResponseEntity.ok(followingService.getFollowerNumber(currentUser));
    }

    // Tests if the current user can follow the given user
    @GetMapping(path = "/can-follow/{followUserId}")
    public boolean canFollow(@PathVariable String followUserId,@AuthenticationPrincipal User currentUser) {
        // See if they're trying to follow someone they're already following
        List<User> following = userRepository.getById(currentUser.getId()).getFollowing();
        if (following != null)
        {
            for (int i = 0; i < following.size(); i++) {
                if (following.get(i).getId().equals(followUserId)) {
                    return false;
                }
            }
        }
        return true;
    }

    // Adds the logged in user as a follower to the provided user.
    @PutMapping(path = "/follow-user/{followUserId}")
    public void followUser(@PathVariable String followUserId, @AuthenticationPrincipal User currentUser) {

        try {
            followingService.followUser(currentUser, followUserId);
            ResponseEntity.ok();
        }
        catch(Exception e) {
            e.printStackTrace();
            ResponseEntity.internalServerError().build();
        }
    }

    // Removes the logged in user as a follower to the provided user.
    @DeleteMapping(path = "/unfollow-user/{unfollowUserId}")
    public void unfollowUser(@PathVariable String unfollowUserId, @AuthenticationPrincipal User currentUser) {

        try {
            followingService.unFollowUser(currentUser, unfollowUserId);
            ResponseEntity.ok();
        }
        catch(Exception e) {
            e.printStackTrace();
            ResponseEntity.internalServerError().build();
        }
    }

    // this method will return FollowerResponse so we can access their name, img_url etc..
    @GetMapping(path="/getFollowerProfilesByUserId/{userId}")
    public List<FollowerResponse> getFollowerProfilesByUserId(@PathVariable String userId){
        User user = userRepository.findUserById(userId);
        List<User> followers = followingService.getFollowers(user);

        // for loop to get profile for each user in followers list
        List<FollowerResponse> followerProfiles = new ArrayList<>();
        for (User follower: followers){
            System.out.println(follower);
            Profile profile = profileService.findUsersProfile(follower);
            followerProfiles.add(new FollowerResponse(profile.getFirstName(), profile.getLastName(),
                    profile.getUser().getEmail(), profile.getProfileImg()));

        }
        System.out.println("FOLLOWER RESPONSE PROFILES " + followerProfiles);
        return followerProfiles;
    };

    @GetMapping(path="/getFollowingProfilesByUserId/{userId}")
    public List<FollowerResponse> getFollowingProfilesByUserId(@PathVariable String userId){
        User user = userRepository.findUserById(userId);
        List<User> followings = followingService.getFollowings(user);

        // for loop to get profile for each user in followers list
        List<FollowerResponse> followingProfiles = new ArrayList<>();
        for (User following: followings){
            System.out.println(following);
            Profile profile = profileService.findUsersProfile(following);
            followingProfiles.add(new FollowerResponse(profile.getFirstName(), profile.getLastName(),
                    profile.getUser().getEmail(), profile.getProfileImg()));

        }
        System.out.println("FOLLOWER RESPONSE PROFILES " + followingProfiles);
        return followingProfiles;
    };

}

