package com.example.firstproject.controller;

import com.example.firstproject.dto.MemberForm;
import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;

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
        return "";
    }
}
