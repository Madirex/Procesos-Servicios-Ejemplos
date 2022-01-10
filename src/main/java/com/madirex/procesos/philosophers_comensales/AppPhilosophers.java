package com.madirex.procesos.philosophers_comensales;

public class AppPhilosophers {

    private static AppPhilosophers appPhilosophersInstance;

    private AppPhilosophers() {
        int numberPhilosophers = 5;
        int vecesComer = 5;
        Philosopher[] philosophers = new Philosopher[numberPhilosophers];
        Tenedor[] tenedores = new Tenedor[numberPhilosophers];

        for (int n = 0; n < numberPhilosophers; n++){
            tenedores[n] = new Tenedor();
        }

        for (int n = 0; n < numberPhilosophers; n++) {
            philosophers[n] = new Philosopher(tenedores[n], tenedores[(n+1)%numberPhilosophers]);
            philosophers[n].start();
        }

        for (int n = 0; n < numberPhilosophers; n++) {
            try {
                philosophers[n].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Programa finalizado. Los filósofos 🧓 están satisfechos con sus vidas maravillosas ♥");

    }

    public static AppPhilosophers getInstance() {
        if (appPhilosophersInstance == null) {
            appPhilosophersInstance = new AppPhilosophers();
        }
        return appPhilosophersInstance;
    }
}
