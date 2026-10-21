package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @Autowired
    private CommentService commentService;

    @PostMapping("/articles/create")
    public String createArticles(ArticleForm form) {
        // System.out.println(form.toString());
        log.info(form.toString());

        // 1. DTO를 변환 -> Entity!!
        Article article = form.toEntity();
        // System.out.println(article.toString());
        log.info(article.toString());

        // 2. Repository에게 Entity를 DB 안에 저장하게 함!!
        Article saved = articleRepository.save(article);
        // System.out.println(saved.toString());
        log.info(saved.toString());


        return "redirect:/articles/"+saved.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        //1. 삭제 대상을 가져온다.
        Article target = articleRepository.findById(id).orElse(null);
        //2. 대상을 삭제한다.
        if(target != null){
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg","삭제가 완료됐습니다");
        }
        //3. 결과 페이지로 리다이렉트한다.

        return "redirect:/articles";
    }




    // @PostMapping("/articles/create")는 방금 폼에서 설정한 action, method와 정확히 짝이
    // 맞아야 한다.

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info("id => "+id);
        // 1. id로 데이터를 가져옴
        Article articleEntity = articleRepository.findById(id).orElse(null);
        log.info(articleEntity.toString());
        List<CommentDto> commentDtos = commentService.comments(id);
        // 2. 가져온 데이터를 모델이 등록
        model.addAttribute("article",articleEntity);
        model.addAttribute("commentDtos",commentDtos);
        // 3. 보여줄 페이지를 설정
        return "articles/show";
    }
    // @PathVariable은 URL 요청으로 들어온 전달 값{id}을 컨트롤러의 매개변수로 가져오는 어노테이션이다.

    @GetMapping("/articles")
    public String index(Model model){
        //1. 모든 Articles를 가져온다.
      // 다운 캐스팅  List<Article> articleEntityList = (List<Article>)articleRepository.findAll();
      // 업 캐스팅  Iterable<Article> articleEntityList2 = articleRepository.findAll();
        // 두가지 전부 좋은 방법은 아님.
        List<Article> articleEntityList = articleRepository.findAll();



        // Iterable은 자바에서 객체가 반복될 수 있다는 것을 얘기해주는 인터페이스이다.
        // 자바 컬렉션 프레임워크로 최상위 인터페이스 중 하나인 Collection 인터페이스는 Iterable을 상속 받는다.
        // 따라서 ArrayList, HashSet, LinkedList 등 자바의 모든 표준 컬렉션 타입들은 Iterable이며,
        // 이 덕분에 향상된 for문(for-each)을 사용할 수 있다.

        //2. 가져온 Article 묶음을 뷰로 전달!
        model.addAttribute("articleList",articleEntityList);

        //3. 뷰 페이지를 설정
        return "articles/index";
    }

    @PostMapping("articles/update")
    public String update(ArticleForm form){
        log.info(form.toString());
        //1. DTO를 Entity로 변환
        Article articleEntity = form.toEntity();
        //2. Entity를 DB에 저장
        //2-1. DB에서 기존 데이터를 가져온다.
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        //2-2. 기존 데이터가 있다면 값을 갱신한다.
        if(target != null){
            articleRepository.save(articleEntity);
        }
        //3. 수정 결과 페이지로 리다이렉트한다.
        return "redirect:/articles/"+articleEntity.getId();
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        //1. 수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);

        //2. 모델에 데이터 중복
        model.addAttribute("article", articleEntity);
        return "articles/edit";
    }
}
