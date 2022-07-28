package de.erdlet.blogging.blog.service;

import de.erdlet.blogging.blog.api.PostDTO;
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
		try {
			em.getTransaction().begin();

			final var post = em.createNamedQuery(Post.FIND_BY_ID_QUERY, Post.class).setParameter("id", id).getResultList().stream().findFirst();

			em.getTransaction().commit();
			return post;
		} catch (Exception ex) {
			em.getTransaction().rollback();

			return Optional.empty();
		}
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

	public Post create(final PostDTO dto) {
		final var post = new Post(dto.getTitle(), dto.getContent(), LocalDateTime.now());

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
}
