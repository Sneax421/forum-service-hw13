package ait.cohort55.forum.service;

import ait.cohort55.forum.dao.PostRepository;
import ait.cohort55.forum.dto.CommentDto;
import ait.cohort55.forum.dto.PostAddDto;
import ait.cohort55.forum.dto.PostDto;
import ait.cohort55.forum.dto.exeptions.PostNotFoundException;
import ait.cohort55.forum.model.Comment;
import ait.cohort55.forum.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;


    @Override
    public PostDto addPost(PostAddDto postAddDto) {
        Post post = new Post(null, postAddDto.getTitle(), postAddDto.getContent(), postAddDto.getTags());
        Post saved = postRepository.save(post);

        List<CommentDto> comment1 = saved.getComments().stream()
                .map(c -> new CommentDto(
                        c.getUser(),
                        c.getMessage(),
                        c.getDateCreated(),
                        c.getLikes()
                ))
                .toList();

        return new PostDto(
                saved.getId(),
                saved.getTitle(),
                saved.getContent(),
                saved.getAuthor(),
                saved.getDateCreated(),
                saved.getTags(),
                saved.getLikes(),
                comment1
        );
    }

    @Override
    public PostDto findPostById(String id) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        return new PostDto(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getAuthor(),
                post.getDateCreated(),
                post.getTags(),
                post.getLikes(),
                post.getComments().stream()
                        .map(c -> new CommentDto(c.getUser(), c.getMessage(), c.getDateCreated(), c.getLikes()))
                        .collect(Collectors.toList())
        );
    }


    @Override
    public Integer addLike(String postId) {
        Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
        post.addLike();
        postRepository.save(post);
        return post.getLikes();
    }

    @Override
    public PostDto removePostById(String id) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        postRepository.deleteById(id);
        return new PostDto(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getAuthor(),
                post.getDateCreated(),
                post.getTags(),
                post.getLikes(),
                post.getComments().stream()
                        .map(c -> new CommentDto(c.getUser(), c.getMessage(), c.getDateCreated(), c.getLikes()))
                        .collect(Collectors.toList())
        );
    }

    @Override
    public PostDto updatePost(String id, PostAddDto postAddDto) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        if (postAddDto.getTitle() != null) {
            post.setTitle(postAddDto.getTitle());
        }
        if (postAddDto.getContent() != null) {
            post.setContent(postAddDto.getContent());
        }
        if (postAddDto.getTags() != null) {
            post.setTags(postAddDto.getTags());
        }
        postRepository.save(post);
        return new PostDto(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getAuthor(),
                post.getDateCreated(),
                post.getTags(),
                post.getLikes(),
                post.getComments().stream()
                        .map(c -> new CommentDto(c.getUser(), c.getMessage(), c.getDateCreated(), c.getLikes()))
                        .collect(Collectors.toList())
        );
    }

    @Override
    public PostDto addComment(String id, CommentDto comment) {
        Post post = postRepository.findById(id).orElseThrow(PostNotFoundException::new);
        Comment comment1 = new Comment(
                comment.getUser(),
                comment.getMessage(),
                LocalDateTime.now(),
                0
        );
        post.addComment(comment1);
        Post saved = postRepository.save(post);
        return new PostDto(
                saved.getId(),
                saved.getTitle(),
                saved.getContent(),
                saved.getAuthor(),
                saved.getDateCreated(),
                saved.getTags(),
                saved.getLikes(),
                saved.getComments().stream()
                        .map(c -> new CommentDto(c.getUser(), c.getMessage(), c.getDateCreated(), c.getLikes()))
                        .collect(Collectors.toList())
        );
    }

    @Override
    public List<PostDto> findPostByAuthor(String author) {
        List<Post> posts = postRepository.findByAuthor(author);
        return posts.stream()
                .map(p -> new PostDto(
                        p.getId(),
                        p.getTitle(),
                        p.getContent(),
                        p.getAuthor(),
                        p.getDateCreated(),
                        p.getTags(),
                        p.getLikes(),
                        p.getComments().stream()
                                .map(c -> new CommentDto(c.getUser(), c.getMessage(), c.getDateCreated(), c.getLikes()))
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> findPostsByTags(List<String> tags) {
        return StreamSupport.stream(postRepository.findAll().spliterator(), false)
                .filter(t -> t.getTags().stream().anyMatch(tags::contains))
                .map(t -> new PostDto(
                        t.getId(),
                        t.getTitle(),
                        t.getContent(),
                        t.getAuthor(),
                        t.getDateCreated(),
                        t.getTags(),
                        t.getLikes(),
                        t.getComments().stream()
                                .map(c -> new CommentDto(c.getUser(), c.getMessage(), c.getDateCreated(), c.getLikes()))
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }


    @Override
    public List<PostDto> findPostsByPeriod(LocalDate dateFrom, LocalDate dateTo) {
        return List.of();
    }
}
