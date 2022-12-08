package com.Bikkadit.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Bikkadit.Exceptions.ResourceNotFoundException;
import com.Bikkadit.Payloads.CategoryDto;
import com.Bikkadit.Repository.CategoryRepo;
import com.Bikkadit.model.Category;

@Service
public class CategoryserviceImpl implements CategoryI {
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {

		Category map = modelMapper.map(categoryDto, Category.class);
		Category save = categoryRepo.save(map);
		
		return modelMapper.map(save, CategoryDto.class);
	
	
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto,Integer cid)
	{
		
		Category cat = this.categoryRepo.findById(cid)
				.orElseThrow(() -> new ResourceNotFoundException("category", "Category id", cid));
		
		
		cat.setCDescription(categoryDto.getCDescription());
		cat.setCTitle(categoryDto.getCDescription());
		
		Category save = categoryRepo.save(cat);
		
		
		return modelMapper.map(save, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer cid) {

	  Category cat = categoryRepo.findById(cid).orElseThrow(() ->new ResourceNotFoundException("category", "category id", cid));
		 
		
	  categoryRepo.delete(cat);
		
		
		
		
	}

	@Override
	public CategoryDto getCategory(Integer cid) {

         Category id = this.categoryRepo.findById(cid).orElseThrow(()-> new ResourceNotFoundException("Category", "category id", cid));
		
		
		
		
		return modelMapper.map(id, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategories() {

		List<Category> categories = categoryRepo.findAll();
		
		List<CategoryDto> collect = categories.stream().map((cat)->modelMapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
		
		
		
		
		return collect;
	}

}
