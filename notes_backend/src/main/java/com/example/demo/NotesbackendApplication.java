package com.example.notesbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Boot application entry point. Scans com.example.notesbackend.* packages.
 */
@SpringBootApplication(scanBasePackages = "com.example.notesbackend")
public class NotesbackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotesbackendApplication.class, args);
	}

}
