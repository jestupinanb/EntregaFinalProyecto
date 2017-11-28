/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greedyking;

import Vista.Iniciar;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.FloatControl;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Jaime
 */
public class Board extends JPanel implements ActionListener {
    
    private String nickName;

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    
    private int scale = 4;//El tamaño al que se aumenta el juego 1 = originial, 2 = al doble de grande, 3 = triple de grande etc...
    private int unidadMapaOriginal = 16;//El tile del mapa esta dividido en cuadros de 16x16
    private int unidadMapaGrande = 16*scale;//Tamaño que va a terner el juego al ejecutarse
    private final int delay = 16;
    Personaje personaje;//Crear un personaje
    private Timer timer;
    Rectangle personajeColision;
    
    Rectangle personajeColisionPies;
    
    private ArrayList<MapaColision> colisionMovY = new ArrayList();
    private ArrayList<MapaColision> colisionMovX = new ArrayList();
    private ArrayList<MapaColision> colisionMovXY = new ArrayList();
    private ArrayList<Cofre> colisionCofres = new ArrayList();
    private ColisionBloqueLargo colisionBloqueLargoCaida;//Colision cuando cae el personaje
    private ColisionBloqueLargo colisionBloqueLargoDerecha;
    private ColisionBloqueLargo colisionBloqueLargoIzquierda;
    
    boolean pressS;boolean pressA;boolean pressW;boolean pressD;//La tecla esta siendo precionada
    boolean firstTimeD;//Primera vez que se preciona la tecla 
    boolean firstTimeEnSalto;//Primera vez que se preciona espacio
    boolean pressDMov2=false;//USAR
    
    //Repaint relacionado con delay
    int contadorDelays=0;
    final int velocidadDelay=2;
    int contadorDelaysSalto=0;
    final int velocidadDelaySalto=1;
    
    //Bross
    final java.util.Timer timer2 = new java.util.Timer();
    boolean tiempoDelay=true;boolean tiempoDelay2=true;
    TimerTask timeTrue;
    TimerTask tiempoInmune;
    int contador=0;//Borrar
    //saltoPersonaje
    boolean enSalto=false;
    boolean pressSpace=false;
    int velocidadSalto;
    int gravedad=1;
    
    boolean haySuelo;
    
    int lastKeyPressed;
    
    //Mapa
    private int moverImgMapa = 0;//Dependiendo del numero que tenga mueve esa cantidad de columnas la matriz 
    private int cuadroInicioMapa =-1;
    private int moverMapa = cuadroInicioMapa*unidadMapaGrande;
    int posInicioCreacionMapa = cuadroInicioMapa*unidadMapaGrande;
    
    //sonido
    Sonidos sonidos = new Sonidos();
    
    ArrayList<int[]> tiles;
    int largoMapa = 83;
    int mapa[][][][] = new int[8][largoMapa][2][3];
    
    private int cuadroInicioMovMapaPersonaje = 8;
    
    //Enemigos
    private Enemigo enemigo;
    
    //Score
    private int score = 0;
    
    //jframe
    private JFrame jframe;
    
    boolean personajeInumne = false;
    
    private int temporal;
    
