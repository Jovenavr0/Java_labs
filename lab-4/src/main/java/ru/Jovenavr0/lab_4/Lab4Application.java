package ru.Jovenavr0.lab_4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class Lab4Application {

	public static void main(String[] args) {
		String encodedPassword = new BCryptPasswordEncoder().encode("admin");
		System.out.println(encodedPassword);
		SpringApplication.run(Lab4Application.class, args);
	}

}
