package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ArticleApiController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleService.index();
    }

    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id) {
        return articleService.show(id);
    }

    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto){
        Article created = articleService.create(dto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto){
        Article updated = articleService.update(id, dto);

        return (updated != null)?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        Article deleted = articleService.delete(id);

        return (deleted != null)?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    @PostMapping("/api/transaction-test/")
    public ResponseEntity<List<Article>> transactionTest(@RequestBody List<ArticleForm> dtos){
        List<Article> createdList = articleService.createArticles(dtos);
        return (createdList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(createdList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }





}
//
//    @GetMapping("/api/articles/{id}")
//    public Article show(@PathVariable Long id) {
//        return articleRepository.findById(id).orElse(null);
//    }
//
//    // form으로 오는 데이터는 컨트롤러가 알아서 DTO에 매핑해주지만,
//    // JSON 본문으로 오는 데이터는 @RequestBody를 명시적으로 붙여야만 데이터를 받을 수 있다.
//    @PostMapping("/api/articles")
//    public Article create(@RequestBody ArticleForm dto) {
//        Article article = dto.toEntity();
//        return articleRepository.save(article);
//    }
//
//    @PatchMapping("/api/articles/{id}")
//    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto) {
//        // 1. 수정용 엔티티 생성
//        Article article = dto.toEntity();
//        log.info("id: {}, article: {}", id, article.toString());
//
//        // 2. 대상 엔티티를 조회
//        Article target = articleRepository.findById(id).orElse(null);
//
//        // 3. 잘못된 요청 처리(대상이 없거나, id가 다른 경우)
//        //    id는 Long(객체)이므로 != 가 아니라 equals()로 값을 비교해야 한다.
//        if (target == null || !id.equals(article.getId())) {
//            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//
//        // 4. 업데이트 및 정상 응답(200)
//        target.patch(article);
//        Article updated = articleRepository.save(target);
//        return ResponseEntity.status(HttpStatus.OK).body(updated);
//    }
//
//    @DeleteMapping("/api/articles/{id}")
//    public ResponseEntity<Article> delete(@PathVariable Long id) {
//        // 1. 대상 찾기
//        Article target = articleRepository.findById(id).orElse(null);
//
//        // 2. 잘못된 요청 처리
//        if (target == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//        }
//
//        // 3. 삭제
//        articleRepository.delete(target);
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }
//}
