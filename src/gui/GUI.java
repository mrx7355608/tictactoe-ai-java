/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import tic.tac.toe.BoardUtils;
import tic.tac.toe.TicTacToe;

public class GUI {
    JFrame frame = new JFrame();
    private final JLabel label = new JLabel();
    private final JPanel buttonsPanel = new JPanel(new GridLayout(3, 3));
    private final TicTacToe tictactoe = TicTacToe.getInstance();
    
    private final BoardGUI boardGUI = new BoardGUI(buttonsPanel);
    private final BoardUtils boardUtils = new BoardUtils();
    
    public void start() {
        this.setupWindow();
        this.setupLabel();
        this.mainloop();
    }

    private void setupWindow() {
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        frame.add(buttonsPanel);
    }

    private void setupLabel() {
        label.setText("Tic Tac Toe");
        label.setPreferredSize(new Dimension(500, 80));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 30));
        frame.add(label, BorderLayout.NORTH);
    }

    public void mainloop() {
        this.boardGUI.clearBoard();
        this.boardGUI.displayboard();
        
        String winner = this.tictactoe.checkWinner();
        if (winner != null) {
            new GameOver(winner, this).setVisible(true);
            this.highlightWinningButtons();
            this.disableMoves();
            return;
        }

        if ("computer".equals(this.tictactoe.getTurn())) {
            this.tictactoe.computerMakeMove();
            int[] move = this.tictactoe.getComputerMove();
            this.boardGUI.updateBoardGui(move[0], move[1], "O");
        } else {
            
        }
        
        String winner2 = this.tictactoe.checkWinner();
        if (winner2 != null) {
            new GameOver(winner2, this).setVisible(true);
            this.highlightWinningButtons();
            this.disableMoves();
            return;
        }
        
        this.tictactoe.switchTurn();
    }

    public void highlightWinningButtons() {
        Component[] components = this.buttonsPanel.getComponents();
        
        for (int i = 0; i < components.length - 1; i++) {
            for (Object index : this.boardUtils.discoverWinningIndices()) {
                int idx = (int) index;
                JButton b = (JButton) this.buttonsPanel.getComponent(idx);
                b.setBackground(Color.green);
            }
        }
    }

    public void disableMoves() {
        for (Component component : this.buttonsPanel.getComponents()) {
            component.setEnabled(false);
        }
    }
    
    public void restart(){
        this.buttonsPanel.removeAll();
        this.tictactoe.reset();
        this.start();
    }
    
}
