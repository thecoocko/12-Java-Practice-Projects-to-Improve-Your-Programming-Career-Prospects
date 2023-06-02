package firstProject.entity;

import firstProject.GamePanel;
import firstProject.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Enity{
    GamePanel gp;
    KeyHandler kh;
    public final int screenX;
    public final int screenY;
    public Player(GamePanel gp, KeyHandler kh){
        this.gp = gp;
        this.kh=kh;

        screenX = gp.screenWidth / 2 - (gp.tileSize/2);
        screenY = gp.screenHeight / 2 - (gp.tileSize/2);

        setDefaultValues();
        getPlayerImage();
    }
    public void getPlayerImage(){
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_right_2.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void setDefaultValues(){
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 3;
        direction = "down";
    }

    public void update(){

        if(kh.upPressed == true || kh.downPressed==true || kh.leftPressed==true || kh.rightPressed==true){

            if(kh.upPressed == true){
                direction ="up";
                worldY-=speed;
            } else if (kh.downPressed==true) {
                direction ="down";
                worldY+=speed;
            } else if (kh.leftPressed==true) {
                direction ="left";
                worldX-=speed;
            } else if (kh.rightPressed==true) {
                direction ="right";
                worldX+=speed;
            }

            spriteCounter++;
            if(spriteCounter>=15){
                if(spriteNum==1){spriteNum=2;} else if (spriteNum==2) {
                    spriteNum=1;
                }
                spriteCounter=0;
            }
        }


    }
    public void paintComponent(Graphics2D graphics2D){

//        graphics2D.setColor(Color.white);
//        graphics2D.fillOval(x,y,gp.tileSize,gp.tileSize);

        BufferedImage image = null;
        switch (direction){
            case "up":
                if(spriteNum ==1){image = up1;}
                if(spriteNum ==2){image=up2;}
            break;
            case "down":
                if(spriteNum ==1){image=down1;}
                if(spriteNum ==2){image=down2;}
            break;
            case "left" :
                if(spriteNum ==1){image=left1;}
                if(spriteNum ==2){image=left2;}
            break;
            case "right":
                if(spriteNum ==1){image=right1;}
                if(spriteNum ==2){image=right2;}
            break;
        }
        graphics2D.drawImage(image, screenX,screenY,gp.tileSize,gp.tileSize,null);

    }
}
