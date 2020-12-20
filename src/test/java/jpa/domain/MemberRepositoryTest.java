package jpa.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void save() {
        Member member = new Member(25, "1234");
        Member actual = memberRepository.save(member);

        assertThat(actual.getId()).isNotNull();
        assertThat(actual.getAge()).isEqualTo(25);
    }

    @Test
    void findByAge() {
        memberRepository.save(new Member(24, "1111"));
        memberRepository.save(new Member(24, "kj@xxx.com", "222"));

        List<Member> members = memberRepository.findAllByAge(24);

        assertThat(members).hasSize(2);
    }

    @Test
    void update_age() {
        Member member = memberRepository.save(new Member(27, "woo@xxx.com", "333"));

        member.setPassword("222");
        Member actual = memberRepository.getOne(member.getId());

        assertThat(actual.getPassword()).isEqualTo("222");
    }

    @Test
    void delete() {
        Member member = memberRepository.save(new Member(24, "1111"));

        memberRepository.delete(member);
        Optional<Member> actual = memberRepository.findById(member.getId());

        assertThat(actual.isPresent()).isFalse();
    }
}
