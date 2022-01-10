package com.madirex.procesos.lector_escritor;

public class AppLectorEscritor {

    private static AppLectorEscritor appLectorEscritorInstance;

    private AppLectorEscritor() {
        Library library = new Library();
        int numLectores = 3;
        int numEscritores = 3;
        int booksNumberPerPerson = 5;

        Lector[] lectores = new Lector[numLectores];
        Escritor[] escritores = new Escritor[numEscritores];

        //Crear los escritores y los lectores
        for (int n = 0; n < numLectores; n++){
            lectores[n] = new Lector(booksNumberPerPerson,library);
        }

        for (int n = 0; n < numEscritores; n++){
            escritores[n] = new Escritor(booksNumberPerPerson,library);
        }

        //Iniciar los escritores y los lectores
        for (int n = 0; n < numLectores; n++){
            lectores[n].start();
        }

        for (int n = 0; n < numEscritores; n++){
            escritores[n].start();
        }

        //Esperar a que todos terminen para terminar programa
        //Iniciar los escritores y los lectores
        for (int n = 0; n < numLectores; n++){
            try {
                lectores[n].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int n = 0; n < numEscritores; n++){
            try {
                escritores[n].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Programa finalizado.");
    }

    public static AppLectorEscritor getInstance() {
        if (appLectorEscritorInstance == null) {
            appLectorEscritorInstance = new AppLectorEscritor();
        }
        return appLectorEscritorInstance;
    }
}
