package org.galileo.test.position.conversions;

import org.galileo.Constants;
import org.galileo.Units;
import org.galileo.datum.Datum;
import org.galileo.datum.Datums;
import org.galileo.position.ECEF;
import org.galileo.position.ENU;
import org.galileo.position.LLA;
import org.galileo.test.TestSuite;
import org.junit.jupiter.api.Test;
import tech.units.indriya.quantity.Quantities;

import static org.galileo.internal.MeasureUtil.zero;

public class ECEFToENUTest extends TestSuite {

    private static final Datum datum = Datums.DEFAULT_DATUM;
    private ECEF ecef;
    private ENU enu;
    private LLA origin;

    public void checkConversion() {
        assertAlmostEquals(ecef.toENU(datum, origin), enu, "conversion from ecef to enu is wrong");
        assertAlmostEquals(enu.toECEF(datum, origin), ecef, "conversion from enu to ecef is wrong");
    }

    @Test
    public void testConversionInNorthPoleOriginInEquator() {
        ecef = new ECEF(
                Constants.ZERO_METERS,
                Constants.ZERO_METERS,
                datum.getSemiMinorAxis());
        origin = new LLA(
                Constants.ZERO_DEGREES,
                Constants.ZERO_DEGREES,
                Constants.ZERO_METERS);
        enu = new ENU(
                Constants.ZERO_METERS,
                datum.getSemiMinorAxis(),
                datum.getSemiMajorAxis().negate()
        );
        checkConversion();
    }

    @Test
    public void testConversionInSouthPoleOriginInEquator() {
        ecef = new ECEF(
                Constants.ZERO_METERS,
                Constants.ZERO_METERS,
                datum.getSemiMinorAxis().negate());
        origin = new LLA(
                Constants.ZERO_DEGREES,
                Constants.ZERO_DEGREES,
                Constants.ZERO_METERS);
        enu = new ENU(
                Constants.ZERO_METERS,
                datum.getSemiMinorAxis().negate(),
                datum.getSemiMajorAxis().negate()
        );
        checkConversion();
    }

    @Test
    public void testConversionWhenOriginIsInPrimeMeridianAndECEFInEast() {
        ecef = new LLA(
                Constants.ZERO_DEGREES,
                Constants.NINETY_DEGREES,
                Constants.ZERO_METERS).toECEF(datum);
        origin = new LLA(
                Constants.ZERO_DEGREES,
                Constants.ZERO_DEGREES,
                Constants.ZERO_METERS);
        enu = new ENU(
                datum.getSemiMajorAxis(),
                Constants.ZERO_METERS,
                datum.getSemiMajorAxis().negate()
        );
        checkConversion();
    }

    @Test
    public void testConversionWhenOriginIsInPrimeMeridianAndECEFInWest() {
        ecef = new LLA(
                Constants.ZERO_DEGREES,
                Constants.NINETY_DEGREES.negate(),
                Constants.ZERO_METERS).toECEF(datum);
        origin = new LLA(
                Constants.ZERO_DEGREES,
                Constants.ZERO_DEGREES,
                Constants.ZERO_METERS);
        enu = new ENU(
                datum.getSemiMajorAxis().negate(),
                Constants.ZERO_METERS,
                datum.getSemiMajorAxis().negate()
        );
        checkConversion();
    }

    @Test
    public void testConversionWhenOriginIsInPrimeMeridianAndECEFInOtherSide() {
        ecef = new LLA(
                Constants.ZERO_DEGREES,
                Constants.ONE_HUNDRED_AND_EIGHTY_DEGREES,
                Constants.ZERO_METERS).toECEF(datum);
        origin = new LLA(
                Constants.ZERO_DEGREES,
                Constants.ZERO_DEGREES,
                Constants.ZERO_METERS);
        enu = new ENU(
                Constants.ZERO_METERS,
                Constants.ZERO_METERS,
                datum.getSemiMajorAxis().multiply(-2)
        );
        checkConversion();
    }

}
