package rach.android_swzom;

import java.util.Random;

public class World {
    static final int WORLD_WIDTH = 10;
    static final int WORLD_HEIGHT = 13;
    static final int SCORE_INCREMENT = 10;

    static final float TICK_INITIAL = 0.1f;
    static final float TICK_DECREMENT = 0.5f;

    public Snake snake;
    public boolean gameOver = false;;
    public int score = 0;

    Random random = new Random();
    float tickTime = 0;
    static float tick = TICK_INITIAL;

    public World() {
        snake = new Snake();
    }

    public void update(float deltaTime) {
        if (gameOver)
        	
            return;

        tickTime += deltaTime;

        while (tickTime > tick) {
            tickTime -= tick;
            snake.advance();
        }
    }
}
