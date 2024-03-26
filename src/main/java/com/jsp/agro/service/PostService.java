package com.jsp.agro.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.agro.dao.ImageDao;
import com.jsp.agro.dao.PostDao;
import com.jsp.agro.dao.UserCrud;
import com.jsp.agro.entity.Image;
import com.jsp.agro.entity.Post;
import com.jsp.agro.entity.User;
import com.jsp.agro.exception.PostNotFound;
import com.jsp.agro.exception.UserNotFoundException;
import com.jsp.agro.util.ResponseStructure;

@Service
public class PostService {
	@Autowired
	private PostDao pdao;
	@Autowired
	private ImageDao idao;
	@Autowired
	private UserCrud udao;
	
	public ResponseEntity<ResponseStructure<Post>> savePost(String uid,MultipartFile file,String location,String caption) throws IOException{
		User userDb = udao.fetchById(uid);
		if(userDb!=null) {
			ResponseStructure<Post> r=new ResponseStructure<Post>();
			Image imageDb = idao.uploadProfile(file.getOriginalFilename(),file);
			Post postDb = pdao.savePost(imageDb,location,caption);
			List<Post> list=new ArrayList<Post>();
			list.add(postDb);
			list.addAll(userDb.getPost());
			userDb.setPost(list);
			udao.register(userDb);
			r.setData(postDb);
			r.setMsg(caption+" posted successfully...!");
			r.setStatus(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Post>>(r,HttpStatus.CREATED);
		}else
			throw new UserNotFoundException();
		
	}
	public ResponseEntity<ResponseStructure<Post>> fetchPostById(String id){
		Post postDb=pdao.fetchPostById(id);
		if(postDb!=null) {
			ResponseStructure<Post> r=new ResponseStructure<Post>();
			r.setData(postDb);
			r.setMsg("fetch  successfully...!");
			r.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Post>>(r,HttpStatus.FOUND);
		}else
			throw new PostNotFound("post not found for id : "+id);
		
	}
	public ResponseEntity<ResponseStructure<Post>> deletePostById(String id){
		Post db = pdao.fetchPostById(id);
		if(db!=null) {
			List<User> u = udao.fetchAll();
			for(User user:u) {
				List<Post> post = user.getPost();
				if(post.contains(db)) {
					post.remove(db);
					udao.updateUser(user);
					pdao.deletePostById(id);
					break;
				}
			}
			ResponseStructure<Post> rs=new ResponseStructure<Post>();
			rs.setData(db);
			rs.setMsg("Post deleted successfully...");
			rs.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Post>>(rs,HttpStatus.FOUND);
		}
		else {
			throw new PostNotFound("Id not found with in the exist database :"+id);
		}

}
}
