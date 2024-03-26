package com.jsp.agro.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.agro.entity.Post;
import com.jsp.agro.service.PostService;
import com.jsp.agro.util.ResponseStructure;

@RestController
public class PostController {
	@Autowired
	private PostService service;
	
	
	@PostMapping("/savePost")
	public ResponseEntity<ResponseStructure<Post>> savePost(@RequestParam String userId,@RequestParam MultipartFile file,@RequestParam String location,@RequestParam String caption ) throws IOException{
		return service.savePost(userId,file,location,caption);
	
	}
	@GetMapping("/fetchPost")
	public ResponseEntity<ResponseStructure<Post>> fetchPostById(@RequestParam String postId){
		return service.fetchPostById(postId);
	}
	@DeleteMapping("/deletePost")
	public ResponseEntity<ResponseStructure<Post>> deletePostById(@RequestParam String postId){
		return service.deletePostById(postId);
	}

}
