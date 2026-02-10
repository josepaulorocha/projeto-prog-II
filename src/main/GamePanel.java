package main;

import entities.Bomb;
import entities.EntityFactory;
import entities.Explosion;
import entities.Player;
import tile.TileManager;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable{
    // SCREEN SETTINGS
    private final int originalTileSize = 16; // 16x16 tile
    private final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 17;
    public final int maxScreenRow = 13;
    public final int screenWidth = tileSize * maxScreenCol; // 816 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 624 pixels

    // FPS
    private int FPS = 60;

    public TileManager tileM = new TileManager(this);
    public KeyHandler keyH = new KeyHandler();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public EntityFactory factory = new EntityFactory(this);
    private Thread gameThread;

    // PLAYERS
    public Player player1 = (Player) factory.createPlayer(keyH, 1);
    public Player player2 = (Player) factory.createPlayer(keyH, 2);

    // BOMBS AND EXPLOSIONS
    public ArrayList<Bomb> bombs = new ArrayList<>();
    public ArrayList<Explosion> explosions = new ArrayList<>();

    public GameState currentState;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        currentState = new MenuState(this);
    }

    public void resetGame() {
        bombs.clear();
        explosions.clear();

        tileM = new TileManager(this);

        player1 = factory.createPlayer(keyH, 1);
        player2 = factory.createPlayer(keyH, 2);
    }

    public void setState(GameState state) {
        this.currentState = state;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        // metodo para rodar o jogo em 60 quadros por segundo
        while(gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        currentState.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        currentState.draw(g2);

        g2.dispose();
    }
}
