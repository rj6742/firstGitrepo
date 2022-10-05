package com.Bikkadit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Bikkadit.model.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{
	

}
