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
public class Sonidos {
    protected Sonido musicaFondo;
    protected Sonido caminar; 
    protected Sonido salto;
    protected Sonido caida;
    protected Sonido perdidaDeVida;
    private boolean sonidoActivo;
    
    public Sonidos() {
        this.musicaFondo = new Sonido();
        this.caminar = new Sonido();
        this.salto = new Sonido();
        this.caida = new Sonido();
        this.perdidaDeVida = new Sonido();
    }
    
    public void reproducirCaminar(){
        reproducirUnSonido('c');
    }
    
    public void detenerCaminar(){
        this.caminar.stop();
        this.sonidoActivo = false;
    }
    
    public void reproducirSalto(){
        reproducirUnSonido('j');
    }
    
    public void detenerSalto(){
        this.salto.stop();
        this.sonidoActivo = false;
    }
    
    private void reproducirUnSonido(char c){
        if(sonidoActivo){
//            System.out.println("Habia un sonido activo");
            this.caminar.stop();
            this.salto.stop();
        };
        switch (c) {
            case 'c':
                this.caminar.reproducir();
                break;
            case 'j':
                this.salto.reproducir();
                break;
        }
        sonidoActivo = true;
    }
    
}