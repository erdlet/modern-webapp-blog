package de.erdlet.blogging.blog.service;

import de.erdlet.blogging.blog.model.Post;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class CommentService {

	@Inject
	EntityManager em;

	public UUID createInPost(final UUID postId, final String author, final String content) {
		Objects.requireNonNull(postId, "postId mustn't be null");
		Objects.requireNonNull(author, "author mustn't be null");
		Objects.requireNonNull(content, "content mustn't be null");

		try {
			em.getTransaction().begin();

			final var post = Optional.ofNullable(em.find(Post.class, postId))
					.orElseThrow(() -> new IllegalStateException("Expected post with ID " + postId + " to exist"));

			final var comment = post.addComment(author, content);

			em.persist(comment);

			em.getTransaction().commit();

			return comment.getId();
		} catch (final Exception ex) {
			em.getTransaction().rollback();

			return null;
		}
	}
}
