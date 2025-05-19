package ait.cohort55.forum.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Document(collection = "posts")
public class Post {
    @Id
    private String id;
    @Setter
    private String title;
    @Setter
    private String content;
    @Setter
    private String author;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateCreated;
    @Setter
    private List<String> tags;
    @Setter
    private Integer likes = 0;
    @Setter
    private List<String> comments = new ArrayList<>();

    public Post(String id, String title, String content, List<String> tags) {
        this.id = id;
        this.title = title;
        this.content = content;
        dateCreated = LocalDateTime.now();
        this.tags = tags;
    }
    public void addLike() {
        likes++;
    }

    public void addComment(String comment) {
        comments.add(comment);
    }
}
