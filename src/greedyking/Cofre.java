/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greedyking;

import java.awt.Graphics;

/**
 *
 * @author JaimeEduardo
 */
public class Cofre extends ColisionBloqueLargo {
    private final int posXMatriz;
    private final int posYMatriz;    
    public Cofre(int posicionX, int posicionY, int scale, Graphics g, int posicionXFinal, int posicionYFinal,int posXMatriz,int posYMatriz) {
        super(posicionX, posicionY, scale, g, posicionXFinal, posicionYFinal);
        this.posXMatriz = posXMatriz;
        this.posYMatriz = posYMatriz;
    }

    public int getPosXMatriz() {
        return posXMatriz;
    }

    public int getPosYMatriz() {
        return posYMatriz;
    }
}
