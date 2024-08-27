import java.awt.*;

public class Dinosaur extends GameElements{

    private final int gravity = 1;
    public int dinoJumpingVelocity = 17;

    private final int startYPosition;

    private final Image startImg;

    private boolean running;

    private boolean jumping;

    private boolean ducking;


    Dinosaur(int x, int y, int width, int height, Image img) {
        super(x, y, width, height, img);
        startYPosition = y;
        startImg = img;
        running = true;
        jumping = false;
        ducking = false;
    }


    public void move(){
        // Only if dino is jumping, manage the gravity and jumping velocity values
        if(jumping){
            dinoJumpingVelocity -= gravity;
            yPosition -= dinoJumpingVelocity;
            if(yPosition == startYPosition){
                jumping = false;
                run(startImg);
            }
        }
    }


    public void run(Image runImg){
        // If dino is already jumping at that moment, can't run
        if(!jumping){
            ducking = false;
            running = true;
            setHeight(runImg.getHeight(null));
            setYPosition(startYPosition);
            setImg(runImg);
        }
    }


    public void jump(Image jumpImg, int boardHeight){
        // If dino is already jumping at that moment, can't jump.
        if(!jumping){
            jumping = true;
            running = false;
            ducking = false;
            setImg(jumpImg);
            setHeight(jumpImg.getHeight(null));
            setYPosition(boardHeight - height);
            // Every new jump, reset jumping velocity value to 17.
            dinoJumpingVelocity = 17;
        }
    }


    public void duck(Image duckImg, int boardHeight){
        // Dino shouldn't be able to duck if it is jumping
        if(!jumping){
            setImg(duckImg);
            setHeight(duckImg.getHeight(null));
            setYPosition(boardHeight - height);
        }
    }
}
