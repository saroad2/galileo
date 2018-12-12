package org.galileo.test;

import org.galileo.position.ECEF;
import org.galileo.position.LLA;
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
}
