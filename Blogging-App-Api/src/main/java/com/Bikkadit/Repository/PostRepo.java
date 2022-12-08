package com.Bikkadit.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Bikkadit.model.Category;
import com.Bikkadit.model.Post;
import com.Bikkadit.model.User;

public interface PostRepo extends JpaRepository<Post, Integer>{

	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	
	@Query("select p from Post p where p.pTitle like :key")
	List<Post> searchbykeyword(@Param("key") String title);
	
}
