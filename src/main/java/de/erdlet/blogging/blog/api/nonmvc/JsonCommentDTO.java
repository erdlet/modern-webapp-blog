package de.erdlet.blogging.blog.api.nonmvc;

import de.erdlet.blogging.blog.api.OutputFormats;
import de.erdlet.blogging.blog.model.Comment;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public class JsonCommentDTO {

	@JsonbProperty("comment_id")
	private UUID id;

	@JsonbProperty("comment_author")
	@NotBlank(message = "{JsonComment.author.NotBlank}")
	@Size(min = 2, max = 256, message = "{JsonComment.author.Size}")
	private String author;

	@JsonbProperty("comment_content")
	@NotBlank(message = "{JsonComment.content.NotBlank}")
	@Size(min = 10, max = 256, message = "{JsonComment.content.Size}")
	private String content;

	@JsonbProperty("comment_publish_date")
	private String publishedAt;

	public JsonCommentDTO() {
	}

	public JsonCommentDTO(final Comment comment) {
		this.id = comment.getId();
		this.author = comment.getAuthor();
		this.content = comment.getContent();
		this.publishedAt = comment.getPublishedAt().format(OutputFormats.DATE_GERMAN);
	}

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
	}

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

	public String getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(final String publishedAt) {
		this.publishedAt = publishedAt;
	}

	@Override
	public String toString() {
		return "JsonComment{" +
				"id=" + id +
				", author='" + author + '\'' +
				", content='" + content + '\'' +
				", publishedAt='" + publishedAt + '\'' +
				'}';
	}
}
