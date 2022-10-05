package com.Bikkadit.Payloads;

import java.util.Date;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.Bikkadit.model.Category;
import com.Bikkadit.model.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class PostDto {

	private Integer pId;
	
	
	private String pTitle;
	
	private String pContent;
	
	private String imagename;
	
	private Date addedDate;

	public String getpTitle() {
		return pTitle;
	}

	private CategoryDto category;
	
	private UserDto user;
	
}
