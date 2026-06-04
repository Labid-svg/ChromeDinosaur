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
    Image resetImg;
    Image trackImg;
    Image birdImg;
    Image birdDeadImg;

    // class Block{
    //     int x;
    //     int y;
    //     int width;
    //     int height;
    //     Image img;

    //     Block(int x, int y, int width, int height, Image img){
    //         this.x = x;
    //         this.y = y;
    //         this.width = width;
    //         this.height = height;
    //         this.img = img;
    //     }
    // }

    Dinosaur dinosaur;

    int dinosaurWidth = 80;
    int dinosaurHeight = 90;
    int dinosaurX = 50;
    int dinosaurY = boardHeight - dinosaurHeight; // initial position of dinosaur


    //Cactus
    int cactus1Width = 30;
    int cactus2Width = 60;
    int cactus3Width = 100;

    int cactusHeight = 70;
    int cactusX = 800;
    int cactusY = boardHeight - cactusHeight;

    ArrayList<Block> cactusArray;

    // Bird
    int birdWidth = 50;
    int birdHeight = 60;
    int birdX = 1600;
    int birdY = 70;

    ArrayList<Block> birdArray;

    // GameOver
    int gameoverX = 150;
    int gameoverY = 60;
    int gameoverWidth = 600;
    int gameoverHeight = 100;

    Block gameover;

    // Reset
    int resetX = 400;
    int resetY = 140;
    int resetWidth = 80;
    int resetHeight = 80;

    Block reset;

    // Track
    int trackWidth = 850;
    int trackHeight = 15;
    int trackX = 0;
    int trackY = boardHeight - trackHeight;

    Block track;

    int velocityCX = -10; // cactus velocity
    int velocityBX = -15; // bird velocity
    int velocityY = 0;    // jumping speed
    int gravity = 1;

    boolean gameOver = false;
    int score = 0;

    Timer gameLoop;
    Timer placeCactusTimer;
    Timer placeBirdTimer;

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
        resetImg = new ImageIcon(getClass().getResource("./img/reset.png")).getImage();
        trackImg = new ImageIcon(getClass().getResource("./img/track.png")).getImage();
        birdImg  = new ImageIcon(getClass().getResource("./img/bird.gif")).getImage();
        birdDeadImg = new ImageIcon(getClass().getResource("./img/bird1.png")).getImage();

        dinosaur = new Dinosaur(dinosaurX, dinosaurY,dinosaurWidth, dinosaurHeight,dinosaurImg, dinosaurDeadImg, dinosaurJumpImg);

        gameover = new Block(gameoverX, gameoverY, gameoverWidth, gameoverHeight, gameoverImg){
            @Override 
            public void update() {}

            @Override 
            public void draw(Graphics g) {}
        };

        reset = new Block(resetX, resetY, resetWidth, resetHeight, resetImg){

            @Override
            public void update() {}

            @Override 
            public void draw(Graphics g) {}
        };

        track = new Block(trackX, trackY, trackWidth, trackHeight, trackImg){

            @Override
            public void update() {}

            @Override 
            public void draw(Graphics g) {}
        };

        cactusArray = new ArrayList<>();
        birdArray   = new ArrayList<>();

        gameLoop = new Timer(1000/60, this);
        gameLoop.start();

        placeCactusTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                placeCactus();
            }
        });
        placeCactusTimer.start();

        placeBirdTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                placeBird();
            }
        });
        placeBirdTimer.start();
    }

    void placeCactus(){
        if(gameOver || !birdArray.isEmpty()){
            return;
        }
        double placeCactusChance = Math.random();
        if(placeCactusChance > 0.90){
            Cactus cactus = new Cactus(cactusX, cactusY, cactus3Width, cactusHeight, cactus3Img, velocityCX);
            cactusArray.add(cactus);
        }
        else if(placeCactusChance > 0.70){
            Cactus cactus = new Cactus(cactusX, cactusY, cactus2Width, cactusHeight, cactus2Img, velocityCX);
            cactusArray.add(cactus);
        }
        else if(placeCactusChance > 0.50){
            Cactus cactus = new Cactus(cactusX, cactusY, cactus1Width, cactusHeight, cactus1Img, velocityCX);
            cactusArray.add(cactus);
        }
    }

    void placeBird(){
        if(gameOver || !cactusArray.isEmpty()){
            return;
        }

        double placeBirdChance = Math.random();
        if(placeBirdChance > 0.00 && placeBirdChance < 0.20){
            Bird bird = new Bird(birdX, birdY, birdWidth, birdHeight, birdImg, velocityBX, birdDeadImg);
            birdArray.add(bird);
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        g.drawImage(track.getImg(), track.getX(), track.getY(), track.getWidth(), track.getHeight(), null);
        // g.drawImage(dinosaur.img, dinosaur.x, dinosaur.y, dinosaur.width, dinosaur.height, null);
        dinosaur.draw(g);

        for(int i = 0; i < cactusArray.size(); i++){
            Block cactus = cactusArray.get(i);
            cactus.draw(g);
            // g.drawImage(cactus.img, cactus.x, cactus.y ,cactus.width, cactus.height,null);
        }

        for(int i = 0; i < birdArray.size(); i++){
            Block bird = birdArray.get(i);
            bird.draw(g);
            // g.drawImage(bird.img, bird.x, bird.y, bird.width, bird.height, null);
        }

        g.setColor(Color.RED);
        g.setFont(new Font("Roboto", Font.PLAIN, 36));

        if(gameOver){
            g.drawString("Game Over: " + String.valueOf(score), 10, 30);
            g.drawImage(gameover.getImg(), gameover.getX(), gameover.getY(), gameover.getWidth(), gameover.getHeight(), null);
            g.drawImage(reset.getImg(), reset.getX(), reset.getY(), reset.getWidth(), reset.getHeight(), null);
        }
        else{
            g.drawString(String.valueOf(score), 10, 30);
        }
    }


    public void move(){
        dinosaur.update();
        // velocityY += gravity;
        // dinosaur.y += velocityY;

        // if(dinosaur.y > dinosaurY){
        //     dinosaur.y = dinosaurY;
        //     velocityY = 0;
        //     dinosaur.img = dinosaurImg;
        // }

        for(int i = 0; i < cactusArray.size(); i++){
            Block cactus = cactusArray.get(i);
            // cactus.x += velocityCX;
            cactus.update();

            if(cactus.getX() + cactus.getWidth() < 0){
                cactusArray.remove(i);
                i--;
                continue;
            }

            if(collision(dinosaur, cactus)){
                gameOver = true;
                dinosaur.die();
                // dinosaur.img = dinosaurDeadImg;
                gameover.setImg(gameoverImg);
                reset.setImg(resetImg);
            }
        }

        for(int i = 0; i < birdArray.size(); i++){
            Block bird = birdArray.get(i);
            // bird.x += velocityBX;
            bird.update();

            if(bird.getX() + bird.getWidth() < 0){
                birdArray.remove(i);
                i--;
                continue;
            }

            if(collision(dinosaur, bird)){
                gameOver = true;
                dinosaur.die();
                // dinosaur.img = dinosaurDeadImg;
                bird.setImg(birdDeadImg);
                gameover.setImg(gameoverImg);
                reset.setImg(resetImg);
            }
        }
        score++;
    }

    boolean collision(Block a, Block b){
        return a.getX() < b.getX() + b.getWidth()   && // a's top left corner doesn't reach b's top right corner
               a.getX() + a.getWidth()  > b.getX()  && // a's top right corner passes b's top left corner
               a.getY() < b.getY() + b.getHeight()  && // a's top left corner doesn't reach b's bottom left corner
               a.getY() + a.getHeight() > b.getY();

        // For Block a (Dinosaur):
        // Left   = a.x , Right  = a.x + a.width, Top = a.y, Bottom = a.y + a.height;
    // For Block b (cactus):
        // Left   = b.x , Right  = b.x + b.width, Top = b.y, Bottom = b.y + b.height;

    // mainly checking horizontal overlapping and vertical overlapping
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        if(gameOver){
            placeCactusTimer.stop();
            placeBirdTimer.stop();
            gameLoop.stop();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            // if(dinosaur.y == dinosaurY){
            // velocityY = -18;
            // dinosaur.img = dinosaurJumpImg;
            // }
            dinosaur.jump();
        }

        if(gameOver){
            // dinosaur.y = dinosaurY;
            // dinosaur.img = dinosaurImg;
            // velocityY = 0;
            dinosaur.back();
            cactusArray.clear();
            birdArray.clear();   // Fixed: birds must also be cleared on reset
            score = 0;
            gameOver = false;
            gameLoop.start();
            placeCactusTimer.start();
            placeBirdTimer.start();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) { }
}
