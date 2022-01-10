package com.madirex.procesos.carrera_camellos;

public class Camello extends Thread{
    int distanciaRecorrida = 0;
    int id;

    public Camello(int id){
        this.id = id;
    }

    @Override
    public void run() {
        super.run();
        correr();
    }

    public void correr(){
        while (distanciaRecorrida < 100){
            try {
                Thread.sleep((long) (Math.random() * (1000 - 500) + 500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            distanciaRecorrida += 10;
            System.out.println("🐫 Camello " + id + " ahora está en la posición " + distanciaRecorrida);
        }
        Podio.getInstance().addPodio(id);
        System.out.println("🐫 Camello " + id + " ha llegado a la meta.");
    }

}
