import java.awt.*;

public class Bird extends Block {

    private int velocityBX;
    private Image deadImg;

    Bird(int x, int y, int width, int height, Image img, int velocityBX, Image deadImg) {
        super(x, y, width, height, img);
        this.velocityBX = velocityBX;
        this.deadImg = deadImg;
    }

    public Image getDeadImg() {
        return deadImg;
    }

    @Override
    public void update() {
        setX(getX() + velocityBX);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(getImg(), getX(), getY(), getWidth(), getHeight(), null);
    }
}