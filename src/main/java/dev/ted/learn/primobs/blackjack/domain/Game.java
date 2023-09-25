package dev.ted.learn.primobs.blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {

    private final List<Card> deck;

    private final List<Card> dealerHand = new ArrayList<>();
    private final List<Card> playerHand = new ArrayList<>();

    private boolean initialCardsDealt;
    private boolean playerDone;
    private boolean dealerDone;

    public Game() {
        this.deck = createShuffledDeck();
    }

    public Game(List<Card> deck) {
        this.deck = new ArrayList<>(deck);
    }

    public void initialDeal() {
        requireCardsNotDealt();

        dealRoundOfCards();
        dealRoundOfCards();

        initialCardsDealt = true;
        playerDone = hasBlackjack(playerHand);
    }

    public void playerHits() {
        requireInitialCardsDealt();
        requirePlayerNotDone();
        playerHand.add(deck.remove(0));
        playerDone = isBusted(playerHand);
    }

    public void playerStands() {
        requireInitialCardsDealt();
        requirePlayerNotDone();
        playerDone = true;
    }

    public boolean isPlayerDone() {
        return playerDone;
    }

    public void dealerTurn() {
        requirePlayerDone();
        requireDealerNotDone();

        // Dealer takes turn only if the player stood, otherwise the player already lost because they must have busted
        if (!isBusted(playerHand)) {
            // Dealer makes its choice automatically based on a simple heuristic (<=16 must hit, =>17 must stand)
            while (handValue(dealerHand) <= 16) {
                dealerHand.add(deck.remove(0));
            }
        }
        dealerDone = true;
    }

    public List<Card> playerHand() {
        return playerHand;
    }

    public List<Card> dealerHand() {
        return dealerHand;
    }

    public int playerHandValue() {
        return handValue(playerHand);
    }

    public int dealerHandValue() {
        return handValue(dealerHand);
    }

    public GameOutcome determineOutcome() {
        if (isBusted(playerHand)) {
            return GameOutcome.PLAYER_BUSTED;
        } else if (hasBlackjack(playerHand)) {
            return GameOutcome.PLAYER_WINS_BLACKJACK;
        } else if (isBusted(dealerHand)) {
            return GameOutcome.DEALER_BUSTED;
        } else if (handValue(playerHand) > handValue(dealerHand)) {
            return GameOutcome.PLAYER_BEATS_DEALER;
        } else if (handValue(playerHand) == handValue(dealerHand)) {
            return GameOutcome.PLAYER_PUSHES;
        } else {
            return GameOutcome.PLAYER_LOSES;
        }
    }

    public boolean isBusted(List<Card> hand) {
        return handValue(hand) > 21;
    }

    public boolean hasBlackjack(List<Card> hand) {
        return handValue(hand) == 21 && hand.size() == 2;
    }

    private List<Card> createShuffledDeck() {
        List<Card> cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(rank, suit));
            }
        }
        Collections.shuffle(cards);
        return cards;
    }

    private void dealRoundOfCards() {
        // Players go first because of the rule of dealing cards in Blackjack
        playerHand.add(deck.remove(0));
        dealerHand.add(deck.remove(0));
    }

    private int handValue(List<Card> hand) {
        int handValue = hand
                .stream()
                .mapToInt(Card::rankValue)
                .sum();

        // does the hand contain at least 1 Ace?
        boolean hasAce = hand
                .stream()
                .anyMatch(card -> card.rankValue() == 1);

        // if the total hand value <= 11, then count the Ace as 11 by adding 10
        if (hasAce && handValue <= 11) {
            handValue += 10;
        }

        return handValue;
    }

    private void requirePlayerNotDone() {
        if (playerDone) {
            throw new PlayerAlreadyDone();
        }
    }

    private void requireInitialCardsDealt() {
        if (!initialCardsDealt) {
            throw new InitialCardsNotDealt();
        }
    }

    private void requireDealerNotDone() {
        if (dealerDone) {
            throw new DealerAlreadyDone();
        }
    }

    private void requirePlayerDone() {
        if (!playerDone) {
            throw new PlayerNotDone();
        }
    }

    private void requireCardsNotDealt() {
        if (initialCardsDealt) {
            throw new CardsAlreadyDealt();
        }
    }

}
