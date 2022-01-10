package com.madirex.procesos;

import com.madirex.procesos.carrera_camellos.AppCamellos;
import com.madirex.procesos.lector_escritor.AppLectorEscritor;
import com.madirex.procesos.philosophers_comensales.AppPhilosophers;
import com.madirex.procesos.procesos.AppProcesos;
import com.madirex.procesos.productor_consumidor.AppProductorConsumidor;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        boolean exitProgram = false;
        int sel = -1;
        Scanner sc;
        int minSel = 0;
        int maxSel = 5;

        do {
            do {
                sc = new Scanner(System.in);
                System.out.println("\n⚡ Selecciona el programa a ejecutar ⚡" +
                        "\n\t0. Salir ❌" +
                        "\n\t1. Carrera camellos 🐫" +
                        "\n\t2. Productor consumidor 👷‍♀️" +
                        "\n\t3. Lector escritor 📚" +
                        "\n\t4. Filósofos comensales 🍽" +
                        "\n\t5. Procesos y web 👨‍💻\n");

                try {
                    sel = sc.nextInt();
                } catch (InputMismatchException e) {
                    System.err.println("No has introducido un valor correcto.");
                    sel = -1;
                }

                if (sel != -1 && (sel < minSel || sel > maxSel)) {
                    System.err.println("No has introducido un valor correcto.");
                    sel = -1;
                }

            } while (sel == -1);

            switch (sel) {
                case 1:
                    AppCamellos.getInstance();
                    break;
                case 2:
                    AppProductorConsumidor.getInstance();
                    break;
                case 3:
                    AppLectorEscritor.getInstance();
                    break;
                case 4:
                    AppPhilosophers.getInstance();
                    break;
                case 5:
                    AppProcesos.getInstance();
                    break;
                default:
                    System.exit(0);
                    break;
            }
        }while(!exitProgram);
    }
}
