package jpabook.jpashop.domain.repository;

import jpabook.jpashop.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Repository
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Optional<Member> findOne(Long id) {
        Member member;
        try {
            member = em.find(Member.class, id);
        } catch (Exception e) {
            log.info("엔티티에 해당하는 키를 찾을 수 없음");
            return Optional.empty();
        }
        return Optional.of(member);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m")
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name")
                .setParameter("name", name)
                .getResultList();
    }
}
