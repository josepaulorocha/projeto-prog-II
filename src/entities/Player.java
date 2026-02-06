package entities;

import main.GamePanel;
import main.KeyHandler;
import main.ResourceManager;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;

    int bombCooldown = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        super();
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
        up1 = ResourceManager.getTexture("/player/player_up_1.png");
        up2 = ResourceManager.getTexture("/player/player_up_2.png");
        up3 = ResourceManager.getTexture("/player/player_up_3.png");
        down1 = ResourceManager.getTexture("/player/player_down_1.png");
        down2 = ResourceManager.getTexture("/player/player_down_2.png");
        down3 = ResourceManager.getTexture("/player/player_down_3.png");
        left1 = ResourceManager.getTexture("/player/player_left_1.png");
        left2 = ResourceManager.getTexture("/player/player_left_2.png");
        left3 = ResourceManager.getTexture("/player/player_left_3.png");
        right1 = ResourceManager.getTexture("/player/player_right_1.png");
        right2 = ResourceManager.getTexture("/player/player_right_2.png");
        right3= ResourceManager.getTexture("/player/player_right_3.png");
    }

    public void update() {
        boolean isMoving = false;

        // EIXO Y (Vertical)
        if (keyH.upPressed) {
            move("up");
            isMoving = true;
        } else if (keyH.downPressed) {
            move("down");
            isMoving = true;
        }

        // EIXO X (Horizontal)
        if (keyH.leftPressed) {
            move("left");
            isMoving = true;
        } else if (keyH.rightPressed) {
            move("right");
            isMoving = true;
        }

        // lógica da Bomba
        if (bombCooldown > 0) bombCooldown--;

        if (keyH.spacePressed && bombCooldown == 0) {
            placeBomb();
            bombCooldown = 30;
        }

        // gerenciamento dos sprites
        if (isMoving) {
            spriteCounter++;
            if (spriteCounter > 10) { // velocidade da animação
                spriteNum++;
                if (spriteNum > 3) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        } else {
            spriteNum = 1; // personagem parado
        }
    }

    private void placeBomb() {
        // calcular o centro do jogador para determinar o tile
        int centerX = x + solidArea.x + solidArea.width / 2;
        int centerY = y + solidArea.y + solidArea.height / 2;

        // posição do grid
        int col = centerX / gp.tileSize;
        int row = centerY / gp.tileSize;

        int bombX = col * gp.tileSize;
        int bombY = row * gp.tileSize;

        // verifica se já existe bomba na posição atual
        boolean bombExists = false;
        for(Bomb b : gp.bombs) {
            if(b.x == bombX && b.y == bombY) {
                bombExists = true;
                break;
            }
        }

        if(!bombExists) {
            gp.bombs.add(new Bomb(gp, bombX, bombY));
        }
    }

    private void move(String direction) {
        this.direction = direction; // atualiza a direção
        collisionOn = false;
        gp.cChecker.checkerTile(this);

        if (!collisionOn) {
            switch (direction) {
                case "up":    y -= speed; break;
                case "down":  y += speed; break;
                case "left":  x -= speed; break;
                case "right": x += speed; break;
            }
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
