package de.erdlet.blogging.blog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Comment {

	@Id
	private UUID id;

	private String author;

	private String content;

	private LocalDateTime publishedAt;

	@ManyToOne(fetch = FetchType.LAZY)
	private Post post;

	/**
	 * Default constructor for framework usage
	 */
	protected Comment() {
	}

	/**
	 * Constructor for a new {@link Comment}
	 */
	public Comment(final String author, final String content, final LocalDateTime publishedAt, final Post post) {
		this.id = UUID.randomUUID();
		this.author = author;
		this.content = content;
		this.publishedAt = publishedAt;
		this.post = post;
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

	public LocalDateTime getPublishedAt() {
		return publishedAt;
	}

	public Post getPost() {
		return post;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		final Comment group = (Comment) o;
		return Objects.equals(id, group.id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}

	@Override
	public String toString() {
		return "Comment{" +
				"id=" + id +
				", author='" + author + '\'' +
				", content='" + content + '\'' +
				", publishedAt=" + publishedAt +
				", post=" + post +
				'}';
	}
}
