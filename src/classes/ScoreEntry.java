package classes;

import java.io.Serializable;

public class ScoreEntry implements Serializable {
    private String name;
    private Integer move;


    /**
     * Létehoz egy scoreboard bejegyzést
     * @param name A játékos neve
     * @param move A játékos lépéseinek száma
     */
    public ScoreEntry(String name, int move) {
        this.name = name;
        this.move = move;
    }

    public String getName() {
        return name;
    }

    public Integer getMove() {
        return move;
    }
}
