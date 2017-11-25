/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greedyking;

/**
 *
 * @author INTEL
 */
public class Sonido {
    private ReproducirSonido sonido;
    protected boolean reproducido;
    
    public Sonido(){
        reproducido=false;
    }
    
    public void asignarDireccion(String direccion,boolean loop){
        sonido = new ReproducirSonido(direccion,loop);
    }
    public void reproducir(){
        reproducido = true;
//        System.out.println("Reprodujo");
        sonido.run();
    };
    
    public void stop(){
        reproducido = false;
        sonido.stop();
    }
}
