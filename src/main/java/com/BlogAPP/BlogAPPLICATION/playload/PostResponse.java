package com.BlogAPP.BlogAPPLICATION.playload;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostResponse {
	private List<PostDTO> content;
	private int pageNo;
	private int pageSize;
	private long totalElements;
	private int totalPages;
	private boolean last;
	
	public List<PostDTO> getContent() {
		return content;
	}
	public void setContent(List<PostDTO> content) {
		this.content = content;
	}
	
	public int getPageNo() {
		return pageNo;
	}
	
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public long getTotalElements() {
		return totalElements;
	}
	
	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}
	
	public int getTotalPages() {
		return totalPages;
	}
	
	public void setTotalPages(int totlaPages) {
		this.totalPages = totlaPages;
	}
	
	public boolean isLast() {
		return last;
	}
	
	public void setLast(boolean last) {
		this.last = last;
	}
    public PostResponse() {
    } 

}
