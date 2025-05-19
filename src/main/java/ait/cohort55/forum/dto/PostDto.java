package ait.cohort55.forum.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

// Find post
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private String id;
    private String title;
    private String content;
    private String author;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateCreated;
    private List<String> tags;
    private Integer likes;
    private List<String> comments;
}
