/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greedyking;

import java.awt.Image;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.Timer;

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
        {123, 27}, {148,27}, {173,27}, {198,27},//movimiento a la izquierda{}
        {105,2},{130,2},{155,2},{181,2},//salto derecha
        {122,2},{147,2},{172,2},{198,2}//salto izquierda
    };
    private int[][] imgVidas ={
        {0,0},{23,0},{46,0},{253,0},//Imagenes de las vidas 1,2,3
        {69,0},{92,0},{115,0},{138,0},{161,0},{184,0},//Perdidad corazon
        {207,0},{230,0}//perdida juego
    };
    private int[] posicionesDeImgAnimacionVidaPerdida = {4,5,6,7,8,9,10,11};
    private boolean noPermitirCambioImgMovimiento;
    private Image pjImage;
    private Image vidaImage;
    private int vidas;
    private int numImgVida;
    private int anchoImgVida;
    private int altoImgVida;
    
    public Personaje(int escala) {
        this.pjImage = loadImage("Animacion Pj.png");
        this.vidaImage = loadImage("vida.png");
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
        this.noPermitirCambioImgMovimiento=false;
        this.vidas = 3;
        this.numImgVida =0;
        this.anchoImgVida = 22;
        this.altoImgVida = 19;
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
    };

    //vidas
    public int getPosXImgVidaPaint() {
        return this.imgVidas[this.numImgVida][0];
    }

    public int getPosYImgVidaPaint() {
        return this.imgVidas[this.numImgVida][1];
    }

    public Image getVidaImage() {
        return vidaImage;
    }

    public int getAnchoImgVida() {
        return anchoImgVida;
    }

    public int getAltoImgVida() {
        return altoImgVida;
    }

    public void disminuirVida() {
        this.vidas--;
    }

    public int getNumImgVida() {
        return numImgVida;
    }
    
    public int getVidas() {
        return vidas;
    }

    public int[] getPosicionesDeImgAnimacionVidaPerdida() {
        return posicionesDeImgAnimacionVidaPerdida;
    }

    public void setNumImgVida(int numImgVida) {
        this.numImgVida = numImgVida;
    }
      
    public int getNumImagen() {
        return numImagen;
    }
    
    public void sentidoDeMovimiento(boolean derecha,char movement){
        if(!noPermitirCambioImgMovimiento){
            if(derecha){
                if(unidadAnchoImagenOriginal<0){
                unidadAnchoImagenOriginal *=-1;
                };
                if ('r' == movement) {
                    this.numImagen = 0;
                }
            }else{
                if(unidadAnchoImagenOriginal>0){
                unidadAnchoImagenOriginal *=-1;
                };
                if ('r' == movement) {
                    this.numImagen = 5;
                }
            };
        noPermitirCambioImgMovimiento=true;
        };
    };
    
    public void sentidoDeMovimiento(boolean derecha){
        if (derecha) {
            if (unidadAnchoImagenOriginal < 0) {
                unidadAnchoImagenOriginal *= -1;
            }
        } else {
            if (unidadAnchoImagenOriginal > 0) {
                unidadAnchoImagenOriginal *= -1;
            }
        };
//        noPermitirCambioImgMovimiento = true;
    }
    
    public void sentidoDeMovimiento(char movement){
        if(!noPermitirCambioImgMovimiento){
        if(movement=='j'){
            if(unidadAnchoImagenOriginal>0){
                this.numImagen = 10;
            }
            if(unidadAnchoImagenOriginal<0){
                this.numImagen = 14;
            }
        }
        noPermitirCambioImgMovimiento=true;
        }
    }

    public void noPermitirCambioImgMovimiento(boolean noPermitirCambioImgMovimiento) {
        this.noPermitirCambioImgMovimiento = noPermitirCambioImgMovimiento;
    }
    
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
    
    public static Image loadImage(String imageName) {
        ImageIcon ii = new ImageIcon(imageName);
        Image image = ii.getImage();
        return image;
    };

    public boolean isNoPermitirCambioImgMovimiento() {
        return noPermitirCambioImgMovimiento;
    }

    public void setVidas(int vidas) {
        this.vidas = vidas;
    }
    
};
