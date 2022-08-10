package de.erdlet.blogging.blog.api;

import jakarta.inject.Inject;
import jakarta.mvc.Models;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class InternalServerErrorController implements ExceptionMapper<Exception> {

	@Inject
	Models models;

	@Override
	public Response toResponse(final Exception exception) {
		models.put("msg", exception.getMessage());

		return Response.serverError().entity("500.jsp").build();
	}
}
