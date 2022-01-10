package com.madirex.procesos.productor_consumidor;

public class Productor implements Runnable{

    private final int ID;
    private Restaurante restaurante;

    public Productor(int id, Restaurante restaurante){
        this.ID = id;
        this.restaurante = restaurante;
    }

    @Override
    public void run() {
        do {
            restaurante.producir();
        }while(!restaurante.isShopClosed());
        System.out.println("El productor " + ID + " se va porque el restaurante ha cerrado 🔒");
    }
}
