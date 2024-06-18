/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tic.tac.toe;

import java.util.ArrayList;

/**
 *
 * @author fawad
 */
public class BoardUtils {
    private final TicTacToe tictactoe = TicTacToe.getInstance();
    private int convertToIndex(int row, int col) {
        return switch (row) {
            case 0 ->
                col + 0;
            case 1 ->
                col + 3;
            default ->
                col + 6;
        };
    }
    
    public ArrayList discoverWinningIndices() {
        ArrayList winningIndices = new ArrayList();
        String[][] gameboard = this.tictactoe.getGameboard();
        // Horizontal checks
        for (int i = 0; i < 3; i++) {
            if (gameboard[i][0].isBlank() == false
                    && gameboard[i][0].equals(gameboard[i][1])
                    && gameboard[i][0].equals(gameboard[i][2])) {
                winningIndices.add(this.convertToIndex(i, 0));
                winningIndices.add(this.convertToIndex(i, 1));
                winningIndices.add(this.convertToIndex(i, 2));
            }
        }

        // Vertical checks
        for (int i = 0; i < 3; i++) {
            if (gameboard[0][i].isBlank() == false
                    && gameboard[0][i].equals(gameboard[1][i])
                    && gameboard[0][i].equals(gameboard[2][i])) {
                winningIndices.add(this.convertToIndex(0, i));
                winningIndices.add(this.convertToIndex(1, i));
                winningIndices.add(this.convertToIndex(2, i));
            }
        }

        // Diagonal checks
        if (gameboard[0][0].isBlank() == false
                && gameboard[0][0].equals(gameboard[1][1])
                && gameboard[0][0].equals(gameboard[2][2])) {
            winningIndices.add(this.convertToIndex(0, 0));
            winningIndices.add(this.convertToIndex(1, 1));
            winningIndices.add(this.convertToIndex(2, 2));
        }
        if (gameboard[0][2].isBlank() == false
                && gameboard[0][2].equals(gameboard[1][1])
                && gameboard[0][2].equals(gameboard[2][0])) {
            winningIndices.add(this.convertToIndex(0, 2));
            winningIndices.add(this.convertToIndex(1, 1));
            winningIndices.add(this.convertToIndex(2, 0));
        }
        
        return winningIndices;
    }
}
