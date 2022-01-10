package com.madirex.procesos.philosophers_comensales;

public class Tenedor {
    private boolean tomado;

    public Tenedor(){
        this.tomado = false;
    }

    public synchronized void tomar(){
        while (tomado){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        tomado = true;
    }

    public synchronized void dejar(){
        tomado = false;
        notify();
    }
}
