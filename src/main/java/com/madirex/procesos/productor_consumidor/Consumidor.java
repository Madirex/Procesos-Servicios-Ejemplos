package com.madirex.procesos.productor_consumidor;

public class Consumidor implements Runnable{

    private final int ID;
    private Restaurante restaurante;
    private int numItemsToConsume;
    private int itemsConsumed = 0;

    public Consumidor(int id, Restaurante restaurante, int numItemsToConsume){
        this.ID = id;
        this.restaurante = restaurante;
        this.numItemsToConsume = numItemsToConsume;
    }

    @Override
    public void run() {
        while(itemsConsumed < numItemsToConsume){
            System.out.println("El consumidor " + ID + " consume el item 🍕 " + restaurante.consumir());
            itemsConsumed +=1;
        }
        System.out.println("El consumidor " + ID + " se va del restaurante. Ya está satisfecho 🤤");
    }
}
