package de.erdlet.blogging.blog.api;

import de.erdlet.blogging.blog.model.Comment;

import java.util.UUID;

public class CommentDTO {

	private final UUID id;
	private final String author;
	private final String content;
	private final String publishedAt;

	public CommentDTO(final Comment comment) {
		this.id = comment.getId();
		this.author = comment.getAuthor();
		this.content = comment.getContent();
		this.publishedAt = comment.getPublishedAt().format(OutputFormats.DATE_GERMAN);
	}

	public UUID getId() {
		return id;
	}

	public String getAuthor() {
		return author;
	}

	public String getContent() {
		return content;
	}

	public String getPublishedAt() {
		return publishedAt;
	}
}
