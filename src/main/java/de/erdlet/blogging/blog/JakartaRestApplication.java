package de.erdlet.blogging.blog;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.glassfish.jersey.server.ServerProperties;

import java.util.Map;

@ApplicationPath("")
public class JakartaRestApplication extends Application {

    @Override
    public Map<String, Object> getProperties() {
        return Map.of(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
    }
}
