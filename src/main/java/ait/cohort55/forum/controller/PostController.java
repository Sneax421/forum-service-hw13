package ait.cohort55.forum.controller;

import ait.cohort55.forum.dto.CommentDto;
import ait.cohort55.forum.dto.PostAddDto;
import ait.cohort55.forum.dto.PostDto;
import ait.cohort55.forum.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {


    private final PostService postService;

    @PostMapping("/forum/post/{user}")
    public PostDto addPost(@PathVariable String user, @RequestBody PostAddDto postAddDto) {
        return postService.addPost(postAddDto);
    }

    @GetMapping("/forum/post/{postId}")
    public PostDto findPostById(@PathVariable String postId) {
        return postService.findPostById(postId);
    }

    @DeleteMapping("/forum/post/{postId}")
    public PostDto removePostById(@PathVariable String postId) {
        return postService.removePostById(postId);
    }

    @PatchMapping("/forum/post/{postId}")
    public PostDto updatePost(@PathVariable String postId, @RequestBody PostAddDto postAddDto) {
        return postService.updatePost(postId, postAddDto);
    }

    @PatchMapping("/forum/post/{postId}/like")
    public Integer addLike(@PathVariable String postId, @RequestBody PostDto like) {
        return postService.addLike(like);
    }

    @PatchMapping("/forum/post/{postId}/comment/{commenter}")
    public PostDto addComment(@PathVariable String postId, @PathVariable CommentDto commenter) {
        return postService.addComment(postId, commenter);
    }

    @GetMapping("/forum/posts/author/{user}")
    public List<PostDto> findPostByAuthor(@PathVariable String user) {
        return postService.findPostByAuthor(user);
    }

    @GetMapping("/forum/posts/tags")
    public List<PostDto> findPostsByTags(@RequestParam List<String> values) {
        return postService.findPostsByTags(values);
    }

    @GetMapping("/forum/posts/period")
    public List<PostDto> findPostsByPeriod(
            @RequestParam("dateFrom") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
            @RequestParam("dateTo") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo) {

        return postService.findPostsByPeriod(dateFrom, dateTo);
    }

}
