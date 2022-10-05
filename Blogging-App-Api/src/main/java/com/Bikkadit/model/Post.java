package com.Bikkadit.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ManyToAny;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Post {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pId;
	
	@Column(length = 100,nullable = false)
	private String pTitle;
	
	@Column(length = 1000)
	private String pContent;
	

	private Date addedDate;
	
	private String imagename;
	

	@ManyToOne
	@JoinColumn(name = "Categoryid")
	private Category category;
	
	@ManyToOne
	private User user;

	
}
