package com.madirex.procesos.lector_escritor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Library {
    private List<Integer> libros;
    private int escritores;
    private int lectores;
    private int escritoresEscribiendo;
    private Random random;

    public Library(){
        libros = new ArrayList<>();
        escritores = 0;
        lectores = 0;
        escritoresEscribiendo = 0;
        random = new Random();
    }

    public synchronized void read(int lector){
        lectores++;
        //Mientras haya alguien escribiendo o escritores escribiendo, no leer
        while(escritores > 0 || escritoresEscribiendo > 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("El lector 👁 " + lector + " está leyendo 📒");

        try {
            Thread.sleep(random.nextInt(2_000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("El lector 👁 " + lector + " ha terminado de leer.");

        lectores--;
        notifyAll();
    }

    public synchronized void write(int escritor) {
        escritores++;
        //Mientras haya un lector o un escritor escribiendo, esperar a que terminen antes de escribir
        while(lectores > 0 || escritoresEscribiendo > 0){
            try {
                System.out.println("El escritor ✍ " + escritor + " espera para escribir.");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //El escritor escribe
        escritoresEscribiendo +=1;
        System.out.println("El escritor ✍ " + escritor + " empieza a escribir.");

        try {
            Thread.sleep(random.nextInt(2_000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        libros.add(random.nextInt(2_000));
        System.out.println("El escritor ✍ " + escritor + " termina de escribir.");

        //El escritor deja de escribir
        escritoresEscribiendo -=1;
        escritores--;
        notifyAll();
    }
}
