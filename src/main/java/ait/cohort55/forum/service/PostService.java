package ait.cohort55.forum.service;

import ait.cohort55.forum.dto.CommentDto;
import ait.cohort55.forum.dto.PostAddDto;
import ait.cohort55.forum.dto.PostDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PostService {

    PostDto addPost(PostAddDto postAddDto);

    PostDto findPostById(String id);

    PostDto removePostById(String id);

    PostDto updatePost(String id, PostAddDto postAddDto);

    Integer addLike(PostDto addLike);

    PostDto addComment(String id, CommentDto comment);

    List<PostDto> findPostByAuthor(String author);

    List<PostDto> findPostsByTags(List<String> tags);

    List<PostDto> findPostsByPeriod(LocalDate dateFrom, LocalDate dateTo);

}
