import java.awt.Graphics;
import java.awt.Image;

public class Dinosaur extends Block {

    private int velocityY = 0;
    private int gravity = 1;
    private int groundY;

    Image dinosaurImg;
    Image dinosaurDeadImg;
    Image dinosaurJumpImg;

    Dinosaur(int x, int y, int width, int height, Image runImg, Image deadImage, Image jumpImg){
        super(x, y, width, height, runImg);
        this.dinosaurImg = runImg;
        this.dinosaurDeadImg = deadImage;
        this.dinosaurJumpImg = jumpImg;
        this.groundY = y;
    }

    @Override
    public void update() {
        velocityY += gravity;
        setY(getY() + velocityY); 

        if (getY() > groundY) {
            setY(groundY);          
            velocityY = 0;
            setImg(dinosaurImg);  
        }
    }

    @Override
    public void draw(Graphics g) {
         g.drawImage(getImg(), getX(), getY(), getWidth(), getHeight(), null);
    }

    public void jump() {
        if (getY() == groundY) {
            velocityY = -18;
            setImg(dinosaurJumpImg);
        }
    }

    public void die() {
        setImg(dinosaurDeadImg);
    }

    public void back() {
        setY(groundY);
        setImg(dinosaurImg);
        velocityY = 0;
    }
}