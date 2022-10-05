package com.Bikkadit.Controller;

import java.util.List;

import javax.persistence.GeneratedValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Bikkadit.Payloads.ApiResponcse;
import com.Bikkadit.Payloads.PostDto;
import com.Bikkadit.Service.PostI;
import com.Bikkadit.Service.PostServiceImpl;
import com.Bikkadit.model.Post;

@RestController
@RequestMapping("/apis/")
public class PostController {
	
	@Autowired
	private PostI impl;
	
	@PostMapping("/user/{uid}/category/{cid}/posts")
	public ResponseEntity<PostDto> create(@RequestBody PostDto dto,@PathVariable Integer uid,@PathVariable Integer cid)
	{
		PostDto createpost = this.impl.createpost(dto, uid, cid);
		
		return new ResponseEntity<PostDto>(createpost,HttpStatus.CREATED);
	}
	
//	public ResponseEntity<PostDto> update(@RequestBody PostDto dto, @PathVariable Integer pid)
//	{
//		return null;
//		
//	}
//	
	@GetMapping("/category/{cid}/posts")
	public ResponseEntity<List<PostDto>> getbycategory(@PathVariable Integer cid)
	{
		List<PostDto> cat = impl.getbycategory(cid);
		
		return new ResponseEntity<List<PostDto>>(cat,HttpStatus.OK);
	}
	
	@GetMapping("/user/{uid}/posts")
	public ResponseEntity<List<PostDto>> getbyuser(@PathVariable Integer uid)
	{
		List<PostDto> cat = impl.getbycategory(uid);
		
		return new ResponseEntity<List<PostDto>>(cat,HttpStatus.OK);
	}

	@GetMapping("/posts")
	public ResponseEntity<List<PostDto>> getallpost()
	{
		List<PostDto> allPost = impl.getAllPost();
		
		return new ResponseEntity<List<PostDto>>(allPost,HttpStatus.OK);
	}
	
	@GetMapping("/post/{pid}")
	public ResponseEntity<PostDto> getpostbyid(@PathVariable Integer pid)
	{
		PostDto getbyid = impl.getbyid(pid);
		return new  ResponseEntity<PostDto>(getbyid,HttpStatus.OK);
	}
	
	@DeleteMapping("/post/{pid}")
	public ApiResponcse deletepost(@PathVariable Integer pid)
	{
		impl.deletePost(pid);
		return new ApiResponcse ("post deleted successfully",true);
	}
	
	@PutMapping("/update/{pid}/post")
	public ResponseEntity<PostDto> updatepost(@RequestBody PostDto dto, @PathVariable Integer pid)
	{
		PostDto update = impl.update(dto, pid);
		
		return new ResponseEntity<PostDto>(update,HttpStatus.OK);
	}
}
