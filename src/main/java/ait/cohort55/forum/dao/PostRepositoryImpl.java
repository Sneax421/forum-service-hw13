package ait.cohort55.forum.dao;

import ait.cohort55.forum.model.Post;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class PostRepositoryImpl implements PostRepository {

    private Map<String, Post> posts = new ConcurrentHashMap<>();

    @Override
    public List<Post> findByAuthor(String author) {
        return posts.values().stream()
                .filter(post -> post.getAuthor().equals(author))
                .toList();
    }

    @Override
    public Post save(Post post) {
        posts.put(post.getId(), post);
        return post;
    }

    @Override
    public Optional<Post> findByIdPost(String id) {
        return Optional.ofNullable(posts.get(id));
    }

    @Override
    public void deleteByIdPost(String id) {
        posts.remove(id);
    }

    @Override
    public Iterable<Post> findAll() {
        return posts.values();
    }
}
