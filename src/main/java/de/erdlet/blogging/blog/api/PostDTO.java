package de.erdlet.blogging.blog.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.ws.rs.FormParam;

public class PostDTO {

	@FormParam("title")
	@NotBlank(message = "{PostDTO.title.NotBlank}")
	@Size(min = 10, max = 256, message = "{PostDTO.title.Size}")
	private String title;

	@FormParam("content")
	@NotBlank(message = "{PostDTO.content.NotBlank}")
	private String content;

	public PostDTO() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(final String content) {
		this.content = content;
	}
}
