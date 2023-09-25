package dev.ted.learn.primobs.blackjack;

import dev.ted.learn.primobs.blackjack.adapter.in.console.ConsoleGame;
import dev.ted.learn.primobs.blackjack.domain.Game;

public class Blackjack {

    public static void main(String[] args) {
        Game game = new Game();
        ConsoleGame consoleGame = new ConsoleGame(game);
        consoleGame.start();
    }

}
