package com.jsp.agro.exception;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.agro.entity.Image;
import com.jsp.agro.util.ResponseStructure;

@RestControllerAdvice
public class ExceptionHandlerForUser {
	

	@ExceptionHandler(PasswordIncorrect.class)
	public ResponseEntity<ResponseStructure<String>> paswordIncorrect(PasswordIncorrect ex){
		System.out.println("exception Handler");
		ResponseStructure<String> m=new ResponseStructure<String>();
		m.setData("not found for user id");
		m.setMsg(ex.getMsg());
		m.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(m,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(EmailNotFound.class)
	public ResponseEntity<ResponseStructure<String>> emailNotFound(EmailNotFound ex){
		System.out.println("exception Handler");
		ResponseStructure<String> m=new ResponseStructure<String>();
		m.setData("not found for user id");
		m.setMsg(ex.getMsg());
		m.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(m,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> UserNotFoundException(UserNotFoundException ex){
		ResponseStructure<String> m=new ResponseStructure<String>();
		m.setData("not found for user id");
		m.setMsg(ex.getMsg());
		m.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(m,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<ResponseStructure<String>> sqlException(SQLIntegrityConstraintViolationException ex){
		ResponseStructure<String> m=new ResponseStructure<String>();
		m.setData("mail already exists");
		m.setMsg("duplicate mail");
		m.setStatus(HttpStatus.ALREADY_REPORTED.value());
		return new ResponseEntity<ResponseStructure<String>>(m,HttpStatus.ALREADY_REPORTED);
	}
	
	
	
	
	/////////////////////image///////////
	@ExceptionHandler(ImageNotFound.class)
	public ResponseEntity<ResponseStructure<Image>> imageNotFound(ImageNotFound ex){
		ResponseStructure<Image> r=new ResponseStructure<Image>();
		r.setData(null);
		r.setMsg(ex.getMsg());
		r.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<Image>>(r,HttpStatus.NOT_FOUND);

	}
}
