package tile;

import main.GamePanel;
import main.ResourceManager;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    public static final int TILE_GRASS = 0;
    public static final int TILE_BRICK = 2; // destrutível
    public static final int TILE_WALL = 3; // indestrutível

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];

        getTileImage();
        generateRandomMap();;
    }

    public void getTileImage() {
        tile[0] = new Tile();
        tile[0].image = ResourceManager.getTexture("/tiles/grass_1.png");

        tile[1] = new Tile();
        tile[1].image = ResourceManager.getTexture("/tiles/grass_2.png");

        tile[2] = new Tile();
        tile[2].image = ResourceManager.getTexture("/tiles/brick_1.png");
        tile[2].collision = true;

        tile[3] = new Tile();
        tile[3].image = ResourceManager.getTexture("/tiles/wall_1.png");
        tile[3].collision = true;

        tile[4] = new Tile();
        tile[4].image = ResourceManager.getTexture("/tiles/background.png");
    }

    public void loadMap(String filePatch) {
        try {
            InputStream is = getClass().getResourceAsStream(filePatch);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
                String line = br.readLine();

                while(col < gp.maxScreenCol) {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        }catch(Exception e) {}
    }

    public void generateRandomMap() {
        // limpa o mapa (tudo vira grama)
        for (int col = 0; col < gp.maxScreenCol; col++) {
            for (int row = 0; row < gp.maxScreenRow; row++) {
                mapTileNum[col][row] = TILE_GRASS;
            }
        }

        // cria as paredes fixas (grid indestrutível)
        for (int col = 0; col < gp.maxScreenCol; col++) {
            for (int row = 0; row < gp.maxScreenRow; row++) {

                // bordas do mapa sempre são paredes
                if (col == 0 || col == gp.maxScreenCol - 1 || row == 0 || row == gp.maxScreenRow - 1) {
                    mapTileNum[col][row] = TILE_WALL;
                }
                // paredes internas
                else if (col % 2 == 0 && row % 2 == 0) {
                    mapTileNum[col][row] = TILE_WALL;
                }
                else {
                    // chance aleatória de virar tijoloa
                    if (Math.random() < 0.90) { // 90%
                        mapTileNum[col][row] = TILE_BRICK;
                    }
                }
            }
        }

        // limpa a área de spawn (safe zone)
        // variáveis auxiliares
        int c1 = 1;
        int r1 = 1;
        int c2 = gp.maxScreenCol - 2;
        int r2 = gp.maxScreenRow - 2;

        // --- PLAYER 1 (superior esquerdo) ---
        // horizontal
        mapTileNum[c1][r1]     = TILE_GRASS; // spawn
        mapTileNum[c1 + 1][r1] = TILE_GRASS;
        mapTileNum[c1 + 2][r1] = TILE_GRASS;
        // vertical
        mapTileNum[c1][r1 + 1] = TILE_GRASS;
        mapTileNum[c1][r1 + 2] = TILE_GRASS;

        // --- PLAYER 2 (superior direito) ---
        // horizontal
        mapTileNum[c2][r1]     = TILE_GRASS; // spawn
        mapTileNum[c2 - 1][r1] = TILE_GRASS;
        mapTileNum[c2 - 2][r1] = TILE_GRASS;
        // vertical
        mapTileNum[c2][r1 + 1] = TILE_GRASS;
        mapTileNum[c2][r1 + 2] = TILE_GRASS;

        // --- PLAYER 3 (inferior esquerdo) ---
        // horizontal
        mapTileNum[c1][r2]     = TILE_GRASS; // spawn
        mapTileNum[c1 + 1][r2] = TILE_GRASS;
        mapTileNum[c1 + 2][r2] = TILE_GRASS;
        // vertical
        mapTileNum[c1][r2 - 1] = TILE_GRASS;
        mapTileNum[c1][r2 - 2] = TILE_GRASS;

        // --- PLAYER 4 (inferior direito) ---
        // horizontal
        mapTileNum[c2][r2]     = TILE_GRASS; // spawn
        mapTileNum[c2 - 1][r2] = TILE_GRASS;
        mapTileNum[c2 - 2][r2] = TILE_GRASS;
        // vertical
        mapTileNum[c2][r2 - 1] = TILE_GRASS;
        mapTileNum[c2][r2 - 2] = TILE_GRASS;
    }

    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
            int tileNum = mapTileNum[col][row];

            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;

            if(col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }
}
