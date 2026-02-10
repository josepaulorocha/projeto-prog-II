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

    // identificador do player
    private int playerId;

    private int bombCooldown = 0;

    private int deathTimer = 0;
    public int deathSpriteNum = 1;

    public Player(GamePanel gp, KeyHandler keyH, int playerId) {
        super();
        this.gp = gp;
        this.keyH = keyH;
        this.playerId = playerId;

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 24;
        solidArea.width = 32;
        solidArea.height = 32;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        speed = 3;
        alive = true;
        deathTimer = 0;

        bombCooldown = 30;

        // posição de spawn dos players no grid
        if (playerId == 1) {
            x = gp.tileSize;
            y = 32;
            direction ="down";
        } else {
            x = 15 * gp.tileSize;
            y = (11 * gp.tileSize) - 16;
            direction ="down";
        }
    }

    public void getPlayerImage() {
        String prefix = (playerId == 1) ? "player" : "player2";


        // carrega os sprites de movimento
        up1 = ResourceManager.getTexture("/player/" + prefix + "_up_1.png");
        up2 = ResourceManager.getTexture("/player/" + prefix + "_up_2.png");
        up3 = ResourceManager.getTexture("/player/" + prefix + "_up_3.png");

        down1 = ResourceManager.getTexture("/player/" + prefix + "_down_1.png");
        down2 = ResourceManager.getTexture("/player/" + prefix + "_down_2.png");
        down3 = ResourceManager.getTexture("/player/" + prefix + "_down_3.png");

        left1 = ResourceManager.getTexture("/player/" + prefix + "_left_1.png");
        left2 = ResourceManager.getTexture("/player/" + prefix + "_left_2.png");
        left3 = ResourceManager.getTexture("/player/" + prefix + "_left_3.png");

        right1 = ResourceManager.getTexture("/player/" + prefix + "_right_1.png");
        right2 = ResourceManager.getTexture("/player/" + prefix + "_right_2.png");
        right3 = ResourceManager.getTexture("/player/" + prefix + "_right_3.png");

        // carrega os sprites de morte
        death1 = ResourceManager.getTexture("/player/" + prefix + "_death_1.png");
        death2 = ResourceManager.getTexture("/player/" + prefix + "_death_2.png");
        death3 = ResourceManager.getTexture("/player/" + prefix + "_death_3.png");
        death4 = ResourceManager.getTexture("/player/" + prefix + "_death_4.png");
        death5 = ResourceManager.getTexture("/player/" + prefix + "_death_5.png");
        death6 = ResourceManager.getTexture("/player/" + prefix + "_death_6.png");
        death7 = ResourceManager.getTexture("/player/" + prefix + "_death_7.png");
        death8 = ResourceManager.getTexture("/player/" + prefix + "_death_8.png");
        death9 = ResourceManager.getTexture("/player/" + prefix + "_death_9.png");
        death10 = ResourceManager.getTexture("/player/" + prefix + "_death_10.png");
    }

    public boolean isDeathAnimationFinished() {
        return !alive && deathTimer >= 165;
    }

    public void update() {
        if (alive) {
            updateAlive();
        } else {
            playDeathAnimation();
        }
    }

    public void updateAlive() {
        checkCollisionWithExplosions();
        if (!alive) return;

        boolean isMoving = false;

        boolean up = false, down = false, left = false, right = false, bomb = false;

        if (playerId == 1) {
            up = keyH.upPressed;
            down = keyH.downPressed;
            left = keyH.leftPressed;
            right = keyH.rightPressed;
            bomb = keyH.spacePressed;
        } else {
            up = keyH.upPressed2;
            down = keyH.downPressed2;
            left = keyH.leftPressed2;
            right = keyH.rightPressed2;
            bomb = keyH.enterPressed;
        }

        if (up) {
            move("up");
            isMoving = true;
        } else if (down) {
            move("down");
            isMoving = true;
        }

        if (left) {
            move("left");
            isMoving = true;
        } else if (right) {
            move("right");
            isMoving = true;
        }

        // lógica da Bomba
        if (bombCooldown > 0) bombCooldown--;

        if (bomb && bombCooldown == 0) {
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

    private void checkCollisionWithExplosions() {
        Rectangle playerRect = new Rectangle(x + solidArea.x, y + solidArea.y, solidArea.width, solidArea.height);

        for (int i = 0; i < gp.explosions.size(); i++) {
            Explosion e = gp.explosions.get(i);

            Rectangle explosionRect = new Rectangle(e.x + 4, e.y + 4, gp.tileSize - 8, gp.tileSize - 8);

            if (playerRect.intersects(explosionRect)) {
                alive = false;
                deathTimer = 0;
                break;
            }
        }
    }

    private void playDeathAnimation() {
        deathTimer++;

        // fase 1: loop inicial
        if (deathTimer < 60) {
            int interval = 5;
            int step = (deathTimer / interval) % 4;

            deathSpriteNum = step + 1;
        }
        // fase 2: transição
        else if (deathTimer < 105) {
            int timeInPhase2 = deathTimer - 60;
            int step = timeInPhase2 / 15;
            deathSpriteNum = 5 + step;
        }
        // fase 3: loop final
        else if (deathTimer < 165) {
            int localTime = deathTimer - 105;
            int interval = 5;
            int step = (localTime / interval) % 4;

            switch (step) {
                case 0: deathSpriteNum = 8; break;
                case 1: deathSpriteNum = 9; break;
                case 2: deathSpriteNum = 8; break;
                case 3: deathSpriteNum = 10; break;
            }
        }
        else {
            deathSpriteNum = 8;
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
            gp.bombs.add(gp.factory.createBomb(bombX, bombY));
        }
    }

    private void move(String direction) {
        this.direction = direction; // atualiza a direção
        collisionOn = false;

        gp.cChecker.checkerTile(this);
        gp.cChecker.checkerEntity(this, gp.bombs);

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


        if (alive) {
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
        } else {
            switch (deathSpriteNum) {
                case 1: image = death1; break;
                case 2: image = death2; break;
                case 3: image = death3; break;
                case 4: image = death4; break;
                case 5: image = death5; break;
                case 6: image = death6; break;
                case 7: image = death7; break;
                case 8: image = death8; break;
                case 9: image = death9; break;
                case 10: image = death10; break;
                default: image = death10; break;
            }
        }
        if (image != null) {
            g2.drawImage(image, x, y, gp.tileSize, gp.tileSize + 12, null);
        }
    }
}
