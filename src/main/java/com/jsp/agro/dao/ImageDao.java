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
	public Image fetchById(int id) {
		Optional<Image> db = irepo.findById(id);
		if(db.isPresent())
			return db.get();
		else
			return null;
	
	}
//	public Image deleteById(int id) {
//		Optional<Image> db = irepo.findById(id);
//		if(db.isPresent()) {
//			Image imageDb=db.get();
//		irepo.deleteById(id);
//		return imageDb;
//		}
//		else 
//			return null;
//	}
	public Image deleteById(int id) {
		Optional<Image> db = irepo.findById(id);
		if(db.isPresent()) {
			Image imageDb=db.get();
			System.out.println("image dao....");
			 User userdb = userRepo.fetchByImage(imageDb);
			 System.out.println("image ....");
			 userdb.setImage(null);
			 userdao.updateUser(userdb);
		irepo.deleteById(id);
		return imageDb;
		}
		else 
			return null;
	}
	
	
	public Image updateImage(int id,String description,MultipartFile file) throws IOException {
		Optional<Image> db = irepo.findById(i.getId());
		if(db.isPresent()) {
			Image imageDb=db.get();
			if(description==null) {
				i.setDescription(imageDb.getDescription());
			}else
				i.setDescription(description);
			if(file==null) {
				i.setPicture(imageDb.getPicture());
			}else
				i.setPicture(file.getBytes());
			return irepo.save(i);
			
		}
		return null;
	}
	

}
