package com.example.firstproject.entity;

import com.example.firstproject.dto.CommentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // 해당 댓글 엔티티 여러 개가 하나의 article에 연결된다.
    @JoinColumn(name = "article_id") // 외래 키를 생성하고 Article 엔티티의 기본키 id와 매핑한다.
    @OnDelete(action = OnDeleteAction.CASCADE) // 게시글이 삭제되면 달린 댓글도 함께 삭제된다.
    private Article article;

    @Column
    private  String nickname;

    @Column
    private String body;

    public static Comment createComment(CommentDto dto, Article article) {
        // 예외 처리
        if (dto.getId() != null)
            throw new IllegalArgumentException("댓글 생성 실패! : 댓글의 id가 업성야 합니다.");

        if (!article.getId().equals(dto.getArticleId()))
            throw new IllegalArgumentException("댓글 생성 실패! : 댓글의 id가 업성야 합니다.");
        // 엔티티 생성 및 반환
        return new Comment(
                dto.getId(),
                article,
                dto.getNickname(),
                dto.getBody()

        );
    }

    public void patch(CommentDto dto) {
        // 예외 발생 : url의 id와 json(dto) 데이터의 id가 다른 경우
        if(!this.id.equals(dto.getId()))
            throw new IllegalArgumentException("댓글 수정 실패! : 잘못된 id가 입력되었습니다.");
        //객체 갱신
        if (dto.getNickname() != null)
            this.nickname = dto.getNickname();
        if(dto.getBody() != null)
            this.body = dto.getBody();
    }
}
