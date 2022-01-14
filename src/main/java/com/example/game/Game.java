package com.example.game;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Représente une partie jouée par l'utilisateur
 */
public class Game
{
    /**
     * La solution de la partie en cours
     */
    private ColorCombination solution;
    /**
     * Le gestionnaire permettant de surveiller ce que l'utilisateur entre dans la console
     */
    private Scanner scanner;
    /**
     * Le jeu est-il en cours d'exécution?
     */
    private boolean isRunning;

    /**
     * Crée une nouvelle partie jouée par l'utilisateur
     */
    public Game()
    {
        scanner = new Scanner(System.in);
    }

    /**
     * Configure la partie
     */
    public void setup()
    {
        // Crée une solution
        solution = ColorCombination.createRandom(4);
        // Indique que la partie est en cours
        isRunning = true;
    }

    /**
     * Représente un cycle d'exécution du jeu
     */
    public void update()
    {
        // Attend une saisie utilisateur
        String userInput = scanner.nextLine();

        // Vérifie que la saisie utilisateur contient exactement 4 caractères parmi ceux autorisés
        Pattern pattern = Pattern.compile("^[RVBJCM]{4}$");
        Matcher matcher = pattern.matcher(userInput);
        if (!matcher.matches()) {
            scanner.close();
            throw new IllegalArgumentException("Invalid user input");
        }

        // Interprète la saisie de l'utilisateur comme une combinaison de couleurs
        ColorCombination proposition = new ColorCombination(userInput);

        // Demande à la proposition de l'utilisateur de se comparer avec la solution
        ComparisonResult result = proposition.compareTo(solution);

        // Affiche le résultat de la comparaison pour l'utilisateur
        System.out.println(result.makeFeedback());
        System.out.println("");

        // Si l'utilisateur a trouvé exactement la solution
        if (result.isWinning()) {
            // Affiche un message
            System.out.println("Bravo! Vous avez trouvé la solution!");
            // Arrête la partie
            terminate();
        }
    }

    /**
     * @return La partie est-elle en cours d'exécution?
     */
    public boolean isRunning()
    {
        return isRunning;
    }

    /**
     * Arrête la partie
     */
    public void terminate()
    {
        scanner.close();
        isRunning = false;
    }
}
