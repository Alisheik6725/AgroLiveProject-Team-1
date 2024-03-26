package com.jsp.agro.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.agro.dao.ImageDao;
import com.jsp.agro.entity.Image;
import com.jsp.agro.service.ImageService;
import com.jsp.agro.util.ResponseStructure;

@RestController
public class ImageController {
	@Autowired
	private ImageService service;
	
	@PostMapping("/uploadProfile")
	public ResponseEntity<ResponseStructure<Image>> uploadProfile(@RequestParam String userId,@RequestParam String description ,@RequestParam MultipartFile file) throws IOException{
		return  service.uploadProfile(userId, description, file);
	}
	
	@GetMapping("/fetchImage")
	public ResponseEntity<ResponseStructure<Image>> fetchById(@RequestParam String imageId) {
		return service.fetchById(imageId);
	}
	@DeleteMapping("/deleteImage")
	public ResponseEntity<ResponseStructure<Image>> deleteById(@RequestParam String imageId){
		return service.deleteById(imageId);
	}
	@PutMapping("/updateImage")
	public ResponseEntity<ResponseStructure<Image>> updateImage(@RequestParam String imageId,@RequestParam(required = false) String description ,@RequestParam(required = false) MultipartFile file) throws IOException{
		System.out.println("controller");
		return service.updateImage(imageId,description,file);
	}
   @GetMapping("/getimage")
   public ResponseEntity<byte[]> getImage(@RequestParam String imageId){
	   return service.getImage(imageId);
   }
}

