package kr.co.project.f1korea.dto;

import kr.co.project.f1korea.domain.Post;
import kr.co.project.f1korea.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddPostRequest {
    private String title;

    private String content;

    public Post toEntity(){
        return Post.builder()
                .title(title)
                .content(content).build();
    }

}
