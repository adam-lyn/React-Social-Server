package com.revature.follow.dtos;
import lombok.Data;

@Data
public class FollowingRequestDto {

    private String userId;
    private int followingNumber;

    public FollowingRequestDto(String userId, int followingNumber) {
        this.userId = userId;
        this.followingNumber = followingNumber;
    }
}
