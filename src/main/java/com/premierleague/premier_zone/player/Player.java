package com.premierleague.premier_zone.player;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "player_statistics")
@NoArgsConstructor

public class Player {

    @Id
    @Column(name = "player_name",unique = true) //not best practice since name might not be unique in some data
    private String name;
    private String nation;
    private String position;
    private Integer age;
    private Integer matches_played;
    private Integer starts;
    private Double minutes_played;
    private Double assists;
    private Double penalties_scored;
    private Double yellow_cards;
    private Double red_cards;
    private Double expected_goals;
    private Double expected_assists;
    private String team_name;

    public Player(String name) {
        this.name = name;
    }
}
