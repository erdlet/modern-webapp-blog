package de.erdlet.blogging.blog.jpa;

import de.erdlet.blogging.blog.model.Comment;
import de.erdlet.blogging.blog.model.Post;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.h2.Driver;
import org.hibernate.dialect.H2Dialect;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Stream;

@ApplicationScoped
public class EntityManagerProducer {

	private static final String PERSISTENCE_UNIT = "blog";

	private EntityManagerFactory emf;

	//  UNCOMMENT TO USE persistence.xml
//    public void init() {
//        emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
//    }
	// COMMENT TO USE persistence.xml
	public void init() {
		final var properties = new Properties();
		properties.put("jakarta.persistence.jdbc.url", "jdbc:h2:mem:blog");
		properties.put("jakarta.persistence.jdbc.driver", Driver.class.getName());
		properties.put("jakarta.persistence.jdbc.user", "sa");
		properties.put("jakarta.persistence.jdbc.password", "");
		properties.put("hibernate.dialect", H2Dialect.class.getName());
		properties.put("jakarta.persistence.schema-generation.database.action", "drop-and-create");
		properties.put("hibernate.show_sql", true);

		final var persistenceUnitInfo =
				new CustomPersistenceUnitInfo(PERSISTENCE_UNIT, properties, getEntities());

		emf = new HibernatePersistenceProvider().createContainerEntityManagerFactory(
				persistenceUnitInfo, Map.of());
	}

	private List<String> getEntities() {
		return Stream.of(Post.class, Comment.class).map(Class::getName).toList();
	}

	@Produces
	@RequestScoped
	public EntityManager entityManager() {
		return emf.createEntityManager();
	}

	public void closeEntityManager(@Disposes EntityManager em) {
		if (em.isOpen()) {
			em.close();
		}
	}

	@PreDestroy
	void closeEntityManagerFactory() {
		emf.close();
	}
}
