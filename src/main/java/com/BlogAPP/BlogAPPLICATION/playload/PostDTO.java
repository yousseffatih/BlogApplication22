package com.BlogAPP.BlogAPPLICATION.playload;

import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class PostDTO {
	private long id;

	@NotEmpty
	@Size(min = 2 ,message = "Post title should have at least 2 caracters.")
	private String title;

	@NotEmpty
	@Size(min = 10 ,message = "Post title should have at least 10 caracters.")
	private String description;
	@NotEmpty
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
