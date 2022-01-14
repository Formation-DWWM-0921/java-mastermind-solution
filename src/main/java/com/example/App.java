package com.example;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.game.Color;

/**
 * Point d'entrée de l'application
 */
public class App
{
    /**
     * Processus principal de l'application
     */
    public static void main(String[] args) throws Exception {
        Color[] allColors = Color.values();


        // Efface la console
        System.out.print("\033[H\033[2J");   
        System.out.flush();
        
        // Crée une solution
        Color[] solution = new Color[] { Color.Magenta, Color.Magenta, Color.Cyan, Color.Red };

        // Crée un gestionnaire permettant de sureveiller ce que l'utilisateur entre dans la console
        Scanner scanner = new Scanner(System.in);

        // Répète indéfiniment un tour de jeu
        while (true) {
            // Attend une saisie utilisateur
            String userInput = scanner.nextLine();

            // Vérifie que la saisie utilisateur contient exactement 4 caractères parmi ceux autorisés
            Pattern pattern = Pattern.compile("^[RVBJCM]{4}$");
            Matcher matcher = pattern.matcher(userInput);
            if (!matcher.matches()) {
                scanner.close();
                throw new IllegalArgumentException("Invalid user input");
            }

            // Convertit la saisie utilisateur en tableau de couleurs
            // Crée un tableau vide de 4 couleurs
            Color[] proposition = new Color[4];
            // Pour chaque caractère de la saisie utilisateur
            for (int i = 0; i < 4; i ++) {
                // Convertir le caractère en couleur et l'insère dans la combinaison
                proposition[i] = Color.findByCharacter(userInput.charAt(i));
            }

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

            // Initialise le nombre de couleurs absentes
            int absentCount = 0;
            // Pour chaque couleur parmi toutes les couleurs possibles
            for (Color color : Color.values()) {
                // Compte la quantité de cette couleur présente dans la proposition de l'utilisateur
                int propositionColorCount = 0;
                for (Color propositionColor : proposition) {
                    if (propositionColor == color) {
                        propositionColorCount ++;
                    }
                }

                // Compte la quantité de cette couleur présente dans la solution de l'utilisateur
                int solutionColorCount = 0;
                for (Color solutionColor : solution) {
                    if (solutionColor == color) {
                        solutionColorCount ++;
                    }
                }

                // Ajoute à la quantité de couleurs absentes, la différence entre la quantité de cette couleur
                // dans la proposition de l'utilisateur et dans la solution
                // Uniquement dans le cas où il s'agit de couleurs dans la proposition qui manquent dans la solution
                // (et pas de couleurs dans la solution qui manquent dans la proposition)
                if (propositionColorCount > solutionColorCount) {
                    absentCount += propositionColorCount - solutionColorCount;
                }
            }

            // Détermine la nombre de couleurs mal placées par déduction,
            // car si une couleur n'est ni bien placée, ni absente, elle est forcément mal placée
            int misplacedCount = 4 - correctCount - absentCount;

            // Affiche le résultat de la comparaison pour l'utilisateur
            for (int i = 0; i < correctCount; i ++) {
                System.out.print("O ");
            }
            for (int i = 0; i < misplacedCount; i ++) {
                System.out.print("X ");
            }
            for (int i = 0; i < absentCount; i ++) {
                System.out.print("- ");
            }

            System.out.println("");

            // Si l'utilisateur a trouvé exactement la solution
            if (correctCount == 4) {
                // Affiche un message
                System.out.println("Bravo! Vous avez trouvé la solution!");
                // Arrête la boucle infinie
                break;
            }
        }

        scanner.close();
    }
}
