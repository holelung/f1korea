package kr.co.project.f1korea.dto;

import kr.co.project.f1korea.domain.Comment;
import kr.co.project.f1korea.domain.Post;

public class CommentDto {

    private Long postId; // 게시글 ID
    private Long userId; // 사용자 ID
    private String content; // 댓글 내용



    // 기본 생성자
    public CommentDto() {}

    // 모든 필드를 포함한 생성자
    public CommentDto(Long postId, Long userId, String content) {
        this.postId = postId;
        this.userId = userId;
        this.content = content;
    }

    // 게터와 세터
    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // toString, hashCode, equals 메서드 등이 필요할 수 있습니다.
}

