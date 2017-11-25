/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greedyking;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author INTEL
 */
public class Animacion implements Runnable {
    int tiempoDeEspera;
    int[] numeroACambiar;
    Personaje personaje;
    int vidaPersonaje;
    public Animacion(int tiempoDeEspera, int[] numeroACambiar,Personaje personaje) {
        this.tiempoDeEspera = tiempoDeEspera;
        this.numeroACambiar = numeroACambiar;
        this.personaje = personaje;
        this.vidaPersonaje = personaje.getNumImgVida();
    }
    
    @Override
    public void run() {
        try {
            ejecutar();
        } catch (InterruptedException ex) {
            System.out.println("Algo anda mal en el hilo");
        }
    }
    public void ejecutar() throws InterruptedException{
//        System.out.println("Vida personaje "+this.vidaPersonaje);
        if(this.vidaPersonaje<=2){
            for (int i = 0; i <= 5; i++) {
            personaje.setNumImgVida(numeroACambiar[i]);
            Thread.sleep(tiempoDeEspera);
            }
        }else{
            for(int i = 7;i>5; i--){
                personaje.setNumImgVida(numeroACambiar[i]);
                Thread.sleep(tiempoDeEspera);
            }
        }
        if(this.vidaPersonaje<2){
            for (int i = 5; i >= 0; i--) {
                personaje.setNumImgVida(numeroACambiar[i]);
                Thread.sleep(tiempoDeEspera);
            }
            personaje.setNumImgVida(this.vidaPersonaje + 1);
        }else{
            for(int i = 6;i<8; i++){
                personaje.setNumImgVida(numeroACambiar[i]);
                Thread.sleep(tiempoDeEspera);
            }
            personaje.setNumImgVida(3);
        }
    }
}
