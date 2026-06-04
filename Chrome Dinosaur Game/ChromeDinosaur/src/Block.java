import java.awt.*;

public abstract class Block {
    private int x;
    private int y;
    private int width;
    private int height;
    private Image img;

    Block(int x, int y, int width, int height, Image img) {
        this.x      = x;
        this.y      = y;
        this.width  = width;
        this.height = height;
        this.img    = img;
    }

    public int getX() {
        return x;
    }
    public int getY(){
        return y;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public Image getImg(){
        return img;
    }

    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }

    public void setWidth(int width){
        this.width = width;
    }

    public void setHeight(int height){
        this.height = height;
    }

    public void setImg(Image img){
        this.img = img;
    }

    public abstract void update();

    public abstract void draw(Graphics g);
}
