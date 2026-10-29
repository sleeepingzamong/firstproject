package com.example.firstproject.controller;

import com.example.firstproject.dto.MemberForm;
import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/members/signup")
    public String newMemberForm(){
        return "members/signup";
    }

    @PostMapping("/members/create")
    public String join(MemberForm form){
        log.info(form.toString());

        // DTO -> entity
        Member member = form.toEntity();
        log.info(member.toString());

        // Repository -> DB
        Member saved = memberRepository.save(member);

        log.info(saved.toString());
        return "redirect:/members/"+saved.getId();
    }

    @GetMapping("/members/{id}")
    public String show(@PathVariable Long id, Model model){
        // 1. id로 데이터를 가져옴
        Member memberEntity = memberRepository.findById(id).orElse(null);
        if(memberEntity == null){
            return "redirect:/members";
        }
        // 2. 가져온 데이터를 모델에 등록
        model.addAttribute("member", memberEntity);
        // 3. 보여줄 페이지를 설정
        return "members/show";
    }

    @GetMapping("/members")
    public String index(Model model){
        //1. 모든 Member를 가져온다.
        List<Member> memberEntityList = memberRepository.findAll();
        //2. 가져온 Member 묶음을 뷰로 전달!
        model.addAttribute("memberList", memberEntityList);
        //3. 뷰 페이지를 설정
        return "members/index";
    }
}
