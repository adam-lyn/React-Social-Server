package com.revature.users.profiles;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.revature.users.User;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
@EnableAutoConfiguration
@Entity
@Table(name = "user_profile")
public class Profile {

    @Id
    @Column(name="id", unique = true)
    @GeneratedValue
    private UUID id;
	
    @Column(name="firstName", unique = false, nullable = false)
    private String firstName;
    
    @Column(name="lastName",unique = false, nullable = false)
    private String lastName;
    
    @Column(name="profileImg",unique = false, nullable = false)
    private String profileImg;
    
    @Column(name="headerImg",unique = false, nullable = false)
    private String headerImg;

    @Column(name="birthday",unique = false, nullable = false)
    private String birthday;

    @Column(name="hobby",unique = false, nullable = false)
    private String hobby;

    @Column(name="location", unique = false, nullable = false)
    private String location;
    
    @Column(name="aboutMe", unique = false, nullable = false)
    private String aboutMe;

    @OneToOne
    @JoinColumn(name="user_id_fk", unique = true, referencedColumnName = "user_id")
    private User user;


    public Profile() {
    	this.firstName = "Reverb";
    	this.lastName = "User";
    	this.profileImg = "https://i.pinimg.com/originals/ca/f3/93/caf393479404b953bc5368a63c32e4e4.png";
    	this.headerImg = "https://www.windowslatest.com/wp-content/uploads/2017/10/Windows-XP-min.jpg";
        this.birthday = "A Mystery...";
        this.hobby = "Programming, surely";
        this.location = "Planet Earth";
    	this.aboutMe = "I just joined Reverb!";
    }
}
