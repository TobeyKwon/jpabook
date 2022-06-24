package jpabook.jpashop.web.controller;

import jpabook.jpashop.domain.entity.Address;
import jpabook.jpashop.domain.entity.Member;
import jpabook.jpashop.domain.service.MemberService;
import jpabook.jpashop.web.dto.MemberSaveForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(@ModelAttribute("memberForm") MemberSaveForm memberForm) {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid @ModelAttribute("memberForm") MemberSaveForm memberForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "members/createMemberForm";
        }
        Member member = memberFormConvertToMember(memberForm);
        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

    private Member memberFormConvertToMember(MemberSaveForm memberForm) {
        Address address = new Address(memberForm.getCity(), memberForm.getStreet(), memberForm.getZipcode());
        Member member = new Member();

        member.setName(memberForm.getName());
        member.setAddress(address);

        return member;
    }
}
