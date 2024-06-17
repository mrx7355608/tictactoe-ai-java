package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import players.ComputerPlayer;
import players.HumanPlayer;
import players.Player;
import tic.tac.toe.TicTacToe;

public class GameGUI extends JFrame {
    private final TicTacToe tictactoe = TicTacToe.getInstance();
    private final JLabel label = new JLabel();
    private final JPanel panel = new JPanel(new GridLayout(3,3));
    private static Player playerOne;
    private static Player playerTwo;
    
    public GameGUI(String mode) {
        // Create game window
        setTitle("Tic Tac Toe");
        setLayout(new BorderLayout());
        setSize(500,500);
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
        
        // Setup players
        if(mode.equals("player-vs-player")) {
            playerOne = new HumanPlayer("X");
            playerTwo = new HumanPlayer("O");
        } else {
            playerOne = new HumanPlayer("X");
            playerTwo = new ComputerPlayer("O");
        }
    }
    
    private void createGrid() {
        String[][] gameboard = this.tictactoe.getGameboard();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton b = new JButton(gameboard[i][j]);
                b.setSize(50, 50);
                panel.add(b);
            }
        }
        
        panel.revalidate();
        panel.repaint();

    }
    
//    @Override
//    public void paint(Graphics g) {
//        super.paint(g);
//        this.panel.repaint();
//    }
}
