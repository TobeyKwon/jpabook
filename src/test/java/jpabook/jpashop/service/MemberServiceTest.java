package jpabook.jpashop.service;

import jpabook.jpashop.domain.entity.Address;
import jpabook.jpashop.domain.entity.Member;
import jpabook.jpashop.domain.repository.MemberRepository;
import jpabook.jpashop.domain.service.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @BeforeEach
    public void initData() {
        Member member = new Member();
        Address address = new Address("칠곡군", "석전로 7길", "5");
        member.setName("권도현");
        member.setAddress(address);
        memberRepository.save(member);
    }

    @Test
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("kim");
        memberService.join(member);

        //when
        Member findMember = memberRepository.findOne(member.getId()).get();

        //then
        Assertions.assertEquals(member, findMember);
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        //given
        Member member = new Member();
        member.setName("권도현");

        //when
        assertThrows(IllegalStateException.class, () -> memberService.join(member));
    }
}