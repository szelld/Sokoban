package SokobanTest;

import classes.*;
import org.junit.Test;
import javax.swing.*;

import static org.junit.Assert.*;



public class MapControlTest {

    @Test
    public void testDrawMap() throws Exception {
        GameMap map = new GameMap();
        map.loadMap("1");
        JPanel gamePanel = new JPanel();
        MapControl mapControl = new MapControl(map, gamePanel);

        mapControl.drawMap();
    }

    @Test
    public void testMoveCharacter() throws Exception {
        GameMap map = new GameMap();
        map.loadMap("testMaps/test");
        JPanel gamePanel = new JPanel();
        MapControl mapControl = new MapControl(map, gamePanel);

        try {
            assertEquals(2, mapControl.moveCharacter('w'));
            assertEquals(2, mapControl.moveCharacter('s'));
            assertEquals(2, mapControl.moveCharacter('a'));
            assertEquals(2, mapControl.moveCharacter('d'));
        } catch (Exception e) {
            fail("Exception occurred during moveCharacter: " + e.getMessage());
        }
    }

    @Test
    public void testConstructor() {

        GameMap map = new GameMap();
        JPanel gamePanel = new JPanel();
        MapControl mapControl = new MapControl(map, gamePanel);
        assertNotNull(mapControl);
    }

}
