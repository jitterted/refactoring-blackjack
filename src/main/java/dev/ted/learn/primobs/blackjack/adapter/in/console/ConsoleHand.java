package dev.ted.learn.primobs.blackjack.adapter.in.console;

import dev.ted.learn.primobs.blackjack.domain.Card;

import java.util.List;
import java.util.stream.Collectors;

import static org.fusesource.jansi.Ansi.ansi;

class ConsoleHand {

    static String displayFaceUpCard(List<Card> hand) {
        return ConsoleCard.forDisplayOf(hand.get(0));
    }

    static String cardsAsString(List<Card> hand) {
        return hand.stream()
                   .map(ConsoleCard::forDisplayOf)
                   .collect(Collectors.joining(
                           ansi().cursorUp(6).cursorRight(1).toString()));
    }
}
