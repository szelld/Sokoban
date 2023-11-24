import java.util.ArrayList;

public class Boxes {
    private ArrayList<Box> boxes = new ArrayList<>();

    public void addBox (Box box) {
        boxes.add(box);
    }

    public int getBoxTile (int x, int y) {
        for (int i = 0; i < boxes.size(); i++) {
            if (boxes.get(i).getX() == x && boxes.get(i).getY() == y) {
                return boxes.get(i).getTileBefore();
            }
        }
        return ' ';
    }

    public void setBoxTile (int prevX, int prevY, int newX, int newY, int tile) {
        for (int i = 0; i < boxes.size(); i++) {
            if (boxes.get(i).getX() == prevX && boxes.get(i).getY() == prevY) {
                boxes.get(i).setX(newX);
                boxes.get(i).setY(newY);
                boxes.get(i).setTileBefore(tile);
            }
        }
    }
}
