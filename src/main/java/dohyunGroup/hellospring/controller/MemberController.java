package dohyunGroup.hellospring.controller;

import dohyunGroup.hellospring.domain.Member;
import dohyunGroup.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }
    // 스프링 빈 등록 방법
    // 1. 컴포넌트(Controller, Service, Repository) 스캔과 자동 의존관계 설정(Autowired)
    // 2. 자바 코드로 직접 스프링 빈 등록.

    // 스프링은 스프링 컨테이너에 스프링 빈을 등록할 때 기본으로 싱글톤으로 등록한다 (유일하게 하나만 등록해서 공유한다.)
    // 따라서 같은 스프링 빈이면 모두 같은 인스턴스다.

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
