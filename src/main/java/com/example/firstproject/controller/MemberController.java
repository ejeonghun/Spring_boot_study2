package com.example.firstproject.controller;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Member;
import com.example.firstproject.dto.MemberForm;
import com.example.firstproject.repository.MemberRepository;
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
public class MemberController {

    @Autowired
    MemberRepository memberRepository;

    @GetMapping("/signup")
    public String signUpPage() {
        return "members/new";
    }

    @PostMapping("/join")
    public String join(MemberForm memberForm) {
        log.info(memberForm.toString());
        Member member = memberForm.toEntity();
        log.info(member.toString());
        Member saved = memberRepository.save(member);
        log.info(saved.toString());
        return "redirect:/members/detail/" + saved.getId();
    }

    @GetMapping("/members")
    public String memberList(Model model) {
        List<Member> memData = memberRepository.findAll();
        model.addAttribute("members", memData);
        return "/members/index";
    }

    @GetMapping("/members/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        log.info(id + " 멤버 정보 요청됨");
        Member data = memberRepository.findById(id).orElse(null);
        model.addAttribute("member", data);
        return "/members/show";
    }

    @GetMapping("/members/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        // 수정할 데이터 가져오기
        Member memEntity = memberRepository.findById(id).orElse(null);
        // 모델에 데이터 등록하기
        model.addAttribute("member",memEntity);
        return "/members/edit";
    }


    @PostMapping("/members/update")
    public String update(MemberForm form) {
        log.info(form.getId() + "수정 요청");
        Member memEntity =form.toEntity();
        Member data = memberRepository.findById(memEntity.getId()).orElse(null);

        if (data != null) memberRepository.save(memEntity);
        log.info(form.getId() + "수정 완료 DB 반영");
        return "redirect:/members/detail/" + memEntity.getId();
    }

    @GetMapping("/members/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {
        log.info(id + " 삭제 요청");
        Member target = memberRepository.findById(id).orElse(null);

        if(target != null) {
            memberRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제되었습니다.");
        }
        return "redirect:/members";
    }
}

