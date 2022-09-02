package com.example.deck;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static java.util.UUID.randomUUID;

@RestController
public class DeckController {

    @Autowired
    private CardDeckRepository cardDeckRepository;

//  TODO: REPLACE GETMAPPING METHOD TO ADJUST FOR CARDDECKREPOSITORY

    @GetMapping("/new")
    public List<CardDeck> getCardDeck(@RequestParam(value="decks", defaultValue="1") Long decks) {
        cardDeckRepository.deleteAll();

        List<String> names = Arrays.asList("Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
                "Ten", "Jack", "Queen", "King", "Ace");
        List<Long> points = new ArrayList<>(Arrays.asList(
                11L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 10L, 10L

        ));

        List<String> suits = Arrays.asList("Clubs", "Hearts", "Spades", "Diamonds");
        Long position = 1L;
        for (Long deck = 1L; deck <= decks; deck++) {
            for (String suit : suits ) {
                for (int i = 0; i < names.size(); i++) {
                    String name = names.get(i);
                    Long point = points.get(i);
                    UUID uuid = randomUUID();

                    cardDeckRepository.save( new CardDeck(
                            uuid,
                            name,
                            suit,
                            deckNumber,
                            position,
                            point
                    ));
                    ++position;
                }
            }
        }

        Iterable<CardDeck> iterableCardDecks = cardDeckRepository.findAll();
        List<CardDeck> cardDecks = new ArrayList<CardDeck>();
        for( CardDeck iterableCardDeck : iterableCardDecks) {
            cardDecks.add(iterableCardDeck);
        }

        return cardDecks;

}

    @GetMapping("/shuffle")
    public String shuffleDeck() {

        // Read order of cards
        Iterable<CardDeck> cardDeck = cardDeckRepository.findAll();
        List<Long> order = new ArrayList<Long>();
        for (CardDeck deckItem : cardDeck) {
            order.add(deckItem.getPosition());
        }

        // Shuffle order
        Collections.shuffle(order);

        // Write new order of cards
        ListIterator<Long> orderItr = order.listIterator();
        for (CardDeck deckItem : cardDeck) {
            deckItem.setPosition(orderItr.next());
            cardDeckRepository.save(deckItem);
        }
        return "Deck shuffled.";
    }

    @GetMapping("/deal")
    public String dealCard() {

        CardDeck cardDeckItem = cardDeckRepository.findFirstByOrderByPositionDesc().orElseGet(null);
        cardDeckRepository.delete(cardDeckItem);

        return String.format("Dealt %s of %s: Worth %s points.", cardDeckItem.getName(), cardDeckItem.getSuit(),
                            cardDeckItem.getPoints());
    }

}
