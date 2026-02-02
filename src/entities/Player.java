package entities;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 24;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 96;
        y = 32;
        speed = 3;
        direction ="down";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/player_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/player_up_2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/player/player_up_3.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/player_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/player_down_2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/player/player_down_3.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/player_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/player_left_2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/player/player_left_3.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/player_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/player_right_2.png"));
            right3= ImageIO.read(getClass().getResourceAsStream("/player/player_right_3.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if(keyH.upPressed == true) {
            direction = "up";
        } else if(keyH.downPressed == true) {
            direction = "down";
        } else if(keyH.leftPressed == true) {
            direction = "left";
        } else if(keyH.rightPressed == true) {
            direction = "right";
        }

        // checa a colisão do tile
        collisionOn = false;
        gp.cChecker.checkerTile(this);

        // se colisão é falso, o player pode se mover
        if(keyH.upPressed || keyH.downPressed ||
                keyH.leftPressed || keyH.rightPressed) {

            for(int i = 0; i < speed; i++) {
                collisionOn = false;
                gp.cChecker.checkerTile(this);

                if(!collisionOn) {
                    switch(direction) {
                        case "up":
                            y--;
                            break;
                        case "down":
                            y++;
                            break;
                        case "left":
                            x--;
                            break;
                        case "right":
                            x++;
                            break;
                    }
                }
            }
        }

        if(keyH.upPressed == true || keyH.downPressed == true ||
                keyH.leftPressed == true || keyH.rightPressed == true) {

            spriteCounter++;

            if(spriteCounter > 10) {
                spriteNum++;
                if(spriteNum > 3) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        } else {
            // personagem parado
            spriteNum = 1;
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch(direction) {
            case "up":
                if(spriteNum == 1) {
                    image = up1;
                }
                if(spriteNum == 2) {
                    image = up2;
                }
                if(spriteNum == 3) {
                    image = up3;
                }
                break;
            case "down":
                if(spriteNum == 1) {
                    image = down1;
                }
                if(spriteNum == 2) {
                    image = down2;
                }
                if(spriteNum == 3) {
                    image = down3;
                }
                break;
            case "left":
                if(spriteNum == 1) {
                    image = left1;
                }
                if(spriteNum == 2) {
                    image = left2;
                }
                if(spriteNum == 3) {
                    image = left3;
                }
                break;
            case "right":
                if(spriteNum == 1) {
                    image = right1;
                }
                if(spriteNum == 2) {
                    image = right2;
                }
                if(spriteNum == 3) {
                    image = right3;
                }
                break;
        }

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize + 12, null);
    }
}
