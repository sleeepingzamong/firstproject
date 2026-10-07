package com.example.firstproject.repository;


import com.example.firstproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    //특정 게시글의 모든 댓글 조회
    @Query(value=
            "SELECT * " +
            "FROM comment " +
            "WHERE article_id = :articleId",
            nativeQuery = true)
    List<Comment> findByArticleId(Long articleId);

    // 특정 닉네임의 모든 댓글 조회
    List<Comment> findByNickname(String nickname);
}

// Repository
//  - CrudRepository (Crud 기능)
//      - PagingAndSortingRepository (페이징 & 정렬 가능)
//          - JpaRepository (그 외 JPA 특화 기능)

// 네이티브 쿼리 메서드 작성
// select 문을 사용하는 일반 쿼리문을 메서드로 정의해보자
// 네이티브 쿼리 메서드를 만드는 두가지 방법
// 1. @Query 어노테이션 이용
// 2. orm.xml
