package com.jsp.agro.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.agro.entity.User;
import com.jsp.agro.repo.UserRepo;
import com.jsp.agro.service.userService;
import com.jsp.agro.util.ResponseStructure;

@RestController
public class UserController {
	@Autowired
	private userService service;
	@Autowired
	private UserRepo r;
	@PostMapping("/register")
	public ResponseEntity<ResponseStructure<User>> register(@RequestBody  User u){
		return service.register(u);
	}
	
	@PutMapping("/updateUser")
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestBody User u){
		return service.updateUser(u);
	}
	@GetMapping("/fetchUser")
	public ResponseEntity<ResponseStructure<User>> fetchById(@RequestParam String userId){
		return service.fetchById(userId);
	}
	@GetMapping("/fetchAllUser")
	public ResponseEntity<ResponseStructure<List<User>>> fetchAll(){
		return service.fetchAll();
	}
	@DeleteMapping("/deleteUser")
	public ResponseEntity<ResponseStructure<User>> deleteUser(@RequestParam String userId){
		return service.deleteUser(userId);
	}
	@GetMapping("/login")
	public ResponseEntity<ResponseStructure<User>> login(@RequestParam String email,@RequestParam String pwd) {
		return service.fetchByEmail(email, pwd);
		
	}
	@GetMapping("/otp")
	public ResponseEntity<ResponseStructure<Integer>> otpSend(@RequestParam String email){
		System.out.println("hello");
		return service.otp(email);
	}
	
}
