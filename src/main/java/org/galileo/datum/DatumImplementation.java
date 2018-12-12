package org.galileo.datum;

import org.galileo.internal.AngleUtil;

import javax.measure.Quantity;
import javax.measure.quantity.Angle;
import javax.measure.quantity.Dimensionless;
import javax.measure.quantity.Length;

public class DatumImplementation implements Datum {

    private final String name;
    private final Quantity<Length> semiMajorAxis;
    private final Quantity<Length> semiMinorAxis;
    private final double flattening;
    private final double eccentricity;

    public DatumImplementation(
            String name,
            Quantity<Length> semiMajorAxis,
            Quantity<Length> semiMinorAxis,
            double flattening,
            double eccentricity) {
        this.name = name;
        this.semiMajorAxis = semiMajorAxis;
        this.semiMinorAxis = semiMinorAxis;
        this.flattening = flattening;
        this.eccentricity = eccentricity;
    }

    @Override
    public Quantity<Length> getSemiMajorAxis() {
        return semiMajorAxis;
    }

    @Override
    public Quantity<Length> getSemiMinorAxis() {
        return semiMinorAxis;
    }

    @Override
    public double getEccentricity() {
        return eccentricity;
    }

    @Override
    public double getFlattening() {
        return flattening;
    }

    @Override
    public Quantity<Length> radiusAtLatitude(Quantity<Angle> latitude) {
        return semiMajorAxis.divide(Math.sqrt(1-Math.pow(eccentricity * AngleUtil.sin(latitude), 2)));
    }

    @Override
    public String toString() {
        return name +
                "{semiMajorAxis=" + semiMajorAxis +
                ", semiMinorAxis=" + semiMinorAxis +
                ", flattening=" + flattening+
                ", eccentricity=" + eccentricity+
                '}';
    }
}