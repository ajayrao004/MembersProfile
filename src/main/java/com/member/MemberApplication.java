package com.member;

import com.member.dao.UserDao;
import com.member.model.Role;
import com.member.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class MemberApplication implements CommandLineRunner {
 @Autowired
 private UserDao userDao;
	public static void main(String[] args) {
		SpringApplication.run(MemberApplication.class, args);
	}
	public void run(String... args)
	{
		User adminAccount= userDao.findByRole(Role.ADMIN);
		if(null == adminAccount)
		{
			User user=new User();
			user.setEmail("admin@gmail.com");
			user.setFirstName("admin");
			user.setLastName("admin");
			user.setRole(Role.ADMIN);
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userDao.save(user);
		}
	}

}
