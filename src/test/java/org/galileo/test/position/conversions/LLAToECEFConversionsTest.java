package org.galileo.test.position.conversions;

import org.galileo.Constants;
import org.galileo.Units;
import org.galileo.datum.Datum;
import org.galileo.position.ECEF;
import org.galileo.position.LL;
import org.galileo.position.LLA;
import org.galileo.test.DatumArgumentsProvider;
import org.galileo.test.TestSuite;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import tech.units.indriya.quantity.Quantities;

public class LLAToECEFConversionsTest extends TestSuite {

    private ECEF ecef;
    private LLA lla;
    private LL ll;

    public void checkLLAConversion(Datum datum) {
        assertAlmostEquals(ecef, lla.toECEF(datum), "conversion from lla to ecef is wrong");
        assertAlmostEquals(lla, ecef.toLLA(datum), "conversion from ecef to lla is wrong");

        assertAlmostEquals(ecef, ll.toECEF(datum), "conversion from ll to ecef is wrong");
        assertAlmostEquals(ll, ecef.toLL(datum), "conversion from ecef to ll is wrong");
    }

    public void checkLLAConversion() {
        assertAlmostEquals(ecef, lla.toECEF(), "conversion from lla to ecef is wrong");
        assertAlmostEquals(lla, ecef.toLLA(), "conversion from ecef to lla is wrong");
        assertAlmostEquals(ll, ecef.toLL(), "conversion from ecef to ll is wrong");
    }

    public void checkLLConversion() {
        assertAlmostEquals(ecef, lla.toECEF(), "conversion from lla to ecef is wrong");
        assertAlmostEquals(lla, ecef.toLLA(), "conversion from ecef to lla is wrong");

        assertAlmostEquals(ecef, ll.toECEF(), "conversion from ll to ecef is wrong");
        assertAlmostEquals(ll, ecef.toLL(), "conversion from ecef to ll is wrong");
    }

    @ParameterizedTest
    @ArgumentsSource(DatumArgumentsProvider.class)
    public void testConversionInNorthPole(Datum datum) {
        ecef = new ECEF(
                Constants.ZERO_METERS,
                Constants.ZERO_METERS,
                datum.getSemiMinorAxis());
        lla = new LLA(
                Constants.NINETY_DEGREES,
                Constants.ZERO_DEGREES,
                Constants.ZERO_METERS);
        ll = new LL(
                Constants.NINETY_DEGREES,
                Constants.ZERO_DEGREES);
        checkLLAConversion(datum);
    }

    @ParameterizedTest
    @ArgumentsSource(DatumArgumentsProvider.class)
    public void testConversionInSouthPole(Datum datum) {
        ecef = new ECEF(
                Constants.ZERO_METERS,
                Constants.ZERO_METERS,
                datum.getSemiMinorAxis().negate());
        lla = new LLA(
                Constants.NINETY_DEGREES.negate(),
                Constants.ZERO_DEGREES,
                Constants.ZERO_METERS);
        ll = new LL(
                Constants.NINETY_DEGREES.negate(),
                Constants.ZERO_DEGREES);
        checkLLAConversion(datum);
    }

    @ParameterizedTest
    @ArgumentsSource(DatumArgumentsProvider.class)
    public void testConversionInZeroZero(Datum datum) {
        ecef = new ECEF(
                datum.getSemiMajorAxis(),
                Constants.ZERO_METERS,
                Constants.ZERO_METERS);
        lla = new LLA(
                Constants.ZERO_DEGREES,
                Constants.ZERO_DEGREES,
                Constants.ZERO_METERS);
        ll = new LL(
                Constants.ZERO_DEGREES,
                Constants.ZERO_DEGREES);
        checkLLAConversion(datum);
    }

    @ParameterizedTest
    @ArgumentsSource(DatumArgumentsProvider.class)
    public void testConversionInZeroNinety(Datum datum) {
        ecef = new ECEF(
                Constants.ZERO_METERS,
                datum.getSemiMajorAxis(),
                Constants.ZERO_METERS);
        lla = new LLA(
                Constants.ZERO_DEGREES,
                Constants.NINETY_DEGREES,
                Constants.ZERO_METERS);
        ll = new LL(
                Constants.ZERO_DEGREES,
                Constants.NINETY_DEGREES);
        checkLLAConversion(datum);
    }

