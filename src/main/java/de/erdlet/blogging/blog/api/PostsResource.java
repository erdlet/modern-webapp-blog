package de.erdlet.blogging.blog.api;

import de.erdlet.blogging.blog.service.CommentService;
import de.erdlet.blogging.blog.service.PostService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.mvc.UriRef;
import jakarta.mvc.binding.BindingResult;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.UUID;

@Path("/posts")
@Controller
@RequestScoped
public class PostsResource {

	@Inject
	PostService postService;

	@Inject
	CommentService commentService;

	@Inject
	Models models;

	@Inject
	BindingResult bindingResult;

	@Inject
	RedirectedMessage redirectedMessage;

	@Inject
	CommentFormErrors commentFormErrors;

	@GET
	@UriRef("overview")
	public Response index() {
		models.put("posts", postService.findAll()
				.stream().map(PostDTO::new).toList());
		return Response.ok("posts.jsp").build();
	}

	@GET
	@UriRef("post")
	@Path("{id}")
	public Response show(@PathParam("id") final UUID id) {
		final var post = postService.findById(id);

		return post.map(p -> {
			models.put("post", new PostDTO(p));

			if (redirectedMessage.containsValue()) {
				models.put("commonMessage", redirectedMessage.getValue());
			}

			if (commentFormErrors.containsErrors()) {
				models.put("authorErrors", commentFormErrors.getAuthorErrors());
				models.put("contentErrors", commentFormErrors.getContentErrors());
			}

			return Response.ok("post.jsp").build();
		}).orElseGet(() -> Response.status(Response.Status.NOT_FOUND).entity("404.jsp").build());
	}

	@GET
	@Path("new")
	@UriRef("new-post")
	public Response newPost() {
		return Response.ok("createpost.jsp").build();
	}

	@POST
	@UriRef("create-post")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response create(@Valid @BeanParam final PostForm form) {
		if (bindingResult.isFailed()) {
			models.put("titleErrors", bindingResult.getErrors("title"));
			models.put("contentErrors", bindingResult.getErrors("content"));

			return Response.status(Response.Status.BAD_REQUEST).entity("createpost.jsp").build();
		}

		final var post = postService.create(form.getTitle(), form.getContent());
		final var location = URI.create("/posts/" + post.getId());

		return Response.created(location).entity("redirect:/posts/" + post.getId()).build();
	}

	@GET
	@UriRef("edit-post")
	@Path("{id}/edit")
	public Response edit(@PathParam("id") final UUID id) {
		final var post = postService.findById(id);

		return post.map(p -> {
			models.put("post", p);

			return Response.ok("editpost.jsp").build();
		}).orElseGet(() -> Response.status(Response.Status.NOT_FOUND).entity("404.jsp").build());
	}

	@PATCH
	@UriRef("update-post")
	@Path("{id}")
	public Response update(@PathParam("id") final UUID id, @Valid @BeanParam final PostForm form) {
		if (bindingResult.isFailed()) {
			models.put("titleErrors", bindingResult.getErrors("title"));
			models.put("contentErrors", bindingResult.getErrors("content"));
			models.put("post", PostDTO.fromEditForm(id, form));

			return Response.status(Response.Status.BAD_REQUEST).entity("editpost.jsp").build();
		}

		final var post = postService.update(id, form.getTitle(), form.getContent());

		return post.map(p -> Response.ok("redirect:/posts/" + id).build())
				.orElseGet(() -> Response.status(Response.Status.NOT_FOUND).entity("404.jsp").build());
	}

	@DELETE
	@UriRef("delete-post")
	@Path("{id}")
	public Response delete(@PathParam("id") final UUID id) {
		postService.delete(id);

		return Response.ok("redirect:/posts").build();
	}

	@POST
	@UriRef("create-comment")
	@Path("{id}/comments")
	public Response commentsResource(@PathParam("id") final UUID postId, @Valid @BeanParam CommentForm dto) {
		if (bindingResult.isFailed()) {
			commentFormErrors.setAuthorErrors(bindingResult.getErrors("author"));
			commentFormErrors.setContentErrors(bindingResult.getErrors("content"));

			return Response.status(Response.Status.BAD_REQUEST).entity("redirect:/posts/" + postId).build();
		}

		final var commentId = commentService.createInPost(postId, dto.getAuthor(), dto.getContent());

		if (commentId == null) {
			redirectedMessage.setValue("There was an error while processing the comment. Please try again later.");
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("post.jsp").build();
		} else {
			return Response.created(URI.create(postId + "/comments/" + commentId))
					.entity("redirect:/posts/" + postId).build();
		}
	}
}
