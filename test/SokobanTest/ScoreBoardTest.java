package SokobanTest;

import classes.*;
import org.junit.Test;
import static org.junit.Assert.*;
import classes.Scoreboard;

public class ScoreBoardTest {
    @Test
    public void testAddEntry() throws Exception {
        Scoreboard scoreboard = new Scoreboard("test/SokobanTest/test_sc.txt");
        scoreboard.add("Player1", 50);

        assertEquals(7, scoreboard.getRowCount());
    }

    @Test
    public void testScoreEntry() {
        ScoreEntry entry = new ScoreEntry("Player1", 50);

        assertEquals("Player1", entry.getName());
        assertEquals((Integer) 50, entry.getMove());
    }

    @Test
    public void testConstrucThor() throws Exception {
        Scoreboard scoreboard = new Scoreboard("test/SokobanTest/test_sc.txt");

        assertNotNull(scoreboard);
    }
}
