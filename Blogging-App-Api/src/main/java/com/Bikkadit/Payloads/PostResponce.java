package com.Bikkadit.Payloads;

import java.util.List;

public class PostResponce {
	
	private List<PostDto> content;
	
	private int pageNumber;
	
	private int pageSize;
	
	private long totalElement;
	
	private int totalPages;
	
	private boolean lastpage;

	public List<PostDto> getContent() {
		return content;
	}

	public void setContent(List<PostDto> content) {
		this.content = content;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotalElement() {
		return totalElement;
	}

	public void setTotalElement(long totalElement) {
		this.totalElement = totalElement;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public boolean isLastpage() {
		return lastpage;
	}

	public void setLastpage(boolean lastpage) {
		this.lastpage = lastpage;
	}

	public PostResponce() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
