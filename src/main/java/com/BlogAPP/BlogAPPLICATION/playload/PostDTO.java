package com.BlogAPP.BlogAPPLICATION.playload;

import java.util.Set;

import lombok.Data;


@Data
public class PostDTO {
	private long id;
	private String title;
	private String description;
	private String content;
	private Set<CommentDTO> comments;

	public String toString() {
		return "PostDTO{" +
				"id=" + id +
				", title='" + title + '\'' +
				", content='" + content + '\'' +
				", comments='" + comments + '\'' +
				'}';
	}
}
