package com.revature.users.dtos;

import com.revature.users.User;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class PicUrlDto implements Serializable {

    private final String profileId;

    private final String picCate;

    private final String picurl;

    public PicUrlDto(String picCate, String profileId, String picurl) {
        this.profileId = profileId;
        this.picCate = picCate;
        this.picurl = picurl;
    }
}
