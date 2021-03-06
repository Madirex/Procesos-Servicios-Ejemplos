package com.madirex.procesos.philosophers_comensales;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Philosopher extends Thread{
    private static AtomicInteger counter = new AtomicInteger(0);
    private Tenedor izquierdo;
    private Tenedor derecho;
    private final int ID;
    private Random random;

    public Philosopher(Tenedor izquierdo, Tenedor derecho){
        this.izquierdo = izquierdo;
        this.derecho = derecho;
        this.ID = counter.addAndGet(1);
        random = new Random();
    }

    public void pausa() throws InterruptedException {
        sleep((random.nextInt(10000-5000) + 5000));
    }

    @Override
    public void run() {
        try {
            System.out.println("El fil贸sofo 馃 " + ID + " est谩 pensando.");
            pausa();
            System.out.println("El fil贸sofo 馃 " + ID + " quiere comer.");
            izquierdo.tomar();
            System.out.println("El fil贸sofo 馃 " + ID + " ha tomado el tenedor 馃嵈 izquierdo.");
            derecho.tomar();
            System.out.println("El fil贸sofo 馃 " + ID + " ha tomado el tenedor 馃嵈 derecho.");
            System.out.println("Ahora el fil贸sofo 馃 " + ID + " est谩 comiendo 馃構馃崝");
            pausa();
            System.out.println("El fil贸sofo 馃 " + ID + " ha terminado de comer 馃崝");
            izquierdo.dejar();
            derecho.dejar();
            System.out.println("El fil贸sofo 馃 " + ID + " ha dejado los tenedores en la mesa 馃嵔");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
