package jpa.step1;


import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import jpa.step1.domain.Station;
import jpa.step1.repository.StationRepository;

@DataJpaTest
public class StationTest {

	@Autowired
	private StationRepository stationRepository;

	@DisplayName("Station 생성")
	@Test
	void given_station_when_save_then_return_created_station_with_primary_key() {
		final String stationName = "사당역";
		Station station = new Station(stationName);

		Station createdStation = stationRepository.save(station);

		assertAll(
			() -> assertThat(createdStation.getId()).isNotNull(),
			() -> assertThat(createdStation.getName()).isEqualTo(station.getName())
		);
	}

}
