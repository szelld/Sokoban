public class Box {

    private int x, y, tileBefore;

    public Box (int x, int y, int tileBefore) {
        this.x = x;
        this.y = y;
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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setTileBefore(int tileBefore) {
        this.tileBefore = tileBefore;
    }
}
