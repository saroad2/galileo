package org.galileo.test;

import org.galileo.position.ECEF;
import org.galileo.position.ENU;
import org.galileo.position.LLA;
import org.galileo.position.NED;
import org.galileo.test.internal.QuantityMatcher;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;

import javax.measure.Quantity;

import static org.hamcrest.Matchers.*;

public class TestSuite {

    private static final double EPSILON = 1e-9;

    public static void assertEquals(String message, double expected, double actual) {
        Assertions.assertEquals(expected, actual, EPSILON, message);
    }

    public static <Q extends Quantity<Q>>void assertQuantitiesEquals(
            String message,
            Quantity<Q> expected,
            Quantity<Q> actual) {
        MatcherAssert.assertThat(message, actual, quantitiesEqualsMatcher(expected));
    }

    public static <Q extends Quantity<Q>> void assertQuantitiesNotEquals(
            String message,
            Quantity<Q> expected,
            Quantity<Q> actual) {
        MatcherAssert.assertThat(message, actual, not(quantitiesEqualsMatcher(expected)));
    }

    public static <Q extends Quantity<Q>> Matcher<Quantity<Q>> quantitiesEqualsMatcher(
            Quantity<Q> expected) {

        return QuantityMatcher.getQuantityMatcher(expected);
    }

    public static void assertAlmostEquals(ECEF ecef1, ECEF ecef2, String message) {
        assertQuantitiesEquals(
                String.format("%s. X values are conflicting",message),
                ecef1.getX(),
                ecef2.getX());
        assertQuantitiesEquals(
                String.format("%s. Y values are conflicting",message),
                ecef1.getY(),
                ecef2.getY());
        assertQuantitiesEquals(
                String.format("%s. Z values are conflicting",message),
                ecef1.getZ(),
                ecef2.getZ());
    }

    public static void assertAlmostEquals(LLA lla1, LLA lla2, String message) {
        assertQuantitiesEquals(
                String.format("%s. Latitude values are conflicting", message),
                lla1.getLatitude(),
                lla2.getLatitude());
        assertQuantitiesEquals(
                String.format("%s. Longitude values are conflicting", message),
                lla1.getLongitude(),
                lla2.getLongitude());
        assertQuantitiesEquals(
                String.format("%s. Altitude values are conflicting", message),
                lla1.getAltitude(),
                lla2.getAltitude());
    }

    public static void assertAlmostEquals(ENU enu1, ENU enu2, String message) {
        assertQuantitiesEquals(
                String.format("%s. East values are conflicting",message),
                enu1.getEast(),
                enu2.getEast());
        assertQuantitiesEquals(
                String.format("%s. North values are conflicting",message),
                enu1.getNorth(),
                enu2.getNorth());
        assertQuantitiesEquals(
                String.format("%s. Up values are conflicting",message),
                enu1.getUp(),
                enu2.getUp());
    }


    public static void assertAlmostEquals(NED ned1, NED ned2, String message) {
        assertQuantitiesEquals(
                String.format("%s. North values are conflicting",message),
                ned1.getNorth(),
                ned2.getNorth());
        assertQuantitiesEquals(
                String.format("%s. East values are conflicting",message),
                ned1.getEast(),
                ned2.getEast());
        assertQuantitiesEquals(
                String.format("%s. Down values are conflicting",message),
                ned1.getDown(),
                ned2.getDown());
    }
}
