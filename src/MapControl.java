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

    public int moveCharacter (int direction) {
        int x1=0,x2=0,y1=0,y2=0;
        switch (direction) {
            case 'w': {
                x1 = -1;
                x2 = -2;
                y1 = 0;
                y2 = 0;
                break;
            }
            case 's':{
                x1 = +1;
                x2 = +2;
                y1 = 0;
                y2 = 0;
                break;
            }
            case 'a':{
                x1 = 0;
                x2 = 0;
                y1 = -1;
                y2 = -2;
                break;
            }
            case 'd':{
                x1 = 0;
                x2 = 0;
                y1 = +1;
                y2 = +2;
                break;
            }
        }

        //TODO: Implementálni mozgatást
        if (map.getTile(map.getPlayerX()+x1, map.getPlayerY()+y1) == '$' || map.getTile(map.getPlayerX()+x1, map.getPlayerY()+y1) == '*') {
            if (map.getTile(map.getPlayerX()+x2, map.getPlayerY()+y2) == '#' || map.getTile(map.getPlayerX()+x2, map.getPlayerY()+y2) == '$' || map.getTile(map.getPlayerX()+x2, map.getPlayerY()+y2) == '*') {
                System.out.println("Nem tudsz erre lépni!");
            } else {
                if (map.getTile(map.getPlayerX()+x2, map.getPlayerY()+y2) == '.') {
                    map.setMap(map.getPlayerX(), map.getPlayerY(), map.getPlayerTile()); //map[karakter.x][karakter.y] = originalMap[karakter.x][karakter.y];
                    map.setPlayerTile(map.getBoxTile(map.getPlayerX()+x1, map.getPlayerY()+y1));
                    map.setBoxTile(map.getPlayerX()+x1, map.getPlayerY()+y1, map.getPlayerX()+x2, map.getPlayerY()+y2,'.');

                    map.setMap(map.getPlayerX()+x2, map.getPlayerY()+y2, '*'); //map[karakter.x - 2][karakter.y] = '*';
                    map.setMap(map.getPlayerX()+x1, map.getPlayerY()+y1, '@');  //map[karakter.x - 1][karakter.y] = 'I';          ///Meg kell keresni miért ír ki plusz 0-kat
                    map.setPlayerX(map.getPlayerX()+x1);  //karakter.x -= 1;
                    map.setPlayerY(map.getPlayerY()+y1);
                    map.addMoves();
                } else {
                    map.setMap(map.getPlayerX(), map.getPlayerY(), map.getPlayerTile()); //map[karakter.x][karakter.y] = originalMap[karakter.x][karakter.y];
                    map.setPlayerTile(map.getBoxTile(map.getPlayerX()+x1, map.getPlayerY()+y1));
                    map.setBoxTile(map.getPlayerX()+x1, map.getPlayerY()+y1, map.getPlayerX()+x2, map.getPlayerY()+y2,' ');

                    map.setMap(map.getPlayerX()+x2, map.getPlayerY()+y2, '$'); //map[karakter.x - 2][karakter.y] = '$';
                    map.setMap(map.getPlayerX()+x1, map.getPlayerY()+y1, '@');  //map[karakter.x - 1][karakter.y] = 'I';
                    map.setPlayerX(map.getPlayerX()+x1);  //karakter.x -= 1;
                    map.setPlayerY(map.getPlayerY()+y1);
                    map.addMoves();
                }
            }
        } else if (map.getTile(map.getPlayerX()+x1, map.getPlayerY()+y1) == ' ' || map.getTile(map.getPlayerX()+x1, map.getPlayerY()+y1) == '.') {
            map.setMap(map.getPlayerX(), map.getPlayerY(), map.getPlayerTile()); //map[karakter.x][karakter.y] = originalMap[karakter.x][karakter.y];
            map.setPlayerTile(map.getTile(map.getPlayerX()+x1, map.getPlayerY()+y1));
            map.setMap(map.getPlayerX()+x1, map.getPlayerY()+y1, '@');
            map.setPlayerX(map.getPlayerX()+x1);
            map.setPlayerY(map.getPlayerY()+y1);
            map.addMoves();
        } else if (map.getTile(map.getPlayerX()+x1, map.getPlayerY()+y1) == '*') {
            if (map.getTile(map.getPlayerX()+x2, map.getPlayerY()+y2) == '#' || map.getTile(map.getPlayerX()+x2, map.getPlayerY()+y2) == '$' || map.getTile(map.getPlayerX()+x2, map.getPlayerY()+y2) == '*') { //map[karakter.x][karakter.y - 2] == '$'
                System.out.println("Nem tudsz erre lepni!");
            } else {
                map.setMap(map.getPlayerX(), map.getPlayerY(), map.getPlayerTile());
                map.setPlayerTile(map.getBoxTile(map.getPlayerX()+x1, map.getPlayerY()+y1));
                map.setBoxTile(map.getPlayerX()+x1, map.getPlayerY()+y1, map.getPlayerX()+x2, map.getPlayerY()+y2,map.getTile(map.getPlayerX()+x2, map.getPlayerY()+y2));
                map.setMap(map.getPlayerX()+x2, map.getPlayerY()+y2, '$');
                map.setMap(map.getPlayerX()+x1, map.getPlayerY()+y1, '@');
                map.setPlayerX(map.getPlayerX()+x1);
                map.setPlayerY(map.getPlayerY()+y1);
                map.addMoves();
            }
        } else {
            System.out.println("Nem tudsz erre lepni!\n");
        }


        drawMap();

        if (map.win()) {
            return 1;
        }
        if (map.cornered()) {
            return 0;
        }
        return 2;

    }

}
