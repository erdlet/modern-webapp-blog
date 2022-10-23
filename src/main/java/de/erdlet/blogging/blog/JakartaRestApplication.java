package de.erdlet.blogging.blog;

import jakarta.mvc.security.Csrf;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.eclipse.krazo.Properties;
import org.glassfish.jersey.server.ServerProperties;

import java.util.HashMap;
import java.util.Map;

@ApplicationPath("")
public class JakartaRestApplication extends Application {

	@Override
	public Map<String, Object> getProperties() {
		final Map<String, Object> values = new HashMap<>();
		values.put(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

		// Enable implicit CSRF protection. This means every writing action (POST, PUT, PATCH, DELETE)
		// is required to contain a CSRF token.
		values.put(Csrf.CSRF_PROTECTION, Csrf.CsrfOptions.IMPLICIT);

		// Enable Cookie to store redirect scope token
		values.put(Properties.REDIRECT_SCOPE_COOKIES, true);

		return values;
	}
}
