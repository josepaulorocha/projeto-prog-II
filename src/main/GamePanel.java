package main;

<<<<<<< HEAD
import entities.EntityFactory;
=======
>>>>>>> 542fb2b7c4c03ce30317febad5ec393ba1ac4c68
import entities.Player;
import tile.TileManager;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable{
    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 17;
    public final int maxScreenRow = 13;
    final int screenWidth = tileSize * maxScreenCol; // 816 pixels
    final int screenHeight = tileSize * maxScreenRow; // 624 pixels

    // FPS
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
<<<<<<< HEAD

    EntityFactory factory = new EntityFactory(this);
    Player player = (Player) factory.createPlayer(keyH);

    public GameState currentState;
=======
    Player player = new Player(this, keyH);
>>>>>>> 542fb2b7c4c03ce30317febad5ec393ba1ac4c68

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
<<<<<<< HEAD

        currentState = new PlayState(this);
=======
>>>>>>> 542fb2b7c4c03ce30317febad5ec393ba1ac4c68
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

        // método para rodar o jogo em 60 quadros por segundo
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
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
<<<<<<< HEAD
        currentState.update();
=======
        player.update();
>>>>>>> 542fb2b7c4c03ce30317febad5ec393ba1ac4c68
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
<<<<<<< HEAD
        Graphics2D g2 = (Graphics2D)g;

        currentState.draw(g2);
=======

        Graphics2D g2 = (Graphics2D)g;

        tileM.draw(g2);
        player.draw(g2);
>>>>>>> 542fb2b7c4c03ce30317febad5ec393ba1ac4c68

        g2.dispose();
    }
}
