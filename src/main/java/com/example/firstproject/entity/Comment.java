package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    private Article article;

    @Column
    private  String nickname;

    @Column
    private String body;

}
