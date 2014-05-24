package rach.android_swzom;

public class Snake {
    public static final int UP = 0;
    public static final int LEFT = 1;
    public static final int DOWN = 2;
    public static final int RIGHT = 3;
    
    public int x;
    public int y;
    
    public int direction;   
    
    
    public Snake() {        
        direction = UP;
        x = 5;
        y = 6;
    }
    
    public void turnLeft() {
        direction += 1;
        if(direction > RIGHT)
            direction = UP;
    }
    
    public void turnRight() {
        direction -= 1;
        if(direction < UP)
            direction = RIGHT;
    }
    
    public void advance() {
        if(direction == UP)
            y -= 1;
        if(direction == LEFT)
            x -= 1;
        if(direction == DOWN)
            y += 1;
        if(direction == RIGHT)
            x += 1;
        
        if(x < 0)
            x = 9;
        if(x > 9)
            x = 0;
        if(y < 0)
            y = 12;
        if(y > 12)
            y = 0;
    }
    
}
