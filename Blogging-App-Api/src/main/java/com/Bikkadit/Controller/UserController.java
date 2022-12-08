 package com.Bikkadit.Controller;

import java.util.List;

import javax.persistence.PostLoad;
import javax.validation.Valid;

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
import com.Bikkadit.Payloads.UserDto;
import com.Bikkadit.Service.UserServiceImpl;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserServiceImpl impl;
	
	
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto dto)
	{
		UserDto creatUser = impl.creatUser(dto);
		
		return new ResponseEntity<>(creatUser,HttpStatus.CREATED);
	}
	
	@PutMapping (value = "/update/{id}",consumes = "application/json",produces = "application/json")
	public ResponseEntity<UserDto> update(@Valid @RequestBody UserDto dto, @PathVariable Integer id)
	{
		UserDto updateuser = impl.updateuser(dto, id);
		
		return new ResponseEntity<UserDto>(updateuser,HttpStatus.OK);
		
	}
	@DeleteMapping(value = "/deleteuser/{id}")
	public ResponseEntity<String> deleteuser(@PathVariable Integer id)
	{
		impl.deleteuser(id);
		String msg="Deleted sSuccessfully";
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	@GetMapping(value = "/getall",produces = "application/json")
	public ResponseEntity<List<UserDto>> getAll()
	{
		List<UserDto> all = impl.getAll();
		
		return new ResponseEntity<List<UserDto>>(all,HttpStatus.OK);
	}
	
	@GetMapping(value = "/getbyid/{id}",produces = "application/json")
	public ResponseEntity<UserDto> getbyid(@PathVariable Integer id)
	{
		UserDto userbyid = impl.getUserbyid(id);
		
		return new ResponseEntity<UserDto>(userbyid,HttpStatus.OK);
		
	}
	
}