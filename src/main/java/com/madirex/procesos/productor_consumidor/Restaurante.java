package com.madirex.procesos.productor_consumidor;

import java.util.ArrayList;

public class Restaurante {
    private ArrayList<Integer> availableItems = new ArrayList<>();
    private final int MAXITEMS = 10;
    private boolean shopClosed = false;

    public synchronized Integer consumir() {
        //Tiempo de espera entre consumición
        try {
            wait((long) ((Math.random() * 4_000 - 2_000) + 2_000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while(availableItems.isEmpty()){
            try {
                System.out.println("Esperando para consumir... 🕖");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        notifyAll();
        return availableItems.remove(0);
    }

    public synchronized void producir() {
    //Tiempo de espera entre producción
        try {
            wait((long) (Math.random() * (6_000 - 3_000) + 3_000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

            while (availableItems.size() >= MAXITEMS) {
                try {
                    if (isShopClosed()){
                        break;
                    }else{
                        System.out.println("Depósito lleno. El productor espera a que consuma el consumidor... 🕖");
                        wait(10_000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        if (!isShopClosed()){
            availableItems.add((int) (Math.random() * 100));
            System.out.println("El productor produce un nuevo item 🍕");

            //Ahora el productor se pega un descanso...
            try {
                wait((long) (Math.random() * (6_000 - 3_000) + 3_000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            notifyAll();
    }

    public boolean isShopClosed() {
        return shopClosed;
    }

    public void setShopClosed(boolean b) {
        if (b == true){
            System.out.println("❌ Restaurante cerrado ❌");
        }
        shopClosed = b;
    }
}