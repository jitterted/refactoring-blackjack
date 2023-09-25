package dev.ted.learn.primobs.blackjack.adapter.in.console;

import dev.ted.learn.primobs.blackjack.domain.Game;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import java.util.Scanner;

import static org.fusesource.jansi.Ansi.ansi;

public class ConsoleGame {

    private final Game game;
    private final Scanner scanner;

    public ConsoleGame(Game game) {
        this.game = game;
        scanner = new Scanner(System.in);
    }

    public void start() {
        displayWelcomeScreen();
        waitForEnterFromUser();

        game.initialDeal();

        playerPlays();

        game.dealerTurn();

        displayFinalGameState();

        System.out.println(game.determineOutcome().displayString());

        resetScreen();
    }

    private void displayGameState() {
        System.out.print(ansi().eraseScreen().cursor(1, 1));
        System.out.println("Dealer has: ");
        System.out.println(ConsoleCard.forDisplayOf(game.dealerHand().get(0))); // first card is face-up
        System.out.println(ConsoleCard.backOfCard()); // second card ("hole" card) is face-down

        System.out.println("Player has: ");
        System.out.println(ConsoleHand.cardsAsString(game.playerHand()));
        System.out.println(" (" + game.playerHandValue() + ")");
    }

    private void displayFinalGameState() {
        System.out.print(ansi().eraseScreen().cursor(1, 1));
        System.out.println("Dealer has: ");
        System.out.println(ConsoleHand.cardsAsString(game.dealerHand()));
        System.out.println(" (" + game.dealerHandValue() + ")");

        System.out.println();
        System.out.println("Player has: ");
        System.out.println(ConsoleHand.cardsAsString(game.playerHand()));
        System.out.println(" (" + game.playerHandValue() + ")");
    }


    private void playerPlays() {
        while (!game.isPlayerDone()) {
            displayGameState();
            String command = commandFromPlayer();
            handle(command);
        }
    }

    private void handle(String command) {
        if (command.toLowerCase().startsWith("h")) {
            game.playerHits();
        } else if (command.toLowerCase().startsWith("s")) {
            game.playerStands();
        }
    }

    private void resetScreen() {
        System.out.println(ansi().reset());
    }

    private void waitForEnterFromUser() {
        System.out.println(ansi()
                                   .cursor(3, 1)
                                   .fgBrightBlack().a("Hit [ENTER] to start..."));

        System.console().readLine();
    }

    private void displayWelcomeScreen() {
        AnsiConsole.systemInstall();
        System.out.println(ansi()
                                   .bgBright(Ansi.Color.WHITE)
                                   .eraseScreen()
                                   .cursor(1, 1)
                                   .fgGreen().a("Welcome to")
                                   .fgRed().a(" JitterTed's")
                                   .fgBlack().a(" BlackJack game"));
    }

    private String commandFromPlayer() {
        System.out.println("[H]it or [S]tand?");
        return scanner.nextLine();
    }

}
