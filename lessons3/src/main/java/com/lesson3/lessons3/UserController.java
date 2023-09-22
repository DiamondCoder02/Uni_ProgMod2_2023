package com.lesson4.lessons4;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/users")
public class UserController {
	private List<User> userList = new ArrayList<>();

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		if (user.getName() == null || user.getEmail() == null) {
			throw new RuntimeException("Name or email is null");
		}
		user.setId(generateUserId());
		userList.add(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}

	private Long generateUserId(){
		return (long) (userList.size() + 1);
	}

	@ExceptionHandler(NullPointerException.class)
	private ResponseEntity<?> handleNullPointerException(NullPointerException e, HttpServletRequest request) {
		ErrorResponses errorResponses = new ErrorResponses(
			HttpStatus.BAD_REQUEST.value(),
			HttpStatus.BAD_REQUEST.getReasonPhrase(),
			e.getMessage(),
			request.getRequestURI()
		);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponses);
	}
}
