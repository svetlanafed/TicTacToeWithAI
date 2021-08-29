package com.svetlana.fedorova.tictactoe.TicTacToeAI;

public class Launcher {

    public static void main(String[] args) {
        Terminal terminal = new Terminal(System.in, System.out);
        Game game = new Game();

        // заменить имплементацию в зависимости от типа ввода начальной сетки
        StartGrid grid = new StartStandardGridImpl();
        //StartGrid grid = new StartGridFromLineImpl();

        grid.createStartGrid(terminal);
        game.showCurrentGrid(terminal, Game.grid);
        boolean isGamerMoved;
        do {
            terminal.println("Enter the coordinates: ");
            isGamerMoved = game.makeAMove(terminal, terminal.readLine());
        } while (!isGamerMoved);
        game.showCurrentGrid(terminal, Game.grid);
        game.getGameResult(terminal, Game.grid);
    }
}
