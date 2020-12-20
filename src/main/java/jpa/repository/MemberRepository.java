package jpa.repository;

import jpa.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByAge(String age);
    Member findByEmail(String email);
}
