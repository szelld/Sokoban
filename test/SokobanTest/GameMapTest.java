package SokobanTest;
import classes.*;
import org.junit.Test;

import javax.swing.*;
import java.util.Map;

import static org.junit.Assert.*;

public class GameMapTest {
    @Test
    public void testLoadMap() throws Exception {
        GameMap gameMap = new GameMap();
        try {
            gameMap.loadMap("testMaps/test");
        } catch (Exception e) {
            System.out.println("Exception thrown: " + e.getMessage());
        }
        assertEquals(3,gameMap.getPlayerX());
        assertEquals(3,gameMap.getPlayerY());
    }

    @Test
    public void testSetAndGetMap() throws Exception {
        GameMap gameMap = new GameMap();
        gameMap.loadMap("testMaps/test");

        gameMap.setMap(1, 1, '$');
        assertEquals('$', gameMap.getTile(1, 1));

        gameMap.setMap(2, 2, '*');
        assertEquals('*', gameMap.getTile(2, 2));
    }

    @Test
    public void testMoveCharacter() {

        GameMap gameMap = new GameMap();
        JPanel jPanel = new JPanel();
        MapControl mapControl = new MapControl(gameMap,jPanel);

        try {
            gameMap.loadMap("1");
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }

        try {
            mapControl.moveCharacter('w');
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }

        assertEquals(2, gameMap.getPlayerX());
        assertEquals(4, gameMap.getPlayerY());
        assertEquals(1, gameMap.getMoves());
    }

    @Test
    public void testCornered() {
        GameMap gameMap = new GameMap();
        JPanel jPanel = new JPanel();
        MapControl mapControl = new MapControl(gameMap,jPanel);

        try {
            gameMap.loadMap("testMaps/1");
        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }

        assertTrue(gameMap.cornered());
    }

}
