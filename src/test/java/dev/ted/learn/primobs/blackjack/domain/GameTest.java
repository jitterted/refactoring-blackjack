package dev.ted.learn.primobs.blackjack.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class GameTest {

    @Test
    void initialDealAfterCardsAlreadyDealtThrowsException() {
        Game game = new Game();
        game.initialDeal();

        assertThatThrownBy(game::initialDeal)
                .isExactlyInstanceOf(CardsAlreadyDealt.class);
    }

    @Test
    void playerHitsAndGoesBustThenOutcomeIsPlayerLoses() {
        Game game = new Game(DeckFactory.playerHitsAndGoesBust());
        game.initialDeal();

        game.playerHits();

        assertThat(game.determineOutcome())
                .isEqualByComparingTo(GameOutcome.PLAYER_BUSTED);
        assertThat(game.isPlayerDone())
                .isTrue();
    }

    @Test
    void playerDealtBetterHandThanDealerAndStandsThenPlayerBeatsDealer() {
        Game game = new Game(DeckFactory.playerStandsAndBeatsDealer());
        game.initialDeal();

        game.playerStands();
        game.dealerTurn();

        assertThat(game.determineOutcome())
                .isEqualByComparingTo(GameOutcome.PLAYER_BEATS_DEALER);
        assertThat(game.isPlayerDone())
                .isTrue();
    }

    @Test
    void playerDealtHandWithSameValueAsDealerThenPlayerPushesDealer() {
        Game game = new Game(DeckFactory.playerStandsAndPushesDealer());
        game.initialDeal();

        game.playerStands();
        game.dealerTurn();

        assertThat(game.determineOutcome())
                .isEqualByComparingTo(GameOutcome.PLAYER_PUSHES);
        assertThat(game.isPlayerDone())
                .isTrue();
    }

    @Test
    void playerDealtBlackjackUponInitialDealThenWinsBlackjack() {
        Game game = new Game(DeckFactory.playerDealtBlackjackDealerNoBlackjack());

        game.initialDeal();

        assertThat(game.determineOutcome())
                .isEqualByComparingTo(GameOutcome.PLAYER_WINS_BLACKJACK);
        assertThat(game.isPlayerDone())
                .isTrue();
    }

    @Test
    void playerWinsButNotWithBlackjackWhenHandValueIs21() {
        Game game = new Game(DeckFactory.playerGets21AfterHitsAndBeatsDealer());
        game.initialDeal();

        game.playerHits();
        game.playerStands();
        game.dealerTurn();

        assertThat(game.determineOutcome())
                .isEqualByComparingTo(GameOutcome.PLAYER_BEATS_DEALER);
        assertThat(game.isPlayerDone())
                .isTrue();
    }

    @Test
    void playerNotDealtBlackjackUponInitialDealAndDoesNotHitNorStandThenIsNotDone() {
        Game game = new Game(DeckFactory.playerNotDealtBlackjack());

        game.initialDeal();

        assertThat(game.isPlayerDone())
                .isFalse();
    }

    @Test
    void playerHitBeforeInitialDealThrowsException() {
        Game game = new Game(DeckFactory.playerHitsAndGoesBust());

        assertThatThrownBy(game::playerHits)
                .isExactlyInstanceOf(InitialCardsNotDealt.class);
    }

    @Test
    void playerStandsBeforeInitialDealThrowsException() {
        Game game = new Game(DeckFactory.playerStandsAndBeatsDealer());

        assertThatThrownBy(game::playerStands)
                .isExactlyInstanceOf(InitialCardsNotDealt.class);
    }

    @Test
    void playerHitAfterAlreadyDoneThrowsException() {
        Game game = new Game(DeckFactory.playerHitsAndGoesBust());
        game.initialDeal();
        game.playerHits();

        assertThatThrownBy(game::playerHits)
                .isExactlyInstanceOf(PlayerAlreadyDone.class);
    }

    @Test
    void playerStandsAfterAlreadyDoneThrowsException() {
        Game game = new Game(DeckFactory.playerStandsAndBeatsDealer());
        game.initialDeal();
        game.playerStands();

        assertThatThrownBy(game::playerStands)
                .isExactlyInstanceOf(PlayerAlreadyDone.class);
    }

    @Test
    void dealerTurnBeforePlayerDoneThrowsException() {
        Game game = new Game(DeckFactory.playerStandsAndBeatsDealer());
        game.initialDeal();

        assertThatThrownBy(game::dealerTurn)
                .isExactlyInstanceOf(PlayerNotDone.class);

    }

    @Test
    void dealerTurnAfterDealerDoneThrowsException() {
        Game game = new Game(DeckFactory.playerNotDealtBlackjack());
        game.initialDeal();
        game.playerStands();
        game.dealerTurn();

        assertThatThrownBy(game::dealerTurn)
                .isExactlyInstanceOf(DealerAlreadyDone.class);
    }
}



