package com.example.deck;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;
import java.util.UUID;

public class CardDeck implements Serializable {

    @PrimaryKey
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;

    // CARD PROPS
    private String name;

    private String suit;

    private Long deckNumber;

    //DECK PROPS
    private Long position;

    //VALUE PROPS
    private Long points;

    //CONSTRUCTOR
    public CardDeck( UUID uuid, String name, String suit, Long deckNumber, Long position, Long points) {
        this.uuid = uuid;
        this.name = name;
        this.suit = suit;
        this.deckNumber = deckNumber;
        this.position = position;
        this.points = points;
    }

    //METHODS
    public Long getId() {
        return id;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public Long getDeckNumber() {
        return deckNumber;
    }

    public void setDeckNumber(Long deckNumber) {
        this.deckNumber = deckNumber;
    }

    public Long getPosition() {
        return position;
    }

    public void setPosition(Long position) {
        this.position = position;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }


}
