package com.svetlana.fedorova.tictactoe.TicTacToeAI;

import java.util.ArrayList;
import java.util.List;

import static com.svetlana.fedorova.tictactoe.TicTacToeAI.Game.grid;

public class AIHardImpl extends AI {

    @Override
    public void move(Terminal terminal, char playerChar) {

    }

    private List<String> getGridBeforeMinimax() {
        List<String> gridBeforeMinimax = new ArrayList<>();
        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == ' ') {
                    gridBeforeMinimax.add(String.valueOf(k));
                } else {
                    gridBeforeMinimax.add(String.valueOf(grid[i][j]));
                }
                k++;
            }
        }
        return gridBeforeMinimax;
    }

    private List<Integer> getEmptyIndices(List<String> gridBeforeMinimax) {
        List<Integer> emptyIndices = new ArrayList<>();
        for (String cell : gridBeforeMinimax) {
            if (!cell.equals("X") && !cell.equals("O")) {
                emptyIndices.add(Integer.parseInt(cell));
            }
        }
        return emptyIndices;
    }
}
