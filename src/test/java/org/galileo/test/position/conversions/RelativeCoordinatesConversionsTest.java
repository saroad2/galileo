package org.galileo.test.position.conversions;

import org.galileo.Constants;
import org.galileo.Units;
import org.galileo.datum.Datum;
import org.galileo.datum.Datums;
import org.galileo.position.*;
import org.galileo.test.DatumArgumentsProvider;
import org.galileo.test.TestSuite;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import tech.units.indriya.quantity.Quantities;

import static org.galileo.internal.MeasureUtil.zero;

public class RelativeCoordinatesConversionsTest extends TestSuite {

    private LL ll;
    private LLA lla;
    private ECEF ecef;
    private ENU enu;
    private NED ned;
    private LLA origin;

    public void checkConversion(Datum datum) {
        assertAlmostEquals(enu, ll.toENU(datum, origin), "conversion from ll to enu is wrong");
        assertAlmostEquals(ll, enu.toLL(datum, origin), "conversion from enu to ll is wrong");

        assertAlmostEquals(enu, lla.toENU(datum, origin), "conversion from lla to enu is wrong");
        assertAlmostEquals(lla, enu.toLLA(datum, origin), "conversion from enu to lla is wrong");

        assertAlmostEquals(enu, ecef.toENU(datum, origin),"conversion from ecef to enu is wrong");
        assertAlmostEquals(ecef, enu.toECEF(datum, origin),"conversion from enu to ecef is wrong");

        assertAlmostEquals(ned, ll.toNED(datum, origin), "conversion from ll to ned is wrong");
        assertAlmostEquals(ll, ned.toLL(datum, origin), "conversion from ned to ll is wrong");

        assertAlmostEquals(ned, lla.toNED(datum, origin), "conversion from lla to ned is wrong");
        assertAlmostEquals(lla, ned.toLLA(datum, origin), "conversion from ned to lla is wrong");

        assertAlmostEquals(ned, ecef.toNED(datum, origin),"conversion from ecef to ned is wrong");
        assertAlmostEquals(ecef, ned.toECEF(datum, origin),"conversion from ned to ecef is wrong");
    }

    @ParameterizedTest
    @ArgumentsSource(DatumArgumentsProvider.class)
    public void testConversionInNorthPoleOriginInEquator(Datum datum) {
        ll = new LL(
                Constants.NINETY_DEGREES,
                Constants.ZERO_DEGREES);
        lla = new LLA(
                Constants.NINETY_DEGREES,
                Constants.ZERO_DEGREES,
                Constants.ZERO_METERS);
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
        ned = new NED(
                datum.getSemiMinorAxis(),
                Constants.ZERO_METERS,
                datum.getSemiMajorAxis()
        );
        checkConversion(datum);
    }

    @ParameterizedTest
    @ArgumentsSource(DatumArgumentsProvider.class)
    public void testConversionInSouthPoleOriginInEquator(Datum datum) {
        ll = new LL(
                Constants.NINETY_DEGREES.negate(),
                Constants.ZERO_DEGREES);
        lla = new LLA(
                Constants.NINETY_DEGREES.negate(),
                Constants.ZERO_DEGREES,
                Constants.ZERO_METERS);
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
        ned = new NED(
                datum.getSemiMinorAxis().negate(),
                Constants.ZERO_METERS,
                datum.getSemiMajorAxis()
        );
        checkConversion(datum);
    }

    @ParameterizedTest
    @ArgumentsSource(DatumArgumentsProvider.class)
    public void testConversionWhenOriginIsInPrimeMeridianAndECEFInEast(Datum datum) {
        ll = new LL(
                Constants.ZERO_DEGREES,
                Constants.NINETY_DEGREES);
        lla = new LLA(
                Constants.ZERO_DEGREES,
                Constants.NINETY_DEGREES,
                Constants.ZERO_METERS);
        ecef = new ECEF(
                Constants.ZERO_METERS,
                datum.getSemiMajorAxis(),
                Constants.ZERO_METERS
        );
        origin = new LLA(
                Constants.ZERO_DEGREES,
                Constants.ZERO_DEGREES,
                Constants.ZERO_METERS);
        enu = new ENU(
                datum.getSemiMajorAxis(),
                Constants.ZERO_METERS,
                datum.getSemiMajorAxis().negate()
        );
        ned = new NED(
                Constants.ZERO_METERS,
                datum.getSemiMajorAxis(),
                datum.getSemiMajorAxis()
        );
        checkConversion(datum);
    }

    @ParameterizedTest
    @ArgumentsSource(DatumArgumentsProvider.class)
    public void testConversionWhenOriginIsInPrimeMeridianAndECEFInWest(Datum datum) {
        ll = new LL(
                Constants.ZERO_DEGREES,
                Constants.NINETY_DEGREES.negate());
        lla = new LLA(
                Constants.ZERO_DEGREES,
                Constants.NINETY_DEGREES.negate(),
                Constants.ZERO_METERS);
        ecef = new ECEF(
                Constants.ZERO_METERS,
                datum.getSemiMajorAxis().negate(),
                Constants.ZERO_METERS
        );
        origin = new LLA(
                Constants.ZERO_DEGREES,
                Constants.ZERO_DEGREES,
                Constants.ZERO_METERS);
        enu = new ENU(
                datum.getSemiMajorAxis().negate(),
                Constants.ZERO_METERS,
                datum.getSemiMajorAxis().negate()
        );
        ned = new NED(
                Constants.ZERO_METERS,
                datum.getSemiMajorAxis().negate(),
                datum.getSemiMajorAxis()
        );
        checkConversion(datum);
    }

    @ParameterizedTest
    @ArgumentsSource(DatumArgumentsProvider.class)
    public void testConversionWhenOriginIsInPrimeMeridianAndECEFInOtherSide(Datum datum) {
        ll = new LL(
                Constants.ZERO_DEGREES,
                Constants.ONE_HUNDRED_AND_EIGHTY_DEGREES);
        lla = new LLA(
                Constants.ZERO_DEGREES,
                Constants.ONE_HUNDRED_AND_EIGHTY_DEGREES,
                Constants.ZERO_METERS);
        ecef = new ECEF(
                datum.getSemiMajorAxis().negate(),
                Constants.ZERO_METERS,
                Constants.ZERO_METERS
        );
        origin = new LLA(
                Constants.ZERO_DEGREES,
                Constants.ZERO_DEGREES,
                Constants.ZERO_METERS);
        enu = new ENU(
                Constants.ZERO_METERS,
                Constants.ZERO_METERS,
                datum.getSemiMajorAxis().multiply(-2)
        );
        ned = new NED(
                Constants.ZERO_METERS,
                Constants.ZERO_METERS,
                datum.getSemiMajorAxis().multiply(2)
        );
        checkConversion(datum);
    }

}
