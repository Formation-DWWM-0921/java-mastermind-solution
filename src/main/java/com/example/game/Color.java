package com.example.game;

/**
 * Représente une couleur existante dans le jeu
 */
public enum Color
{
    Red('R'),
    Green('V'),
    Blue('B'),
    Cyan('C'),
    Yellow('J'),
    Magenta('M');

    /**
     * Le caractère associé à là couleur
     */
    private char character;

    /**
     * Crée une nouvelle couleur
     * @param character Le caractère associé à là couleur
     */
    private Color(char character)
    {
        this.character = character;
    }

    /**
     * @return Le caractère associé à la couleur
     */
    public char getCharacter()
    {
        return this.character;
    }
    
    /**
     * @param character Le caractère recherché
     * @return La couleur correspondant au caractère recherché
     */
    public static Color findByCharacter(char character)
    {
        // Pour chaque couleur parmi toutes les couleurs possibles
        for (Color color : Color.values()) {
            // Si le caractêre recherché est le même que celui associé à la couleur
            if (character == color.getCharacter()) {
                // Renvoie la couleur
                return color;
            }
        }
        // Si aucune couleur ne corrspond au caractère recherché
        throw new IllegalArgumentException("No color matches character '" + character + "'.");
    }
}
