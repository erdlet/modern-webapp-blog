package de.erdlet.blogging.blog.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@NamedQueries({
		@NamedQuery(name = Post.FIND_ALL_QUERY, query = "select p from Post p order by publishedAt desc"),
		@NamedQuery(name = Post.FIND_BY_ID_QUERY, query = "select p from Post p where id = :id")
})
public class Post {

	public static final String FIND_ALL_QUERY = "Post.findAll";
	public static final String FIND_BY_ID_QUERY = "Post.findById";

	@Id
	private UUID id;

	private String title;

	private String content;

	private LocalDateTime publishedAt;

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Comment> comments;

	/**
	 * Default constructor for framework usage
	 */
	protected Post() {
	}

	/**
	 * Constructor for a new {@link Post} taking its name
	 */
	public Post(final String name, final String content, final LocalDateTime publishedAt) {
		this.id = UUID.randomUUID();
		this.title = name;
		this.content = content;
		this.publishedAt = publishedAt;
		this.comments = new ArrayList<>();
	}

	public UUID getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public List<Comment> getComments() {
		return List.copyOf(comments);
	}

	public LocalDateTime getPublishedAt() {
		return publishedAt;
	}

	public Comment addComment(String author, String content) {
		final var comment = new Comment(author, content, LocalDateTime.now(), this);
		comments.add(comment);

		return comment;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		final Post post = (Post) o;
		return Objects.equals(id, post.id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}

	@Override
	public String toString() {
		return "Post{" +
				"id=" + id +
				", title='" + title + '\'' +
				", content='" + content + '\'' +
				", publishedAt=" + publishedAt +
				'}';
	}
}
