package jpa.domain.line;

import jpa.domain.station.Station;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class LineStation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "line_id")
    private Line line;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_id")
    private Station station;

    private int distance;

    protected LineStation() {}

    public Long getId() {
        return id;
    }

    public Line getLine() {
        return line;
    }

    public Station getStation() {
        return station;
    }

    public int getDistance() {
        return distance;
    }

    public void changeLine(Line line) {
        this.line = line;
    }

    public void changeStation(Station station) {
        this.station = station;
        station.addLineStation(this);
    }

    public void changeDistance(int distance) {
        this.distance = distance;
    }

    public static LineStation createLineStation(Station station, int distance) {
        LineStation lineStation = new LineStation();
        lineStation.changeStation(station);
        lineStation.changeDistance(distance);
        return lineStation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LineStation)) return false;
        LineStation that = (LineStation) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
