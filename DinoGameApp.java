import javax.swing.*;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Class            DinoGameApp
 * File             DinoGameApp.java
 * Description      This class creates the game window and adds the game logic to this window. It is the class where
 *                  the game is run, the game title, the window size and the position of the window are defined.
 * @author          Batuhan Ozel
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class DinoGameApp {

    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       main()
     * Description  Set the size of the game window, set the title, center the window.
     * @author      <i>Batuhan Ozel</i>
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static void main(String[] args) {
        // Width of the game board
        int boardWidth = 750;
        // Height of the game board
        int boardHeight = 350;
        // Create the JFrame with the title
        JFrame gameWindow = new JFrame("Chrome Dino Dunking Survival Game");
        // Set the size of the game window
        gameWindow.setSize(boardWidth, boardHeight);
        // Center the game window to the screen
        gameWindow.setLocationRelativeTo(null);
        // Make the window not resizable
        gameWindow.setResizable(false);
        // Set closing operation
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a ChromeDinoGame object
        ChromeDinoGame dinoGame = new ChromeDinoGame(boardWidth, boardHeight);
        // Add dinoGame object to the game window
        gameWindow.add(dinoGame);
        // It fixes the dimension problems, dimensions will start after the title bar
        gameWindow.pack();
        // Requests that this Component gets the input focus.
        dinoGame.requestFocus();

        // Set game window visible
        gameWindow.setVisible(true);
    }
}
