package com.jsp.agro.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.agro.entity.Comment;
import com.jsp.agro.entity.Image;
import com.jsp.agro.entity.Post;
import com.jsp.agro.entity.User;
import com.jsp.agro.repo.PostRepo;
import com.jsp.agro.repo.UserRepo;

@Repository
public class PostDao {
	@Autowired
	private Post post;
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private UserCrud udao;
	@Autowired
	private UserRepo urepo;
	
	public Post savePost(Image image,String location,String Caption) {
		post.setCaption(Caption);
		post.setDate(LocalDateTime.now());
		post.setLocation(location);
		post.setImage(image);
		return postRepo.save(post);
	}
	
	public Post fetchPostById(String id) {
		Optional<Post> db = postRepo.findById(id);
		if(db.isPresent()) {
			return db.get();
		}else
			return null;
		
	}
	public Post deletePostById(String id) {
			Optional<Post> db = postRepo.findById(id);
			if(db.isPresent()) {
				postRepo.deleteById(id);
				return db.get();
			}else
				return null;
	}
	public Post updatePost(Post p) {
		Optional<Post> db = postRepo.findById(p.getId());
		Post postDb = db.get();
		if(db.isPresent()) {
			
//			if(p.getImage()!=null) {
//				postDb.setImage(p.getImage());
//			}
//			if(p.getLikes()!=0) {
//				postDb.setLikes(p.getLikes());
//			}
//			if(p.getComment()!=null) {
//				postDb.setComment(p.getComment());
//			}
//			if(p.getDate()!=null) {
//				postDb.setDate(p.getDate());
//			}
//			if(p.getCaption()!=null) {
//				postDb.setCaption(p.getCaption());
//			}
//			if(p.getLocation()!=null) {
//				postDb.setLocation(p.getLocation());
//			}
//		}
			return postRepo.save(postDb);
	}
		return null;
//	int id,Image image,int likes,List<Comment> comment,LocalDateTime date,String caption,String location
		
	}
	public List<Post> fetchPostAll(){
		return postRepo.findAll();
	}
}


