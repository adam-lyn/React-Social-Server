package com.revature.users;

import com.revature.ReverbApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;



@SpringBootTest(classes = ReverbApplication.class)
@ActiveProfiles("test")
public class TestUserControllerIntegration {

	
	@Autowired
	private UserController userctrl;

	 @Test
	 public void loginUserTest() throws Exception {

		 User u=new User("d921e5f2-86cb-4a0f-abd9-b9ec4aafa3c5","dev4@dev.com", null,null, null);
		 assertThat((userctrl.loginUser(u)).getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
	 }
	 
	 @Test
	 public void registerUserTest() throws Exception {

		 User u=new User("d921e5f2-86cb-4a0f-abd9-b9ec4aafa3c5","dev4@dev.com", null,null, null);
		 assertThat(userctrl.registerUser(u).getStatusCode()).isEqualByComparingTo(HttpStatus.OK);
	 }


}