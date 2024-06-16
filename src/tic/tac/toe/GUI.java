/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tic.tac.toe;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GUI {

    JFrame frame = new JFrame();
    JLabel label = new JLabel();
    JPanel buttonsPanel = new JPanel(new GridLayout(3, 3));
    
    TicTacToe tictactoe = new TicTacToe();

    public void start() {
        this.setupWindow();
        this.setupLabel();
        this.displayboard();
    }

    private void setupWindow() {
        frame.setSize(500, 500);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        String turn = this.tictactoe.getTurn();
        
        String winner = this.tictactoe.checkWinner();
        if (winner != null) {
            new GameOver(winner).setVisible(true);
            this.highlightWinningButtons();
            this.disableMoves();
            return;
        }

        if ("computer".equals(turn)) {
            this.tictactoe.computerMakeMove();
            int[] move = this.tictactoe.getComputerMove();
            this.updateBoardGui(move[0], move[1], "O");
        }
        
        String winner2 = this.tictactoe.checkWinner();
        if (winner2 != null) {
            new GameOver(winner2).setVisible(true);
            this.highlightWinningButtons();
            this.disableMoves();
            return;
        }
        
        this.tictactoe.switchTurn();
    }

    public void displayboard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttonsPanel.add(this.createbutton(i, j));
            }
        }
        
        buttonsPanel.revalidate();
        buttonsPanel.repaint();
    }

    public JButton createbutton(int row, int col) {
        String data = this.tictactoe.getData(row, col);
        JButton button = new JButton(data);
        button.setFont(new Font("Fira Code", Font.BOLD, 40));
        button.setBackground(Color.white);
        button.setForeground(Color.magenta);

        button.addActionListener((ActionEvent e) -> {
            this.tictactoe.makeMove(row, col, "X");
            this.updateBoardGui(row, col, "X");
            this.tictactoe.switchTurn();
            this.mainloop();
        });

        return button;
    }

    public void highlightWinningButtons() {
        Component[] components = this.buttonsPanel.getComponents();
        for (int i = 0; i < components.length - 1; i++) {
            for (Object index : this.discoverWinningIndices()) {
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

    public void updateBoardGui(int row, int col, String mark) {
        int btnIndex = this.convertToIndex(row, col);
        JButton btn = (JButton) this.buttonsPanel.getComponent(btnIndex);
        btn.setText(mark);
        this.buttonsPanel.revalidate();
        this.buttonsPanel.repaint();
    }

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
