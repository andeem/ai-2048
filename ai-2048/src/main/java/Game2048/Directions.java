package Game2048;

public enum Directions {
    UP,
    RIGHT,
    DOWN,
    LEFT;

    Directions next() {
        switch(this)
        {
            case UP:
                return RIGHT;
            case RIGHT:
                return DOWN;
            case DOWN:
                return LEFT;
            case LEFT:
                return UP;
            default:
                return this;
        }
    }
}
