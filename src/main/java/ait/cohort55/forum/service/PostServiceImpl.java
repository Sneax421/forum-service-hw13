package ait.cohort55.forum.service;

import ait.cohort55.forum.dao.PostRepository;
import ait.cohort55.forum.dto.CommentDto;
import ait.cohort55.forum.dto.PostAddDto;
import ait.cohort55.forum.dto.PostDto;
import ait.cohort55.forum.dto.exeptions.PostNotFoundException;
import ait.cohort55.forum.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public PostDto addPost(PostAddDto postAddDto) {
        Post post = new Post(null, postAddDto.getTitle(), postAddDto.getContent(), postAddDto.getTags());
        Post saved = postRepository.save(post);

        return new PostDto(
                saved.getId(),
                saved.getTitle(),
                saved.getContent(),
                saved.getAuthor(),
                saved.getDateCreated(),
                saved.getTags(),
                saved.getLikes(),
                saved.getComments()
        );
    }

    @Override
    public PostDto findPostById(String id) {
        Post post = postRepository.findByIdPost(id).orElseThrow(PostNotFoundException::new);
        return new PostDto(post.getId(), post.getTitle(), post.getContent(), post.getAuthor(), post.getDateCreated(), post.getTags(), post.getLikes(), post.getComments());
    }

    @Override
    public PostDto removePostById(String id) {
        Post post = postRepository.findByIdPost(id).orElseThrow(PostNotFoundException::new);
        postRepository.deleteByIdPost(id);
        return new PostDto(post.getId(), post.getTitle(), post.getContent(), post.getAuthor(), post.getDateCreated(), post.getTags(), post.getLikes(), post.getComments());

    }

    @Override
    public PostDto updatePost(String id, PostAddDto postAddDto) {
        Post post = postRepository.findByIdPost(id).orElseThrow(PostNotFoundException::new);
        if(postAddDto.getTitle() != null) {
            post.setTitle(postAddDto.getTitle());
        }
        if(postAddDto.getContent() != null) {
            post.setContent(postAddDto.getContent());
        }
        if(postAddDto.getTags() != null) {
            post.setTags(postAddDto.getTags());
        }
        postRepository.save(post);
        return new PostDto(post.getId(), post.getTitle(), post.getContent(), post.getAuthor(), post.getDateCreated(), post.getTags(), post.getLikes(), post.getComments());

    }

    @Override
    public Integer addLike(PostDto addLike) {
        Post post = postRepository.findByIdPost(addLike.getId()).orElseThrow(PostNotFoundException::new);
        if(post != null) {
            post.addLike();
            postRepository.save(post);
            return post.getLikes();
        }
        return 0;
    }

    @Override
    public PostDto addComment(String id, CommentDto comment) {
        Post post = postRepository.findByIdPost(id).orElseThrow(PostNotFoundException::new);
        if(post != null) {
            post.addComment(comment.getMessage());
            Post saved = postRepository.save(post);
            return new PostDto(
                    saved.getId(),
                    saved.getTitle(),
                    saved.getContent(),
                    saved.getAuthor(),
                    saved.getDateCreated(),
                    saved.getTags(),
                    saved.getLikes(),
                    saved.getComments()
            );
        }
        return null;
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
                        p.getComments()
                ))
                .toList();
    }

    @Override
    public List<PostDto> findPostsByTags(List<String> tags) {
        return StreamSupport.stream(postRepository.findAll().spliterator(), false)
                .filter(t -> tags.contains(t.getTags()))
                .map(t-> new PostDto(
                        t.getId(),
                        t.getTitle(),
                        t.getContent(),
                        t.getAuthor(),
                        t.getDateCreated(),
                        t.getTags(),
                        t.getLikes(),
                        t.getComments()
                ))
                .toList();
    }

    @Override
    public List<PostDto> findPostsByPeriod(LocalDate dateFrom, LocalDate dateTo) {
        return List.of();
    }
}
