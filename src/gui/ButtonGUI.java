/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import tic.tac.toe.TicTacToe;

/**
 *
 * @author fawad
 */
public class ButtonGUI extends JButton {

    public int row;
    public int col;

    public ButtonGUI(int row, int col) {
        this.row = row;
        this.col = col;
        this.setSize(80, 80);
        this.setBackground(Color.white);
        this.setFont(new Font("Arial", Font.BOLD, 40));
    }
}
