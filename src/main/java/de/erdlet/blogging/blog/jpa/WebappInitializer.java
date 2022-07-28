package de.erdlet.blogging.blog.jpa;

import jakarta.inject.Inject;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class WebappInitializer implements ServletContextListener {

	@Inject
	EntityManagerProducer entityManagerProducer;

	@Override
	public void contextInitialized(final ServletContextEvent sce) {
		entityManagerProducer.init();
	}
}
