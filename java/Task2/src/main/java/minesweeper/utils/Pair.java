package minesweeper.utils;

public class Pair {
    String first;
    double second;

    public static final double EMPTY = -1;

    public void swap(Pair pair) {
        var tmp1 = pair.getFirst();
        var tmp2 = pair.getSecond();
        pair.setFirst(first);
        pair.setSecond(second);
        first = tmp1;
        second = tmp2;
    }

    public Pair() {
        super();
        second = EMPTY;
    }

    public Pair(String first, double second) {
        this.first = first;
        this.second = second;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }


    public double getSecond() {
        return second;
    }

    public void setSecond(double second) {
        this.second = second;
    }

}
