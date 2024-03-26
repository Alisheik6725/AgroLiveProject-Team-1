package com.jsp.agro.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.agro.entity.Comment;
import com.jsp.agro.entity.User;
import com.jsp.agro.repo.CommentRepo;

@Repository
public class CommentDao {
	@Autowired
	private CommentRepo crepo;
	@Autowired
	private Comment c;
	
	
	public Comment saveComment(String msg,User user) {
		c.setMsg(msg);
		c.setUser(user);
		System.out.println("dao");
		return crepo.save(c);
	}
	public Comment deleteComment(String id) {
		Optional<Comment> db = crepo.findById(id);
		System.out.println(id);
		if(db.isPresent()) {
			crepo.delete(db.get());
			return db.get();
		}
		else
			return null;
	}
	public Comment fetchCommentById(String id) {
		Optional<Comment> db = crepo.findById(id);
		if(db.isPresent()) {
			return db.get();
		}else
			return null;
	}

}
