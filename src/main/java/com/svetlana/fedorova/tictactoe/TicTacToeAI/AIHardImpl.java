package com.svetlana.fedorova.tictactoe.TicTacToeAI;

import static com.svetlana.fedorova.tictactoe.TicTacToeAI.Game.currentState;
import static com.svetlana.fedorova.tictactoe.TicTacToeAI.Game.grid;
import static com.svetlana.fedorova.tictactoe.TicTacToeAI.State.ONGOING_GAME;

public class AIHardImpl extends AI {

    private static final int MAX_DEPTH = 6;
    Game game = new Game();

    @Override
    public void move(Terminal terminal, char playerChar) {
        int[] move = getBestMove(playerChar);
        grid[move[0]][move[1]] = playerChar;
        currentState = ONGOING_GAME;
    }

    private int[] getBestMove(char playerChar) {
        int[] bestMove = new int[2];
        int bestValue = Integer.MIN_VALUE;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == ' ') {
                    grid[i][j] = playerChar;
                    int moveValue = miniMax(MAX_DEPTH, false, playerChar);
                    grid[i][j] = ' ';
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

    private int miniMax(int depth, boolean isMax, char playerChar) {
        int boardVal = winning(playerChar);
        if (boardVal == Math.abs(10) || boardVal == 0 || depth == 0) {
            return boardVal;
        }

        if (isMax) {
            int highestValue = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (grid[i][j] == ' ') {
                        grid[i][j] = playerChar;
                        highestValue = Math.max(highestValue,
                            miniMax(depth - 1, false, playerChar));
                        grid[i][j] = ' ';
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
                        lowestValue = Math.min(lowestValue, miniMax(depth - 1, true, playerChar));
                        grid[i][j] = ' ';
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
        } else if (winner == ' ' && currentState == ONGOING_GAME) {
            return 2;
        }
        return 0;
    }
}

