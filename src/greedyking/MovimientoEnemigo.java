/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greedyking;

import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author COMPAQ
 */
public class MovimientoEnemigo implements Runnable {

    Image[] imagenes = new Image[8];
    private int velocidad;
    private int direccion;
    private Enemigo enemigo;

    public MovimientoEnemigo(Enemigo e) {
        this.enemigo = e;
        this.direccion = 0;
    }

    @Override
    public void run() {
        int y;
        while (true) {
            for (int i = 0; i < 100; i++) {
                this.enemigo.Aumentarderecha();
//                enemigo.setX(x+4);
                System.out.println("Posicion enemigo 1 "+this.enemigo.cambioMovimientoX);
//                this.enemigo.cambioMovimientoX = this.enemigo.cambioMovimientoX + 4;
                System.out.println("Posicion enemigo 2 "+this.enemigo.cambioMovimientoX);
                try {
                    Thread.sleep(40);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MovimientoEnemigo.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (i == 100) {
                    for (int n = 0; n < 3; n++) {
                        y = this.enemigo.getY();
                        this.enemigo.setY(y - 1);
                        
                        try {
                            Thread.sleep(40);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(MovimientoEnemigo.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    for (int n = 0; n < 3; n++) {
                        y = this.enemigo.getY();
                        this.enemigo.setY(y + 1);
                    }
                    this.enemigo.Aumentarderecha();

                    break;

                }
            }
            this.enemigo.cambiardedireccion();
            for (int m = 0; m < 100; m++) {
                y = this.enemigo.getY();
                this.enemigo.AumentarIzquierda();
                System.out.println("Posicion enemigo 1 "+this.enemigo.cambioMovimientoX);
//                this.enemigo.cambioMovimientoX = this.enemigo.cambioMovimientoX - 4;
                System.out.println("Posicion enemigo 2 "+this.enemigo.cambioMovimientoX);
                try {
                    Thread.sleep(40);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MovimientoEnemigo.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (m == 100) {
                    for (int n = 0; n < 3; n++) {
                        y = this.enemigo.getY();
                        this.enemigo.setY(y - 1);
                        try {
                            Thread.sleep(40);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(MovimientoEnemigo.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    for (int n = 0; n < 3; n++) {
                        y = this.enemigo.getY();
                        this.enemigo.setY(y + 1);
                    }
                    this.enemigo.Aumentarderecha();

                    break;

                }
            }
            this.enemigo.cambiardedireccion();
        }
    }
}