    public Board(JFrame jframe) {
        this.jframe = jframe;
        try {
            this.enemigo = new Enemigo(unidadMapaGrande*3,unidadMapaGrande*4);
        } catch (IOException ex) {
            System.out.println("Error con el enemigo");
        }
        this.nickName = nickName;
        this.personaje = new Personaje(scale);
        timer = new Timer(this.delay,this);
        setFocusable(true);
        addKeyListener(new EventosTeclado());
        timer.start();
        sonidos.musicaFondo.asignarDireccion("Troll Song 10 The Happy Troll",true);
        System.out.println("Se ejecuto el sonido");
        sonidos.musicaFondo.reproducir();
        sonidos.caminar.asignarDireccion("slime5",true);
        sonidos.salto.asignarDireccion("sfx_movement_jump20", false);
        sonidos.caida.asignarDireccion("sfx_sounds_falling12", false);
        sonidos.perdidaDeVida.asignarDireccion("sfx_deathscream_human1", false);
        this.firstTimeD = true;
        
        tiles = new ArrayList();
        
        for(int i =0;i< 10;i++){
            for(int f = 0; f<17; f++){
                int[] x = {f,i,0};
                tiles.add(x);
            }
        }
        tiles.get(7)[2] = 1;
        tiles.get(8)[2] = 1;
        tiles.get(9)[2] = 1;

        tiles.get(25)[2] = 3;
        tiles.get(26)[2] = 3;
        tiles.get(27)[2] = 3;
        tiles.get(28)[2] = 1;
        tiles.get(29)[2] = 3;
        tiles.get(30)[2] = 3;
        tiles.get(41)[2] = 3;
        tiles.get(42)[2] = 3;
        tiles.get(43)[2] = 3;
        tiles.get(44)[2] = 2;
        tiles.get(46)[2] = 2;
        tiles.get(47)[2] = 2;
        tiles.get(58)[2] = 1;
        tiles.get(59)[2] = 1;
        tiles.get(60)[2] = 1;
        tiles.get(61)[2] = 3;
        tiles.get(62)[2] = 1;
        tiles.get(63)[2] = 3;
        tiles.get(64)[2] = 3;
        tiles.get(75)[2] = 1;
        tiles.get(76)[2] = 1;
        tiles.get(77)[2] = 1;
        tiles.get(78)[2] = 3;
        tiles.get(79)[2] = 1;
        tiles.get(80)[2] = 3;
        tiles.get(81)[2] = 3;
        tiles.get(93)[2] = 3;
        tiles.get(94)[2] = 3;
        tiles.get(95)[2] = 2;
        tiles.get(97)[2] = 2;
        tiles.get(98)[2] = 2;
        tiles.get(101)[2] = 4;
        tiles.get(99)[2] = 4;
        int[] z = {-1,-1,0};
        tiles.set(169, z);
//        int i = 0;
//        for(int x[]:tiles){
//            System.out.println(i+" "+x[2]);
//        i++;}
        try{
        leerMapa();
        }catch(FileNotFoundException e){
            System.out.println("Error al leer el mapa");
        }
        
    }
    
    public void otherKeyPressed(int key){
//        System.out.println("Laskey "+KeyEvent.getKeyText(lastKeyPressed)+" key "+KeyEvent.getKeyText(key));
        if(lastKeyPressed!=key){
            if (lastKeyPressed != KeyEvent.VK_SPACE && key != KeyEvent.VK_SPACE) {
                switch (lastKeyPressed) {
                    case KeyEvent.VK_D:
                        pressD = false;
                        if (!enSalto) {
                            personaje.sinMovimiento();
                            personaje.noPermitirCambioImgMovimiento(false);
                        }
                        break;
                    case KeyEvent.VK_A:
                        pressA = false;
                        if (!enSalto) {
                            personaje.sinMovimiento();
                            personaje.noPermitirCambioImgMovimiento(false);
                        }
                        break;
                    case KeyEvent.VK_W:
                        pressW = false;
                        break;
                    case KeyEvent.VK_S:
                        pressS = false;
                        break;
                    case KeyEvent.VK_SPACE:
                        break;
                }
            }
            if((lastKeyPressed == KeyEvent.VK_SPACE) && (key==KeyEvent.VK_D)){
                pressA=false;
                if(!enSalto){
                    personaje.noPermitirCambioImgMovimiento(false);
                }
            }else if ((lastKeyPressed == KeyEvent.VK_SPACE) && (key==KeyEvent.VK_A)){
                pressD=false;
                if(!enSalto){
                    personaje.noPermitirCambioImgMovimiento(false);
                }
            };
            lastKeyPressed=key;
        }
    }
    
    public void leerMapa() throws FileNotFoundException{
        ArrayList<int[]> a = tiles;
        int k=0;
//        int temporal;
        Scanner leer = new Scanner(new File("mapa.txt"));
        while(leer.hasNext()){
            for (int i = 0; i < largoMapa; i++) {
                for(int j=0; j<2; j++){
                    temporal = leer.nextInt();
                    mapa[k][i][j] = a.get(temporal);
                    System.out.print(temporal+" ");
                }
            }
            k++;
            System.out.println("K="+k);
        };
        System.out.println("Termino");
    }
    
