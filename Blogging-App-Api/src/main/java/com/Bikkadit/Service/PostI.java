package com.Bikkadit.Service;

import java.util.List;

import javax.persistence.PostRemove;

import com.Bikkadit.Payloads.PostDto;
import com.Bikkadit.Payloads.PostResponce;
import com.Bikkadit.model.Post;

public interface PostI {

	
	PostDto createpost(PostDto dto,Integer uid,Integer cid);
	
	PostDto update(PostDto dto,Integer pid);
	
	void deletePost(Integer pid);
	
	PostResponce getAllPost(Integer pagenumber, Integer pagesize,String sortby,String sortdir);
	
	PostDto getbyid(Integer pid);
	
	List<PostDto> getbycategory(Integer cid);
	
	List<PostDto> getpostbyuser(Integer uid);
	
	List<PostDto> searchpost(String     keyword);
	
	
	
}
