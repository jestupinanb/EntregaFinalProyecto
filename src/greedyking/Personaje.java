/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greedyking;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Jaime
 */
public class Personaje {
    private int scale;
    private int unidadAnchoRun;
    private int unidadAltoRun;
    private int unidadAnchoImagenOriginal;
    private int unidadAltoImagenOriginal;
    private int posicionX;
    private int posicionY;
    private int posicionXPaint;
    private int posicionYPaint;
    private int numImagen;//numero de animaciones
    private int[][] animacion = {//Matriz con las posiciones para la animacion del personaje
        {5, 2},//No se mueve derecha {5,2}
        {106, 27}, {131, 27}, {156, 27}, {181, 27},//movimiento a la derecha
        {22,2},//No se mueve izquierda
        {123, 27}, {148,27}, {173,27}, {198,27}
    };
    private boolean seUsoSentidoDeMovimiento;
    private Image pjImage;
    

    public Personaje(int escala) {
        this.pjImage = loadImage("Animacion Pj.png");
        this.scale = escala;
        this.unidadAnchoRun = 17 * this.scale;
        this.unidadAltoRun = 22 * this.scale;
        this.unidadAnchoImagenOriginal = 17;
        this.unidadAltoImagenOriginal = 22;
        this.posicionX = 8 * this.scale;
        this.posicionY = 74 * this.scale;
        this.posicionXPaint=posicionX;
        this.posicionYPaint=posicionY;
        this.numImagen = 0;
        this.seUsoSentidoDeMovimiento=false;
    };
    
    public void moveRight() {
        this.posicionX += 1 * scale;
    }

    public void sinMovimiento() {
        if(unidadAnchoImagenOriginal>0)
            this.numImagen = 0;
        else{
            this.numImagen = 5;
        };
        seUsoSentidoDeMovimiento=false;
    };

    public int getNumImagen() {
        return numImagen;
    }
    
    public void sentidoDeMovimiento(boolean derecha){
        if(!seUsoSentidoDeMovimiento){
        if(derecha){
            if(unidadAnchoImagenOriginal<0){
                this.numImagen=0;
                unidadAnchoImagenOriginal *=-1;
            };
        }else{
            if(unidadAnchoImagenOriginal>0){
                this.numImagen=5;
                unidadAnchoImagenOriginal *=-1;
            };
        };
        seUsoSentidoDeMovimiento=true;
        };
    };
    
    public void setNumImagen(int numImagen) {
        this.numImagen = numImagen;
//        System.out.println("Cambio de imagen a "+numImagen);
    }

    public int numeroDeImagenAncho() {
        return animacion[this.numImagen][0];
    };
    
    public int numeroDeImagenAlto() {
        return animacion[this.numImagen][1];
    };
    
    public void moveLeft() {
        this.posicionX -= 1 * scale;
    }

    public void moveUP() {
        this.posicionY -= 1 * scale;
    }

    public void moveDown() {
        this.posicionY += 1 * scale;
    }

    public void setPosicionX(int posicionX) {
        this.posicionX = posicionX;
    }

    public void setPosicionY(int posicionY) {
        this.posicionY = posicionY;
    }

    public int getUnidadAnchoRun() {
        return unidadAnchoRun;
    }

    public int getUnidadAltoRun() {
        return unidadAltoRun;
    }

    public int getUnidadAnchoImagenOriginal() {
        return unidadAnchoImagenOriginal;
    }

    public int getUnidadAltoImagenOriginal() {
        return unidadAltoImagenOriginal;
    }
    
    public void positionXYEqualsPositionXYPaint(){
        this.posicionXPaint=this.posicionX;
        this.posicionYPaint=this.posicionY;
    };
    
    public int getPositionX(){
        return this.posicionX;
    };
    
    public int getPositionY(){
        return this.posicionY;
    };
    
    public int getPositionXPaint() {
        return posicionXPaint;
    }

    public int getPositionYPaint() {
        return posicionYPaint;
    }

    public Image getPjImage() {
        return pjImage;
    }
    
    public Image loadImage(String imageName) {
        ImageIcon ii = new ImageIcon(imageName);
        Image image = ii.getImage();
        return image;
    };
};