    private class EventosTeclado extends KeyAdapter {
        boolean otherKeyPress;
        @Override
        public void keyPressed(KeyEvent e){
//            pressS = false;pressW=false;pressA=false;pressD=false;
            int key = e.getKeyCode();
//            System.out.println("Ultima tecla presionada "+KeyEvent.getKeyText(key));
            switch (key){
                case KeyEvent.VK_D:
//                    if(firstTimeD){firstTimeD = true;};
                    pressD = true;//System.out.println("D");
                    break;
                case KeyEvent.VK_A:
                    pressA = true;//System.out.println("A"+" pressD "+pressD;
                    break;
                case KeyEvent.VK_W:
                    pressW = true;
                    break;
                case KeyEvent.VK_S:
                    pressS = true;
                    break;
                case KeyEvent.VK_SPACE:
                    pressSpace=true;
                    break;
                case KeyEvent.VK_E:
                    break;
                case KeyEvent.VK_Q:
                    break;
            };
            if (!enSalto && pressSpace) {
//                if(firstTimeEnSalto){firstTimeEnSalto = true;}
                personaje.noPermitirCambioImgMovimiento(false);
                pressSpace = true;
                enSalto = true;
//                        System.out.println("Salto");
                velocidadSalto = -25;
            }
            otherKeyPressed(key);
        };
        
        @Override
        public void keyReleased(KeyEvent e){
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_D:
                    pressD=false;
                    firstTimeD = true;
                    if(!enSalto){
                        personaje.noPermitirCambioImgMovimiento(false);
                        personaje.sinMovimiento();
                    }
                    sonidos.detenerCaminar();
                    break;
                case KeyEvent.VK_A:
                    pressA=false;
                    if(!enSalto){
                        personaje.noPermitirCambioImgMovimiento(false);
                        personaje.sinMovimiento();
                    }
                    sonidos.detenerCaminar();
                    break;
                case KeyEvent.VK_W:
                    pressW=false;
                    break;
                case KeyEvent.VK_S:
                    pressS=false;
                    break;
                case KeyEvent.VK_SPACE:
                    pressSpace=false;
                    break;
                case KeyEvent.VK_RIGHT:
                    System.out.println("MOVE RIGHT MoverImgMapa++");
                    moverImgMapa++;
                    break;
                case KeyEvent.VK_LEFT:
                    if(moverImgMapa>0){
                    System.out.println("MOVE LEFT MoverImgMapa--");
                    moverImgMapa--;}else{System.out.println("Distancia minima");}
                    break;
                case KeyEvent.VK_E:
                    sonidos.musicaFondo.stop();
//                    sonidos.musicaFondo.reproducir();
                    break;
                case KeyEvent.VK_Q:
                    perdidaDeVida();
                    break;
            }
        }
        
