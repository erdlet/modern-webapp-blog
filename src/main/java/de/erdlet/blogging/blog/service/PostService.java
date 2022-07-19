package de.erdlet.blogging.blog.service;

import de.erdlet.blogging.blog.api.PostDTO;
import de.erdlet.blogging.blog.model.Post;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    private List<Post> POSTS = new ArrayList<>();

    {
        POSTS.add(new Post("My first post!", "This is my first post", LocalDateTime.now()));
        POSTS.add(new Post("How to bake a cake", "Baking a cake is simple...", LocalDateTime.now().minusDays(3)));
    }

    public Optional<Post> findById(final UUID id) {
        return POSTS.stream().filter(post -> post.getId().equals(id)).findFirst();
    }

    public List<Post> findAll() {
        return List.copyOf(POSTS);
    }

    public Post create(final PostDTO dto) {
        final var post = new Post(dto.getTitle(), dto.getContent(), LocalDateTime.now());

        POSTS.add(post);

        return post;
    }
}
