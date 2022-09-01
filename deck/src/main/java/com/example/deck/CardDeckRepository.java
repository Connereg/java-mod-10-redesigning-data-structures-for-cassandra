package com.example.deck;

import org.springframework.data.repository.CrudRepository;

public interface CardDeckRepository extends CrudRepository<CardDeck, Long> {
}
