package com.Bikkadit.Service;

import java.util.List;

import com.Bikkadit.Payloads.UserDto;

public interface UserService {
	
	UserDto creatUser(UserDto userdto);	
	
	UserDto updateuser(UserDto userdto,Integer id);
	
	UserDto getUserbyid(Integer uid);
	
	List<UserDto> getAll();
	
	void deleteuser(Integer uid);
}
