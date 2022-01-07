package com.revature.users.dtos;

import com.revature.users.User;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class PicUrlDto implements Serializable {

    private final String profileId;

    private final String picurl;

    public PicUrlDto(String profileId, String picurl) {
        this.profileId = profileId;
        this.picurl = picurl;
    }
}
