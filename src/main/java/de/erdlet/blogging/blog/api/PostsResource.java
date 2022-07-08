package de.erdlet.blogging.blog.api;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("posts")
public class PostsResource {

    @GET
    public Response index() {
        return Response.ok("Posts resource index").build();
    }
}
