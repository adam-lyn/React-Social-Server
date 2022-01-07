package com.revature.users.profiles;

import com.revature.exceptions.ProfileNotFoundException;
import com.revature.users.User;
import com.revature.users.dtos.PicUrlDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@RequestMapping("/storage")
public class BucketController {

    private AmazonClientService amazonClient;
    private ProfileService profileService;

    @Autowired
    BucketController(AmazonClientService amazonClient, ProfileService profileService) {
        this.amazonClient = amazonClient;
        this.profileService = profileService;
    }

//    @PostMapping(path = "/uploadfile")
//    public String uploadFile(@ModelAttribute Profile profile,  @RequestPart(value = "file") MultipartFile file, @AuthenticationPrincipal User user) {
//
//        return this.amazonClient.uploadFile(file);
//    }

    @PostMapping(path = "/uploadfile")
    public PicUrlDto uploadFile(@RequestPart(value = "file") MultipartFile file, @RequestPart(value = "picCate") String picCate,
                                @RequestPart(value = "profileId") String profileId, @AuthenticationPrincipal User user) throws ProfileNotFoundException {

        String savedURL = this.amazonClient.uploadFile(file);
        try {
            return profileService.updatePicUrl(picCate, savedURL, profileId, user);
        } catch (ProfileNotFoundException e) {
            throw new ProfileNotFoundException();
        }
    }

    @DeleteMapping("/deleteFile")
    public String deleteFile(@RequestPart(value = "url") String fileUrl) {
        return this.amazonClient.deleteFileFromS3Bucket(fileUrl);
    }

    @PostMapping(path = "/test")
    public ResponseEntity<String> loginUser(@AuthenticationPrincipal User user) {
        System.out.println("Hitting Testing Endpoint");
        return ResponseEntity.ok("Successfully added user with email " + user.getEmail());
    }

}