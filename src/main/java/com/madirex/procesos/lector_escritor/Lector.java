package com.madirex.procesos.lector_escritor;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Lector extends Thread{
    private static AtomicInteger counterId = new AtomicInteger(0);
    private int booksToRead;
    private final int ID;
    private Random random;
    private Library library;

    public Lector(int booksToRead, Library library){
        random = new Random();
        this.booksToRead = booksToRead;
        this.library = library;
        this.ID = counterId.addAndGet(1);
    }

    @Override
    public void run() {
        for(int n = 0; n < booksToRead; n++){
            try {
                Thread.sleep(random.nextInt(200));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            library.read(ID);
        }

        System.out.println("El lector 👁 " + ID + " se ha aburrido y se va 🚪");
    }
}
