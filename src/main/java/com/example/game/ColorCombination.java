package com.example.game;

import java.util.Random;

/**
 * Représente une combinaison de couleurs
 */
public class ColorCombination
{
    /**
     * Les couleurs qui forment la combinaison
     */
    private Color[] colors;

    /**
     * Crée de une combinaison de couleurs générées aléatoirement
     * @param size La taille de la combinaison
     * @return
     */
    public static ColorCombination createRandom(int size)
    {
        // Récupère la liste de toutes les couleurs existantes
        Color[] allColors = Color.values();
        // Crée un nouveau tableau de couleurs de la taille désirée
        Color[] colors = new Color[size];
        // Crée un générateur de nombres aléatoires
        Random randomGenerator = new Random();
        // Pour chaque emplacement du tableau de couleurs
        for (int i = 0; i < size; i ++) {
            // Ajoute une couleur choisie au hasard à cet emplacement
            colors[i] = allColors[randomGenerator.nextInt(6)];
        }
        // Renvoie une combinaison de couleurs crée à partir de ce tableau de couleurs
        return new ColorCombination(colors);
    }

    /**
     * Crée une nouvelle combinaison de couleurs
     * @param colors Les couleurs qui forment la combinaison
     */
    public ColorCombination(Color[] colors)
    {
        this.colors = colors;
    }

    /**
     * Crée une nouvelle combinaison de couleurs à partir d'une chaîne de caractères
     * @param input La chaîne de caractère à analyser
     */
    public ColorCombination(String input)
    {
        this.colors = new Color[input.length()];
        for (int i = 0; i < input.length(); i ++) {
            // Convertir le caractère en couleur et l'insère dans la combinaison
            this.colors[i] = Color.findByCharacter(input.charAt(i));
        }
    }

    /**
     * Effectue une comparaison avec une autre combinaison de couleurs
     * @param otherCombination L'autre combinaison de couleurs
     * @return
     */
    public ComparisonResult compareTo(ColorCombination otherCombination)
    {
        // Compte le nombre de couleurs bien placées par rapport à l'autre combinaison
        int correctCount = 0;
        for (int i = 0; i < 4; i ++) {
            // Si les deux couleurs sont identiques
            if (this.colors[i] == otherCombination.colors[i]) {
                // Incrémente le nombre de couleurs bien placées
                correctCount ++;
            }
        }

        // Compte le nombre de couleurs absentes de l'autre combinaison
        int absentCount = 0;
        // Pour chaque couleur parmi toutes les couleurs possibles
        for (Color color : Color.values()) {
            // Compte la quantité de cette couleur présente dans la proposition de l'utilisateur
            int propositionColorCount = 0;
            for (Color propositionColor : this.colors) {
                if (propositionColor == color) {
                    propositionColorCount ++;
                }
            }

            // Compte la quantité de cette couleur présente dans la solution de l'utilisateur
            int solutionColorCount = 0;
            for (Color solutionColor : otherCombination.colors) {
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

        return new ComparisonResult(correctCount, misplacedCount, absentCount);
    }
}
