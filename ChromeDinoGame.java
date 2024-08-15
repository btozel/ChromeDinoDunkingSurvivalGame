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


    // Images of the game
    Image dinosaurImg;
    Image dinosaurDeadImg;
    Image dinosaurJumpImg;
    Image dinosaurDuckImg;


    // Dinosaur
    int dinosaurWidth;
    int dinosaurHeight;
    int dinosaurStartX;
    int dinosaurStartY;
    Dinosaur dinosaur;


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
        gameLoop.setInitialDelay(400);
        gameLoop.start();


        // Initializing game images
        dinosaurImg = new ImageIcon(getClass().getResource("img/dino.gif")).getImage();
        dinosaurDeadImg = new ImageIcon(getClass().getResource("img/dino_dead.png")).getImage();
        dinosaurJumpImg = new ImageIcon(getClass().getResource("img/dino_jump.png")).getImage();
        dinosaurDuckImg = new ImageIcon(getClass().getResource("img/dino_duck.gif")).getImage();

        // Initializing Dinosaur object
        dinosaurWidth = 88;
        dinosaurHeight = 94;
        dinosaurStartX = 50;
        dinosaurStartY = boardHeight - dinosaurHeight;
        this.dinosaur = new Dinosaur(dinosaurStartX, dinosaurStartY, dinosaurWidth, dinosaurHeight, dinosaurImg);
    }


    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       paintComponent(Graphics g)
     * Description  Calls draw() method.
     * @author      Batuhan Ozel
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }


    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       draw(ActionEvent e)
     * Description  Manages and draws visual elements of the game.
     * @author      Batuhan Ozel
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void draw(Graphics g){
        dinosaur.draw(g);
    }


    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       actionPerformed(ActionEvent e)
     * Description  With the 60 FPS game flow, game is calling this method to control game.
     * @author      Batuhan Ozel
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public void actionPerformed(ActionEvent e) {
        dinosaur.move();
        repaint();
    }


    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       keyPressed(KeyEvent e)
     * Description  This methods controls the key pressed events.
     * @author      Batuhan Ozel
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public void keyPressed(KeyEvent e) {
        // If space button is pressed --> jump
        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            dinosaur.jump(dinosaurJumpImg, boardHeight);
        }

        // If down button is pressed --> duck
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            dinosaur.duck(dinosaurDuckImg, boardHeight);
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {}


    @Override
    public void keyReleased(KeyEvent e) {
        // If down button is released --> keep running
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            dinosaur.run(dinosaurImg);
        }
    }

}
