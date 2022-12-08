package com.Bikkadit.Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Bikkadit.Configuration.AppConstants;
import com.Bikkadit.Payloads.ApiResponcse;
import com.Bikkadit.Payloads.PostDto;
import com.Bikkadit.Payloads.PostResponce;
import com.Bikkadit.Service.PostI;
import com.Bikkadit.Service.PostServiceImpl;
import com.Bikkadit.Service.fileService;
import com.Bikkadit.model.Post;

import lombok.val;

@RestController
@RequestMapping("/apis/")
public class PostController {
	
	@Autowired
	private PostI impl;
	
	@Autowired
	private fileService fileservice;
	
	@Value("${project.image")
	private String path;
	
	@PostMapping("/user/{uid}/category/{cid}/posts")
	public ResponseEntity<PostDto> create(@RequestBody PostDto dto,@PathVariable Integer uid,@PathVariable Integer cid)
	{
		PostDto createpost = this.impl.createpost(dto, uid, cid);
		
		return new ResponseEntity<PostDto>(createpost,HttpStatus.CREATED);
	}
	
 
	
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
	public ResponseEntity<PostResponce> getallpost(@RequestParam (value = "pagenumber",defaultValue = AppConstants.page_number,required = false) Integer pagenumber,@RequestParam(
			value = "pageSize",defaultValue = AppConstants.page_size,required = false) Integer pagesize,
			@RequestParam(value = "sortby",defaultValue =AppConstants.sort_by,required = false ) String sortby,
			@RequestParam(value = "sortdir",defaultValue =AppConstants.sort_dir,required = false )String sortdir)
	{
		  PostResponce allPost = impl.getAllPost(pagenumber,pagesize,sortby,sortdir);
		
		return new ResponseEntity<PostResponce >(allPost, HttpStatus.OK);
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
	
	@GetMapping("/posts/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchpostbytitle(@PathVariable String keyword)
	{
		List<PostDto> searchpost = impl.searchpost(keyword);
		
		return new ResponseEntity<List<PostDto>>(searchpost,HttpStatus.OK);
	}
	
	@PostMapping("/post/image/upload/{pId}")
	public ResponseEntity<PostDto> uploadImage(@RequestParam MultipartFile image,
			@PathVariable Integer pId) throws IOException
	{
		PostDto postdto= impl.getbyid(pId);
		String uploadImage = fileservice.uploadImage(path, image);
		
		postdto.setImagename(uploadImage);
		PostDto update = impl.update(postdto, pId);
		return new ResponseEntity<PostDto>(update,HttpStatus.OK);
		
		
	}
	
	@GetMapping(value ="/post/image/{imagename}",produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadimage(@PathVariable String imagename,
			HttpServletResponse responce) throws IOException
	{
		
		
		InputStream resource = fileservice.getResource(imagename, imagename);
		responce.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, responce.getOutputStream());
		
	}
}
