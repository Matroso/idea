package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GameWindow extends JFrame {

    private static GameWindow gameWindow;
    private static long lastFrameTime;
    private static Image background;
    private static Image droid;
    private static float droidLeft = 200;
    private static float droidTop = -200;
    private static float droidV = 200;

    public static void main(String[] args) throws IOException {
        gameWindow = new GameWindow();
        gameWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameWindow.setLocation(0, 30);
        gameWindow.setSize(1900, 1000);
        gameWindow.setResizable(false);

        background = ImageIO.read(GameWindow.class.getResourceAsStream("57ab9564cf1cb156763f91f0.png"));
        droid = ImageIO.read(GameWindow.class.getResourceAsStream("ic_launcher.png"));
        lastFrameTime = System.nanoTime();
        GameField gameField = new GameField();
        gameWindow.add(gameField);

        gameWindow.setVisible(true);
    }

    private static void onRepaint(Graphics g){
        long currentTime = System.nanoTime();
        float deltaTime = (currentTime - lastFrameTime) * 0.000000001f;
        lastFrameTime = currentTime;

        droidTop = droidTop + droidV * deltaTime;
        droidLeft += droidV * deltaTime * 0.2f;
        g.drawImage(background, 0, 0, null);
        g.drawImage(droid, (int) droidLeft, (int) droidTop, null);
    }

    private static class GameField extends JPanel{
        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            onRepaint(g);
            repaint();
        }
    }
}
