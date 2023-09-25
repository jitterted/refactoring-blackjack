package dev.ted.learn.primobs.blackjack.domain;

import java.util.Arrays;
import java.util.List;

public class DeckFactory {
    private static final Suit DUMMY_SUIT = Suit.HEARTS;

    public static List<Card> createCardsWithRanksOf(Rank... ranks) {
        return Arrays.stream(ranks)
                     .map(rank -> new Card(rank, DUMMY_SUIT))
                     .toList();
    }

    public static List<Card> playerHitsAndGoesBust() {
        return createCardsWithRanksOf(
                Rank.TEN, Rank.EIGHT,
                Rank.QUEEN, Rank.JACK,
                Rank.THREE
        );
    }

    public static List<Card> playerStandsAndBeatsDealer() {
        return createCardsWithRanksOf(
                Rank.TEN, Rank.EIGHT,
                Rank.QUEEN, Rank.JACK
        );
    }

    public static List<Card> playerStandsAndPushesDealer() {
        return createCardsWithRanksOf(
                Rank.TEN, Rank.QUEEN,
                Rank.NINE, Rank.NINE
        );
    }

    public static List<Card> playerDealtBlackjackDealerNoBlackjack() {
        return createCardsWithRanksOf(
                Rank.ACE, Rank.NINE,
                Rank.JACK, Rank.EIGHT
        );
    }

    public static List<Card> playerGets21AfterHitsAndBeatsDealer() {
        return createCardsWithRanksOf(
                Rank.SEVEN, Rank.NINE,
                Rank.JACK, Rank.EIGHT,
                Rank.FOUR
        );
    }

    public static List<Card> playerNotDealtBlackjack() {
        return createCardsWithRanksOf(
                Rank.SIX, Rank.NINE,
                Rank.JACK, Rank.EIGHT
        );
    }

}
