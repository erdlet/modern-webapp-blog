package de.erdlet.blogging.blog.api;

import java.time.format.DateTimeFormatter;

public interface OutputFormats {

	DateTimeFormatter DATE_GERMAN = DateTimeFormatter.ofPattern("dd.MM.yyyy");
}
