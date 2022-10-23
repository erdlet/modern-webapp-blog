package de.erdlet.blogging.blog.api.nonmvc;

import de.erdlet.blogging.blog.api.OutputFormats;
import de.erdlet.blogging.blog.model.Post;
import jakarta.json.bind.annotation.JsonbNillable;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Post transfer object which is used to handle the application/json format.
 */
public class JsonPostDTO {
	@JsonbProperty("post_id")
	private UUID id;

	@JsonbProperty("post_title")
	@NotBlank(message = "{JsonPost.title.NotBlank}")
	@Size(min = 10, max = 256, message = "{JsonPost.title.Size}")
	private String title;

	@JsonbProperty("post_content")
	@JsonbNillable(false)
	@NotBlank(message = "{JsonPost.content.NotBlank}")
	private String content;

	@JsonbProperty("post_publish_date")
	private String publishedAt;

	@JsonbProperty("post_comments")
	private List<JsonCommentDTO> comments;

	public JsonPostDTO() {
	}

	public JsonPostDTO(final Post post) {
		Objects.requireNonNull(post, "post mustn't be null");

		id = post.getId();
		title = post.getTitle();
		content = post.getContent();
		publishedAt = post.getPublishedAt().format(OutputFormats.DATE_GERMAN);

		comments = post.getComments().stream().map(JsonCommentDTO::new).collect(Collectors.toList());
	}

	public UUID getId() {
		return id;
	}

	public void setId(final UUID id) {
		this.id = id;
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

	public String getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(final String publishedAt) {
		this.publishedAt = publishedAt;
	}

	public List<JsonCommentDTO> getComments() {
		return comments;
	}

	public void setComments(final List<JsonCommentDTO> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "JsonPost{" +
				"id=" + id +
				", title='" + title + '\'' +
				", content='" + content + '\'' +
				'}';
	}
}
