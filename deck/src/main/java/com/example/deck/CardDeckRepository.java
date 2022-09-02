package com.example.deck;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CardDeckRepository extends CrudRepository<CardDeck, Long> {
    Optional<CardDeck> findFirstByOrderByPositionDesc();
}
