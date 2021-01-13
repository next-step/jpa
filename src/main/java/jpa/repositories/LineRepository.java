package jpa.repositories;

import jpa.domain.Line;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LineRepository extends JpaRepository<Line, Long> {
    Line findByName(String name);

}
