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
    public GreedyKing(){
        initUI();
    };
    
    public void initUI(){
        add(new Board());
        setSize(272*4+16,128*4+39);
        setTitle("Greedy King");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    };
    
    public static void main(String[] args) {
        GreedyKing ex = new GreedyKing();
        ex.setVisible(true);
    }   
}
