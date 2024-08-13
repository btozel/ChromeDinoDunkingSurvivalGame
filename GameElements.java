import java.awt.*;

abstract class GameElements {
    int xPosition;
    int yPosition;
    int width;
    int height;
    Image img;


    GameElements(int x, int y, int width, int height, Image img){
        this.xPosition = x;
        this.yPosition = y;
        this.width = width;
        this.height = height;
        this.img = img;
    }


    public void draw(Graphics g){
        g.drawImage(img, xPosition, yPosition, width, height, null);
    }

    public abstract void move();



    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Image getImg() {
        return img;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setImg(Image img) {
        this.img = img;
    }
}
