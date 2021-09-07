package com.svetlana.fedorova.tictactoe.TicTacToeAI;

import java.util.ArrayList;
import java.util.List;

import static com.svetlana.fedorova.tictactoe.TicTacToeAI.Game.grid;

public class AIHardImpl extends AI {

    private static String firstPlayer = "X";
    private static String secondPlayer = "O";

    @Override
    public void move(Terminal terminal, char playerChar) {

    }

    private List<String> getGridBeforeMinimax() {
        List<String> board = new ArrayList<>();
        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == ' ') {
                    board.add(String.valueOf(k));
                } else {
                    board.add(String.valueOf(grid[i][j]));
                }
                k++;
            }
        }
        return board;
    }

    private List<Integer> getEmptyIndices(List<String> board) {
        List<Integer> emptyIndices = new ArrayList<>();
        for (String cell : board) {
            if (!cell.equals("X") && !cell.equals("O")) {
                emptyIndices.add(Integer.parseInt(cell));
            }
        }
        return emptyIndices;
    }

    boolean winning(List<String> board, String player) {
        return (board.get(0).equals(player) && board.get(1).equals(player) && board.get(2)
            .equals(player)) ||
            (board.get(3).equals(player) && board.get(4).equals(player) && board.get(5)
                .equals(player)) ||
            (board.get(6).equals(player) && board.get(7).equals(player) && board.get(8)
                .equals(player)) ||
            (board.get(0).equals(player) && board.get(3).equals(player) && board.get(6)
                .equals(player)) ||
            (board.get(1).equals(player) && board.get(4).equals(player) && board.get(7)
                .equals(player)) ||
            (board.get(2).equals(player) && board.get(5).equals(player) && board.get(8)
                .equals(player)) ||
            (board.get(0).equals(player) && board.get(4).equals(player) && board.get(8)
                .equals(player)) ||
            (board.get(2).equals(player) && board.get(4).equals(player) && board.get(6)
                .equals(player));
    }

    int minimax(List<String> board, String player) {
        List<Integer> emptyIndices = getEmptyIndices(board);

        int result = 0;
        if (winning(board, firstPlayer)) {
            result = -1;
        } else if (winning(board, secondPlayer)) {
            result = 1;
        } else if (emptyIndices.size() == 0) {
        }
        return result;
    }


}
