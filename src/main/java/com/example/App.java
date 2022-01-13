package com.example;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        // Crée une solution
        char[] solution = new char[] { 'M', 'M', 'C', 'R' };

        // Crée un gestionnaire permettant de sureveiller ce que l'utilisateur entre dans la console
        Scanner scanner = new Scanner(System.in);
        // Attend une saisie utilisateur
        String userInput = scanner.nextLine();

        // Vérifie que la saisie utilisateur contient exactement 4 caractères parmi ceux autorisés
        Pattern pattern = Pattern.compile("^[RVBJCM]{4}$");
        Matcher matcher = pattern.matcher(userInput);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid user input");
        }

        // Convertit la saisie utilisateur en tableau de caractères
        char[] proposition = userInput.toCharArray();
        // Initialise le nombre de couleurs bien placées
        int correctCount = 0;
        // Pour chaque couple de caractères de la proposition de l'utilisateur, et de la solution
        for (int i = 0; i < 4; i ++) {
            // Si les deux caractères sont identiques
            if (solution[i] == proposition[i]) {
                // Incrémente le nombre de couleurs bien placées
                correctCount ++;
            }
        }

        System.out.print("Hello, world!");
    }
}
