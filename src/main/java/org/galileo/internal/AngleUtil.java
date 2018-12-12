package org.galileo.internal;

import org.galileo.Units;
import tech.units.indriya.quantity.Quantities;

import javax.measure.Quantity;
import javax.measure.Unit;
import javax.measure.quantity.Angle;

public final class AngleUtil {

    private AngleUtil() {
    }

    // Trigonometry

    public static double sin(Quantity<Angle> angle)
    {
        return Math.sin(toRadianValue(angle));
    }

    public static double cos(Quantity<Angle> angle)
    {
        return Math.cos(toRadianValue(angle));
    }

    public static double tan(Quantity<Angle> angle)
    {
        return Math.tan(toRadianValue(angle));
    }

    // Inverse Trigonometry

    public static Quantity<Angle> asin(double value, Unit<Angle> unit) {
        return toQuantity(Math.asin(value), unit);
    }

    public static Quantity<Angle> acos(double value, Unit<Angle> unit) {
        return toQuantity(Math.acos(value), unit);
    }

    public static Quantity<Angle> atan(double value, Unit<Angle> unit) {
        return toQuantity(Math.atan(value), unit);
    }

    public static Quantity<Angle> atan2(double y, double x, Unit<Angle> unit) {
        return toQuantity(Math.atan2(y, x), unit);
    }

    public static <Q extends Quantity<?>> Quantity<Angle>
        atan2(Q y, Q x, Unit<Angle> unit) {
        double xValue= x.getValue().doubleValue();
        double yAsX = y.asType(x.getClass()).to(x.getUnit()).getValue().doubleValue();
        return atan2(yAsX, xValue, unit);
    }

    // Private Methods

    private static double toRadianValue(Quantity<Angle> angle) {
        return angle.to(Units.RADIAN).getValue().doubleValue();
    }

    private static Quantity<Angle> toQuantity(double radianValue, Unit<Angle> unit) {
        return Quantities.getQuantity(radianValue, Units.RADIAN).to(unit);
    }
}
