package com.example.expense_tracker;

import com.example.expense_tracker.entity.User;
import com.example.expense_tracker.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ExpenseTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseTrackerApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(UserRepository userRepo) {
		return args -> {
			if (userRepo.findByEmail("test@example.com").isEmpty()) {
				User user = new User();
				user.setEmail("test@example.com");
				user.setPassword("password123");
				userRepo.save(user);
				System.out.println(">> Test User Created with ID: " + user.getId());
			} else {
				System.out.println(">> Test User already exists, skipping creation.");
			}
		};
	}
}