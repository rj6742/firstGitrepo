package com.Bikkadit.Service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import com.Bikkadit.Payloads.CategoryDto;

public interface CategoryI {

	
	CategoryDto createCategory (CategoryDto categoryDto);
	
	CategoryDto updateCategory(CategoryDto categoryDto, Integer cid);
	
	void deleteCategory(Integer cid);
	
	CategoryDto getCategory (Integer  cid);
	
	List<CategoryDto> getCategories();
	
	
}