package com.jsp.agro.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.agro.dao.ImageDao;
import com.jsp.agro.dao.UserCrud;
import com.jsp.agro.entity.Image;
import com.jsp.agro.entity.User;
import com.jsp.agro.exception.ImageNotFound;
import com.jsp.agro.exception.UserNotFoundException;
import com.jsp.agro.util.ResponseStructure;
@Service
public class ImageService {
	@Autowired
	private ImageDao idao;
	@Autowired
	private UserCrud udao;
	
	
	public ResponseEntity<ResponseStructure<Image>> uploadProfile(int id,String description , MultipartFile file) throws IOException{
		User userDb = udao.fetchById(id);
		if(userDb!=null) {
			Image iDb = idao.uploadProfile(description, file);

			userDb.setImage(iDb);
			udao.updateUser(userDb);
			ResponseStructure<Image> r=new ResponseStructure<Image>();
			r.setMsg("save img Successfully.....");
			r.setData(iDb);
			r.setStatus(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Image>>(r,HttpStatus.ACCEPTED);
		}else {
			throw new UserNotFoundException("user not found for id: "+id);
		}
		
	}
	public ResponseEntity<ResponseStructure<Image>> fetchById(int id){
		Image imageDb = idao.fetchById(id);
		if(imageDb!=null) {
			ResponseStructure<Image> r=new ResponseStructure<Image>();
			r.setMsg("fetch image Successfully.....");
			r.setData(imageDb);
			r.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Image>>(r,HttpStatus.FOUND);
		}else {
			throw new ImageNotFound("Image not found for id : "+id);
		}
	}
	public ResponseEntity<ResponseStructure<Image>> deleteById(int id){
		Image imageDb = idao.deleteById(id);
		if(imageDb!=null) {
			ResponseStructure<Image> r=new ResponseStructure<Image>();
			r.setMsg("delete image Successfully.....");
			r.setData(imageDb);
			r.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Image>>(r,HttpStatus.FOUND);
		}else {
			throw new ImageNotFound("Image not found for id : "+id);
		}
	}
	public ResponseEntity<ResponseStructure<Image>> updateImage(int id,String description,MultipartFile file) throws IOException{
		Image imageDb=idao.updateImage(id, description, file);
		if(imageDb!=null) {
			 ResponseStructure<Image> r=new ResponseStructure<Image>();
			 r.setData(imageDb);
			 r.setMsg("update image successfully......!");
			 r.setStatus(HttpStatus.ACCEPTED.value());
			 return new ResponseEntity<ResponseStructure<Image>>(r,HttpStatus.ACCEPTED);
		}else
			throw new ImageNotFound("Image not found for id : "+id);
		
	}
}
