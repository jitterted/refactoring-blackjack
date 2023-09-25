package dev.ted.learn.primobs.blackjack.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CardTest {

    private static final Suit DUMMY_SUIT = Suit.HEARTS;

    @Test
    void withNumberCardHasNumericValueOfTheNumber() {
        Card card = new Card(Rank.SEVEN, DUMMY_SUIT);

        assertThat(card.rankValue())
                .isEqualTo(7);
    }

    @Test
    void withValueOfQueenHasNumericValueOf10() {
        Card card = new Card(Rank.QUEEN, DUMMY_SUIT);

        assertThat(card.rankValue())
                .isEqualTo(10);
    }

    @Test
    void withAceHasNumericValueOf1() {
        Card card = new Card(Rank.ACE, DUMMY_SUIT);

        assertThat(card.rankValue())
                .isEqualTo(1);
    }

}