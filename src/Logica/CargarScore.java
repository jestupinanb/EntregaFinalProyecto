/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author COMPAQ
 */
public class CargarScore {
    private Scanner entrada;
    ArrayList<Score>scores;
    public CargarScore(){
        this.scores=new ArrayList<>();
        try {
            this.entrada=new Scanner(new File("Scores.txt"));
            while(entrada.hasNext()){
            String Nombre=entrada.next();
            int puntaje=entrada.nextInt();
            this.scores.add(new Score(Nombre, puntaje));
            
            
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    
    }
public ArrayList<Score>Puntajes(){
return this.scores;
}
}
