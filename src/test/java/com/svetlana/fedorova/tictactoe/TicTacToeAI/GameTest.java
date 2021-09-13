package com.svetlana.fedorova.tictactoe.TicTacToeAI;

import static com.svetlana.fedorova.tictactoe.TicTacToeAI.Game.currentState;
import static com.svetlana.fedorova.tictactoe.TicTacToeAI.Game.xAmount;
import static com.svetlana.fedorova.tictactoe.TicTacToeAI.State.ONGOING_GAME;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class GameTest {

    @Test
    void getCorrectParam() {
        String input = "start user easy";
        assertThat(currentState).isSameAs(ONGOING_GAME);
        assertThat(xAmount).isEqualTo(0);
    }

    @Test
    void getIncorrectParam() {
        String input = "start user easy";

    }

    @Test
    void isCellOccupied() {
    }

    @Test
    void playGame() {

    }

    @Test
    void getGameResult() {
    }
}