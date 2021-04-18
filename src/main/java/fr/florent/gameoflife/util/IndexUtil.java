package fr.florent.gameoflife.util;

public abstract class IndexUtil {

    public static int getIndex(double x, double y, double width) {
        return (int) (x + y * width);
    }

}
