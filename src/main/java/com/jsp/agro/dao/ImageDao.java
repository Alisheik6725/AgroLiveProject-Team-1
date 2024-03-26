package com.jsp.agro.dao;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.agro.entity.Image;
import com.jsp.agro.entity.User;
import com.jsp.agro.repo.ImageRepo;
import com.jsp.agro.repo.UserRepo;

@Repository
public class ImageDao {
	@Autowired
	private ImageRepo irepo;
	@Autowired
	private Image i;
	@Autowired
	private UserCrud userdao;
	@Autowired
	private UserRepo userRepo;
	
	
	
	public Image uploadProfile(String description,MultipartFile file) throws IOException {
		i.setDescription(description);
		i.setPicture(file.getBytes());
		return irepo.save(i);
		
		
	}
	public Image fetchById(String id) {
		Optional<Image> db = irepo.findById(id);
		if(db.isPresent())
			return db.get();
		else
			return null;
	
	}
	public byte[] fetchByImage(String id) {
		Optional<Image> db = irepo.findById(id);
		if(db.isPresent())
			return db.get().getPicture();
		else
			return null;
	
	}
	public Image deleteById(String id) {
		Optional<Image> db = irepo.findById(id);
		if(db.isPresent()) {
			Image imageDb=db.get();
			 User userdb = userRepo.fetchByImage(imageDb);
			 if(userdb!=null) {
				 userdb.setImage(null);
				 userdao.updateUser(userdb);
			 }
		irepo.deleteById(id);
		return imageDb;
		}
		else 
			return null;
	}
	
	
	public Image updateImage(String id,String description,MultipartFile file) throws IOException {
		Optional<Image> db = irepo.findById(id);
		if(db.isPresent()) {
			Image imageDb=db.get();
			if(description!=null) {
			imageDb.setDescription(description);
			}
			if(file!=null) {
				imageDb.setPicture(imageDb.getPicture());
			}
			return irepo.save(imageDb);
			
		}
		return null;
	}
	

}
