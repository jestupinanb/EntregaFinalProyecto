/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greedyking;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Estudiante
 */
public class ColisionBloqueLargo extends MapaPadre{
    protected Graphics g;
    protected int posicionXFinal;
    protected int posicionYFinal;
    protected Rectangle ColisionBloque;
    public ColisionBloqueLargo(int posicionX, int posicionY, int scale,Graphics g, int posicionXFinal, int posicionYFinal) {
        super(posicionX, posicionY, scale);
        this.g = g;
        this.posicionXFinal = posicionXFinal;
        this.posicionYFinal = posicionYFinal;
        makeCollision();
    }
    private void makeCollision(){
        this.ColisionBloque = new
        Rectangle(posicionX * unidadMapaGrande, posicionY * unidadMapaGrande, posicionXFinal * unidadMapaGrande, posicionYFinal * unidadMapaGrande);
        g.drawRect(ColisionBloque.x, ColisionBloque.y, ColisionBloque.width, ColisionBloque.height);
//        System.out.println(posicionX+","+posicionY+","+posicionXFinal+","+posicionYFinal);
//        g.drawRect(0*unidadMapaGrande, 8*unidadMapaGrande, 18*unidadMapaGrande, 1*unidadMapaGrande);
    };

    public Rectangle getColisionBloque() {
        return ColisionBloque;
    }
}
