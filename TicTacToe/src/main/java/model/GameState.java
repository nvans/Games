package model;

/**
 *
 * Game state enum
 *
 * @author Ivan Konovalov
 */
public enum GameState {
    X_MOVE("X move"),
    O_MOVE("O move"),
    X_WINS("X Win!"),
    O_WINS("O Win!"),
    DRAW("Draw!");

    private final String state;

    GameState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    @Override
    public String toString() {
        return state;
    }
}
