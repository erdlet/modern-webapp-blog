package de.erdlet.blogging.blog.api.nonmvc;

import de.erdlet.blogging.blog.service.CommentService;
import de.erdlet.blogging.blog.service.PostService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.UUID;

@RequestScoped
public class PostsResourceNonMvcDelegate {

	@Inject
	PostService postService;

	@Inject
	CommentService commentService;

	public Response handleIndex() {
		return Response.ok(postService.findAll()
				.stream().map(JsonPostDTO::new).toList()).build();
	}

	public Response handleShow(final UUID id) {
		final var post = postService.findById(id);

		return post.map(p -> Response.ok(new JsonPostDTO(p)).build())
				.orElseGet(() -> Response.status(Response.Status.NOT_FOUND).build());
	}

	public Response handleCreate(final JsonPostDTO dto) {
		final var post = postService.create(dto.getTitle(), dto.getContent());
		final var location = URI.create("/posts/" + post.getId());

		return Response.created(location).build();
	}

	public Response handleUpdate(final UUID id, final @Valid JsonPostDTO form) {
		final var post = postService.update(id, form.getTitle(), form.getContent());

		return post.map(p -> Response.noContent().location(URI.create("/posts/" + id)).build())
				.orElseGet(() -> Response.status(Response.Status.NOT_FOUND).build());
	}

	public Response handleDelete(final UUID id) {
		postService.delete(id);

		return Response.noContent().build();
	}

	public Response handleCreateComment(final UUID postId, final @Valid JsonCommentDTO dto) {
		final var commentId = commentService.createInPost(postId, dto.getAuthor(), dto.getContent());

		if (commentId == null) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		} else {
			return Response.created(URI.create(postId + "/comments/" + commentId)).build();
		}
	}
}
