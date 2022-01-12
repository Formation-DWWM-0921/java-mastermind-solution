package com.example;

/**
 * Point d'entrée de l'application
 */
public class App
{   
    /**
     * Processus principal de l'application
     */
    public static void main(String[] args) throws Exception {
        // Efface la console
        System.out.print("\033[H\033[2J");   
        System.out.flush();
        
        System.out.print("Hello, world!");
    }
}
