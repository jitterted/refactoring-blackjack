package dev.ted.learn.primobs.blackjack.domain;

public enum GameOutcome {
    PLAYER_BUSTED("You Busted, so you lose.  💸"),
    DEALER_BUSTED("Dealer went BUST, Player wins! Yay for you!! 💵"),
    PLAYER_BEATS_DEALER("You beat the Dealer! 💵"),
    PLAYER_PUSHES("Push: Nobody wins, we'll call it even."),
    PLAYER_LOSES("You lost to the Dealer. 💸"),
    PLAYER_WINS_BLACKJACK("You win with Blackjack!! 💵");

    private final String display;

    GameOutcome(String display) {
        this.display = display;
    }

    public String displayString() {
        return display;
    }
}