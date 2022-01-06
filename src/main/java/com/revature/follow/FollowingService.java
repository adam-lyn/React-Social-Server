package com.revature.follow;

import com.revature.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.revature.users.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class FollowingService {

    //TODO: create isValidUser helper method to clean up code

    private final UserRepository userRepository;
    private FollowRepository followRepository;

    //constructor
    @Autowired
    public FollowingService(UserRepository userRepository, FollowRepository followRepository) {
        this.userRepository = userRepository;
        this.followRepository = followRepository;
    }



    //TODO: Get follower number
    public int getFollowerNumber(User user) {
        return user.getFollower().size();
    }

    //TODO: Get following number
    public int getFollowingNumber(User user) {
        return user.getFollowing().size();
    }

    //TODO: Get list of followers given a specific user id
    public List<User> getFollowers(User user) {
        return user.getFollower();
    }

    //TODO: get list of followings given a specific user id
    public List<User> getFollowings(User user) {
        return user.getFollowing();
    }

    //TODO: update following-follower table after a user follows/unfollows someone
    //Method to allow a user to follow another user
    public boolean followUser(User currentUser, String followUserId) {
            currentUser = userRepository.findById(currentUser.getId()).get();
            List<User> followingList = currentUser.getFollowing();
            User followedUser = userRepository.findById(followUserId).get();
            for(int i = 0; i < followingList.size(); i++) {
                if (followingList.get(i).equals(followedUser)) {
                    return false; //TODO: change so that it throws custom error instead
                }
            }
            if (followUserId == null) {
                return false; //TODO: change to exception (Enter invalid followUser)
            }
            followingList.add(followedUser);
            currentUser.setFollowing(followingList);
            userRepository.save(currentUser);
            return true;
    }

    // Method to allow a user to unfollow another user
    public boolean unFollowUser(User currentUser, String unFollowUserId) {
        currentUser = userRepository.findById(currentUser.getId()).get();
        List<User> followingList = currentUser.getFollowing();
        User unFollowedUser = userRepository.findById(unFollowUserId).get();

        if(followingList.contains(unFollowedUser)){
            followingList.remove(unFollowedUser);
            currentUser.setFollowing(followingList);
            userRepository.save(currentUser);
            return true;
        }

        if (unFollowUserId == null) {
            return false; //TODO: change to exception (Enter invalid followUser)
        }

        return false;
    }



    public List<User> getFollowers() {return followRepository.findAll();}

}
