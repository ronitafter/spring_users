package com.ronit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ronit.beans.ResponseDto;
import com.ronit.beans.User;
import com.ronit.exceptions.InvalidOperationException;
import com.ronit.exceptions.UserExceptions;
import com.ronit.service.UserService;

@RestController
@RequestMapping("/user")
public class UsersController {

	private UserService userService;

	@Autowired
	public UsersController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getUserById(@PathVariable("id") Integer id) {
		try {
			User user = userService.getUser(id);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (UserExceptions e) {
			ResponseDto responsdto = new ResponseDto(false, e.getMessage());
			return new ResponseEntity<>(responsdto, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<ResponseDto> addUser(@RequestBody User user) {
		try {
			userService.addUser(user);
			// userService.save(user).getId();
			ResponseDto responsdto = new ResponseDto(true, "user added");
			return new ResponseEntity<>(responsdto, HttpStatus.CREATED);
		} catch (InvalidOperationException e) {
			ResponseDto responsdto = new ResponseDto(false, e.getMessage());
			return new ResponseEntity<ResponseDto>(responsdto, HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping
	public ResponseEntity<?> updateUser(@RequestBody User user) throws UserExceptions, InvalidOperationException {
		userService.updateUser(user);
		ResponseDto responseDto = new ResponseDto(true, "updateUser successfully");
		return new ResponseEntity<ResponseDto>(responseDto, HttpStatus.OK);
//		Map<String, Object> response = new HashMap<String, Object>();
//		response.put("success", true);
//		response.put("message", "user updateUser successfully");
//		return new ResponseEntity<Map>(response, HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") Integer id) {
		try {
			userService.deleteUser(id);
			return new ResponseEntity<>("user deleted", HttpStatus.OK);
			//			return new ResponseEntity(HttpStatus.CREATED);

		} catch (UserExceptions e) {
			ResponseDto responsdto = new ResponseDto(false, e.getMessage());
			return new ResponseEntity<ResponseDto>(responsdto, HttpStatus.OK);
		}
	}


	@GetMapping("/ByAge")
	public List<User> getAllUsersByAgeQueryParam(@RequestParam("age") Integer age) {
		return userService.getUsersByAge(age);
	}

	@GetMapping("/ByAge/{age}")
	public List<User> getAllUsersByAge(@PathVariable("age") Integer age) {
		return userService.getUsersByAge(age);
	}

	@GetMapping("/ByAgeAndName")
	public List<User> getAllUsersByAgeAndName(@RequestParam("age") Integer age, @RequestParam("name") String name) {
		return userService.getUsersByAgeAndName(age, name);
	}

//	@PostMapping
//	public ResponseEntity<?> addUser(@RequestBody User user) {
//		userService.addUser(user);
//		return new ResponseEntity(HttpStatus.CREATED);
//	}

//
//	@GetMapping("/x/{name}")
//	public String greet(@PathVariable("name") String name) {
//		return "hello " + name;
//	}

}
