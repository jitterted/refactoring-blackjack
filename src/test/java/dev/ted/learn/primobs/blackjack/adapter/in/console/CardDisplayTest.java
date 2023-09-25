package dev.ted.learn.primobs.blackjack.adapter.in.console;

import dev.ted.learn.primobs.blackjack.domain.Card;
import dev.ted.learn.primobs.blackjack.domain.Rank;
import dev.ted.learn.primobs.blackjack.domain.Suit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.fusesource.jansi.Ansi.ansi;

class CardDisplayTest {

    private static final Rank DUMMY_RANK = Rank.FOUR;

    @Test
    void displayTenAsString() {
        Card card = new Card(Rank.TEN, Suit.CLUBS);

        assertThat(ConsoleCard.forDisplayOf(card))
                .isEqualTo("[30m┌─────────┐[1B[11D│10       │[1B[11D│         │[1B[11D│    ♣    │[1B[11D│         │[1B[11D│       10│[1B[11D└─────────┘");
    }

    @Test
    void displayNonTenAsString() {
        Card card = new Card(Rank.QUEEN, Suit.SPADES);

        assertThat(ConsoleCard.forDisplayOf(card))
                .isEqualTo("[30m┌─────────┐[1B[11D│Q        │[1B[11D│         │[1B[11D│    ♠    │[1B[11D│         │[1B[11D│        Q│[1B[11D└─────────┘");
    }


    @Test
    void suitOfHeartsOrDiamondsIsDisplayedInRed() {
        // given a card with Hearts or Diamonds
        Card heartsCard = new Card(DUMMY_RANK, Suit.HEARTS);
        Card diamondsCard = new Card(DUMMY_RANK, Suit.DIAMONDS);

        // when we ask for its display representation
        String ansiRedString = ansi().fgRed().toString();

        // then we expect a red color ansi sequence
        Assertions.assertThat(ConsoleCard.forDisplayOf(heartsCard))
                  .contains(ansiRedString);
        assertThat(ConsoleCard.forDisplayOf(diamondsCard))
                .contains(ansiRedString);
    }
}
