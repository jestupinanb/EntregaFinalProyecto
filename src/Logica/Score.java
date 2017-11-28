/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

/**
 *
 * @author COMPAQ
 */
public class Score {
 private String Nombre;
 private int puntaje;
 public Score(String nombre,int puntaje){
 this.Nombre=nombre;
 this.puntaje=puntaje;
 }

    public String getNombre() {
        return Nombre;
    }

    public int getPuntaje() {
        return puntaje;
    }
 
}
