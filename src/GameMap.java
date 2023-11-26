import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class GameMap {
    private ArrayList<Integer> map = new ArrayList<>();
    private Player player = new Player();
    private int rows=0, columns=0;
    private Boxes boxes = new Boxes();
    private int moves = 0;


    public GameMap () {
        /*map = new ArrayList<Integer>(rows*columns);
        this.rows = rows;
        this.columns = columns;*/ //TODO: Ez valószínűleg nem kell
    }

    public void loadMap (String fileName) throws Exception {
        FileReader fileReader = new FileReader(fileName);
        BufferedReader fIn = new BufferedReader(fileReader);
        int character;
        while (fIn.ready()) {
            character = fIn.read();
            if (character != 10) {
                if (character != 13) {
                    map.add(character);
                    columns++;
                }
            }
            else {
                rows++;
                columns =0;
            }
            if (character == '@') {
                player.setX(rows);
                player.setY(columns-1);
                player.setTileBefore(' ');
            }
            if (character == '$') {
                Box box = new Box(rows,columns-1, ' ');
                boxes.addBox(box);
            }
            if (character == '*') {
                Box box = new Box(rows,columns-1, '.');
                boxes.addBox(box);
            }

        }
        if(map.size()%rows == 0) columns = map.size()/rows;
        else columns = (map.size()/rows)+1;
    }

    public void setMap (int x, int y, int character) {
        map.set(x*columns+y, character);
    }

    public int getTile (int x, int y) {
        return map.get(x*columns+y);
    }

    public int getRows () {
        return rows;
    }

    public int getColumns () {
        return columns;
    }

    public int getPlayerX () {
        return player.getX();
    }

    public int getPlayerY () {
        return player.getY();
    }

    public int getPlayerTile () {
        return player.getTileBefore();
    }

    public int getBoxTile (int x, int y) {
        return boxes.getBoxTile(x, y);
    }

    public int getMoves() {
        return moves;
    }

    public void setPlayerX (int x) {
        player.setX(x);
    }

    public void setPlayerY (int y) {
        player.setY(y);
    }

    public void setPlayerTile (int tile) {
        player.setTileBefore(tile);
    }

    public void addMoves() {
        this.moves++;
    }

    public void setBoxTile (int prevX, int prevY, int newX, int newY, int tile) {
        boxes.setBoxTile(prevX, prevY, newX, newY, tile);
    }

    public boolean cornered () {

        for (Box box: boxes.getBoxes()) {

            if (getTile(box.getX(),box.getY()) == '$') {
                if (getTile(box.getX(),box.getY()-1) == '#' || getTile(box.getX(),box.getY()-1) == '$' || getTile(box.getX(),box.getY()-1) == '*') {
                    if (getTile(box.getX()-1,box.getY()) == '#' || getTile(box.getX()-1,box.getY()) == '$' || getTile(box.getX()-1,box.getY()) == '*') {
                        return true;
                    }
                    if (getTile(box.getX()+1,box.getY()) == '#' || getTile(box.getX()+1,box.getY()) == '$' || getTile(box.getX()+1,box.getY()) == '*') {
                        return true;
                    }
                }
                if (getTile(box.getX(),box.getY()+1) == '#' || getTile(box.getX(),box.getY()+1) == '$' || getTile(box.getX(),box.getY()+1) == '*') {
                    if (getTile(box.getX()+1,box.getY()) == '#' || getTile(box.getX()+1,box.getY()) == '$' || getTile(box.getX()+1,box.getY()) == '*') {
                        return true;
                    }
                    if (getTile(box.getX()+1,box.getY()) == '#' || getTile(box.getX()+1,box.getY()) == '$' || getTile(box.getX()+1,box.getY()) == '*') {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean win () {
        for (Box box: boxes.getBoxes()) {
            if (box.getTileBefore() != '.') {
                return false;
            }
        }
        return true;
    }
}
