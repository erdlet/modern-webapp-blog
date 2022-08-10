package de.erdlet.blogging.blog.api;

import jakarta.mvc.RedirectScoped;
import jakarta.mvc.binding.ParamError;

import java.io.Serializable;
import java.util.Set;

@RedirectScoped
public class CommentFormErrors implements Serializable {

	private Set<ParamError> authorErrors;
	private Set<ParamError> contentErrors;

	public CommentFormErrors() {
	}

	public CommentFormErrors(final Set<ParamError> authorErrors, final Set<ParamError> contentErrors) {
		this.authorErrors = authorErrors;
		this.contentErrors = contentErrors;
	}

	public Set<ParamError> getAuthorErrors() {
		return authorErrors;
	}

	public void setAuthorErrors(final Set<ParamError> authorErrors) {
		this.authorErrors = authorErrors;
	}

	public Set<ParamError> getContentErrors() {
		return contentErrors;
	}

	public void setContentErrors(final Set<ParamError> contentErrors) {
		this.contentErrors = contentErrors;
	}

	public boolean containsErrors() {
		return (authorErrors != null && !authorErrors.isEmpty()) || (contentErrors != null && !contentErrors.isEmpty());
	}
}
