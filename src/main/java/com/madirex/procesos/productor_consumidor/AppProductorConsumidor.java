package com.madirex.procesos.productor_consumidor;

public class AppProductorConsumidor {

    private static AppProductorConsumidor appProductorConsumidorInstance;

    private AppProductorConsumidor() {
        int numProductores = 2;
        int numConsumidores = 5;
        int itemsToConsumePerConsumidor = 10;
        Thread[] productores = new Thread[numProductores];
        Thread[] consumidores = new Thread[numConsumidores];
        Restaurante restaurante = new Restaurante();

        //Crear consumidores
        for (int n = 0; n < numConsumidores; n++){
            consumidores[n] = new Thread(new Consumidor(n, restaurante, itemsToConsumePerConsumidor));
            consumidores[n].start();
        }

        //Crear productores
        for (int n = 0; n < numProductores; n++){
            productores[n] = new Thread(new Productor(n, restaurante));
            productores[n].start();
        }

        //Esperar a que se vayan los consumidores
        for (int n = 0; n < numConsumidores; n++){
            try {
                consumidores[n].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //Cerrar restaurante
        restaurante.setShopClosed(true);

        //Esperar a que se vayan los productores
        for (int n = 0; n < numProductores; n++){
            try {
                productores[n].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while(!restaurante.isShopClosed()){}

    }

    public static AppProductorConsumidor getInstance() {
        if (appProductorConsumidorInstance == null) {
            appProductorConsumidorInstance = new AppProductorConsumidor();
        }
        return appProductorConsumidorInstance;
    }
}
