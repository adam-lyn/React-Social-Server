package com.revature.follow;

import lombok.Data;

@Data
public class FollowerResponse {

    private String firstName;
    private String lastName;
    private String email;
    private String profilePicURL;

    public FollowerResponse(String firstName, String lastName, String email, String profilePicURL) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.profilePicURL = profilePicURL;
    }
}
