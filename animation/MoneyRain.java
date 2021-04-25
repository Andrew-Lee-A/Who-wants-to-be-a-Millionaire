package animation;

import java.util.Random;

/**
 * This class is used to model money falling within
 * the menu screen
 * @author Rhys Van Rooyen, Student ID: 19049569
 */
public class MoneyRain {

    private int x;
    private int y;
    private int dy;
    private final int UPPER_SPEED = 6; // this is exclusive
    private final int HEIGHT_BOUND;
    private final int WIDTH_BOUND;
    private final String MONEY_SYMBOL; // i.e. $
    private final static Random rand = new Random();

    public MoneyRain(int yBoundary, int xBoundary, String moneySymbol) {
        HEIGHT_BOUND = yBoundary;
        WIDTH_BOUND = xBoundary;
        MONEY_SYMBOL = moneySymbol;
        prepareMoneyForFall();
    }

    /**
     * This method is used to simulate moving the money
     * over a given boundary
     */
    public void moveMoney() {
        y += dy;

        if (getY() >= HEIGHT_BOUND) {
            prepareMoneyForFall();
        }
    }

    /**
     * This helper method will put the money back at the top
     * of the boundary preparing it to fall again randomly
     */
    public void prepareMoneyForFall() {
        y = 0; // y always starts at top of bounds
        x = MoneyRain.rand.nextInt(WIDTH_BOUND); // let the dollar fall from anyways in the bounds - 1
        dy = MoneyRain.rand.nextInt(UPPER_SPEED) + 2;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }
    
}
