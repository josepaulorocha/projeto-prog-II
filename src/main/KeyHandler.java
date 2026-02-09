package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    // controles player 1
    public boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed;

    // controles player 2
    public boolean upPressed2, downPressed2, leftPressed2, rightPressed2, enterPressed;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // player 1
        if(code == KeyEvent.VK_W) upPressed = true;
        if(code == KeyEvent.VK_S) downPressed = true;
        if(code == KeyEvent.VK_A) leftPressed = true;
        if(code == KeyEvent.VK_D) rightPressed = true;
        if(code == KeyEvent.VK_SPACE) spacePressed = true;

        // player 2
        if(code == KeyEvent.VK_UP) upPressed2 = true;
        if(code == KeyEvent.VK_DOWN) downPressed2 = true;
        if(code == KeyEvent.VK_LEFT) leftPressed2 = true;
        if(code == KeyEvent.VK_RIGHT) rightPressed2 = true;
        if(code == KeyEvent.VK_ENTER) enterPressed = true;
    }


    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        // player 1
        if(code == KeyEvent.VK_W) upPressed = false;
        if(code == KeyEvent.VK_S) downPressed = false;
        if(code == KeyEvent.VK_A) leftPressed = false;
        if(code == KeyEvent.VK_D) rightPressed = false;
        if(code == KeyEvent.VK_SPACE) spacePressed = false;

        // player 2
        if(code == KeyEvent.VK_UP) upPressed2 = false;
        if(code == KeyEvent.VK_DOWN) downPressed2 = false;
        if(code == KeyEvent.VK_LEFT) leftPressed2 = false;
        if(code == KeyEvent.VK_RIGHT) rightPressed2 = false;
        if(code == KeyEvent.VK_ENTER) enterPressed = false;
    }
}
