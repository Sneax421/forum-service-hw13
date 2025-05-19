package ait.cohort55.forum.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostAddDto {
    private String title;
    private String content;
    private List<String> tags;
}
