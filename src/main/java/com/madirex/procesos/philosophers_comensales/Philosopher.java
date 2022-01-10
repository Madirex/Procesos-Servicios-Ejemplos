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
            System.out.println("El filósofo 🧓 " + ID + " está pensando.");
            pausa();
            System.out.println("El filósofo 🧓 " + ID + " quiere comer.");
            izquierdo.tomar();
            System.out.println("El filósofo 🧓 " + ID + " ha tomado el tenedor 🍴 izquierdo.");
            derecho.tomar();
            System.out.println("El filósofo 🧓 " + ID + " ha tomado el tenedor 🍴 derecho.");
            System.out.println("Ahora el filósofo 🧓 " + ID + " está comiendo 😋🍔");
            pausa();
            System.out.println("El filósofo 🧓 " + ID + " ha terminado de comer 🍔");
            izquierdo.dejar();
            derecho.dejar();
            System.out.println("El filósofo 🧓 " + ID + " ha dejado los tenedores en la mesa 🍽");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
