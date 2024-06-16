
package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JPanel;
import tic.tac.toe.BoardUtils;
import tic.tac.toe.TicTacToe;

public class BoardGUI {
    private final TicTacToe tictactoe = TicTacToe.getInstance();
    private final JPanel buttonsPanel;
    private final BoardUtils boardUtils = new BoardUtils();

    public BoardGUI(JPanel buttonsPanel) {
        this.buttonsPanel = buttonsPanel;
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
            if (this.tictactoe.isValidMove(row, col)) {
                this.tictactoe.makeMove(row, col, "X");
                this.updateBoardGui(row, col, "X");
                this.tictactoe.switchTurn();
            }
        });

        return button;
    }
    
    public void updateBoardGui(int row, int col, String mark) {
        int btnIndex = this.boardUtils.convertToIndex(row, col);
        JButton btn = (JButton) this.buttonsPanel.getComponent(btnIndex);
        btn.setText(mark);
        this.buttonsPanel.revalidate();
        this.buttonsPanel.repaint();
    }
    
    public void clearBoard() {
        this.buttonsPanel.removeAll();
    }

}
