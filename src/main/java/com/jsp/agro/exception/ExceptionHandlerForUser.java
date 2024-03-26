package com.jsp.agro.exception;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jsp.agro.entity.Image;
import com.jsp.agro.util.ResponseStructure;



@RestControllerAdvice
public class ExceptionHandlerForUser extends ResponseEntityExceptionHandler {
	

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
	public ResponseEntity<ResponseStructure<String>> imageNotFoundException(ImageNotFound ex){
		ResponseStructure<String> m=new ResponseStructure<String>();
		m.setData("not found for image id");
		m.setMsg(ex.getMsg());
		m.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(m,HttpStatus.NOT_FOUND);
	}
	//***********************Post******************************
	
	@ExceptionHandler(PostNotFound.class)
	public ResponseEntity<ResponseStructure<String>> postNotFoundException(PostNotFound ex){
		ResponseStructure<String> m=new ResponseStructure<String>();
		m.setData("not found for post id");
		m.setMsg(ex.getMsg());
		m.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(m,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(CommentNotFound.class)
	public ResponseEntity<ResponseStructure<String>> commentNotFound(CommentNotFound ex){
		ResponseStructure<String> m=new ResponseStructure<String>();
		m.setData("not found for image id");
		m.setMsg(ex.getMsg());
		m.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(m,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(EquipmentNotFound.class)
	public ResponseEntity<ResponseStructure<String>> equipmentNotFound(EquipmentNotFound ex){
		ResponseStructure<String> m=new ResponseStructure<String>();
		m.setData("not found for equipment id");
		m.setMsg(ex.getMsg());
		m.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(m,HttpStatus.NOT_FOUND);
	}
	
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
			
		List<ObjectError> error = ex.getAllErrors();
		Map<String, String> map = new HashMap<String, String>();
		ResponseStructure<Object> structure = new ResponseStructure<>();

		for (ObjectError objectError : error) {
			String filedName = ((FieldError) objectError).getField();
			String message = ((FieldError) objectError).getDefaultMessage();
			map.put(filedName, message);

		}
		structure.setMsg("provide valid details");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(map);

		return new ResponseEntity<Object>(structure, HttpStatus.BAD_REQUEST);
	
	}
	
//
//	@Override
//	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
//			HttpHeaders headers, HttpStatus status, WebRequest request) {
//		List<ObjectError> error = ex.getAllErrors();
//		Map<String, String> map = new HashMap<String, String>();
//		ResponseStructure<Object> structure = new ResponseStructure<>();
//
//		for (ObjectError objectError : error) {
//			String filedName = ((FieldError) objectError).getField();
//			String message = ((FieldError) objectError).getDefaultMessage();
//			map.put(filedName, message);
//
//		}
//		structure.setMsg("provide valid details");
//		structure.setStatus(HttpStatus.NOT_FOUND.value());
//		structure.setData(map);
//
//		return new ResponseEntity<Object>(structure, HttpStatus.BAD_REQUEST);
//	}

//	@org.springframework.web.bind.annotation.ExceptionHandler(ConstraintViolationException.class)
//	public ResponseEntity<ResponseStructure<Object>> handleConstraintViolationException(ConstraintViolationException ex) {
//		ResponseStructure<Object> structure = new ResponseStructure();
//		Map<String, String> map = new HashMap<String, String>();
//
//		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
//			String field = violation.getPropertyPath().toString();
//			String message = violation.getMessage();
//			map.put(field, message);
//
//		}
//
//		structure.setMsg("provide proper details");
//		structure.setStatus(HttpStatus.NOT_FOUND.value());
//		structure.setData(map);
//
//		return new ResponseEntity<ResponseStructure<Object>>(structure, HttpStatus.BAD_REQUEST);
//
//	}
}
