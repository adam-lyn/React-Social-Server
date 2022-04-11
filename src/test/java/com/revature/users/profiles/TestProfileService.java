package com.revature.users.profiles;
import com.revature.exceptions.WrongUserException;
import com.revature.users.User;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import com.revature.ReverbApplication;
import com.revature.exceptions.UserNotFoundException;


@SpringBootTest(classes = ReverbApplication.class)
@ActiveProfiles("test")
public class TestProfileService 
{
	
	private ProfileService sut;
	private ProfileRepository profileRepository;

	@BeforeEach
	public void setup()
	{
		//mocks the repositories for each test
		profileRepository = mock(ProfileRepository.class);
		sut = new ProfileService(profileRepository);
	}
	
	@Test
	public void findProfileByIdPositive() throws UserNotFoundException {
		Profile profile = new Profile();
		when(profileRepository.findById(UUID.fromString("d921e5f2-86cb-4a0f-abd9-b9ec4aafa3c5"))).thenReturn(Optional.of(profile));
		ProfileService profileService=new ProfileService(profileRepository);
		assertThat(profileService.findProfileById(UUID.fromString("d921e5f2-86cb-4a0f-abd9-b9ec4aafa3c5"))).isEqualTo(profile);
	}

	@Test
	public void findProfileByIdNegative() 
	{
		
		Profile profile = new Profile();
		when(profileRepository.findById(UUID.fromString("d921e5f2-86cb-4a0f-abd9-b9ec4aafa3c5"))).thenReturn(Optional.empty());
		ProfileService profileService=new ProfileService(profileRepository);
		
		try {
			profileService.findProfileById(UUID.fromString("d921e5f2-86cb-4a0f-abd9-b9ec4aafa3c5"));
			fail();
		} catch (Exception e) {
			assertEquals(e.getClass(), UserNotFoundException.class);
		}
	}

	@Test
	public void findProfileByUser() throws UserNotFoundException {
		User u=new User();
		Profile profile = new Profile();
		when(profileRepository.getProfileByUser(u)).thenReturn(Optional.of(profile));
		ProfileService profileService=new ProfileService(profileRepository);
		assertThat(profileService.findUsersProfile(u)).isEqualTo(profile);
	}

	@Test
	public void checkProfileOwner() throws UserNotFoundException {
		User u=new User();
		Profile profile = new Profile();
		profile.setUser(u);
		when(profileRepository.findById(UUID.fromString("d921e5f2-86cb-4a0f-abd9-b9ec4aafa3c5"))).thenReturn(Optional.of(profile));
		ProfileService profileService=new ProfileService(profileRepository);
		assertThat(profileService.checkProfileOwnership(UUID.fromString("d921e5f2-86cb-4a0f-abd9-b9ec4aafa3c5"),u)).isTrue();
	}

	@Test
	public void UpdateProfileWithMatchingUserProfile() throws WrongUserException{
		User myuser = new User();
		myuser.setId("2uid");
		myuser.setEmail("guydood@gmail.com");
		Profile profile = new Profile();
		profile.setUser(myuser);


		when(profileRepository.saveAndFlush(any())).thenReturn((profile));

		assertNotNull(sut.updateProfile(profile, myuser));
	}

	@Test
	public void UpdateProfileWithWrongUserProfile() throws WrongUserException{
		User myuser = new User();
		myuser.setId("2uid");
		myuser.setEmail("guydood@gmail.com");
		User wronguser = new User();
		myuser.setId("3uid");
		myuser.setEmail("3guydood@gmail.com");
		Profile profile = new Profile();
		profile.setUser(wronguser);


		when(profileRepository.saveAndFlush(any())).thenReturn((profile));

		try {
			sut.updateProfile(profile, myuser);
		}catch (Exception e){
			assertEquals(e.getClass(), WrongUserException.class);
		}

	}
}
