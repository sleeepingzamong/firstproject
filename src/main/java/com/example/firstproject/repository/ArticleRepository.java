package com.example.firstproject.repository;


import com.example.firstproject.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    @Override
    ArrayList<Article> findAll();
}

//ArticleRepository는 클래스가 아니라 인터페이스이고, 내용이 텅 비어 있어도 동작한다.
//이것이 스프링 데이터 JPA의 강력한 특징이다
//CrudRepository<Article, Long>을 상속하는 순간, save(), findAll(), findById(), delete() 등과 같은
//기본 CRUD 메서드를 스프링이 자동으로 구현해서 제공해준다