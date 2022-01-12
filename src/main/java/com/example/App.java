package com.example;

import java.util.Scanner;

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

        // Crée un gestionnaire permettant de sureveiller ce que l'utilisateur entre dans la console
        Scanner scanner = new Scanner(System.in);
        // Attend une saisie utilisateur
        String userInput = scanner.nextLine();

        // Si la saisie utilisateur ne contient pas exactement 4 caractères
        if (userInput.length() != 4) {
            // Arrête prématurément le processus avec un message d'erreur
            throw new IllegalArgumentException("Invalid user input size.");
        }

        // Pour chaque caractère de la saisie utilisateur
        for (char character : userInput.toCharArray()) {
            // Si le caractère n'est ni R, V, B, C, J ou M
            if (character != 'R' &&
                character != 'V' &&
                character != 'B' &&
                character != 'C' &&
                character != 'J' &&
                character != 'M'
            ) {
                // Arrête prématurément le processus avec un message d'erreur
                throw new IllegalArgumentException("Invalid character in user input.");
            }
        }

        System.out.print("Hello, world!");
    }
}
