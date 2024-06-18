package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import tic.tac.toe.TicTacToe;

public class GameGUI extends JFrame implements ActionListener {

    private final TicTacToe tictactoe = TicTacToe.getInstance();
    private final JLabel label = new JLabel();
    private final JPanel panel = new JPanel(new GridLayout(3, 3));
    private String gameMode;

    public GameGUI(String mode) {
        this.gameMode = mode;

        // Create game window
        setTitle("Tic Tac Toe");
        setLayout(new BorderLayout());
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a text label
        label.setText("Tic Tac Toe");
        label.setFont(new Font("Arial", Font.BOLD, 30));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setPreferredSize(new Dimension(500, 100));
        add(label, BorderLayout.NORTH);

        // Create tictactoe grid
        panel.setPreferredSize(new Dimension(500, 350));
        add(panel, BorderLayout.SOUTH);
        this.createGrid();
    }

    private void createGrid() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                ButtonGUI b = new ButtonGUI(i, j);
                b.addActionListener(this);
                b.setSize(50, 50);
                panel.add(b);
            }
        }

        panel.repaint();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // If the thing being clicked is not a button, then do nothing
        if (e.getSource() instanceof JButton == false) {
            return;
        }

        // Get the button object
        ButtonGUI button = (ButtonGUI) e.getSource();

        // If the clicked button is not empty, do nothing
        if (button.getText().isBlank() == false) {
            return;
        }

        // Otherwise, handle game according to game mode
        if ("player-vs-player".equals(this.gameMode)) {
            if (this.tictactoe.getTurn() == 1) {
                this.tictactoe.updateBoard(button.row, button.col, "X");
                button.setForeground(Color.red);
                button.setText("X");
            } else {
                this.tictactoe.updateBoard(button.row, button.col, "O");
                button.setForeground(Color.cyan);
                button.setText("O");
            }

            // Switch turn
            this.tictactoe.switchTurn();

            // Update label text according to turns
            int turn = this.tictactoe.getTurn();
            if (turn == 1) {
                label.setText("X's turn");
            } else {
                label.setText("O's turn");
            }

        } else {
            // Handle player's move
            this.tictactoe.updateBoard(button.row, button.col, "X");
            button.setForeground(Color.red);
            button.setText("X");

            // Handle computer's move
            this.tictactoe.computerMakeMove();
            int[] computerMove = this.tictactoe.getComputerMove();
            for (Component component : this.panel.getComponents()) {
                ButtonGUI btn = (ButtonGUI) component;
                if (btn.row == computerMove[0] && btn.col == computerMove[1]) {
                    btn.setForeground(Color.cyan);
                    btn.setText("O");
                }
            }
        }

        // Check winner
        String winner = this.tictactoe.checkWinner();
        if (winner != null) {
            this.disableMoves();
            this.displayGameOver(winner);
        }
    }

    private void displayGameOver(String winner) {
        GameOver gameOverScreen = new GameOver(winner);
        gameOverScreen.setVisible(true);
    }

    private void disableMoves() {
        for (Component component : panel.getComponents()) {
            ButtonGUI button = (ButtonGUI) component;
            button.setEnabled(false);
        }
    }
}
