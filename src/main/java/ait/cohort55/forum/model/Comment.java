package ait.cohort55.forum.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private String user;
    private String message;
    private LocalDateTime dateCreated;
    private int likes;
}

