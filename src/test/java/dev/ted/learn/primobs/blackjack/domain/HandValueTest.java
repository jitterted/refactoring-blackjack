package dev.ted.learn.primobs.blackjack.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class HandValueTest {

    private static final Suit DUMMY_SUIT = Suit.CLUBS;

    @Test
    void totalValueIsSumOfIndividualCardsForNumericRanks() {
        Game game = gameWithInitialDealToPlayerOf(Rank.TWO, Rank.SEVEN);

        assertThat(game.playerHandValue())
                .isEqualTo(2 + 7);
    }

    @Test
    void totalValueIsSumOfIndividualCardsForMultipleCards() {
        Game game = gameWithInitialDealToPlayerOf(Rank.TWO, Rank.THREE, Rank.NINE);
        game.playerHits(); // get the 3rd card

        assertThat(game.playerHandValue())
                .isEqualTo(2 + 3 + 9);
    }

    @Test
    void faceCardsAreTreatedAsValueOfTen() {
        Game game = gameWithInitialDealToPlayerOf(Rank.JACK, Rank.QUEEN, Rank.KING);
        game.playerHits(); // get the 3rd card

        assertThat(game.playerHandValue())
                .isEqualTo(10 + 10 + 10);
    }

    @Test
    void handWithOneAceAndOtherCardValuedLessThan10ThenAceIsValuedAt11() {
        Game game = gameWithInitialDealToPlayerOf(Rank.ACE, Rank.NINE);

        assertThat(game.playerHandValue())
                .isEqualTo(11 + 9);
    }

    @Test
    void handWithOneAceAndOtherCardsValuedAt10ThenAceIsValuedAt11() {
        Game game = gameWithInitialDealToPlayerOf(Rank.ACE, Rank.TEN);

        assertThat(game.playerHandValue())
                .isEqualTo(11 + 10);
    }

    @Test
    void handWithOneAceAndOtherCardsValuedAs11ThenAceIsValuedAt1() {
        Game game = gameWithInitialDealToPlayerOf(Rank.ACE, Rank.EIGHT, Rank.THREE);
        game.playerHits(); // get the 3rd card

        assertThat(game.playerHandValue())
                .isEqualTo(1 + 8 + 3);
    }

    private static List<Card> deckWithPlayerHandOf(Rank... ranks) {
        List<Card> cards = new ArrayList<>();
        for (Rank rank : ranks) {
            cards.add(new Card(rank, DUMMY_SUIT)); // player's card
            cards.add(new Card(Rank.THREE, Suit.SPADES)); // dealer's card
        }
        return cards;
    }

    public static Game gameWithInitialDealToPlayerOf(Rank... ranks) {
        List<Card> hand = deckWithPlayerHandOf(ranks);
        Game game = new Game(hand);
        game.initialDeal();
        return game;
    }

}
