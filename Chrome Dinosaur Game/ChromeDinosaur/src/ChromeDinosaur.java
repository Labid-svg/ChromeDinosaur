import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ChromeDinosaur extends JPanel implements ActionListener, KeyListener{
    int boardWidth = 850;
    int boardHeight = 250;

    // Load Image
    Image dinosaurImg;
    Image dinosaurDeadImg;
    Image dinosaurJumpImg;
    Image cactus1Img;
    Image cactus2Img;
    Image cactus3Img;
    Image gameoverImg;

    class Block{
        int x;
        int y;
        int width;
        int height;
        Image img;

        Block(int x, int y, int width, int height, Image img){
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.img = img;
        }
    }

    int dinosaurWidth = 80;
    int dinosaurHeight = 90;
    int dinosaurX = 50;
    int dinosaurY = boardHeight - dinosaurHeight; // initial position of dinosaur

    Block dinosaur;
    Block gameover;

    int cactus1Width = 30;
    int cactus2Width = 60;
    int cactus3Width = 100;

    int cactusHeight = 70;
    int cactusX = 700;
    int cactusY = boardHeight - cactusHeight;

    ArrayList<Block>cactusArray;

    int gameoverX = 150;
    int gameoverY = 100;
    int gameoverWidth = 600;
    int gameoverHeight = 100;

    int velocityCX = -10; // cactus velocity
    int velocityY = 0; // jumping speed
    int gravity = 1;

    boolean gameOver = false;
    int score = 0;

    Timer gameLoop;
    Timer placeCactusTimer;

    public ChromeDinosaur(){
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setBackground(Color.lightGray);
        setFocusable(true);
        addKeyListener(this);

        dinosaurImg = new ImageIcon(getClass().getResource("./img/dino-run.gif")).getImage();
        dinosaurDeadImg = new ImageIcon(getClass().getResource("./img/dino-dead.png")).getImage();
        dinosaurJumpImg = new ImageIcon(getClass().getResource("./img/dino-jump.png")).getImage();
        cactus1Img = new ImageIcon(getClass().getResource("./img/cactus1.png")).getImage();
        cactus2Img = new ImageIcon(getClass().getResource("./img/cactus2.png")).getImage();
        cactus3Img = new ImageIcon(getClass().getResource("./img/cactus3.png")).getImage();
        gameoverImg = new ImageIcon(getClass().getResource("./img/game-over.png")).getImage();

        dinosaur = new Block(dinosaurX, dinosaurY, dinosaurWidth, dinosaurHeight, dinosaurImg);

        gameover = new Block(gameoverX, gameoverY, gameoverWidth, gameoverHeight, gameoverImg);

        cactusArray = new ArrayList<>();

        gameLoop = new Timer(1000/60, this);
        gameLoop.start();

        placeCactusTimer = new Timer(1500, new ActionListener() {
        
            @Override
        public void actionPerformed(ActionEvent e){
            placeCactus();
        }
        });
        placeCactusTimer.start();
    }

    void placeCactus(){
        if(gameOver){
            return;
        }
        double placeCactusChance = Math.random();
        if(placeCactusChance > 0.90){
            Block cactus = new Block(cactusX, cactusY, cactus3Width, cactusHeight, cactus3Img);
            cactusArray.add(cactus);
        }
        else if(placeCactusChance > 0.70){
            Block cactus = new Block(cactusX, cactusY, cactus2Width, cactusHeight, cactus2Img);
            cactusArray.add(cactus);
        }
        else if(placeCactusChance > 0.50){
            Block cactus = new Block(cactusX, cactusY,cactus1Width,cactusHeight, cactus1Img);
            cactusArray.add(cactus);
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        g.drawImage(dinosaur.img, dinosaur.x, dinosaur.y, dinosaur.width, dinosaur.height, null);
    
        for(int i = 0; i<cactusArray.size(); i++){
            Block cactus = cactusArray.get(i);
            g.drawImage(cactus.img, cactus.x, cactus.y ,cactus.width, cactus.height,null);
        }

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 36));

        if(gameOver){
            g.drawString("Game Over: " + String.valueOf(score), 10, 30);
            g.drawImage(gameover.img, gameoverX, gameoverY, gameoverWidth, gameoverHeight, null);
        }
        else{
            g.drawString(String.valueOf(score), 10, 30);
        }
    }


    public void move(){
        velocityY += gravity;
        dinosaur.y += velocityY;

        if(dinosaur.y > dinosaurY){
            dinosaur.y = dinosaurY;
            velocityY = 0;
            dinosaur.img = dinosaurImg;
        }

        for(int i = 0; i < cactusArray.size(); i++){
            Block cactus = cactusArray.get(i);
            cactus.x += velocityCX;

            if(collision(dinosaur, cactus)){
                gameOver = true;
                dinosaur.img = dinosaurDeadImg;
                gameover.img = gameoverImg;
            }
        }
        score++;
    }

    boolean collision(Block a, Block b){
        return a.x < b.x + b.width && //a's top left corner doesn't reach b's top right corner
        a.x + a.width > b.x && //a's top right corner passes b's top left corner
        a.y < b.y + b.height && //a's top left corner doesn't reach b's bottom left corner
        a.y + a.height > b.y; //a's bottom left corner passes b's top left corner
    }

    // For Block a (Dinosaur):
        // Left   = a.x , Right  = a.x + a.width, Top = a.y, Bottom = a.y + a.height;
    // For Block b (cactus):
        // Left   = b.x , Right  = b.x + b.width, Top = b.y, Bottom = b.y + b.height;

    // mainly checking horizontal overlapping and vertical overlapping

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        if(gameOver){
            placeCactusTimer.stop();
            gameLoop.stop();
        }
    }


    @Override
    public void keyPressed(KeyEvent e) { 
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            if(dinosaur.y == dinosaurY){
            velocityY = -18;
            dinosaur.img = dinosaurJumpImg;
            }
        }

        if(gameOver){
            dinosaur.y = dinosaurY;
            dinosaur.img = dinosaurImg;
            velocityY = 0;
            cactusArray.clear();
            score = 0;
            gameOver = false;
            gameLoop.start();
            placeCactusTimer.start();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) { }
}
