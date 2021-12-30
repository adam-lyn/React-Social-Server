package com.revature.follow.dtos;

import lombok.Data;

@Data
public class FollowerRequestDto {

    private String userId;
    private int followerNumber;

    public FollowerRequestDto(String userId, int followerNumber) {
        this.userId = userId;
        this.followerNumber = followerNumber;
    }
}
