package com.ronit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
//		ApplicationContext context = SpringApplication.run(DemoApplication.class);
//		UsersRepository usersRepository = context.getBean(UsersRepository.class);

//  add new employees + jobs
//		List<User> users = new ArrayList<User>();
//		users.add(new User("fgfgf", 30));
//		usersRepository.save(new User("do something...", JobDate));	
//		User user = new User("fgfgf", 30);
//		usersRepository.save(user);
//		
	}

}