        @Override
        public void keyTyped(KeyEvent e) {
        }
        
    }
    
    public void revivir(int time) {
        timeTrue = new TimerTask() {
            @Override
            public void run() {
                tiempoDelay = true;
            }
        };
        timer2.schedule(timeTrue, time);
    };
    
    public void matarRevivir(){
        tiempoDelay=true;
        timeTrue.cancel();
    }
    
    private void ajustarImagen(){
//        personaje.noPermitirCambioImgMovimiento(false);
        switch (personaje.getNumImagen()) {
            case 10:
                personaje.setNumImagen(14);//System.out.println("Era 10 ahora soy 14");
                personaje.sentidoDeMovimiento(false);
                break;
            case 11:
                personaje.setNumImagen(15);//System.out.println("Era 10 ahora soy 15");
                personaje.sentidoDeMovimiento(false);
                break;
            case 12:
                personaje.setNumImagen(16);//System.out.println("Era 10 ahora soy 16");
                personaje.sentidoDeMovimiento(false);
                break;
            case 13:
                personaje.setNumImagen(17);//System.out.println("Era 10 ahora soy 17");
                personaje.sentidoDeMovimiento(false);
                break;
            case 14:
                personaje.setNumImagen(10);
                personaje.sentidoDeMovimiento(true);
                break;
            case 15:
                personaje.setNumImagen(11);
                personaje.sentidoDeMovimiento(true);
                break;
            case 16:
                personaje.setNumImagen(12);
                personaje.sentidoDeMovimiento(true);
                break;
            case 17:
                personaje.setNumImagen(13);
                personaje.sentidoDeMovimiento(true);
                break;
        }
    }
    
    public boolean colisionoBajando(boolean colisionoBoolean){
        for (MapaColision colisiono : colisionMovY) {
                if (personajeColisionPies.intersects(colisiono.getCollisionBloqueUp())) {
                    colisionoBoolean = true;
                };
            }
            for (MapaColision colisiono : colisionMovXY) {
                if (personajeColisionPies.intersects(colisiono.getCollisionBloqueUp())) {
                    colisionoBoolean = true;
                };
            };
            return colisionoBoolean;
    };
    
    public boolean colisionoIzquierda(boolean colisionoBoolean){
        if (tiempoDelay == true && !enSalto) {//ESTE IF NO PERTENECE A ESTA FUNCION
                    tiempoDelay = false;
                    personaje.sentidoDeMovimiento(false,'r');//r=run
                    personaje.setNumImagen(personaje.getNumImagen() + 1);
                    if (personaje.getNumImagen() == 10) {
                        personaje.setNumImagen(6);
                    }
                    revivir(100);
                };
                for (MapaColision colisiono : colisionMovX) {
                    if (personajeColision.intersects(colisiono.getCollisionBloquexyRight())) {;
                        colisionoBoolean = true;
                    }
                };
                for (MapaColision colisiono : colisionMovXY) {
                    if (personajeColision.intersects(colisiono.getCollisionBloquexyRight())) {
                        colisionoBoolean = true;
                    };
                };
                if(personajeColision.intersects(this.colisionBloqueLargoIzquierda.getColisionBloque())){
                    colisionoBoolean = true;
                };
                return colisionoBoolean;
    }
    
    public boolean colisionoDerecha(boolean colisionoBoolean){
        if (tiempoDelay == true && !enSalto) {//ESTE IF NO PERTENECE A ESTA FUNCION
                    tiempoDelay = false;
                    personaje.sentidoDeMovimiento(true,'r');//r=run
                    personaje.setNumImagen(personaje.getNumImagen() + 1);
                    if (personaje.getNumImagen() == 5) {
                        personaje.setNumImagen(1);
                    }
                    revivir(100);
                };
                for (MapaColision colisiono : colisionMovX) {
                    if (personajeColision.intersects(colisiono.getCollisionBloquexyLeft())) {;
                        colisionoBoolean = true;
                    }
                };
                for (MapaColision colisiono : colisionMovXY) {
                    if (personajeColision.intersects(colisiono.getCollisionBloquexyLeft())) {
                        colisionoBoolean = true;
                    };
                };
                if(personajeColision.intersects(this.colisionBloqueLargoDerecha.getColisionBloque())){
                    colisionoBoolean=true;
                }
                return colisionoBoolean;
    }
    
    public void colisionConBloqueCaida(){
        if(personajeColision.intersects(this.colisionBloqueLargoCaida.getColisionBloque())){
            personaje.setPosicionX(0);
            personaje.setPosicionY(unidadMapaGrande);
            perdidaDeVida();
            this.sonidos.caida.reproducir();
            terminoJuego();
        }
    }
    
    public boolean colisionoConCofres(boolean colisionoBoolean){
        for(Cofre cofre:colisionCofres){
            if(personajeColision.intersects(cofre.getColisionBloque())){
                colisionoBoolean=true;
                this.score += 100;
                System.out.println("DESTURYO COFRE");
//                System.out.println("cofre position Y "+cofre.getPosYMatriz()+" posx "+cofre.getPosXMatriz());
                mapa[cofre.getPosYMatriz()][cofre.getPosXMatriz()][1][2] = 0;
            }
        }
        return colisionoBoolean;
    };
    
    @Override
    public void paintComponent(Graphics g) {
//        System.out.println("Nickname: "+this.nickName);
//        System.out.println("PressD "+pressD+" PressA "+pressA);
//        System.out.println("Timepo delay = "+tiempoDelay);
//        System.out.println("PressD="+pressD);
//        System.out.println("enSalto="+enSalto);
//        System.out.println("ContadorDelays="+contadorDelays);
        boolean colisionoBoolean;
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        drawMapa(g);//Crear el mapa, se encarga de pintarlo
        moverPj(g, true);
//        System.out.println("Contador delay salto "+contadorDelaysSalto+" velocidadDelaySalto "+velocidadDelaySalto);
        if (true) {
            if (enSalto) {//Salto
//                if(firstTimeEnSalto){firstTimeEnSalto=false;}
                if(!sonidos.salto.reproducido){sonidos.reproducirSalto();}
                colisionoBoolean = false;
                if((pressD && personaje.getUnidadAnchoImagenOriginal()<0) || (pressA && personaje.getUnidadAnchoImagenOriginal()>0)){
//                    System.out.println("Entre con "+personaje.getNumImagen());
                        ajustarImagen();
                    }
                if (tiempoDelay == true) {//System.out.println("No permitir cambio de img "+personaje.isNoPermitirCambioImgMovimiento());
//                    System.out.println("NO PERMITIR CAMBIO DE MOVIMIENTO "+personaje.isNoPermitirCambioImgMovimiento());
                    tiempoDelay = false;//System.out.println("Numero de imangen Inicio "+personaje.getNumImagen());
                    personaje.setNumImagen(personaje.getNumImagen() + 1);
                    personaje.sentidoDeMovimiento('j');//j=jump
//                    System.out.println("Numero de imange "+personaje.getNumImagen());
                    switch(personaje.getNumImagen()){
                        case 10:
                            revivir(100);
                            break;
                        case 11:
                            revivir(200);
                            break;
                        case 12:
                            revivir(200);
                            break;
                        case 13:
                            revivir(10000);
                            break;
                        case 14:
                            revivir(100);
                            break;
                        case 15:
                            revivir(200);
                            break;
                        case 16:
                            revivir(200);
                            break;
                        case 17:
                            revivir(10000);
                            break;
                        default:
                            personaje.setNumImagen(personaje.getNumImagen());
                            revivir(10000);
                            break;
                    }
//                    System.out.println("Num imagen"+personaje.getNumImagen());
                };
                if (velocidadSalto < 0) {//Sube
                    personaje.moveUP();
                } else {//Baja
                    colisionoBoolean = colisionoBajando(colisionoBoolean);
                    if (colisionoBoolean == true) {//Si toca con el suelo deja de bajar
                        enSalto = false;
                        personaje.sinMovimiento();
                        sonidos.detenerSalto();
//                        personaje.noPermitirCambioImgMovimiento(false); ¿BORRAR?
                        matarRevivir();
                    } else {
                        personaje.moveDown();
                    };
                };
                velocidadSalto += gravedad;
            };
            contadorDelaysSalto=0;
//            moverPj(g,true);//BORRAR
        }else{contadorDelaysSalto++;};
        
        if(true){
            if (pressS == true) {
                colisionoBoolean = false;
                colisionoBoolean = colisionoBajando(colisionoBoolean);
                if (!colisionoBoolean) {
                    personaje.moveDown();
                };
                pressS = false;
            };

        if (pressW == true) {
            colisionoBoolean=false;
            for (MapaColision colisiono : colisionMovY) {
                if (personajeColision.intersects(colisiono.getCollisionBloqueUp())) {
                    colisionoBoolean = true;
                };
            };
            for (MapaColision colisiono : colisionMovXY) {
                if (personajeColision.intersects(colisiono.getCollisionBloquexyDown())) {
                    colisionoBoolean = true;
                };
            };
            if (!colisionoBoolean) {
                personaje.moveUP();
            };
        };
        
        if (pressA == true) {
                colisionoBoolean = false;
                reproducirSonidoSalto();
                colisionoBoolean = colisionoIzquierda(colisionoBoolean);
                if (!colisionoBoolean) {
                        if((moverMapa%unidadMapaGrande!=0 || moverImgMapa !=0) && (personaje.getPositionX()<=cuadroInicioMovMapaPersonaje*unidadMapaGrande)){
                            moverMapa += scale;
                            if(moverMapa==posInicioCreacionMapa+unidadMapaGrande){//if(moverMapa==posInicioCreacionMapa+1*unidadMapaGrande){
                                moverImgMapa--;
                                moverMapa= posInicioCreacionMapa;
                            }
                        }else{personaje.moveLeft();}
                };
        };
        
        if (pressD) {
//            if(firstTimeD){sonidos.reproducirCaminar();firstTimeD=false;}
                reproducirSonidoSalto();
                colisionoBoolean = false;
                colisionoBoolean = colisionoDerecha(colisionoBoolean);
                if (!colisionoBoolean) {
//                    System.out.println("Personaje posicion" + personaje.getPositionX() + "=" + 7 * unidadMapaGrande);
//                    System.out.println("Mover mapa "+moverMapa);
//                    System.out.println("posicion x"+personaje.getPositionX()+">="+cuadroInicioMovMapaPersonaje * unidadMapaGrande);
                    if((moverMapa%unidadMapaGrande!=0 || moverImgMapa!=largoMapa-20) && (personaje.getPositionX() >= cuadroInicioMovMapaPersonaje * unidadMapaGrande)){
                        moverMapa-=scale;
                        if(moverMapa==posInicioCreacionMapa-unidadMapaGrande){//if(moverMapa==posInicioCreacionMapa-1*unidadMapaGrande){
                            moverImgMapa++;
                            moverMapa=posInicioCreacionMapa;//posInicioCreacionMapa
                        }
                    }else{personaje.moveRight();}
                };
//                moverPj(g,true);//BORRAR
        };
        
            /*Gravity*/
        if ((pressA || pressD || !haySuelo) && !enSalto) {
                colisionoBoolean = false;
                haySuelo = colisionoBajando(colisionoBoolean);
                if (!haySuelo) {
                    personaje.moveDown();
                };
            };
            personaje.positionXYEqualsPositionXYPaint();//Se encarga de que la imagen coincida con la colision
            contadorDelays = 0;
        } else {contadorDelays++;};
        
        if(colisionoConCofres(false)){
            System.out.println("Colisiono");
        };
        pintarEnemigo(g);
        colisionConBloqueCaida();
        
        drawScore(g);
        drawVida(g);
    }
    
    public void reproducirSonidoSalto(){
        if(!sonidos.caminar.reproducido && !enSalto){sonidos.reproducirCaminar();}
    }
    
    public void drawScore(Graphics g){
//        g.drawImage(loadImage("fondoTexto.png"), 13*unidadMapaGrande, 0, this);
        g.drawImage(loadImage("fondoTexto.png"), 13*unidadMapaGrande-5*scale, 4*scale, 17*unidadMapaGrande-5*scale, unidadMapaGrande+4*scale,
                0, 0, 128, 32,this);
        g.setColor(Color.WHITE);
        g.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 48));
        g.drawString("Score: "+score, 13*unidadMapaGrande, unidadMapaGrande);
    }
    
    public void guardarScore(){
        try{
            FileWriter fileWriter = new FileWriter("Scores.txt",true);
            fileWriter.write("\n"+nickName+" "+score);
            fileWriter.close();
        }catch(Exception e){
            System.out.println("Error al guardar el score");
        }
        
    }
    
    public void drawVida(Graphics g){   
        g.drawImage(personaje.getVidaImage(),0, 0, personaje.getAnchoImgVida()*scale, personaje.getAltoImgVida()*scale,
        this.personaje.getPosXImgVidaPaint(), this.personaje.getPosYImgVidaPaint(), this.personaje.getPosXImgVidaPaint()+personaje.getAnchoImgVida(), this.personaje.getPosYImgVidaPaint()+this.personaje.getAltoImgVida(),this);
//        System.out.println(this.personaje.getPosXImgVidaPaint()+","+this.personaje.getPosYImgVidaPaint());
//        System.out.println(personaje.getPosXImgVidaPaint()+","+personaje.getPosYImgVidaPaint()+","+(this.personaje.getPosXImgVidaPaint()+personaje.getAnchoImgVida())+","+(this.personaje.getPosYImgVidaPaint()+this.personaje.getAltoImgVida()));
//        g.drawRect(0, 0, this.personaje.getAnchoImgVida()*scale, this.personaje.getAltoImgVida()*scale);
//        g.drawImage(this.personaje.getVidaImage(), 0, 0, this);
//        g.drawImage(this.personaje.getVidaImage(), unidadMapaGrande, unidadMapaGrande, this.personaje.getAnchoImgVida(), this.personaje.getAltoImgVida(), this);
    }
    
    public void drawMapa(Graphics g){
        colisionMovX.clear();
        colisionMovY.clear();
        colisionMovXY.clear();
        colisionCofres.clear();
        Image fondo = loadImage("Tiles.png");
        for(int posY=0;posY<8;posY++){
            for (int posX=0; posX<19; posX++) {//Largo
                if(mapa[posY][posX+moverImgMapa][0][0]!=-1){//En caso de que haya fondo se ejecuta
                //Dibuja el fondo de la imagen g.drawImage(Imagen que va, posicion en pixeles de la columna donde empieza, posicion en pixeles de la fila donde empieza, posicion en pixeles de la columna donde termina, posicion en pixeles de la fila donde termina,
                //Esta parte se encarga de recotar la imagen original... posicion de la columna, posicion de la fila, posicion final de la columna,posicion final de la fila, this)
                
                g.drawImage(fondo,posX*unidadMapaGrande+moverMapa,posY*unidadMapaGrande,posX*unidadMapaGrande+unidadMapaGrande+moverMapa,posY*unidadMapaGrande+unidadMapaGrande,
                mapa[posY][posX+moverImgMapa][0][0]*unidadMapaOriginal,mapa[posY][posX+moverImgMapa][0][1]*unidadMapaOriginal,mapa[posY][posX+moverImgMapa][0][0]*unidadMapaOriginal+16,mapa[posY][posX+moverImgMapa][0][1]*unidadMapaOriginal+16, this);
                };
                
                g.drawImage(fondo,posX*unidadMapaGrande+moverMapa,posY*unidadMapaGrande,posX*unidadMapaGrande+unidadMapaGrande+moverMapa,posY*unidadMapaGrande+unidadMapaGrande,
                mapa[posY][posX+moverImgMapa][1][0]*unidadMapaOriginal,mapa[posY][posX+moverImgMapa][1][1]*unidadMapaOriginal,mapa[posY][posX+moverImgMapa][1][0]*unidadMapaOriginal+16,mapa[posY][posX+moverImgMapa][1][1]*unidadMapaOriginal+16, this);   

                agregarColision(mapa[posY][posX+moverImgMapa][1][2],posX,posY,this.scale,g,moverMapa);
            };
        };
        //bloque de caida
        this.colisionBloqueLargoCaida = new ColisionBloqueLargo((0+cuadroInicioMapa)*unidadMapaGrande,8*unidadMapaGrande,this.scale,g,(18+cuadroInicioMapa)*unidadMapaGrande,1*unidadMapaGrande);
        //bloque de la derecha
        this.colisionBloqueLargoDerecha = new ColisionBloqueLargo((18+cuadroInicioMapa)*unidadMapaGrande,0*unidadMapaGrande,this.scale,g,1*unidadMapaGrande,8*unidadMapaGrande);
        //bloque de la izquierda
        this.colisionBloqueLargoIzquierda = new ColisionBloqueLargo(cuadroInicioMapa*unidadMapaGrande,0*unidadMapaGrande,this.scale,g,1*unidadMapaGrande,8*unidadMapaGrande);
        //Barras de mov del mapa
//        g.drawRect(posInicioCreacionMapa, 0, unidadMapaGrande, 8*unidadMapaGrande);//Barra inicial
//        g.drawRect(18*unidadMapaGrande+posInicioCreacionMapa, 0, unidadMapaGrande, 8*unidadMapaGrande);//Barra finañ
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
    
    public Image loadImage(String imageName){
        ImageIcon ii = new ImageIcon(imageName);
        Image image = ii.getImage();
        return image;
    };
    
    public void perdidaDeVida(){
        if(!this.personajeInumne){
        tiempoInmune();
        this.personaje.disminuirVida();
        this.sonidos.perdidaDeVida.reproducir();
        this.terminoJuego();
        animacionPerdidaVida();
        }
    }
    
    public void animacionPerdidaVida(){
        Thread animacion = new Thread(new Animacion(100,this.personaje.getPosicionesDeImgAnimacionVidaPerdida(),personaje));
        animacion.start();
    }
    
    public void tiempoInmune(){
        System.out.println("Inicio tiempo inmune");
        this.personajeInumne = true;
        this.tiempoInmune = new TimerTask(){
            @Override
            public void run() {
                System.out.println("Termino tiempo inmune");
                personajeInumne = false;
            }
    };
        timer2.schedule(this.tiempoInmune, 1000);
    }
    
    public void terminoJuego(){
        if(this.personaje.getVidas()==0){
            tiempoInmune.cancel();
            guardarScore();
            this.sonidos.musicaFondo.stop();
            Iniciar in = new Iniciar();
            in.show();
            this.jframe.dispose();
        }
    }
    
    public void agregarColision(int agregarColision, int x, int y, int scale, Graphics g,int moverMapa) {
        switch (agregarColision) {
            case 1:
                this.colisionMovY.add(new MapaColision(x, y, this.scale, g, "y",moverMapa));
                break;
            case 2:
                this.colisionMovX.add(new MapaColision(x, y, this.scale, g, "x",moverMapa));
                break;
            case 3:
                this.colisionMovXY.add(new MapaColision(x, y, this.scale, g, "xy",moverMapa));
                break;
            case 4:
                System.out.println("x = "+x+" y= "+y);
                this.colisionCofres.add(new Cofre(x*unidadMapaGrande+moverMapa+1*scale,y*unidadMapaGrande+3*scale,this.scale, g,unidadMapaGrande-2*scale,unidadMapaGrande-3*scale,x,y));
                break;
        };
    };
    
    public void moverPj(Graphics g,boolean pintar){
//        System.out.println("Mover pj");//BORRAR
        //Dibuja el rectangulo de la colision
        personajeColision = new 
         Rectangle(this.personaje.getPositionX()+2*scale,this.personaje.getPositionY(),this.personaje.getUnidadAnchoRun()-4*scale,this.personaje.getUnidadAltoRun()-1*scale);
        //Dibuja un rectangulo que ,muestra el rectangulo de la colision, para que se pueda ver en pantalla
//        g.drawRect(personajeColision.x,personajeColision.y,personajeColision.width,personajeColision.height);

        //pies
        personajeColisionPies = new
        Rectangle(this.personaje.getPositionX()+2*scale,this.personaje.getPositionY()+this.personaje.getUnidadAltoRun()-1*scale,this.personaje.getUnidadAnchoRun()-4*scale,1*scale); 
        //Dibuja pies
//        g.setColor(Color.RED);
//        g.drawRect(personajeColisionPies.x,personajeColisionPies.y,personajeColisionPies.width,personajeColisionPies.height);
//        g.setColor(Color.BLUE);
        
        if(pintar){//Dibuja la imagen
            g.drawImage(this.personaje.getPjImage(),this.personaje.getPositionXPaint(),this.personaje.getPositionYPaint(),this.personaje.getPositionXPaint()+this.personaje.getUnidadAnchoRun(),this.personaje.getPositionYPaint()+this.personaje.getUnidadAltoRun(),
                this.personaje.numeroDeImagenAncho(),this.personaje.numeroDeImagenAlto(),this.personaje.numeroDeImagenAncho()+this.personaje.getUnidadAnchoImagenOriginal(),this.personaje.numeroDeImagenAlto()+this.personaje.getUnidadAltoImagenOriginal(), this);//6,2
        };
    };

    public int getScale() {//Retoruna el tamaño al que se aumenta el juego 1 = originial, 2 = al doble de grande, 3 = triple de grande etc...
        return scale;
    }
    
    public void pintarEnemigo(Graphics g){
//        System.out.println("Enemigo posicion "+this.enemigo.posicionXInicio);
        enemigo.posicionMovimiento = enemigo.posicionXInicio + enemigo.getCambioPosicionMovimientoX();
        enemigo.posicionXInicio = enemigo.posicionXOriginal-moverImgMapa*unidadMapaGrande+moverMapa;
        g.drawImage(enemigo.getImagen(), enemigo.posicionMovimiento, enemigo.getY(), 50, 50, this);
        Rectangle rect=new Rectangle(enemigo.posicionMovimiento, enemigo.getY(), 50, 50);
//        g.drawRect(enemigo.posicionMovimiento, enemigo.getY(), 50, 50);
        if(this.personajeColision.intersects(rect)){
            perdidaDeVida();
        }
//       if(this.personaje.getPositionX()==enemigo.getX()&&this.personaje.getPositionY()==enemigo.getY()){
//           this.perdidaDeVida();
//       };
    }
    
}
