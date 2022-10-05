package com.Bikkadit.Controller;

import java.util.List;

import javax.validation.Valid;

import org.aspectj.weaver.NewConstructorTypeMunger;
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
import com.Bikkadit.Payloads.CategoryDto;
import com.Bikkadit.Service.CategoryserviceImpl;

@RestController
@RequestMapping("/apis/categories")
public class CategoryController {

	@Autowired
	private CategoryserviceImpl impl;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategories(@RequestBody CategoryDto categoryDto)
	{
		CategoryDto createCategory = impl.createCategory(categoryDto);
		
		return new ResponseEntity<CategoryDto>(createCategory,HttpStatus.CREATED);
	}
	
	@PutMapping("/{cid}")
	public ResponseEntity<CategoryDto> update(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer cid)
	{
		CategoryDto updateCategory = impl.updateCategory(categoryDto, cid);
		return new ResponseEntity<CategoryDto>(updateCategory,HttpStatus.OK);
	}
	
	@GetMapping("/{cid}")
	public ResponseEntity<CategoryDto> getbyid(Integer cid)
	{
		CategoryDto category = impl.getCategory(cid);
		
		return new ResponseEntity<CategoryDto>(category,HttpStatus.OK);
	}
	
	@DeleteMapping("/{cid}")
	public ResponseEntity<ApiResponcse> deletecategory(@PathVariable Integer cid)
	{
		impl.deleteCategory(cid);
		return new ResponseEntity<ApiResponcse>(new ApiResponcse("category deleted successfully",true),HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getall()
	{
		List<CategoryDto> categories = impl.getCategories();
		
		return new ResponseEntity<List<CategoryDto>>(categories,HttpStatus.OK);
	}
}
