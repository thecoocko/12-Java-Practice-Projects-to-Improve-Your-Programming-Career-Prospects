package firstProject;

import firstProject.entity.Player;
import firstProject.tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements  Runnable{
    int FPS  = 60;
    final int originalTileSize = 16;//size of the character
    final int scale = 3;
    public int tileSize = originalTileSize*scale;
    public int maxScreenCol = 16;
    public int maxScreenRow = 12;
    public int screenWidth = tileSize*maxScreenCol;//768
    public int screenHeight= tileSize*maxScreenRow;//576
    TileManager tm = new TileManager(this);
    Thread gameThread;
    KeyHandler kh = new KeyHandler();
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWith = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.GREEN);
        this.setDoubleBuffered(true);
        this.addKeyListener(kh);
        this.setFocusable(true);
    }
    public Player player = new Player(this,kh);

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
//    @Override
//    public void run() {
//        double drawInterval = 1000000000/FPS;
//        double nextDrawTime = System.nanoTime()+drawInterval;
//        //Game Loop
//        while(gameThread!=null){
//            long currentTime = System.nanoTime();
//            //Update
//            update();
//            //Draw
//            repaint();
//
//            try {
//                double remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime = remainingTime/1000000;
//                if(remainingTime < 0){
//                    remainingTime = 0;
//                }
//                Thread.sleep((long)remainingTime);
//                nextDrawTime +=drawInterval;
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
    @Override
    public void run(){
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        while(gameThread!=null){
            currentTime = System.nanoTime();
            delta+=(currentTime-lastTime)/drawInterval;
            timer+=(currentTime-lastTime);
            lastTime = currentTime;
            if(delta>=1){
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if(timer>=1000000000) {
                System.out.println("FPS: "+drawCount);
                drawCount=0;
                timer=0;
            }

        }

    }

    public void update(){
        player.update();
    }
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        tm.draw(graphics2D);
        player.paintComponent(graphics2D);
        graphics2D.dispose();
    }
}
