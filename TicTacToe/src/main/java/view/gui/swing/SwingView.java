package view.gui.swing;

import model.Game;
import model.GameState;
import model.Move;

import javax.swing.*;
import java.awt.*;

/**
 * Gui
 *
 * @author Ivan Konovalov
 */
public class SwingView extends JFrame {

    int counterX = 0;
    int counterY = 0;

    int size;
    int side;

    Game game;

    JPanel gameField;
    JLabel statusStr;

    /**
     * Constructor
     *
     * @param game
     */
    public SwingView(Game game) {
        this.game = game;

        // get frame size
        this.size = game.getSize();
        this.side = size * 100;

        UIManager.getDefaults().put("Button.disabledText", Color.BLACK);

        // set size (the game field size + the status line size + the buttons panel)
        this.setSize(side, side + 20 + 30);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("TicTacToe");

        // Create the main panel which will
        // contain all other panels
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        this.add(mainPanel);

        // The game status string
        JPanel status = new JPanel();
        status.setLayout(new BoxLayout(status, BoxLayout.X_AXIS));
        status.setMaximumSize(new Dimension(side, 20));
        statusStr = new JLabel(game.getState().toString());

        status.add(statusStr);
        mainPanel.add(status);

        // The game field panel
        gameField = new JPanel();
        gameField.setSize(side, side);
        gameField.setLayout(new GridLayout(size, size));

        // Fill the game field with buttons
        for (int i = 0; i < size * size; i++) {
            gameField.add(createButton());
        }
        mainPanel.add(gameField);

        // The buttons panel
        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.LINE_AXIS));
        buttons.setMaximumSize(new Dimension(side, 50));

        JButton restartBtn = new JButton();
        restartBtn.setMaximumSize(new Dimension(side, 50));
        restartBtn.setText("Restart");

        restartBtn.addActionListener(e -> {
            this.dispose();
            this.game = new Game();
            new SwingView(this.game);
        });

        buttons.add(restartBtn);
        mainPanel.add(buttons);

        this.setResizable(false);
        // Game window appears on center
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     *
     * Creating buttons with coordinates
     * and action listeners
     *
     */
    private MyButton createButton() {
        // The new buttons counters -
        // will be translates to coordinates
        if (counterX >= size) {
            counterX = 0;
            counterY++;
        }

        MyButton button = new MyButton();

        // set buttons coordinates
        button.x = counterX++;
        button.y = counterY;

        // The buttons properties adjustment
        button.setPreferredSize(new Dimension(50, 50));
        button.setFont(new Font("Dialog", Font.PLAIN, 72));

        // Action listener
        button.addActionListener(e -> {
            game.move(new Move(button.x, button.y));
            game.isGameOver();
            button.setText(game.getField()[button.x][button.y] > 0 ? "X" : "O");
            button.setEnabled(false);
            statusStr.setText(game.getState().toString());

            // If the game is over
            // disable all unselected buttons
            if (game.getState().equals(GameState.X_WINS) ||
                    game.getState().equals(GameState.O_WINS)){

                for (Component b : gameField.getComponents()) {
                    b.setEnabled(false);
                }
            }
        });


        //System.out.println("button " + button.x + " " + button.y);

        return button;
    }
}
