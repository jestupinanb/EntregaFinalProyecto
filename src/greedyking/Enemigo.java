/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greedyking;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author COMPAQ
 */
class Enemigo {
    
    public int cambioMovimientoX;
    private int y;
    private Image imagenGeneral;
    private boolean derecha;
    private Thread movimiento = new Thread(new MovimientoEnemigo(this));
    public int posicionXInicio;
    public int posicionXOriginal;
    public int posicionMovimiento;
    int[] posicionImgEnemigoDerecha = {0,112};
    int[] posicionImgEnemigoIzquierda = {18,112};
    private int numImg = 0;
    private int ancho = 16;
    private int alto = 16;
    
    public Enemigo(int posicionInicioX,int posicionInicioY,int scale) throws IOException {
//        this.posicionImgEnemigo = new int[][]{{0,112},{18,112}};
        this.posicionXOriginal = posicionInicioX;
        this.posicionXInicio = posicionInicioX;
        this.y = posicionInicioY;
        this.derecha = true;
        this.imagenGeneral = ImageIO.read(new File("characters.png"));
        this.movimiento.start();
        posicionMovimiento = posicionXInicio+cambioMovimientoX;
//        System.out.println("Se ejecuto constructor");
    }
    
    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }
    
    public void iniciarHilo() {
        this.movimiento.start();
    }

    public int getCambioPosicionMovimientoX() {
        return cambioMovimientoX;
    }
    
    
    
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public int posImgX(){
        if(this.derecha){
            return this.posicionImgEnemigoDerecha[0];
        }else{
            return this.posicionImgEnemigoDerecha[0]+ancho;
        }
    }
    
    public int posImgY(){
        return this.posicionImgEnemigoDerecha[1];
    }

    public Image getImagen() {
        if (this.derecha) {
            return this.imagenGeneral;
        } else {
            return this.imagenGeneral;
        }
    }

    public boolean isDerecha() {
        return derecha;
    }

    public void cambiardedireccion() {
        if (this.derecha) {
            this.derecha = false;
        } else {
            this.derecha = true;
        }
    }

}
