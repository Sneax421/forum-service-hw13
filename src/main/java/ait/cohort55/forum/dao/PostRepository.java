package ait.cohort55.forum.dao;

import ait.cohort55.forum.model.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface PostRepository {

//    Stream<Post> findByAuthorIgnoreCase(String author);

    List<Post> findByAuthor(String author);

    Post save(Post post);

    Optional<Post> findByIdPost(String id);

    void deleteByIdPost(String id);

    Iterable<Post> findAll();

}
