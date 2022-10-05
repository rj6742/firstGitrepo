package com.Bikkadit.Exceptions;

public class ResourceNotFoundException extends RuntimeException {

	
	String resourceName;
	
	String fieldname; 
	
	long fieldvalue;

	public ResourceNotFoundException(String resourceName, String fieldname, long fieldvalue) {
		super(String.format("%s not found with %s : %s", resourceName,fieldname,fieldvalue));
		this.resourceName = resourceName;
		this.fieldname = fieldname;
		this.fieldvalue = fieldvalue;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getFieldname() {
		return fieldname;
	}

	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}

	public long getFieldvalue() {
		return fieldvalue;
	}

	public void setFieldvalue(long fieldvalue) {
		this.fieldvalue = fieldvalue;
	}
	
	
	
	
}
