/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greedyking;

/**
 *
 * @author Jaime
 */
public abstract class MapaPadre {
      protected int posicionX;
      protected int posicionY;
      protected int scale;
      protected int unidadMapaGrande;

    public MapaPadre(int posicionX, int posicionY,int scale) {
        this.scale=scale;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.unidadMapaGrande = 16*scale;
    }
      
    public int getPosicionX() {
        return posicionX;
    }

    public void setPosicionX(int posicionX) {
        this.posicionX = posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }

    public void setPosicionY(int posicionY) {
        this.posicionY = posicionY;
    }
}
