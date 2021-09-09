package com.svetlana.fedorova.tictactoe.TicTacToeAI;

import static com.svetlana.fedorova.tictactoe.TicTacToeAI.Game.currentState;
import static com.svetlana.fedorova.tictactoe.TicTacToeAI.Game.grid;
import static com.svetlana.fedorova.tictactoe.TicTacToeAI.State.DRAW;
import static com.svetlana.fedorova.tictactoe.TicTacToeAI.State.ONGOING_GAME;

public class AIHardImpl extends AI {

    Game game = new Game();

    @Override
    public void move(Terminal terminal, char playerChar) {
        int[] move = getBestMove(playerChar);
        grid[move[0]][move[1]] = playerChar;
    }

    private int[] getBestMove(char playerChar) {
        int[] bestMove = new int[2];
        int bestValue = Integer.MIN_VALUE;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == ' ') {
                    grid[i][j] = playerChar;
                    int moveValue = miniMax(false, playerChar);
                    grid[i][j] = ' ';
                    currentState = ONGOING_GAME;
                    if (moveValue > bestValue) {
                        bestValue = moveValue;
                        bestMove[0] = i;
                        bestMove[1] = j;
                    }
                }
            }
        }
        return bestMove;
    }

    private int miniMax(boolean isMax, char playerChar) {
        int boardVal = winning(playerChar);
        if (boardVal == 10 || boardVal == -10 || boardVal == 0) {
            return boardVal;
        }

        if (isMax) {
            int highestValue = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (grid[i][j] == ' ') {
                        grid[i][j] = playerChar;
                        highestValue = Math.max(highestValue,
                            miniMax(false, playerChar));
                        grid[i][j] = ' ';
                        currentState = ONGOING_GAME;
                    }
                }
            }
            return highestValue;
        } else {
            int lowestValue = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (grid[i][j] == ' ') {
                        if (playerChar == 'X') {
                            grid[i][j] = 'O';
                        } else {
                            grid[i][j] = 'X';
                        }
                        lowestValue = Math.min(lowestValue, miniMax(true, playerChar));
                        grid[i][j] = ' ';
                        currentState = ONGOING_GAME;
                    }
                }
            }
            return lowestValue;
        }
    }

    private int winning(char playerChar) {
        char winner = game.getGameResult();
        if (winner == playerChar) {
            return 10;
        } else if (winner != playerChar && winner != ' ') {
            return -10;
        } else if (winner == ' ' && currentState == DRAW) {
            return 0;
        }
        return 2;
    }
}

