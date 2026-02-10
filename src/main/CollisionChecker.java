package main;

import entities.Bomb;
import entities.Entity;

import java.util.ArrayList;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkerTile(Entity entity) {
        int entityLeftScreenX = entity.x + entity.solidArea.x;
        int entityRightScreenX = entity.x + entity.solidArea.x + entity.solidArea.width;
        int entityTopScreenY = entity.y + entity.solidArea.y;
        int entityBottomScreenY = entity.y + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftScreenX/gp.tileSize;
        int entityRightCol = entityRightScreenX/gp.tileSize;
        int entityTopRow = entityTopScreenY/gp.tileSize;
        int entityBottomRow = entityBottomScreenY/gp.tileSize;

        int tileNum1, tileNum2;

        switch(entity.direction) {
            case "up":
                entityTopRow = (entityTopScreenY - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomScreenY + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftScreenX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightScreenX + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
        }
    }

    public void checkerEntity(Entity entity, ArrayList<Bomb> targets) {
        for (int i = 0; i < targets.size(); i++) {
            Bomb target = targets.get(i);

            if(target != null) {
                int entitySolidX = entity.solidArea.x;
                int entitySolidY = entity.solidArea.y;
                int targetSolidX = target.solidArea.x;
                int targetSolidY = target.solidArea.y;

                entity.solidArea.x = entity.x + entity.solidArea.x;
                entity.solidArea.y = entity.y + entity.solidArea.y;

                target.solidArea.x = target.x + target.solidArea.x;
                target.solidArea.y = target.y + target.solidArea.y;

                // verifica se o player está dentro da bomba
                boolean currentlyInside = entity.solidArea.intersects(target.solidArea);

                switch(entity.direction) {
                    case "up": entity.solidArea.y -= entity.speed; break;
                    case "down": entity.solidArea.y += entity.speed; break;
                    case "left": entity.solidArea.x -= entity.speed; break;
                    case "right": entity.solidArea.x += entity.speed; break;
                }

                if(entity.solidArea.intersects(target.solidArea)) {
                    if (!currentlyInside) {
                        entity.collisionOn = true;
                    }
                }

                entity.solidArea.x = entitySolidX;
                entity.solidArea.y = entitySolidY;
                target.solidArea.x = targetSolidX;
                target.solidArea.y = targetSolidY;
            }
        }
    }
}
