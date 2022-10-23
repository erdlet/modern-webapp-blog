package de.erdlet.blogging.blog.api.mvc;

import de.erdlet.blogging.blog.api.OutputFormats;
import de.erdlet.blogging.blog.model.Post;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

public class PostDTO {

	private final UUID id;
	private final String title;
	private final String content;
	private final String publishedAt;
	private final List<CommentDTO> comments;

	public PostDTO(final Post post) {
		Objects.requireNonNull(post, "post mustn't be null");

		id = post.getId();
		title = post.getTitle();
		content = post.getContent();
		publishedAt = post.getPublishedAt().format(OutputFormats.DATE_GERMAN);

		comments = post.getComments().stream().map(CommentDTO::new).collect(Collectors.toList());
	}

	private PostDTO(final UUID id, final String title, final String content) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.publishedAt = null;
		this.comments = List.of();
	}

	public static PostDTO fromEditForm(final UUID id, final PostForm form) {
		return new PostDTO(id, form.getTitle(), form.getContent());
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

	public String getPublishedAt() {
		return publishedAt;
	}

	public List<CommentDTO> getComments() {
		return comments;
	}
}
