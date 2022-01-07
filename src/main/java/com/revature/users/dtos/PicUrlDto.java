package com.revature.users.dtos;

import com.revature.users.User;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class PicUrlDto implements Serializable {

    private final UUID profileId;

    private final String picurl;

    public PicUrlDto(UUID profileId, String picurl) {
        this.profileId = profileId;
        this.picurl = picurl;
    }
}
