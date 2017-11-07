/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greedyking;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author USUARIO
 */
public class Lector {
char[][] nivel=new char[17][200];     
public Lector(String Archivo)   {

   try{
    FileReader leer=new FileReader(new File(Archivo));

       
char m=(char)leer.read();
//while(m!=-1){
    //m=(char)leer.read();
  for(int i=0;i<17;i++){
  for(int j=0;j<200;j++){
  this.nivel[i][j]=(char)leer.read();
          
  }
  }
    
    
    
//}
   
   }catch(FileNotFoundException e){
   
       System.out.println("no se encontro el archivo");
   }catch(IOException a){
       System.out.println("pasa otra ");
   
   }
}


    public char[][] getNivel() {
        return nivel;
    }
}    

    

