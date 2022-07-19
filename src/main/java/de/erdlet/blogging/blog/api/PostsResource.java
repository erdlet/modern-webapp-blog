package de.erdlet.blogging.blog.api;

import de.erdlet.blogging.blog.model.Post;
import de.erdlet.blogging.blog.service.PostService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Path("posts")
@RequestScoped
public class PostsResource {

    @Inject
    PostService postService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response index() {
        return Response.ok(toTextResult(postService.findAll())).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response create(@Valid @BeanParam final PostDTO dto) {
        final var post = postService.create(dto);
        final var location = URI.create("/blogging-app/posts/" + post.getId());

        return Response.created(location).build();
    }

    // Helper until Content Negotiation is introduced
    private String toTextResult(final List<Post> posts) {
        return posts.stream().map(Post::toString).collect(Collectors.joining(", "));
    }
}
