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
    private BufferedImage ImagenGeneral;
    private BufferedImage ImagenGeneralI;
    private Image[] imagenesderecha = new Image[4];
    private Image[] imagenesizquierda = new Image[4];
    private int indicederecha;
    private int indiceizquierda;
    private boolean derecha;
    private Thread movimiento = new Thread(new MovimientoEnemigo(this));
    public int posicionXInicio;
    public int posicionMovimiento;
    
    public Enemigo(int posicionInicioX,int posicionInicioY) throws IOException {
        this.posicionXInicio = posicionInicioX;
        this.y = posicionInicioY;
        imagenesderecha = new Image[4];
        imagenesizquierda = new Image[4];
        this.derecha = true;
        this.ImagenGeneralI = ImageIO.read(new File("charactersI.png"));
        this.ImagenGeneral = ImageIO.read(new File("characters.png"));
        for (int i = 0; i < 4; i++) {
            this.imagenesderecha[i] = (BufferedImage) ImagenGeneral.getSubimage(i * 32, 64, 32, 32);
        }
        for (int i = 0; i < 3; i++) {
            this.imagenesizquierda[i] = (BufferedImage) ImagenGeneralI.getSubimage((i + 5) * 32, 64, 32, 32);
        }
        this.movimiento.start();
        this.indicederecha = 0;
        this.indiceizquierda = 0;
        posicionMovimiento = posicionXInicio+cambioMovimientoX;
        System.out.println("Se ejecuto constructor");
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

    public Image[] getImagenesderecha() {
        return imagenesderecha;
    }

    public Image[] getImagenesizquierda() {
        return imagenesizquierda;
    }

//    public void setX(int x) {
//        this.cambioMovimientoX = x;
//    }

    public void setY(int y) {
        this.y = y;
    }

    public void Aumentarderecha() {
        if (this.indicederecha < 3) {
            this.indicederecha++;
        } else {
            this.indicederecha = 0;
        }
    }

    public void AumentarIzquierda() {
        if (this.indiceizquierda < 3) {
            this.indiceizquierda++;
        } else {
            this.indiceizquierda = 0;
        }
    }

    public Image getImagen() {
        if (this.derecha) {
            return this.imagenesderecha[this.indicederecha];
        } else {
            return this.imagenesizquierda[this.indiceizquierda];

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
