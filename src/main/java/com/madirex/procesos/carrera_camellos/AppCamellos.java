package com.madirex.procesos.carrera_camellos;

public class AppCamellos {

    private static AppCamellos appCamellosInstance;

    private AppCamellos() {
        Camello[] camellos = new Camello[5];

        for (int n = 0; n < camellos.length; n++) {
            camellos[n] = new Camello(n);
        }

        for (int n = 0; n < camellos.length; n++) {
            camellos[n].start();
        }

        for (int n = 0; n < camellos.length; n++) {
            try {
                camellos[n].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\nCarrera finalizada\n");
        Podio.getInstance().showResults();

    }

    public static AppCamellos getInstance() {
        if (appCamellosInstance == null) {
            appCamellosInstance = new AppCamellos();
        }
        return appCamellosInstance;
    }
}