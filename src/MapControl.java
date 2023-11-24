import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MapControl {
    private GameMap map;
    private JPanel gamePanel;
    
    public MapControl (GameMap map, JPanel gamePanel) {
        this.map = map;
        this.gamePanel = gamePanel;
    }

    public void drawMap () {
        int rows = map.getRows(), columns = map.getColumns(), character;
        //gamePanel = new JPanel(new GridLayout(rows,columns));
        gamePanel.removeAll();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                ImageIcon icon;
                character = map.getTile(i,j);
                switch (character) {
                    case '#': {
                        icon = new ImageIcon("src/objects/Wall_Black.png");
                        break;
                    }
                    case '.': {
                        icon = new ImageIcon("src/objects/GroundGravel_Dirt_Point.png");
                        break;
                    }
                    case '$': {
                        icon = new ImageIcon("src/objects/GroundGravel_Dirt_Box.png");
                        break;
                    }
                    case '@': {
                        icon = new ImageIcon("src/objects/GroundGravel_ForwardPeople.png");
                        break;
                    }
                    case '*': {
                        icon = new ImageIcon("src/objects/GroundGravel_Box_GoodPosi.png");
                        break;
                    }
                    default: {
                        icon = new ImageIcon("src/objects/GroundGravel_Dirt.png");
                        break;
                    }
                }

                JLabel image  = new JLabel(icon);
                gamePanel.add(image);
            }
        }
        SwingUtilities.updateComponentTreeUI(gamePanel);
    }

    public void moveCharacter (int direction) {
        //TODO: Implementálni mozgatást
        switch (direction) {
            case 'w': {
                if (map.getTile(map.getPlayerX()-1, map.getPlayerY()) == '$') {
                    if (map.getTile(map.getPlayerX()-2, map.getPlayerY()) == '#' || map.getTile(map.getPlayerX()-2, map.getPlayerY()) == '$' || map.getTile(map.getPlayerX()-2, map.getPlayerY()) == '*') {
                        System.out.println("Nem tudsz erre lépni!");
                    } else {
                        if (map.getTile(map.getPlayerX()-2, map.getPlayerY()) == '.') {
                            map.setMap(map.getPlayerX(), map.getPlayerY(), map.getPlayerTile()); //map[karakter.x][karakter.y] = originalMap[karakter.x][karakter.y];
                            map.setPlayerTile(map.getBoxTile(map.getPlayerX()-1, map.getPlayerY()));
                            map.setBoxTile(map.getPlayerX()-1, map.getPlayerY(), map.getPlayerX()-2, map.getPlayerY(),'.');

                            map.setMap(map.getPlayerX()-2, map.getPlayerY(), '*'); //map[karakter.x - 2][karakter.y] = '*';
                            map.setMap(map.getPlayerX()-1, map.getPlayerY(), '@');  //map[karakter.x - 1][karakter.y] = 'I';          ///Meg kell keresni miért ír ki plusz 0-kat
                            map.setPlayerX(map.getPlayerX()-1);  //karakter.x -= 1;
                        } else {
                            map.setMap(map.getPlayerX(), map.getPlayerY(), map.getPlayerTile()); //map[karakter.x][karakter.y] = originalMap[karakter.x][karakter.y];
                            map.setPlayerTile(map.getBoxTile(map.getPlayerX()-1, map.getPlayerY()));
                            map.setBoxTile(map.getPlayerX()-1, map.getPlayerY(), map.getPlayerX()-2, map.getPlayerY(),' ');

                            map.setMap(map.getPlayerX()-2, map.getPlayerY(), '$'); //map[karakter.x - 2][karakter.y] = '$';
                            map.setMap(map.getPlayerX()-1, map.getPlayerY(), '@');  //map[karakter.x - 1][karakter.y] = 'I';
                            map.setPlayerX(map.getPlayerX()-1);  //karakter.x -= 1;
                        }
                    }
                } else if (map.getTile(map.getPlayerX()-1, map.getPlayerY()) == ' ' || map.getTile(map.getPlayerX()-1, map.getPlayerY()) == '.') {
                    map.setMap(map.getPlayerX(), map.getPlayerY(), map.getPlayerTile()); //map[karakter.x][karakter.y] = originalMap[karakter.x][karakter.y];
                    map.setPlayerTile(map.getTile(map.getPlayerX()-1, map.getPlayerY()));
                    map.setMap(map.getPlayerX()-1, map.getPlayerY(), '@');
                    map.setPlayerX(map.getPlayerX()-1);
                } /*else if (map.getTile(map.getPlayerX()-1, map.getPlayerY()) == '.') {
                    map.setMap(map.getPlayerX(), map.getPlayerY(), map.getPlayerTile()); //map[karakter.x][karakter.y] = originalMap[karakter.x][karakter.y];
                    map.setPlayerTile(map.getTile(map.getPlayerX()-1, map.getPlayerY()));
                    map.setMap(map.getPlayerX()-1, map.getPlayerY(), '@');
                    map.setPlayerX(map.getPlayerX()-1);
                }*/ else if (map.getTile(map.getPlayerX()-1, map.getPlayerY()) == '*') {
                    if (map.getTile(map.getPlayerX()-2, map.getPlayerY()) == '#' || map.getTile(map.getPlayerX()-2, map.getPlayerY()) == '$' || map.getTile(map.getPlayerX()-2, map.getPlayerY()) == '*') { //map[karakter.x][karakter.y - 2] == '$'
                        System.out.println("Nem tudsz erre lepni!");
                    } else {
                        map.setMap(map.getPlayerX(), map.getPlayerY(), map.getPlayerTile());
                        map.setPlayerTile(map.getBoxTile(map.getPlayerX()-1, map.getPlayerY()));
                        map.setBoxTile(map.getPlayerX()-1, map.getPlayerY(), map.getPlayerX()-2, map.getPlayerY(),map.getTile(map.getPlayerX()-2, map.getPlayerY()));
                        map.setMap(map.getPlayerX()-1, map.getPlayerY(), '@');
                        map.setMap(map.getPlayerX()-2, map.getPlayerY(), '$');
                        map.setPlayerX(map.getPlayerX()-1);
                    }
                } else {
                    System.out.println("Nem tudsz erre lepni!\n");
                }
                break;
            }
        }
        drawMap();

    }

    public boolean winCondition () {
        //TODO: Implementálni a win condition chack-et
        return true;
    }
}
