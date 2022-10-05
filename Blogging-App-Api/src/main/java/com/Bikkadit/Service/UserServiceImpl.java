package com.Bikkadit.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Bikkadit.Exceptions.ResourceNotFoundException;
import com.Bikkadit.Payloads.UserDto;
import com.Bikkadit.Repository.UserRepo;
import com.Bikkadit.model.User;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo repo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public UserDto creatUser(UserDto userdto) {

		User user=this.dtotoUser(userdto);
		
		User save = this.repo.save(user);
		
		return this.usertodto(user);
	}

	@Override
	public UserDto updateuser(UserDto userdto, Integer id) {

		User user=this.repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","id",id));
		
		user.setName(userdto.getName());
		user.setEmail(userdto.getEmail());
		user.setAbout(userdto.getAbout());
		user.setPwd(userdto.getPwd());
		
		User save = this.repo.save(user);
		UserDto usertodto1 = this.usertodto(save);
		
		
		
		return usertodto1;
	}

	@Override
	public UserDto getUserbyid(Integer uid) {

		User user=this.repo.findById(uid).orElseThrow(() -> new ResourceNotFoundException ("User","uid",uid));
		
		
		return this.usertodto(user);
	}

	@Override
	public List<UserDto> getAll() {

		List<User> findAll = this.repo.findAll();
		
		List<UserDto> collect = findAll.stream().map(user ->this.usertodto(user)).collect(Collectors.toList());
		
		return collect;
	}

	@Override
	public void deleteuser(Integer uid) {

		
		User user = this.repo.findById(uid).orElseThrow(() -> new ResourceNotFoundException("User", "Id",uid));
		
		this.repo.delete(user);
	}

	

	private User dtotoUser(UserDto dto)
	{
		User user =this.modelMapper.map(dto,User.class);
		return user;
	}
	
	public UserDto  usertodto(User user)
	{
		UserDto dto=this.modelMapper.map(user, UserDto.class);
		
		return dto;
	}
	
}


