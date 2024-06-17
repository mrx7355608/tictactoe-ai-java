/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package players;

/**
 *
 * @author fawad
 */
public abstract class Player {
    protected String mark;

    public Player(String mark) {
        this.mark = mark;
    }
    
    public abstract void makeMove();
}
