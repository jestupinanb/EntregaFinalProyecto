/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greedyking;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Jaime
 */
public class MapaColision extends MapaPadre {
    protected Graphics g;
    protected Rectangle colisionBloqueUp;
    protected Rectangle colisionBloquexyDown;
    protected Rectangle colisionBloquexyRight;
    protected Rectangle colisionBloquexyLeft;
    protected int moverMapa;

    public MapaColision(int posicionX, int posicionY,int scale,Graphics g,String bloquea,int moverMapa){
        super(posicionX, posicionY,scale);
        this.moverMapa = moverMapa;
        this.g=g;
        if(bloquea.equals("y")){
        //Superior
        makeCollisionUp();
        //Inferior
        makeCollisionDown();
        }else if(bloquea.equals("x")){
            //Izquierdo
            makeCollisionLeft();
            //Derecha
            makeCollisionRight();
        }else{
            //Superior
            makeCollisionUp();
            //Inferior
            makeCollisionDown();
            //Izquierdo
            makeCollisionLeft();
            //Derecha
            makeCollisionRight();
        };
    };
    
    private void makeCollisionUp(){
        this.colisionBloqueUp = new 
        Rectangle(posicionX * unidadMapaGrande+moverMapa, posicionY * unidadMapaGrande-1*scale, unidadMapaGrande, 1 * scale);
//        g.drawRect(colisionBloqueUp.x, colisionBloqueUp.y, colisionBloqueUp.width, colisionBloqueUp.height);
    };
    
    private void makeCollisionDown(){
        colisionBloquexyDown = new 
        Rectangle(posicionX * unidadMapaGrande+moverMapa, (posicionY + 1) * unidadMapaGrande, unidadMapaGrande, 1 * scale);
//        g.drawRect(colisionBloquexyDown.x, colisionBloquexyDown.y, colisionBloquexyDown.width, colisionBloquexyDown.height);
    };
    
    private void makeCollisionRight(){
        this.colisionBloquexyRight =
        new Rectangle((posicionX + 1) * unidadMapaGrande+moverMapa, posicionY * unidadMapaGrande, 1 * scale, unidadMapaGrande);
//        g.drawRect(colisionBloquexyRight.x, colisionBloquexyRight.y, colisionBloquexyRight.width, colisionBloquexyRight.height);
    };
    
    private void makeCollisionLeft(){
        colisionBloquexyLeft = new 
        Rectangle(posicionX * unidadMapaGrande - 1 * scale+moverMapa, posicionY * unidadMapaGrande, 1 * scale, unidadMapaGrande);
//        g.drawRect(colisionBloquexyLeft.x, colisionBloquexyLeft.y, colisionBloquexyLeft.width, colisionBloquexyLeft.height);
    };
    
    public Rectangle getCollisionBloqueUp(){
        return colisionBloqueUp;
    };

    public Rectangle getCollisionBloquexyDown() {
        return colisionBloquexyDown;
    }

    public Rectangle getCollisionBloquexyLeft() {
        return colisionBloquexyLeft;
    }

    public Rectangle getCollisionBloquexyRight() {
        return colisionBloquexyRight;
    }
}
