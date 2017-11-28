/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greedyking;

import javax.swing.JFrame;

/**
 *
 * @author Jaime
 */
public class GreedyKing extends JFrame {
    public GreedyKing(String Nickname){
        initUI(Nickname);
        this.Nickname = Nickname;
    };
    private static String Nickname;
    private final int ancho = 17*16;
    private final int alto = 8*16;
    
    public void initUI(String Nickname){
        Board b=new Board();
        b.setNickName(Nickname);
        add(b);
        setSize(ancho*4+16,alto*4+39);//Ancho y alto
        setTitle("Greedy King");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    };
    
    public static void main(String[] args) {
        GreedyKing ex = new GreedyKing(Nickname);
        ex.setVisible(true);
    }   
}
