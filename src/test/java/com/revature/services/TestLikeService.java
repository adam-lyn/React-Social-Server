package com.revature.services;

import com.revature.models.Comment;
import com.revature.models.Like;
import com.revature.models.Post;
import com.revature.models.Profile;
import com.revature.models.User;
import com.revature.repositories.LikeRepository;
import com.revature.repositories.PostRepository;
import com.revature.repositories.UserRepository;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.assertj.core.api.Fail;
import org.hibernate.annotations.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.revature.ReverbApplication;
import com.revature.exceptions.PostNotFoundException;

@SpringBootTest(classes = ReverbApplication.class)
@RunWith(SpringRunner.class)
// @ActiveProfiles("test")
public class TestLikeService {

	private LikeRepository likeRepository;

	private PostRepository postRepository;

	@BeforeEach
	public void Setup() {
		//mocks the repositories for each test
		postRepository = Mockito.mock(PostRepository.class);
		likeRepository = Mockito.mock(LikeRepository.class);

	
	}
	
	@Test
	public void getNumberofLikesPositive() throws Exception{
		//tests getNumberofLikes for no likes on a new post
		Post post = new Post();

		Mockito.when(postRepository.findById(8L)).thenReturn(Optional.of(post));
		Mockito.when(likeRepository.getLikeByPost(post)).thenReturn(new ArrayList<Like>());

		LikeService likeService = new LikeService(postRepository, likeRepository);

		assertThat(likeService.getNumberofLikes(8L)).isEqualTo(0);
	}

	@Test
	public void getNumberofLikesNegative() {
		//tests the post does not exist exception
		Post post = new Post();
		
		Mockito.when(postRepository.findById(8L)).thenReturn(Optional.empty());
		Mockito.when(likeRepository.getLikeByPost(post)).thenReturn(new ArrayList<Like>());
		LikeService likeService = new LikeService(postRepository, likeRepository);
		try {
			likeService.getNumberofLikes(8L);
			fail();
		} catch (Exception e) {
			assertEquals(e.getClass(), PostNotFoundException.class);
		}
		
		
	}
}
