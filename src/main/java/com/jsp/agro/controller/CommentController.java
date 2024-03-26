package com.jsp.agro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.agro.entity.Comment;
import com.jsp.agro.service.CommentService;
import com.jsp.agro.util.ResponseStructure;

@RestController
public class CommentController {
	@Autowired
	private CommentService cservice;
	@PostMapping("/saveComment")
	public ResponseEntity<ResponseStructure<Comment>> saveComment(@RequestParam String postId,@RequestParam String userId,@RequestParam String  msg){
		return cservice.saveComment(postId,userId,msg);
	}
	@DeleteMapping("/deleteComment")
	public ResponseEntity<ResponseStructure<Comment>> deleteComment(@RequestParam String postId){
		return cservice.deleteComment(postId);
	}
}
