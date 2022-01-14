package com.example.game;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ColorCombinationTest
{
    @Test
    public void testConstructors()
    {
        ColorCombination combination;
        combination = new ColorCombination(new Color[] { Color.Magenta, Color.Magenta, Color.Cyan, Color.Red });
        assertArrayEquals(new Color[] { Color.Magenta, Color.Magenta, Color.Cyan, Color.Red }, combination.getColors());

        combination = new ColorCombination("MMCR");
        assertArrayEquals(new Color[] { Color.Magenta, Color.Magenta, Color.Cyan, Color.Red }, combination.getColors());
    }

    @Test
    public void testComparison()
    {
        ColorCombination solution = new ColorCombination("MMCR");
        ColorCombination proposition = new ColorCombination("MRMR");

        ComparisonResult result = proposition.compareTo(solution);
        assertEquals(2, result.getCorrectCount());
        assertEquals(1, result.getMisplacedCount());
        assertEquals(1, result.getAbsentCount());
        assertEquals(false, result.isWinning());
    }
}
