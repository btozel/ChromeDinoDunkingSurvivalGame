import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Class            ChromeDinoGame
 * File             ChromeDinoGame.java
 * Description      ......
 * @author          Batuhan Ozel
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class ChromeDinoGame extends JPanel implements ActionListener, KeyListener {
    // Width and Height of the game window
    int boardWidth, boardHeight;

    // -------- Game Logic Elements --------

    // Variable to track whether the game is over
    boolean gameOver;
    // Variable that will keep score
    int score;
    // The flow rate of the game
    Timer gameLoop;


    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Constructor  ChromeDinoGame()
     * Description  ......
     * @author      Batuhan Ozel
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public ChromeDinoGame(int width, int height){
        this.boardWidth = width;
        this.boardHeight = height;

        // Set the size of the JPanel
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        // Set the background color
        setBackground(Color.LIGHT_GRAY);

        // -------- Game Logic --------

        // JPanel will be listening for the key events
        setFocusable(true);
        addKeyListener(this);

        // Initializing game logic elements
        gameOver = false;
        score = 0;
        gameLoop = new Timer(1000/60, this);
        gameLoop.start();

    }


    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       actionPerformed(ActionEvent e)
     * Description  With the 60 FPS game flow, game is calling this method to control game.
     * @author      Batuhan Ozel
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public void actionPerformed(ActionEvent e) {
        //System.out.println("LOOP TEST!");
    }


    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       keyPressed(KeyEvent e)
     * Description  This methods controls the key pressed events.
     * @author      Batuhan Ozel
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            System.out.println("JUMP TEST!");
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {}


    @Override
    public void keyReleased(KeyEvent e) {}

}
