package org.galileo.test.position.conversions;

import org.galileo.Constants;
import org.galileo.Units;
import org.galileo.datum.Datum;
import org.galileo.datum.Datums;
import org.galileo.position.ECEF;
import org.galileo.position.LLA;
import org.galileo.test.TestSuite;
import org.junit.jupiter.api.Test;
import tech.units.indriya.quantity.Quantities;

public class LLAToECEFTest extends TestSuite {

    private static final Datum datum = Datums.DEFAULT_DATUM;
    private ECEF ecef;
    private LLA lla;

    public void checkConversion() {
        assertAlmostEquals(lla.toECEF(datum), ecef, "conversion from lla to ecef is wrong");
        assertAlmostEquals(ecef.toLLA(datum), lla, "conversion from ecef to lla is wrong");
    };

    @Test
    public void testConversionInNorthPole() {
        ecef = new ECEF(
                Constants.ZERO_METERS,
                Constants.ZERO_METERS,
                datum.getSemiMinorAxis());
        lla = new LLA(
                Constants.NINETY_DEGREES,
                Constants.ZERO_DEGREES,
                Constants.ZERO_METERS);
        checkConversion();
    }

    @Test
    public void testConversionInSouthPole() {
        ecef = new ECEF(
                Constants.ZERO_METERS,
                Constants.ZERO_METERS,
                datum.getSemiMinorAxis().negate());
        lla = new LLA(
                Constants.NINETY_DEGREES.negate(),
                Constants.ZERO_DEGREES,
                Constants.ZERO_METERS);
        checkConversion();
    }

    @Test
    public void testConversionInZeroZero() {
        ecef = new ECEF(
                datum.getSemiMajorAxis(),
                Constants.ZERO_METERS,
                Constants.ZERO_METERS);
        lla = new LLA(
                Constants.ZERO_DEGREES,
                Constants.ZERO_DEGREES,
                Constants.ZERO_METERS);
        checkConversion();
    }

    @Test
    public void testConversionInZeroNinety() {
        ecef = new ECEF(
                Constants.ZERO_METERS,
                datum.getSemiMajorAxis(),
                Constants.ZERO_METERS);
        lla = new LLA(
                Constants.ZERO_DEGREES,
                Constants.NINETY_DEGREES,
                Constants.ZERO_METERS);
        checkConversion();
    }

    @Test
    public void testConversionInZeroOneHundredAndEighty() {
        ecef = new ECEF(
                datum.getSemiMajorAxis().negate(),
                Constants.ZERO_METERS,
                Constants.ZERO_METERS);
        lla = new LLA(
                Constants.ZERO_DEGREES,
                Constants.ONE_HUNDRED_AND_EIGHTY_DEGREES,
                Constants.ZERO_METERS);
        checkConversion();
    }

    @Test
    public void testConversionInZeroMinusNinety() {
        ecef = new ECEF(
                Constants.ZERO_METERS,
                datum.getSemiMajorAxis().negate(),
                Constants.ZERO_METERS);
        lla = new LLA(
                Constants.ZERO_DEGREES,
                Constants.NINETY_DEGREES.negate(),
                Constants.ZERO_METERS);
        checkConversion();
    }

    @Test
    public void testConversionInWestWall() {
        ecef = new ECEF(
                Quantities.getQuantity(4432887.112838, Units.METER),
                Quantities.getQuantity(3131057.037713, Units.METER),
                Quantities.getQuantity(3339417.754266, Units.METER));
        lla = new LLA(
                Quantities.getQuantity(31.7767, Units.DEGREE),
                Quantities.getQuantity(35.2345, Units.DEGREE),
                Quantities.getQuantity(19 , Units.METER));
        checkConversion();
    }

    @Test
    public void testEmpireStateBuilding() {
        ecef = new ECEF(
                Quantities.getQuantity(1335028.253247, Units.METER),
                Quantities.getQuantity(4651415.040293, Units.METER),
                Quantities.getQuantity(4141585.182753, Units.METER));
        lla = new LLA(
                Quantities.getQuantity(40.7484, Units.DEGREE),
                Quantities.getQuantity(73.9857, Units.DEGREE),
                Quantities.getQuantity(443 , Units.METER));
        checkConversion();
    }
}