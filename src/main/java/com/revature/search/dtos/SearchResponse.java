package com.revature.search.dtos;

import com.revature.users.profiles.Profile;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SearchResponse {

    private final List<ProfileSearchResponse> responses;

    public SearchResponse(List<Profile> profiles) {
        this.responses = new ArrayList<ProfileSearchResponse>();
        for (Profile profile : profiles) {
            ProfileSearchResponse resp =
                    new ProfileSearchResponse(profile.getFirst_name(),
                            profile.getLast_name());
            this.responses.add(resp);
        }
    }

    @Data
    static class ProfileSearchResponse {
        private final String firstName;
        private final String lastName;

        public ProfileSearchResponse(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }
}
