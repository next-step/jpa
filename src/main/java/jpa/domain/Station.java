package jpa.domain;

import lombok.Getter;

import javax.persistence.Entity;


@Getter
@Entity
public class Station extends BaseEntity {

    private String name;

    public Station() {
    }

    public Station(String name) {
        this.name = name;
    }
}
