package jpa.repository;

import jpa.domain.Member;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
public class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @PersistenceContext
    private EntityManager em;

    private Member saveMember(Integer age, String email, String password) {
        return memberRepository.save(new Member(age, email, password));
    }

    @Test
    @DisplayName("Member 조회 테스트")
    void memberSelectTest() {
        Member member1 = saveMember(20, "black@gmail.com", "red_black");
        Member member2 = saveMember(30, "white@github.com", "red_white");
        Member member3 = saveMember(40, "green@naver.com", "red_green");
        Member member4 = saveMember(30, "white@github.com", "red_white");

        List<Member> result = memberRepository.findMembers(20, "white@github.com", "red_white");

        assertThat(result.size()).isEqualTo(2);
        for (Member m : result) {
            assertThat(m.getAge()).isEqualTo(30);
            assertThat(m.getEmail()).isEqualTo("white@github.com");
            assertThat(m.getPassword()).isEqualTo("red_white");
        }

        List<Member> findMembers = memberRepository.findMembersByAgeGreaterThan(25);

        assertThat(findMembers.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("Member 수정 테스트")
    void memberUpdateTest() {
        Member member = saveMember(20, "black@gmail.com", "red_black");

        member.changeEmail("black@gmail.com");
        member.changePassword("red_white");

        em.flush();
        em.clear();

        Member findMember = memberRepository.findById(member.getId()).get();

        assertThat(findMember).extracting("email", "password")
                .containsExactly("black@gmail.com", "red_white");
    }
}
