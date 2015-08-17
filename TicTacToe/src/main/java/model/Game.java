package model;

/**
 * Game field description
 * and logic
 *
 * @author Ivan Konovalov
 */
public class Game {

    /**
     * The game field
     */
    private Integer[][] field;

    /**
     * The game field size
     */
    private final int size;

    /**
     * The game state
     */
    private GameState state = GameState.X_MOVE;

    /**
     *  Default no args constructor
     */
    public Game() {
       this(3);
    }

    /**
     * Init the game field
     * @param size
     */
    public Game(int size) {
        // size init
        if (size <= 3) {
            size = 3;
        }

        this.size = size;

        // field init
        this.field = new Integer[size][size];

        // fill the field
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                field[x][y] = 0;
            }
        }

    }


    /**
     * This method manages the order of the moves
     * and changes the cells by coordinates
     *
     * @param move
     */
    public void move(Move move) {
        switch (state){
            case X_MOVE:
                field[move.x][move.y] = 1;
                state = GameState.O_MOVE;
                break;
            case O_MOVE:
                field[move.x][move.y] = -1;
                state = GameState.X_MOVE;
                break;
        }
    }

    /**
     * This method checks the field
     * after each move for a winner.
     *
     * This logic work when we play with the
     * classic rules (row, column or diagonal
     * completely filled by X or O for win)
     *
     * When the game is over -
     * @return true
     */
    public boolean isGameOver(){
        int sum;
       
        // check rows
        for (int y = 0; y < size; y++) {
            sum = 0;
            for (int x = 0; x < size; x++) {
                sum += field[x][y];
            }
            if (sum == size || sum == - size) {
                state = sum > 0 ? GameState.X_WINS : GameState.O_WINS;
                return true;
            }
        }

        // check columns
        for (int y = 0; y < size; y++) {
            sum = 0;
            for (int x = 0; x < size; x++) {
                sum += field[y][x];
            }
            if (sum == size || sum == -size) {
                state = sum > 0 ? GameState.X_WINS : GameState.O_WINS;
                return true;
            }
        }
        
        // check major diagonal
        sum = 0;
        for (int x = 0; x < size; x++) {
            sum += field[x][x];
        }
        if (sum == size || sum == -size) {
            state = sum > 0 ? GameState.X_WINS : GameState.O_WINS;
            return true;
        }
        
        // check minor diagonal
        sum = 0;
        for (int x = 0; x < size; x++) {
           sum += field[x][size - x - 1];
        }
        if (sum == size || sum == -size) {
            state = sum > 0 ? GameState.X_WINS : GameState.O_WINS;
            return true;
        }

        // check the available empty cells
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (field[x][y] == 0) {
                    return false;
                }
            }
        }

        // if we here - we have only one
        // possible game state
        state = GameState.DRAW;
        return true;
    }

    /**
     * Getters and Setters
     */
    public GameState getState() {
        return state;
    }

    public int getSize() {
        return size;
    }

    public Integer[][] getField() {
        return field;
    }
}
