import java.util.HashMap;

public class Scoreboard {
    private HashMap<String, Integer> board;
    
    private String fileName;

    public Scoreboard (String fileName) {
        this.fileName = fileName;
        //TODO: Megcsinálni a serializálást
        board = new HashMap<>();
    }

    public void add (String name, int move) {
        if (board.containsKey(name)) {
            board.put(name, move);
        } else {
            for (int i = 0; i < board.size(); i++) {
                if (move < board.get(i)) {

                }
            }
        }
    }
}
