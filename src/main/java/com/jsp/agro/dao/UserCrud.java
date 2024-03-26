package com.jsp.agro.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.agro.entity.Address;
import com.jsp.agro.entity.User;
import com.jsp.agro.repo.AddressRepo;
import com.jsp.agro.repo.UserRepo;

@Repository
public class UserCrud {
	@Autowired
	private UserRepo repo;
	@Autowired
	private AddressDao aDao;
	
	public User register(User u) {
		return repo.save(u);
	}
	public User updateUser(User u) {
		 Optional<User> dbuser = repo.findById(u.getId());
		if(dbuser.isPresent()) {
			           User db = dbuser.get();
			
			if(u.getFirstName()==null) {
				u.setFirstName(db.getFirstName());
			}
			if(u.getLastName()==null) {
				u.setLastName(db.getLastName());
			}
			if(u.getEmail()==null) {
				u.setEmail(db.getEmail());
			}
			if(u.getPwd()==null) {
				u.setPwd(db.getPwd());
			}
			if(u.getPhno()==0) {
				u.setPhno(db.getPhno());
			}
			if(u.getGender()==null) {
				u.setGender(db.getGender());
			}
			if(u.getAge()==0) {
				u.setAge(db.getAge());
			}
			if(u.getType()==null) {
				u.setType(db.getType());
			}
			if(u.getAddress()==null) {
				u.setAddress(db.getAddress());
			}if(u.getAddress()!=null) {
				u.setAddress(aDao.saveAddress(u.getAddress()));
			}
			if(u.getPost()==null) {
				u.setPost(db.getPost());
			}
		}
		return repo.save(u);
	}
	public User deleteUser(String id) {
		User db = repo.findById(id).get();
		if(db!=null) {
		 repo.deleteById(id);
		 return db;
		}else {
			return null;
		}
	}
	public void deleteAllUser(){
		repo.deleteAll();
		
	}
	
	public User fetchById(String id) {
		 Optional<User> db = repo.findById(id);
		if(db.isPresent()) {
			return db.get();
		}else {
			System.out.println("crud else");
			return null;
		}
	}
	public User fetchByEmail(String email) {
		return repo.fetchByEmail(email);
	}

	public List<User> fetchAll(){
		return repo.findAll();
	}

}
