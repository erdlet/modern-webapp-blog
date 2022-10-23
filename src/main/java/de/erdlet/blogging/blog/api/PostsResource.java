package de.erdlet.blogging.blog.api;

import de.erdlet.blogging.blog.api.mvc.CommentForm;
import de.erdlet.blogging.blog.api.mvc.PostForm;
import de.erdlet.blogging.blog.api.mvc.PostsResourceMvcDelegate;
import de.erdlet.blogging.blog.api.nonmvc.JsonCommentDTO;
import de.erdlet.blogging.blog.api.nonmvc.JsonPostDTO;
import de.erdlet.blogging.blog.api.nonmvc.PostsResourceNonMvcDelegate;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.UriRef;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.UUID;

@Path("/posts")
@RequestScoped
public class PostsResource {

	@Inject
	PostsResourceMvcDelegate mvcDelegate;

	@Inject
	PostsResourceNonMvcDelegate nonMvcDelegate;

	@GET
	@Controller
	@UriRef("overview")
	@Produces(MediaType.TEXT_HTML)
	public Response indexMvc() {
		return mvcDelegate.handleIndex();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response indexApi() {
		return nonMvcDelegate.handleIndex();
	}

	@GET
	@Controller
	@UriRef("post")
	@Path("{id}")
	@Produces(MediaType.TEXT_HTML)
	public Response showMvc(@PathParam("id") final UUID id) {
		return mvcDelegate.handleShow(id);
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response showApi(@PathParam("id") final UUID id) {
		return nonMvcDelegate.handleShow(id);
	}

	@GET
	@Controller
	@Path("new")
	@UriRef("new-post")
	@Produces(MediaType.TEXT_HTML)
	public Response newPost() {
		return mvcDelegate.handleNew();
	}

	@POST
	@Controller
	@UriRef("create-post")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response createMvc(@Valid @BeanParam final PostForm form) {
		return mvcDelegate.handleCreate(form);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createApi(@Valid final JsonPostDTO dto) {
		return nonMvcDelegate.handleCreate(dto);
	}

	@GET
	@Controller
	@UriRef("edit-post")
	@Path("{id}/edit")
	@Produces(MediaType.TEXT_HTML)
	public Response edit(@PathParam("id") final UUID id) {
		return mvcDelegate.handleEdit(id);
	}

	@PATCH
	@Controller
	@UriRef("update-post")
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public Response updateMvc(@PathParam("id") final UUID id, @Valid @BeanParam final PostForm form) {
		return mvcDelegate.handleUpdate(id, form);
	}

	@PATCH
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateApi(@PathParam("id") final UUID id, @Valid final JsonPostDTO dto) {
		return nonMvcDelegate.handleUpdate(id, dto);
	}

	@DELETE
	@Controller
	@UriRef("delete-post")
	@Path("{id}")
	@Produces(MediaType.TEXT_HTML)
	public Response deleteMvc(@PathParam("id") final UUID id) {
		return mvcDelegate.handleDelete(id);
	}

	@DELETE
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteApi(@PathParam("id") final UUID id) {
		return nonMvcDelegate.handleDelete(id);
	}

	@POST
	@Controller
	@UriRef("create-comment")
	@Path("{id}/comments")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public Response createCommentMvc(@PathParam("id") final UUID postId, @Valid @BeanParam CommentForm form) {
		return mvcDelegate.handleCreateComment(postId, form);
	}

	@POST
	@Path("{id}/comments")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createCommentApi(@PathParam("id") final UUID postId, @Valid JsonCommentDTO dto) {
		return nonMvcDelegate.handleCreateComment(postId, dto);
	}
}
