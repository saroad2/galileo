package org.galileo.datum;

import org.galileo.Units;
import org.galileo.internal.MeasureUtil;
import tech.units.indriya.quantity.Quantities;

import javax.measure.Quantity;
import javax.measure.quantity.Length;

public class Datums {

    public static final Datum WGS84 = buildFromAxes(
            "WGS84",
            Quantities.getQuantity(6_378_137.0, Units.METER),
            Quantities.getQuantity(6_356_752.314245, Units.METER)
    );
    public static final Datum ED50 = buildFromSemiMajorAxisAndFlattening(
            "ED50",
            Quantities.getQuantity(6_378_388.0, Units.METER),
            1 / 297.0
    );
    public static final Datum GRS80 = buildFromSemiMajorAxisAndFlattening(
            "GRS80",
            Quantities.getQuantity(6_378_137.0, Units.METER),
            1 / 298.257222101
    );
    public static final Datum DEFAULT_DATUM = WGS84;


    private static Datum buildFromAxes(
            String name,
            Quantity<Length> semiMajorAxis,
            Quantity<Length> semiMinorAxis) {

        double flattening = 1 - MeasureUtil.ratio(semiMinorAxis, semiMajorAxis);
        double eccentricity = Math.sqrt(2 * flattening - Math.pow(flattening, 2));
        return new DatumImplementation(name, semiMajorAxis, semiMinorAxis, flattening, eccentricity);
    }

    private static Datum buildFromSemiMajorAxisAndFlattening(
            String name,
            Quantity<Length> semiMajorAxis,
            double flattening) {

        Quantity<Length> semiMinorAxis= semiMajorAxis.multiply(1 - flattening);
        double eccentricity = Math.sqrt(2 * flattening - Math.pow(flattening, 2));
        return new DatumImplementation(name, semiMajorAxis, semiMinorAxis, flattening, eccentricity);
    }
}
