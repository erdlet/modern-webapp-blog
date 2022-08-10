package de.erdlet.blogging.blog.service;

import de.erdlet.blogging.blog.model.Post;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Singleton service class working on {@link Post} entities.
 * <br><br>
 * <b>Attention:</b> This class won't be covered in detail, as this
 * helps only to keep the example running.
 */
@ApplicationScoped
public class PostService {

	@Inject
	EntityManager em;

	public Optional<Post> findById(final UUID id) {
		return findByIdWithQuery(Post.FIND_BY_ID_QUERY, id);
	}

	public Optional<Post> findWithComments(final UUID id) {
		return findByIdWithQuery(Post.FIND_WITH_COMMENTS, id);
	}

	public List<Post> findAll() {
		try {
			em.getTransaction().begin();

			final var posts = List.copyOf(em.createNamedQuery(Post.FIND_ALL_QUERY, Post.class).getResultList());

			em.getTransaction().commit();

			return posts;
		} catch (Exception ex) {
			em.getTransaction().rollback();

			return List.of();
		}
	}

	public Post create(final String title, final String content) {
		final var post = new Post(title, content, LocalDateTime.now());

		try {
			em.getTransaction().begin();

			em.persist(post);

			em.getTransaction().commit();

			return post;
		} catch (Exception ex) {
			em.getTransaction().rollback();

			throw ex;
		}
	}

	public Optional<Post> update(final UUID id, final String title, final String content) {
		try {
			em.getTransaction().begin();

			final var result = em.createNamedQuery(Post.FIND_BY_ID_QUERY, Post.class)
					.setParameter("id", id)
					.getResultList()
					.stream()
					.findFirst()
					.map(post -> post.update(title, content))
					.map(post -> em.merge(post));

			em.getTransaction().commit();

			return result;
		} catch (final Exception ex) {
			em.getTransaction().rollback();

			throw ex;
		}
	}

	private Optional<Post> findByIdWithQuery(final String query, final UUID id) {
		try {
			em.getTransaction().begin();

			final var post = em.createNamedQuery(query, Post.class).setParameter("id", id).getResultList().stream().findFirst();

			em.getTransaction().commit();
			return post;
		} catch (Exception ex) {
			em.getTransaction().rollback();

			return Optional.empty();
		}
	}

	public void delete(final UUID id) {
		try {
			em.getTransaction().begin();

			em.createNamedQuery(Post.FIND_BY_ID_QUERY, Post.class).setParameter("id", id)
					.getResultList()
					.stream()
					.findFirst()
					.ifPresent(post -> em.remove(post));

			em.getTransaction().commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
		}
	}
}
