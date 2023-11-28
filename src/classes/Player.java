package classes;

public class Player {
    private int x,y, tileBefore;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setTileBefore(int tileBefore) {
        this.tileBefore = tileBefore;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getTileBefore() {
        return tileBefore;
    }
}
