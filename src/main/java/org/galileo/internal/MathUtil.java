package org.galileo.internal;

public final class MathUtil {

    private MathUtil() {

    }

    public static double sqr(double x) {
        return x * x;
    }

    public static double cube(double x) {
        return x * x * x;
    }

    public static double cubicRoot(double x) {
        return Math.pow(x, 1 / 3.0);
    }
}
