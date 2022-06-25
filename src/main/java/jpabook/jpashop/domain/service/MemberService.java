package jpabook.jpashop.domain.service;

import jpabook.jpashop.domain.entity.Member;
import jpabook.jpashop.domain.exception.DuplicatedUsernameException;
import jpabook.jpashop.domain.exception.NoExistUserException;
import jpabook.jpashop.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;


    /**
     * 회원가입
     */
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId).orElse(null);
    }

    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findOne(id)
                .orElseThrow(() -> new NoExistUserException("해당 유저가 존재하지 않습니다."));
        member.setName(name);
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()) {
            throw new DuplicatedUsernameException(member.getName() + "은(는) 이미 존재하는 회원입니다.");
        }
    }
}
