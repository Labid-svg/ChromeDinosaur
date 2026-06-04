import java.awt.*;

public class Cactus extends Block {

    private int velocityCX;

    Cactus(int x, int y, int width, int height, Image img, int velocityCX) {
        super(x, y, width, height, img);
        this.velocityCX = velocityCX;
    }

    @Override
    public void update() {
        setX(getX() + velocityCX);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(getImg(), getX(), getY(), getWidth(), getHeight(), null);
    }
}