package de.erdlet.blogging.blog.api;

import de.erdlet.blogging.blog.service.PostService;
import jakarta.validation.Valid;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;

@Path("posts")
public class PostsResource {

    @GET
    public Response index() {
        return Response.ok("Posts resource index").build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response create(@Valid @BeanParam final PostDTO dto) {
        final var post = PostService.getInstance().create(dto);
        final var location = URI.create("/blogging-app/posts/" + post.getId());

        return Response.created(location).build();
    }
}
