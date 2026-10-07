package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity // DB가 해당 객체를 인식 가능!!
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Article {

    @Id // 대표값을 지정, primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 id 자동 생성
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    public void patch(Article article) {
        if (article.title != null) {
            this.title = article.title;
        }
        if (article.content != null) {
            this.content = article.content;
        }
    }
}

/*
@Entity : 이 클래스가 DB테이블과 매핑되는 클래스임을 JPA에게 알림.
          클래스 이름(Article)이 그대로 테이블 이름(article)이 된다.
@Id : 테이블의 기본키를 지정
@GeneratedValue : id 값을 개발자가 직접 넣지 않아도 1, 2, 3, ... 순서로 DB가 자동으로 채워준다.
@Column : 이 필드가 테이블의 한 열(컬럼)이 됨을 나타낸다.
*/
