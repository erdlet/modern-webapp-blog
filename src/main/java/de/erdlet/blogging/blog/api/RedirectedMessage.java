package de.erdlet.blogging.blog.api;

import jakarta.mvc.RedirectScoped;

import java.io.Serializable;

@RedirectScoped
public class RedirectedMessage implements Serializable {

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(final String value) {
		this.value = value;
	}

	public boolean containsValue() {
		return value != null && !value.isBlank();
	}
}
