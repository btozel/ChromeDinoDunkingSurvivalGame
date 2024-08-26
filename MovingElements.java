import java.awt.*;

public class MovingElements extends GameElements{
    private int movingElementVelocity;


    MovingElements(int x, int y, int width, int height, Image img) {
        super(x, y, width, height, img);
        movingElementVelocity = 10;
    }


    public void move() {
        xPosition -= movingElementVelocity;
    }
}
