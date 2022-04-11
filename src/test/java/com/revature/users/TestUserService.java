package com.revature.users;


import com.revature.exceptions.UserNotFoundException;
import com.revature.users.profiles.Profile;
import com.revature.users.profiles.ProfileRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TestUserService {

        private UserRepository mockUserRepo= mock(UserRepository.class);
        private ProfileRepository mockProfileRepo = mock(ProfileRepository.class);
        private UserService sut;

        @BeforeEach
        public void setup() {
            sut = new UserService(mockUserRepo, mockProfileRepo);
            System.out.println(sut);
        }
        @After
        public void cleanUp() {
            reset(mockUserRepo, mockProfileRepo);
        }


        @Test
        public void registerUserwithValidCreds(){
            UserService spiedSut = spy(sut);
            User user = new User();
            user.setId("2uid");
            user.setEmail("guydood@gmail.com");

            spiedSut.registerUser(user);
            verify(mockUserRepo, times(1)).save(any());
            verify(mockProfileRepo, times(1)).save(any());
        }


        @Test
        public void loginUser() {
            //tests the post does not exist exception
            User user = new User();
            Profile profile = new Profile();
            UUID id = UUID.randomUUID();

            Mockito.when(mockProfileRepo.findById(id)).thenReturn(Optional.empty());
            Mockito.when(mockUserRepo.save(user)).thenReturn(user);
            try {
                sut.loginUser(user);

            } catch (Exception e) {
                assertEquals(e.getClass(), UserNotFoundException.class);
            }
        }

}
