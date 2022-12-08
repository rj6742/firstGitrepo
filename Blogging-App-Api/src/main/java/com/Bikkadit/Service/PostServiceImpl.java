package com.Bikkadit.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.PostRemove;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.Bikkadit.Exceptions.ResourceNotFoundException;
import com.Bikkadit.Payloads.PostDto;
import com.Bikkadit.Payloads.PostResponce;
import com.Bikkadit.Repository.CategoryRepo;
import com.Bikkadit.Repository.PostRepo;
import com.Bikkadit.Repository.UserRepo;
import com.Bikkadit.model.Category;
import com.Bikkadit.model.Post;
import com.Bikkadit.model.User;

@Service
public class PostServiceImpl implements PostI{
	
	@Autowired
	private PostRepo repo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userrepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	

	@Override
	public PostDto createpost(PostDto dto, Integer uid, Integer cid) {
		
		
		User user = this.userrepo.findById(uid).orElseThrow(()-> new ResourceNotFoundException("User", "user id", uid));
		
		Category category=this.categoryRepo.findById(cid).orElseThrow(()-> new ResourceNotFoundException("category", "category id", cid));
		
		Post map = modelMapper.map(dto, Post.class);
		map.setImagename("default.png");
		map.setAddedDate(new Date());
		map.setCategory(category);
		map.setUser(user);
		
		
		Post save = repo.save(map);
		
		
				return modelMapper.map(save, PostDto.class);
	}



	@Override
	public PostDto update(PostDto dto,Integer pid) {

		Post post = repo.findById(pid).orElseThrow(()-> new ResourceNotFoundException("post", "post id", pid));
		
		post.setPTitle(dto.getpTitle());
		post.setPContent(dto.getPContent());
		post.setImagename(dto.getImagename());
		Post save = repo.save(post);
		
		
		
		
		return modelMapper.map(save, PostDto.class);
	}



	@Override
	public void deletePost(Integer pid) {
		
		Post post = repo.findById(pid).orElseThrow(()-> new ResourceNotFoundException("post", "post id", pid));

		repo.delete(post);
		
		
		
		
		
	}



	@Override
	public PostResponce getAllPost(Integer pagenumber, Integer pagesize,String sortby,String sortdir ) {
		
		Sort sort=null;
		if(sortdir.equalsIgnoreCase("ASC"))
		{
			sort=Sort.by(sortby).ascending();
		}
		else
		{
			sort=Sort.by(sortby).descending();
		}
		
		Pageable p=PageRequest.of(pagenumber,pagesize,sort );

		 Page<Post> pagepost = repo.findAll(p);
		 
		 List<Post> content = pagepost.getContent();
		 
		 
		 
		List<PostDto> collect = content.stream().map((post)-> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		PostResponce postresponce=new PostResponce();
		postresponce.setContent(collect);
		postresponce.setPageNumber(pagepost.getNumber());
		postresponce.setPageSize(pagepost.getSize());
		postresponce.setTotalElement(pagepost.getTotalElements());
		postresponce.setTotalPages(pagepost.getTotalPages());
		postresponce.setLastpage(pagepost.isLast());
		
		
		
		
		return postresponce;
	}



	@Override
	public PostDto getbyid(Integer pid) {

		Post post = repo.findById(pid).orElseThrow(()-> new ResourceNotFoundException("post", "post id", pid));
		
		
		
		return modelMapper.map(post, PostDto.class);
	}





	@Override
	public List<PostDto> searchpost(String keyword) {

		List<Post> posts = repo.searchbykeyword("%"+keyword+"%");
		
		List<PostDto> collect = posts.stream().map((post)->modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return collect;
	}



	@Override
	public List<PostDto> getbycategory(Integer cid) {

		
		Category category = categoryRepo.findById(cid).orElseThrow(()-> new ResourceNotFoundException("category", "category id",cid));
		List<Post> list = repo.findByCategory(category);
		List<PostDto> collect = list.stream().map((post)->modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		
		return collect;
	}



	@Override
	public List<PostDto> getpostbyuser(Integer uid) {

		User user = userrepo.findById(uid).orElseThrow(()-> new ResourceNotFoundException("User", "user id", uid));
		List<Post> list = repo.findByUser(user);
		List<PostDto> list2 = list.stream().map((post)-> modelMapper.map(list, PostDto.class)).collect(Collectors.toList());
		
		
		
		
		return list2;
	}

}


