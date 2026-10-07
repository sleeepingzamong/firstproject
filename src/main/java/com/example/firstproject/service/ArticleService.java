package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;


    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id){
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto){
        Article article = dto.toEntity();
        if(article.getId() != null){
            return null;
        }
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {
        // 1. 수정용 ENtity를 생성
        Article article = dto.toEntity();
        log.info("id:{}, article : {}",id,article.toString());
        // 2. 대상 Entity를 찾기
        Article target = articleRepository.findById(id).orElse(null);
        // 3. 잘못된 요청을 처리
        if(target == null || id != article.getId()){
            log.info("잘못된 요청! id:{}, article : {}",id,article.toString());
            return null;
        }
        // 4. 업데이트 및 정상 응답
        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated;

    }

    public Article delete(Long id) {
        // 1. 대상 엔티티를 찾기
        Article target = articleRepository.findById(id).orElse(null);
        // 2. 잘못된 요청 처리(대상 무존재)
        if(target == null){
            return null;
        }
        // 3. 대상 삭제 후 응답 반환
        articleRepository.delete(target);
        return target;
    }

    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos) {
//        List<Article> articlesList = new ArrayList<>();
//        for(int i=0; i<dtos.size(); i++){
//            ArticleForm dto = dtos.get(i);
//            Article entity = dto.toEntity();
//            articlesList.add(entity);
//        }
        List<Article> articleList = dtos.stream().map(dto -> dto.toEntity()).collect(Collectors.toList());
//        for(int i=0; i<articlesList.size(); i++){
//            Article article = articlesList.get(i);
//            articleRepository.save(article);
//        }

        articleRepository.findById(-1L).orElseThrow(
                () -> new IllegalArgumentException("결제 실패")

        );
        articleRepository.findById(-1L).orElseThrow(
                () -> new IllegalArgumentException("결제 실패")

        );
        return articleList;
    }




}
