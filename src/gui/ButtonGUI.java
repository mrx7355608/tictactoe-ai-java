/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.awt.event.ActionEvent;
import javax.swing.JButton;
import tic.tac.toe.TicTacToe;

/**
 *
 * @author fawad
 */
public class ButtonGUI extends JButton {

    public int row;
    public int col;
    private TicTacToe tictactoe = TicTacToe.getInstance();

    public ButtonGUI(int row, int col, String data) {
        this.row = row;
        this.col = col;
        
        this.setText(data);
        this.setSize(80, 80);
        
        this.addActionListener((ActionEvent e) -> {
            if (this.tictactoe.isValidMove(row, col)) {
                this.tictactoe.makeMove(row, col, "X");
                this.setText("X");

                if (this.tictactoe.checkWinner() != null) {
                    // Highlight winning line
                    // Disable moves
                    // Show gameover screen
                    // With rematch and quit functions
                }
            }
        });
    }
}
