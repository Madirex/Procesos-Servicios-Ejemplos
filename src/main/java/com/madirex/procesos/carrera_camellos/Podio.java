package com.madirex.procesos.carrera_camellos;

public class Podio {

    private static Podio podioInstance;
    private int podio[];
    private final int POSITIONS = 3;

    private Podio() {
        podio = new int[POSITIONS];

        for (int n = 0; n < podio.length; n++){
            podio[n] = -1;
        }
    }

    public void addPodio(int id){
        for (int n = 0; n < podio.length; n++){
            if (podio[n] == -1){
                podio[n] = id;
                break;
            }
        }
    }

    public void showResults(){
        System.out.println("🏁 Resultados de la carrera:");
        for (int n = 0; n < podio.length; n++){
            if (podio[n] != -1){
                System.out.println("🏅 Posición nº" + (n + 1) + ": " + podio[n]);
            }
        }
    }

    public static Podio getInstance() {
        if (podioInstance == null) {
            podioInstance = new Podio();
        }
        return podioInstance;
    }
}
