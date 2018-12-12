package org.galileo.datum;

import org.galileo.Units;
import tech.units.indriya.quantity.Quantities;

import javax.measure.Quantity;
import javax.measure.quantity.Dimensionless;
import javax.measure.quantity.Length;

public class Datums {

    public static final Datum WGS84 = buildFromAxes(
            "WGS84",
            Quantities.getQuantity(6378137.0, Units.METER),
            Quantities.getQuantity(6356752.314245, Units.METER)
    );
    public static final Datum ED50 = buildFromSemiMajoeAxisAndFlatenning(
            "WGS84",
            Quantities.getQuantity(6378.388, Units.METER),
            1/ 297.0
    );
    public static final Datum DEFAULT_DATUM = WGS84;


    private static Datum buildFromAxes(
            String name,
            Quantity<Length> semiMajorAxis,
            Quantity<Length> semiMinorAxis) {

        double flattening = 1 - semiMinorAxis
                .divide(semiMajorAxis)
                .asType(Dimensionless.class)
                .getValue()
                .doubleValue();
        double eccentricity = Math.sqrt(2 * flattening - Math.pow(flattening, 2));
        return new DatumImplementation(name, semiMajorAxis, semiMinorAxis, flattening, eccentricity);
    }

    private static Datum buildFromSemiMajoeAxisAndFlatenning(
            String name,
            Quantity<Length> semiMajorAxis,
            double flattening) {

        Quantity<Length> semiMinorAxis= semiMajorAxis.multiply(1 - flattening);
        double eccentricity = Math.sqrt(2 * flattening - Math.pow(flattening, 2));
        return new DatumImplementation(name, semiMajorAxis, semiMinorAxis, flattening, eccentricity);
    }
}
