package utils;

public class Tuple<X, Y> {

    private final X x;
    private final Y y;

    public X getX() {
        return x;
    }

    public Y getY() {
        return y;
    }

    public Tuple(X x, Y y) {
        this.x = x;
        this.y = y;
    }
}
