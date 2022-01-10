package com.madirex.procesos.procesos;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.commons.lang.SystemUtils;

public class AppProcesos {
    private static AppProcesos appProcesosInstance;
    private Desktop desktop;
    private ProcessBuilder processBuilder;
    private String pathScripts;

    private AppProcesos(){
        desktop = Desktop.getDesktop();
        processBuilder = new ProcessBuilder();
        pathScripts = System.getProperty("user.dir") + File.separator + "scripts" + File.separator;

        runCommand();
        runScript("example");
        readFile();
        searchUrl("https://www.madirex.com/");
    }

    private void runScript(String name) {
        if (SystemUtils.IS_OS_UNIX) {
            processBuilder.command(pathScripts + name + ".sh");
        }else{
            processBuilder.command(pathScripts + name + ".bat");
        }
    }

    private void runCommand() {
        if (SystemUtils.IS_OS_UNIX) {
            processBuilder.command("bash", "-c", "ls /Users/link");
        }else{
            processBuilder.command("cmd.exe", "/c", "dir C:\\Users\\link");
        }
    }

    public static AppProcesos getInstance() {
        if (appProcesosInstance == null) {
            appProcesosInstance = new AppProcesos();
        }
        return appProcesosInstance;
    }

    private synchronized void searchUrl(String url){
        try {
            System.out.println("En 5 segundos el navegador 🌐 abrirá la siguiente web: " + url);
            wait(5_000);
            desktop.browse(new URI(url));
        } catch (IOException | URISyntaxException | InterruptedException e) {
            System.err.println("Error al realizar la búsqueda: " + e.getMessage());
        }
    }

    private void readFile(){
        try {
            Process process = processBuilder.start();
            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;

            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            int exitVal = process.waitFor(); //Esperar a que el proceso termine.

            if (exitVal == 0) {
                System.out.println("Archivo leído:");
                System.out.println(output);

            }

        } catch (IOException | InterruptedException e) {
            System.out.println("Error al leer archivo: " + e.getMessage());
        }
    }
}
