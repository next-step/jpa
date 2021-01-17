package jpa.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Station extends BaseDateEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String name;

	@OneToMany(mappedBy = "station", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<LineStation> lines = new ArrayList<>();

	protected Station() {
	}

	public Station(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void changeName(String name) {
		this.name = name;
	}

	public void addLine(LineStation lineStation) {
		lines.add(lineStation);
	}

	private List<Line> mapLines() {
		return lines.stream()
			.map(LineStation::getLine)
			.collect(Collectors.toList());
	}

	public List<Line> getLines() {
		return Collections.unmodifiableList(mapLines());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Station station = (Station)o;
		return Objects.equals(id, station.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
