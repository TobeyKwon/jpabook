package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;


@Transactional
@SpringBootTest
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;
    
    @Test
    public void 회원가입() throws Exception {
        //given
        Member member = Member.builder().name("kim").build();
        //when
        Long saveId = memberService.join(member);
        em.flush();
        //then

        Assertions.assertEquals(member, memberRepository.findById(saveId));
    }
    
    @Test
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = Member.builder().name("kim").build();
        Member member2 = Member.builder().name("kim").build();
        
        //when
        memberService.join(member1);

        Assertions.assertThrows(IllegalStateException.class, () -> {
            memberService.join(member2);
        });
    }
}