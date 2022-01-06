package com.revature.search.dtos;

import com.revature.users.User;
import com.revature.users.profiles.Profile;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class SearchResponse {

    private final List<UserSearchResponse> responses;

    public SearchResponse(List<User> users) {
        this.responses = new ArrayList<UserSearchResponse>();
        for (User user : users) {
            UserSearchResponse resp =
                    new UserSearchResponse(user.getEmail(),
                            user.getId());
            this.responses.add(resp);
        }
    }

    @Data
    public static class UserSearchResponse {
        private final String email;
        private final String id;

        public UserSearchResponse(String email, String id) {
            this.email = email;
            this.id = id;
        }
    }
}
