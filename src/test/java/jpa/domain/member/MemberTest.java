package jpa.domain.member;

import jpa.domain.favorite.Favorite;
import jpa.domain.favorite.FavoriteRepository;
import jpa.domain.station.Station;
import jpa.domain.station.StationRepository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
public class MemberTest {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private StationRepository stationRepository;

    @PersistenceContext
    private EntityManager em;

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

    @Test
    @DisplayName("사용자를 통해 즐겨찾기 조회 테스트")
    void findFavoriteByMember() {
        // given
        Member member = saveMember(20, "black@gmail.com", "red_black");
        Station station1 = saveStation("인천");
        Station station3 = saveStation("모란");
        Station station2 = saveStation("청량리");

        favoriteRepository.save(new Favorite(member, station1, station2));
        favoriteRepository.save(new Favorite(member, station1, station3));

        // when
        em.clear();
        Member findMember = memberRepository.findById(member.getId()).get();

        // then
        assertThat(findMember.getFavorites().size()).isEqualTo(2);
    }

    private Member saveMember(int age, String email, String password) {
        return memberRepository.save(new Member(age, email, password));
    }

    private Station saveStation(String name) {
        return stationRepository.save(new Station(name));
    }
}
