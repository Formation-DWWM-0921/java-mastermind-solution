package com.example;

import com.example.game.*;

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
        
        // Crée une nouvelle partie
        Game game = new Game();
        // Configure la partie
        game.setup();
        // Tant que la partie n'est pas terminée
        while (game.isRunning()) {
            // Exécute un tour de jeu
            game.update();
        }
    }
}
