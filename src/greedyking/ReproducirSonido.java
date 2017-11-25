/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greedyking;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author INTEL
 */
public class ReproducirSonido implements Runnable{
    private URL url = null;
    String direccion;
    AudioClip ac;
    boolean loop;

    public ReproducirSonido(String direccion,boolean loop) {
        this.direccion = direccion;
        this.loop = loop;
        try {
            url = new URL("file:musica/"+direccion+".wav");
        } catch (MalformedURLException ex) {
            System.out.println("Error al crear el sonido");
        }
        ac =Applet.newAudioClip(url);
    }
    
    @Override
    public void run() {
        if(loop){
            ac.loop();
        }else{
            ac.play();
        }
    }
    
    public void stop(){
        ac.stop();
    }
}
