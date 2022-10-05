package com.Bikkadit.Payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
	
	private Integer cId;
	
	@NotEmpty
	@Size(min =4 ,message = "must be more than 4")
	private String cTitle;
	
	@NotBlank
	@Size(min = 10,  message = "must be more than 10")
	private String cDescription;

	
	
	
	

	
	

}
