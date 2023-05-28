package firstProject;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements  Runnable{
    int FPS  = 60;
    final int originalTileSize = 16;//size of the character
    final int scale = 3;
    final int tileSize = originalTileSize*scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize*maxScreenCol;//768
    final int screenHeight= tileSize*maxScreenRow;//576

    Thread gameThread;
    KeyHandler kh = new KeyHandler();

    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 3;
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.GREEN);
        this.setDoubleBuffered(true);
        this.addKeyListener(kh);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime()+drawInterval;
        //Game Loop
        while(gameThread!=null){
            long currentTime = System.nanoTime();
            //Update
            update();
            //Draw
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;
                if(remainingTime < 0){
                    remainingTime = 0;
                }
                Thread.sleep((long)remainingTime);
                nextDrawTime +=drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update(){

        if(kh.upPressed == true){
            playerY-=playerSpeed;
        } else if (kh.downPressed==true) {
            playerY+=playerSpeed;
        } else if (kh.leftPressed==true) {
            playerX-=playerSpeed;
        } else if (kh.rightPressed==true) {
            playerX+=playerSpeed;
        }

    }
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(Color.white);
        graphics2D.fillRect(playerX,playerY,tileSize,tileSize);
        graphics2D.dispose();
    }
}
