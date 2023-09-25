package dev.ted.learn.primobs.blackjack.adapter.in.console;

import dev.ted.learn.primobs.blackjack.domain.Card;
import dev.ted.learn.primobs.blackjack.domain.Rank;
import dev.ted.learn.primobs.blackjack.domain.Suit;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class HandDisplayTest {
    @Test
    void displayFaceUpCard() {
        List<Card> hand = List.of(new Card(Rank.ACE, Suit.HEARTS));

        assertThat(ConsoleHand.displayFaceUpCard(hand))
                .isEqualTo("[31m┌─────────┐[1B[11D│A        │[1B[11D│         │[1B[11D│    ♥    │[1B[11D│         │[1B[11D│        A│[1B[11D└─────────┘");
    }

    @Test
    void cardsAsString() {
        List<Card> hand = List.of(new Card(Rank.JACK, Suit.DIAMONDS),
                                  new Card(Rank.FIVE, Suit.SPADES));

        String output = ConsoleHand.cardsAsString(hand);

        assertThat(output)
                .isEqualTo("[31m┌─────────┐[1B[11D│J        │[1B[11D│         │[1B[11D│    ♦    │[1B[11D│         │[1B[11D│        J│[1B[11D└─────────┘[6A[1C[30m┌─────────┐[1B[11D│5        │[1B[11D│         │[1B[11D│    ♠    │[1B[11D│         │[1B[11D│        5│[1B[11D└─────────┘");
    }
}