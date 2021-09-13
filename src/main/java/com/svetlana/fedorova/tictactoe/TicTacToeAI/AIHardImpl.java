package com.svetlana.fedorova.tictactoe.TicTacToeAI;

import static com.svetlana.fedorova.tictactoe.TicTacToeAI.Game.grid;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class AIHardImpl extends AI {

    private static char hardPlayer;
    private static char nonHardPlayer;

    @Override
    public void move(Terminal terminal, char playerChar) {
        hardPlayer = playerChar;
        nonHardPlayer = hardPlayer == 'X' ? 'O' : 'X';

        int[] originalBoard = new int[9];
        int n = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] != ' ') {
                    originalBoard[n] = grid[i][j];
                } else {
                    originalBoard[n] = n;
                }
                n++;
                if (n == 9) {
                    break;
                }
            }
        }
        int move = miniMax(originalBoard, hardPlayer);
        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (k == move) {
                    grid[i][j] = hardPlayer;
                    k++;
                }
            }
        }
    }

    private int[] emptyIndices(int[] board) {
        return Arrays.stream(board).filter(e -> e != 'X' && e != 'O')
            .toArray();
    }

    private boolean winning(int[] board, char player) {
        return (board[0] == player && board[1] == player && board[2] == player) ||
            (board[3] == player && board[4] == player && board[5] == player) ||
            (board[6] == player && board[7] == player && board[8] == player) ||
            (board[0] == player && board[3] == player && board[6] == player) ||
            (board[1] == player && board[4] == player && board[7] == player) ||
            (board[2] == player && board[5] == player && board[8] == player) ||
            (board[0] == player && board[4] == player && board[8] == player) ||
            (board[2] == player && board[4] == player && board[6] == player);
    }

    private int miniMax(int[] newBoard, char player) {
        int[] availSpots = emptyIndices(newBoard);

        if (winning(newBoard, nonHardPlayer)) {
            return -10;
        } else if (winning(newBoard, hardPlayer)) {
            return 10;
        } else if (availSpots.length == 0) {
            return 0;
        }

        Map<Integer, Integer> moves = new HashMap<>();
        for (int availSpot : availSpots) {
            moves.put(newBoard[availSpot], null);
            int temp = newBoard[availSpot];
            newBoard[availSpot] = player;
            int result;
            if (player == hardPlayer) {
                result = miniMax(newBoard, nonHardPlayer);
            } else {
                result = miniMax(newBoard, hardPlayer);
            }
            moves.put(availSpot, result);
            newBoard[availSpot] = temp;
        }
        int bestMove = 0;
        if (player == hardPlayer) {
            int bestScore = -1000;
            int maxValue = Collections.max(moves.values());
            if (maxValue > bestScore) {
                bestScore = maxValue;
            }
            for (Entry<Integer, Integer> entry : moves.entrySet()) {
                if (entry.getValue() == maxValue) {
                    bestMove = entry.getKey();
                }
            }
        } else {
            int bestScore = 1000;
            int minValue = Collections.min(moves.values());
            if (minValue < bestScore) {
                bestScore = minValue;
            }
            for (Entry<Integer, Integer> entry : moves.entrySet()) {
                if (entry.getValue() == minValue) {
                    bestMove = entry.getKey();
                }
            }
        }
        return bestMove;
    }
}