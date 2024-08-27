import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

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

    Image cactus1;
    Image cactus2;
    Image cactus3;

    Image player1;
    Image player2;



    // Dinosaur
    int dinosaurWidth;
    int dinosaurHeight;
    int dinosaurStartX;
    int dinosaurStartY;
    Dinosaur dinosaur;


    // Obstacles
    ArrayList<MovingElements> obstaclesArray;
    Timer placeObstaclesTimer;

    // Cactus
    int cactusHeight;
    int cactus1Width;
    int cactus2Width;
    int cactus3Width;
    int cactusStartX;
    int cactusStartY;

    // Basketball Player
    int playerHeight;
    int playerWidth;
    int playerStartX;
    int playerStartY;






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
        // Start gameLoop for the game
        gameLoop.start();


        // ------------------------------------ Game Images -----------------------------------------------------------
        // --------------------- Dinosaur ---------------------
        dinosaurImg = new ImageIcon(getClass().getResource("img/dino.gif")).getImage();
        dinosaurDeadImg = new ImageIcon(getClass().getResource("img/dino_dead.png")).getImage();
        dinosaurJumpImg = new ImageIcon(getClass().getResource("img/dino_jump.png")).getImage();
        dinosaurDuckImg = new ImageIcon(getClass().getResource("img/dino_duck.gif")).getImage();

        // --------------------- Cactus ---------------------
        cactus1 = new ImageIcon(getClass().getResource("img/cactus_1.png")).getImage();
        cactus2 = new ImageIcon(getClass().getResource("img/cactus_2.png")).getImage();
        cactus3 = new ImageIcon(getClass().getResource("img/cactus_3.png")).getImage();

        // --------------------- Basketball Player  ---------------------
        player1 = new ImageIcon(getClass().getResource("img/player_1.png")).getImage();
        player2 = new ImageIcon(getClass().getResource("img/player_2.png")).getImage();
        // -----------------------------------------------------------------------------------------------------------


        // ----------------------------------------- Game Objects ----------------------------------------------------

        // --------------------- Dinosaur ---------------------
        dinosaurWidth = 88;
        dinosaurHeight = 94;
        dinosaurStartX = 50;
        dinosaurStartY = boardHeight - dinosaurHeight;
        this.dinosaur = new Dinosaur(dinosaurStartX, dinosaurStartY, dinosaurWidth, dinosaurHeight, dinosaurImg);

        // --------------------- Obstacles General ---------------------
        obstaclesArray = new ArrayList<MovingElements>();
        placeObstaclesTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placeObstacles();
            }
        });
        placeObstaclesTimer.start();

        // --------------------- Cactus ---------------------
        cactusHeight = 70;
        cactus1Width = 34;
        cactus2Width = 69;
        cactus3Width = 102;
        cactusStartX = 700;
        cactusStartY = boardHeight - cactusHeight;

        // --------------------- Basketball Player  ---------------------
        playerHeight = 100;
        playerWidth = 40;
        playerStartX = 700;
        playerStartY = boardHeight - playerHeight;
        // -----------------------------------------------------------------------------------------------------------


    }


    private void placeObstacles(){

        double placeObstacleChance = Math.random();     // 0 - 0.999..

        // 10% change to get randomly player
        if(placeObstacleChance > .90){
            if(placeObstacleChance > .95){
                MovingElements player = new MovingElements(playerStartX, playerStartY, playerWidth, playerHeight, player1);
                obstaclesArray.add(player);
            }else{
                MovingElements player = new MovingElements(playerStartX, playerStartY, playerWidth, playerHeight, player2);
                obstaclesArray.add(player);
            }
        }

        // 10% change to get randomly cactus3
        else if(placeObstacleChance > .80){
            MovingElements cactus = new MovingElements(cactusStartX, cactusStartY, cactus3Width, cactusHeight, cactus3);
            obstaclesArray.add(cactus);
        }
        // 20% change to get randomly cactus2
        else if(placeObstacleChance > .60){
            MovingElements cactus = new MovingElements(cactusStartX, cactusStartY, cactus2Width, cactusHeight, cactus2);
            obstaclesArray.add(cactus);
        }
        // 30% change to get randomly cactus1
        else if(placeObstacleChance > .30) {
            MovingElements cactus = new MovingElements(cactusStartX, cactusStartY, cactus1Width, cactusHeight, cactus1);
            obstaclesArray.add(cactus);
        }

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
        for(int i = 0; i < obstaclesArray.size(); i++){
            obstaclesArray.get(i).draw(g);
        }
    }


    /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Method       actionPerformed(ActionEvent e)
     * Description  With the 60 FPS game flow, game is calling this method to control game.
     * @author      Batuhan Ozel
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        if(gameOver){
            placeObstaclesTimer.stop();
            gameLoop.stop();
        }

    }


    private void move(){
        dinosaur.move();

        for(int i = 0; i < obstaclesArray.size(); i++){
            MovingElements obstacle = obstaclesArray.get(i);
            obstacle.move();
            if(collision(dinosaur, obstacle)){
                dinosaur.img = dinosaurDeadImg;
                gameOver = true;
            }
        }

    }

    private boolean collision(GameElements dinosaur, GameElements obstacle){
        /* If we think of the dinosaur and the obstacles as being inside an imaginary rectangle,
        whether these rectangles touch each other or not gives information about whether a collision occurred. */

        return dinosaur.xPosition < obstacle.xPosition + obstacle.width &&
                dinosaur.xPosition + dinosaurWidth > obstacle.xPosition &&
                dinosaur.yPosition < obstacle.yPosition + obstacle.height &&
                dinosaur.yPosition + dinosaurHeight > obstacle.yPosition;
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