    @ParameterizedTest
    @ArgumentsSource(DatumArgumentsProvider.class)
    public void testConversionInZeroOneHundredAndEighty(Datum datum) {
        ecef = new ECEF(
                datum.getSemiMajorAxis().negate(),
                Constants.ZERO_METERS,
                Constants.ZERO_METERS);
        lla = new LLA(
                Constants.ZERO_DEGREES,
                Constants.ONE_HUNDRED_AND_EIGHTY_DEGREES,
                Constants.ZERO_METERS);
        ll = new LL(
                Constants.ZERO_DEGREES,
                Constants.ONE_HUNDRED_AND_EIGHTY_DEGREES);
        checkLLAConversion(datum);
    }

    @ParameterizedTest
    @ArgumentsSource(DatumArgumentsProvider.class)
    public void testConversionInZeroMinusNinety(Datum datum) {
        ecef = new ECEF(
                Constants.ZERO_METERS,
                datum.getSemiMajorAxis().negate(),
                Constants.ZERO_METERS);
        lla = new LLA(
                Constants.ZERO_DEGREES,
                Constants.NINETY_DEGREES.negate(),
                Constants.ZERO_METERS);
        ll = new LL(
                Constants.ZERO_DEGREES,
                Constants.NINETY_DEGREES.negate());
        checkLLAConversion(datum);
    }

    @Test
    public void testConversionInWestWallTop() {
        ecef = new ECEF(
                Quantities.getQuantity(4432887.112838, Units.METER),
                Quantities.getQuantity(3131057.037713, Units.METER),
                Quantities.getQuantity(3339417.754266, Units.METER));
        lla = new LLA(
                Quantities.getQuantity(31.7767, Units.DEGREE),
                Quantities.getQuantity(35.2345, Units.DEGREE),
                Quantities.getQuantity(19 , Units.METER));
        ll = new LL(
                Quantities.getQuantity(31.7767, Units.DEGREE),
                Quantities.getQuantity(35.2345, Units.DEGREE));
        checkLLAConversion();
    }

    @Test
    public void testConversionInWestWallBottom() {
        ecef = new ECEF(
                Quantities.getQuantity(4432873.919896774, Units.METER),
                Quantities.getQuantity(3131047.7192151, Units.METER),
                Quantities.getQuantity(3339407.7486737226, Units.METER));
        lla = new LLA(
                Quantities.getQuantity(31.7767, Units.DEGREE),
                Quantities.getQuantity(35.2345, Units.DEGREE),
                Constants.ZERO_METERS);
        ll = new LL(
                Quantities.getQuantity(31.7767, Units.DEGREE),
                Quantities.getQuantity(35.2345, Units.DEGREE));
        checkLLConversion();
    }

    @Test
    public void testEmpireStateBuildingTop() {
        ecef = new ECEF(
                Quantities.getQuantity(1335028.253247, Units.METER),
                Quantities.getQuantity(4651415.040293, Units.METER),
                Quantities.getQuantity(4141585.182753, Units.METER));
        lla = new LLA(
                Quantities.getQuantity(40.7484, Units.DEGREE),
                Quantities.getQuantity(73.9857, Units.DEGREE),
                Quantities.getQuantity(443 , Units.METER));
        ll = new LL(
                Quantities.getQuantity(40.7484, Units.DEGREE),
                Quantities.getQuantity(73.9857, Units.DEGREE));
        checkLLAConversion();
    }

    @Test
    public void testEmpireStateBuildingBottom() {
        ecef = new ECEF(
                Quantities.getQuantity(1334935.666256073, Units.METER),
                Quantities.getQuantity(4651092.454965881, Units.METER),
                Quantities.getQuantity(4141296.0195546136, Units.METER));
        lla = new LLA(
                Quantities.getQuantity(40.7484, Units.DEGREE),
                Quantities.getQuantity(73.9857, Units.DEGREE),
                Constants.ZERO_METERS);
        ll = new LL(
                Quantities.getQuantity(40.7484, Units.DEGREE),
                Quantities.getQuantity(73.9857, Units.DEGREE));
        checkLLAConversion();
    }
}
