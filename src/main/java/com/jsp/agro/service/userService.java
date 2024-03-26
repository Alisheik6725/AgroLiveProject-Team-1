package com.jsp.agro.service;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.agro.dao.UserCrud;
import com.jsp.agro.entity.Image;
import com.jsp.agro.entity.User;
import com.jsp.agro.exception.EmailNotFound;
import com.jsp.agro.exception.PasswordIncorrect;
import com.jsp.agro.exception.UserNotFoundException;
import com.jsp.agro.repo.ImageRepo;
import com.jsp.agro.util.ResponseStructure;

@Service
public class userService {
	@Autowired
	private UserCrud dao;
	@Autowired
	private JavaMailSender javaMailSender;
	
	public ResponseEntity<ResponseStructure<User>> register(User u){
		ResponseStructure<User> r=new ResponseStructure<User>();
		r.setData(dao.register(u));
		r.setMsg("RegisterSuccessfully.........!");
		r.setStatus(HttpStatus.CONTINUE.value());
		String email=u.getEmail();
		String subject="Welcome to live project agro....!";
		if(email!=null) {
			sendSimpleMail(email,u.getFirstName()+u.getLastName()+"Register Successfully.....!",subject);
		}
		return new ResponseEntity<ResponseStructure<User>>(r,HttpStatus.CONTINUE);
	}
	public String sendSimpleMail(String email,String msg,String subject) {
		SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
		simpleMailMessage.setFrom("k.anupumajba@gmail.com");
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setTo(email);
		simpleMailMessage.setText(msg);
		javaMailSender.send(simpleMailMessage);
		return"register successfully.....!";
	}
	public ResponseEntity<ResponseStructure<User>> updateUser(User u){
		
		User db=dao.fetchById(u.getId());
		if(db!=null) {
			dao.updateUser(u);
			ResponseStructure<User> r=new ResponseStructure<User>();
			r.setMsg("update Successfully............!");
			r.setStatus(HttpStatus.CREATED.value());
			r.setData(u);
			return new ResponseEntity<ResponseStructure<User>>(r,HttpStatus.CONTINUE);
		}else {
			System.out.println(" service else block");
			 throw new UserNotFoundException("user not found for id:"+u.getId());
		}

	}
	public ResponseEntity<ResponseStructure<User>> fetchById(String userId){
		User db=dao.fetchById(userId);
		if(db!=null) {
			System.out.println("if-block");
			ResponseStructure<User> r=new ResponseStructure<User>();
			r.setMsg("fetch Successfully............!");
			r.setStatus(HttpStatus.FOUND.value());
			r.setData(db);
			return new ResponseEntity<ResponseStructure<User>>(r,HttpStatus.FOUND);
		}else {
			System.out.println(" service else block");
			 throw new UserNotFoundException("user not found for id:"+userId);
		}
		
	}
	public ResponseEntity<ResponseStructure<List<User>>> fetchAll(){
		ResponseStructure<List<User>> r =new ResponseStructure<List<User>>();
		r.setMsg("fetch all objects Successfully....");
		r.setStatus(HttpStatus.FOUND.value());
		r.setData(dao.fetchAll());
		return new ResponseEntity<ResponseStructure<List<User>>>(r,HttpStatus.FOUND);
	}
	
	public ResponseEntity<ResponseStructure<User>> deleteUser(String userId){
		User userDb = dao.deleteUser(userId);
		if(userDb!=null) {
		ResponseStructure<User> r=new ResponseStructure<User>();
		r.setMsg("deleted Successfully............!");
		r.setStatus(HttpStatus.GONE.value());
		r.setData(userDb);
		return new ResponseEntity<ResponseStructure<User>>(r,HttpStatus.GONE);
		}else
			throw new UserNotFoundException("user not found for id : "+userId);
	}
	public ResponseEntity<ResponseStructure<User>> fetchByEmail(String email,String pwd){
		User db = dao.fetchByEmail(email);
		if(db!=null) {
			if(db.getPwd().equals(pwd)) {
				System.out.println("login Successfull.........!");
				ResponseStructure<User> r=new ResponseStructure<User>();
				r.setMsg("Login Successfully............!");
				r.setStatus(HttpStatus.FOUND.value());
				r.setData(db);
				return new ResponseEntity<ResponseStructure<User>>(r,HttpStatus.FOUND);
			}else {
				System.out.println("password incorrect");
				throw new PasswordIncorrect("password incorrect for email: "+email);
			}
			
		}else {
			System.out.println("email incorrect");
			throw new EmailNotFound("email not found");
		}
		
	}
	
	
	public ResponseEntity<ResponseStructure<Integer>> otp(String email){
		User db=dao.fetchByEmail(email);
		if(db!=null) {
			Random random=new Random();
			int value=random.nextInt(900000);
			ResponseStructure<Integer> r=new ResponseStructure<Integer>();
			r.setStatus(HttpStatus.CONTINUE.value());
			r.setMsg("OTP for Password");
			r.setData(value);
			String subject="Welcome to live project agro otp Generation....!";
			sendSimpleMail(email,"OTP for Password Verification:"+value,subject);
			return new ResponseEntity<ResponseStructure<Integer>>(r,HttpStatus.CONTINUE);
		}else {
			throw new UserNotFoundException("User not found for email: "+email);
		}
	}
	
}

