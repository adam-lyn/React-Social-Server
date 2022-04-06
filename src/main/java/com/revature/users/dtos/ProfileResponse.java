package com.revature.users.dtos;

import com.revature.users.User;
import com.revature.users.UserRepository;
import com.revature.users.profiles.Profile;
import com.revature.users.profiles.ProfileRepository;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
public class ProfileResponse {
    private String id;
    private String first_name;
    private String last_name;
    private String birthday;
    private String hobby;
    private String location;
    private String profile_img;
    private String header_img;
    private String about_me;
    private String user_id;
    private Integer follower_num;
    private Integer following_num;
    private List<UserDto> followers;

    public ProfileResponse(Profile raw){
        this.id = raw.getId().toString();
        this.first_name = raw.getFirstName();
        this.last_name = raw.getLastName();
        this.birthday = raw.getBirthday();
        this.hobby = raw.getHobby();
        this.location = raw.getLocation();
        this.profile_img = raw.getProfileImg();
        this.header_img = raw.getHeaderImg();
        this.about_me = raw.getAboutMe();
        this.user_id = raw.getUser().getId();
        this.follower_num = raw.getUser().getFollower().size();
        this.following_num = raw.getUser().getFollowing().size();
        this.followers = raw.getUser().getFollower().stream().map(UserDto::new).collect(Collectors.toList());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProfile_img() {
        return profile_img;
    }

    public void setProfile_img(String profile_img) {
        this.profile_img = profile_img;
    }

    public String getHeader_img() {
        return header_img;
    }

    public void setHeader_img(String header_img) {
        this.header_img = header_img;
    }

    public String getAbout_me() {
        return about_me;
    }

    public void setAbout_me(String about_me) {
        this.about_me = about_me;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Integer getFollower_num() {
        return follower_num;
    }

    public void setFollower_num(Integer follower_num) {
        this.follower_num = follower_num;
    }

    public Integer getFollowing_num() {
        return following_num;
    }

    public void setFollowing_num(Integer following_num) {
        this.following_num = following_num;
    }


}
