package com.jsp.agro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.agro.dao.CommentDao;
import com.jsp.agro.dao.PostDao;
import com.jsp.agro.dao.UserCrud;
import com.jsp.agro.entity.Comment;
import com.jsp.agro.entity.Post;
import com.jsp.agro.entity.User;
import com.jsp.agro.exception.CommentNotFound;
import com.jsp.agro.exception.PostNotFound;
import com.jsp.agro.exception.UserNotFoundException;
import com.jsp.agro.util.ResponseStructure;

@Service
public class CommentService {
	@Autowired
	private CommentDao cdao;
	@Autowired
	private PostDao pdao;
	@Autowired
	private UserCrud udao;
	
	public ResponseEntity<ResponseStructure<Comment>> saveComment(String postId,String userId,String  msz){
		Post postDb = pdao.fetchPostById(postId);
		if(postDb!=null) {
			User userDb = udao.fetchById(userId);
			if(userDb!=null) {
			Comment commentDb = cdao.saveComment(msz, userDb);
			List<Comment> list=new ArrayList<Comment>();
			list.add(commentDb);
			list.addAll(postDb.getComment());
			postDb.setComment(list);
			System.out.println("service");
			pdao.updatePost(postDb);
			System.out.println("after service");
			ResponseStructure<Comment> r=new ResponseStructure<Comment>();
			r.setData(commentDb);
			r.setMsg("comment saved successfully....!");
			r.setStatus(HttpStatus.CONTINUE.value());
			return new ResponseEntity<ResponseStructure<Comment>>(r,HttpStatus.CONTINUE);
			}else
				throw new UserNotFoundException("user not found for id : "+userId);
			
		}else
			throw new PostNotFound("post not found for pid : "+postId);
	}
	public ResponseEntity<ResponseStructure<Comment>> deleteComment(String id){
		Comment commentDb = cdao.fetchCommentById(id);
		if(commentDb!=null) {
			List<Post> post=pdao.fetchPostAll();
			for(Post p:post) {
				List<Comment> comment=p.getComment();
				if(comment.contains(commentDb)) {
					comment.remove(commentDb);
					pdao.updatePost(p);
					
				}
				cdao.deleteComment(id);
				}
			ResponseStructure<Comment> r=new ResponseStructure<Comment>();
			r.setData(commentDb);
			r.setMsg("comment deleted successfully....!");
			r.setStatus(HttpStatus.CONTINUE.value());
			return new ResponseEntity<ResponseStructure<Comment>>(r,HttpStatus.CONTINUE);
		}
		else {
			throw new  CommentNotFound("comment not found for id : "+id);
		}
			
	}
	
}
