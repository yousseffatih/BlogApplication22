package com.BlogAPP.BlogAPPLICATION.playload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDTO {
	private long id;
	@NotEmpty(message = "Name should be null or empty")
	private String name;
	@NotEmpty(message = "Email should be null or empty")
	@Email
	private String email;
	@NotEmpty
	@Size(min=10 , message = "Comment body must have at least 10 caracters")
	private String body;
}
