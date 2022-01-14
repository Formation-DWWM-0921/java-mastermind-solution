package com.example.game;

/**
 * Représente le résultat d'une comparaison entre deux combinaisons de couleurs
 */
public class ComparisonResult
{
    /**
     * La quantité de couleurs bien placées
     */    
    private int correctCount;
    /**
     * La quantité de couleurs mal placées
     */
    private int misplacedCount;
    /**
     * La quantité de couleurs absentes
     */
    private int absentCount;
    
    /**
     * Crée un nouveau résultat de comparaison 
     * @param correctCount La quantité de couleurs bien placées
     * @param misplacedCount La quantité de couleurs mal placées
     * @param absentCount La quantité de couleurs absentes
     */
    public ComparisonResult(int correctCount, int misplacedCount, int absentCount)
    {
        this.correctCount = correctCount;
        this.misplacedCount = misplacedCount;
        this.absentCount = absentCount;
    }

    /**
     * Construit une réponse affichable pour l'utilisateur
     */
    public String makeFeedback()
    {
        String feedback = "";
        for (int i = 0; i < correctCount; i ++) {
            feedback += "O ";
        }
        for (int i = 0; i < misplacedCount; i ++) {
            feedback += "X ";
        }
        for (int i = 0; i < absentCount; i ++) {
            feedback += "- ";
        }
        return feedback;
    }

    /**
     * Détermine si le résultat de la comparaison correspond à une victoire
     * @return
     */
    public boolean isWinning()
    {
        return correctCount == 4;
    }
    
    public int getCorrectCount() {
        return correctCount;
    }

    public int getMisplacedCount() {
        return misplacedCount;
    }

    public int getAbsentCount() {
        return absentCount;
    }
}
