package com.jsp.agro.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.jsp.agro.entity.Image;
import com.jsp.agro.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	@Query("select a from User a where a.email=?1")
	public User fetchByEmail(String email);
//	@Modifying
//	@Transactional
//	@Query("update User u set u.image=?1 where u.image=?2")
//	public User fetchByImage( String s,int id);
	@Query("select a from User a where a.image=?1")
	public User fetchByImage(Image id);
}
