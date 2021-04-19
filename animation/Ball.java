/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animation;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 *
 * @author Rhys Van Rooyen, Student ID: 19049569
 */
public class Ball {

    private int x;
    private int y;
    private int dx;
    private int dy;
    private final int size;
    private final int worldWidth;
    private final int worldHeight;
    private final Random rand = new Random();
    private Color ballColor;

    public Ball(int worldWidth, int worldHeight, int size) {
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        this.size = size;
        createInitialDisplacement();
        pickRandomBallColor();
    }

    /**
     * This helper method is used to set the initial displacement of a ball
     * within the boundaries of the world
     */
    private void createInitialDisplacement() {
        // removing 40, and adding 20 to ensure doesnt
        // start on boundary
        dx = rand.nextInt(6) + 3;
        dy = rand.nextInt(6) + 3;
        x = rand.nextInt((worldWidth - (2 * size))) + size;
        y = rand.nextInt((worldHeight - (2 * size))) + size;

        if ((rand.nextInt(2) + 1) == 0) {
            dx *= -1;
        }

        if ((rand.nextInt(2) + 1) == 0) {
            dy *= -1;
        }
    }

    /**
     * This helper method selects a random color attribute for the ball
     */
    public void pickRandomBallColor() {
        int chosenColor = rand.nextInt(8);

        switch (chosenColor) {
            case 0:
                ballColor = Color.darkGray;
                break;
            case 1:
                ballColor = Color.WHITE;
                break;
            case 2:
                ballColor = new Color(161, 31, 224);
                break;
            case 3:
                ballColor = new Color(235, 105, 50);
                break;
            case 4:
                ballColor = new Color(31, 147, 224);
                break;
            case 5:
                ballColor = new Color(224, 31, 120);
                break;
            case 6:
                ballColor = new Color(165, 117, 236);
                break;
            default:
                ballColor = new Color(219, 90, 81);
                break;
        }
    }

    /**
     * Method used to update the (x,y) position
     * of the ball without going out of bounds
     */
    public void updateDisplacement() {
        x += dx;
        y += dy;

        if (x + size >= worldWidth || x <= 0) {
            dx *= -1;
        }

        if (y + size >= worldHeight || y <= 0) {
            dy *= -1;
        }
    }

    /**
     * drawBall is used to draw the ball
     * @param g, Graphics used to redraw the ball
     */
    public void drawBall(Graphics g) {
        g.setColor(ballColor);
        g.fillOval(x, y, size, size);
    }
}
