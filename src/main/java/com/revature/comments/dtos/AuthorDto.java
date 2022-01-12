package com.revature.comments.dtos;

import com.revature.users.User;
import com.revature.users.profiles.Profile;
import com.revature.users.profiles.ProfileRepository;
import lombok.Data;

import java.util.Optional;

@Data
public class AuthorDto {
    private String id;
    private String firstname;
    private String lastname;
    private String pfId;

    //TODO: Refactor to not use Repo fetch logic here, but in the service class. *from Wezley*
    public AuthorDto (User raw, ProfileRepository profileRepository) {
        // Set the user id from the given user
        this.id = raw.getId();
        //set Author


        Optional<Profile> optionalProfile = profileRepository.getProfileByUser(raw);
        if (optionalProfile.isPresent()){
            Profile rawProf = optionalProfile.get();
            this.firstname = rawProf.getFirstName();
            this.lastname = rawProf.getLastName();
            this.pfId = rawProf.getId().toString();
        }
        else {
            this.firstname = null;
            this.lastname = null;
            this.pfId = null;
        }




    }

    public AuthorDto(Profile authorProfile) {
        this.id = authorProfile.getUser().getId();
        this.firstname = authorProfile.getFirstName();
        this.lastname = authorProfile.getLastName();
        this.pfId = authorProfile.getId().toString();
    }

}
