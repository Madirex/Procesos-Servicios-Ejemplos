package com.madirex.procesos.lector_escritor;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Escritor extends Thread{
    private static AtomicInteger counterId = new AtomicInteger(0);
    private int booksToWrite;
    private final int ID;
    private Random random;
    private Library library;

    public Escritor(int booksToWrite, Library library){
        random = new Random();
        this.booksToWrite = booksToWrite;
        this.library = library;
        this.ID = counterId.addAndGet(1);
    }

    @Override
    public void run() {
        for(int n = 0; n < booksToWrite; n++){
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            library.write(ID);
        }

        System.out.println("El escritor ✍ " + ID + " se ha aburrido y se va 🚪");
    }
}
