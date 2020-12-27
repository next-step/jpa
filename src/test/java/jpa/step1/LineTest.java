package jpa.step1;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import jpa.step1.domain.Color;
import jpa.step1.domain.Line;
import jpa.step1.repository.LineRepository;

@DataJpaTest
public class LineTest {

	@Autowired
	private LineRepository lineRepository;

	@DisplayName("Line 생성")
	@Test
	void given_line_when_save_then_return_created_line_with_primary_key() {
		final String lineName = "2호선";
		Line line = new Line(Color.GREEN, lineName);

		Line createdLine = lineRepository.save(line);

		assertThat(createdLine.getId()).isEqualTo(1L);
	}

	@DisplayName("Line 조회")
	@Test
	void given_line_when_save_and_getOne_then_return_created_line_with_primary_key() {
		final String lineName = "2호선";
		Line line = new Line(Color.GREEN, lineName);
		lineRepository.save(line);

		Line createdLine = lineRepository.getOne(1L);

		assertThat(createdLine.getId()).isEqualTo(1L);
	}

	@DisplayName("Line 등록시 유니크 키 제약조건 위반시 익셉션")
	@Test
	void given_line_duplicated_name_when_save_then_throw_exception() {
		final String lineName = "2호선";
		Line line = new Line(Color.GREEN, lineName);
		Line duplicatedLine = new Line(Color.GREEN, lineName);
		lineRepository.save(line);

		assertThatThrownBy(() -> lineRepository.save(duplicatedLine));
	}

}
