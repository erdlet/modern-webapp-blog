package de.erdlet.blogging.blog.api;

import jakarta.mvc.binding.MvcBinding;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.ws.rs.FormParam;

public class CommentForm {

	@FormParam("author")
	@NotBlank(message = "{CommentForm.author.NotBlank}")
	@Size(min = 2, max = 256, message = "{CommentForm.author.Size}")
	@MvcBinding
	private String author;

	@FormParam("content")
	@NotBlank(message = "{CommentForm.content.NotBlank}")
	@Size(min = 10, max = 256, message = "{CommentForm.content.Size}")
	@MvcBinding
	private String content;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(final String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(final String content) {
		this.content = content;
	}
}
