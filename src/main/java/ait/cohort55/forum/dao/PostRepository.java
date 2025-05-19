package ait.cohort55.forum.dao;

import ait.cohort55.forum.model.Post;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public interface PostRepository extends CrudRepository<Post, String> {

//    Stream<Post> findByAuthorIgnoreCase(String author);

    List<Post> findByAuthor(String author);

}
