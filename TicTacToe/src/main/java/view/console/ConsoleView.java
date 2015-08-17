package view.console;

import model.Game;
import model.Move;
import model.exception.WrongMoveException;
import view.GameView;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * Console view
 *
 * @author Ivan Konovalov
 */
public class ConsoleView
                implements GameView{

    private Game game;

    public ConsoleView(Game game) {
        this.game = game;

        System.out.println("_______________________________________________________________________________________");
        System.out.println(" ________  __            ________                      ________                     \n" +
                "|        \\|  \\          |        \\                    |        \\                    \n" +
                " \\$$$$$$$$ \\$$  _______  \\$$$$$$$$  ______    _______  \\$$$$$$$$  ______    ______  \n" +
                "   | $$   |  \\ /       \\   | $$    |      \\  /       \\   | $$    /      \\  /      \\ \n" +
                "   | $$   | $$|  $$$$$$$   | $$     \\$$$$$$\\|  $$$$$$$   | $$   |  $$$$$$\\|  $$$$$$\\\n" +
                "   | $$   | $$| $$         | $$    /      $$| $$         | $$   | $$  | $$| $$    $$\n" +
                "   | $$   | $$| $$_____    | $$   |  $$$$$$$| $$_____    | $$   | $$__/ $$| $$$$$$$$\n" +
                "   | $$   | $$ \\$$     \\   | $$    \\$$    $$ \\$$     \\   | $$    \\$$    $$ \\$$     \\\n" +
                "    \\$$    \\$$  \\$$$$$$$    \\$$     \\$$$$$$$  \\$$$$$$$    \\$$     \\$$$$$$   \\$$$$$$$\n");
        System.out.println("___________________________________________________________________________The Game____");
    }

    @Override
    public void render(){
        // print field
        System.out.println("Field:");
        for (int y = 0; y < game.getSize(); y++) {
            for (int x = 0; x < game.getSize(); x++) {
                System.out.print(
                        game.getField()[x][y] == 0 ? "_"
                                : game.getField()[x][y] == 1 ? "X" : "O"
                );
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println("_______");
    }

    @Override
    public Move inputMove() throws WrongMoveException {
        int x, y;
        Scanner scanner = new Scanner(System.in);

        System.out.print(game.getState().toString() + "(x, y): ");

        try {
            x = scanner.nextInt();
            y = scanner.nextInt();
        } catch (InputMismatchException e){
            x = -1;
            y = -1;
        }

        return new Move(game, x, y);
    }

}
