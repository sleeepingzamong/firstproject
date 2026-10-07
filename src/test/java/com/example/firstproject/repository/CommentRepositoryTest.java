package com.example.firstproject.repository;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest // 해당 클래스를 JPA와 연동해 테스트
class CommentRepositoryTest {
    @Autowired
    CommentRepository commentRepository;

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId() {
        //case 1 : 4번 게시글의 모든 댓글 조회
        {
            // 입력 데이터 준비
            Long articleId = 4L;
            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 예상하기
            Article article = new Article(4L, "당신의 인생 영화는?", "댓글로 남겨 주세요!");
            Comment a = new Comment(1L, article, "Park", "어벤져스");
            Comment b = new Comment(2L, article, "Kim", "호프");
            Comment c = new Comment(3L, article, "Lee", "오디세이");
            List<Comment> expected = Arrays.asList(a, b, c);
            // 검증
            assertEquals(expected.toString(), comments.toString(), "4번 글의 모든 댓글을 출력!");
        }

        //case 2 : 1번 게시글의 모든 댓글 조회
        {
            // 입력 데이터 준비
            Long articleId = 1L;
            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 예상하기
            List<Comment> expected = Arrays.asList();
            // 검증
            assertEquals(expected.toString(), comments.toString(), "1번 게시글은 댓글이 없음!");
        }
    }

    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {
        //case 1 : "Park"의 모든 댓글 조회
        {
            // 입력 데이터 준비
            String nickname = "Park";
            // 실제 수행
            List<Comment> comments = commentRepository.findByNickname(nickname);
            // 예상하기
            Comment a = new Comment(1L, new Article(4L, "당신의 인생 영화는?", "댓글로 남겨 주세요!"), nickname, "어벤져스");
            Comment b = new Comment(4L, new Article(5L, "당신의 소울 푸드는?", "댓글로 남겨 주세요!!"), nickname, "김밥");
            Comment c = new Comment(7L, new Article(6L, "당신의 취미는?", "댓글로 남겨 주세요!!!"), nickname, "조깅");
            List<Comment> expected = Arrays.asList(a, b, c);
            // 검증
            assertEquals(expected.toString(), comments.toString(), "Park의 모든 댓글을 출력!");
        }

        //case 2 : 댓글이 없는 닉네임 조회
        {
            // 입력 데이터 준비
            String nickname = "Choi";
            // 실제 수행
            List<Comment> comments = commentRepository.findByNickname(nickname);
            // 예상하기
            List<Comment> expected = Arrays.asList();
            // 검증
            assertEquals(expected.toString(), comments.toString(), "Choi는 댓글이 없음!");
        }

        //case 3: Kim의 모든 댓글 조회
        //case 4: null이 되는 댓글 조회
        //case 5: ""의 모든 댓글 조회
        //case 6: "1"의 모든 댓글 조회

    }
}
