package org.galileo.test.position.conversions;

import org.apache.commons.math3.distribution.ExponentialDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.galileo.Constants;
import org.galileo.Units;
import org.galileo.position.LL;
import org.galileo.position.LLA;
import org.galileo.test.TestSuite;
import org.junit.jupiter.api.Test;
import tech.units.indriya.quantity.Quantities;

public class LLToLLAConversionsTest extends TestSuite {

    private static double MEAN_HEIGHT_IN_METERS = 3.0;
    private static double STANDARD_DERIVATION_ANGLE_IN_DEGREES = 30.0;

    private static final ExponentialDistribution RANDOM_HEIGHT = new ExponentialDistribution(
            MEAN_HEIGHT_IN_METERS);
    private static final NormalDistribution RANDOM_DEGREE = new NormalDistribution(
            0,
            STANDARD_DERIVATION_ANGLE_IN_DEGREES);

    @Test
    public void testLLToLLA() {
        double longitude = RANDOM_DEGREE.sample();
        double latitude = RANDOM_DEGREE.sample();
        LLA lla = new LLA(
                Quantities.getQuantity(latitude, Units.DEGREE),
                Quantities.getQuantity(longitude, Units.DEGREE),
                Constants.ZERO_METERS);
        LL ll = new LL(
                Quantities.getQuantity(latitude, Units.DEGREE),
                Quantities.getQuantity(longitude, Units.DEGREE));
        assertAlmostEquals(ll, lla.toLL(), "conversion from lla to ll is incorrect");
        assertAlmostEquals(lla, ll.toLLA(), "conversion from ll to lla is incorrect");
    }

    @Test
    public void testLLAToLL() {
        double longitude = RANDOM_DEGREE.sample();
        double latitude = RANDOM_DEGREE.sample();
        double height = RANDOM_HEIGHT.sample();
        LLA lla = new LLA(
                Quantities.getQuantity(latitude, Units.DEGREE),
                Quantities.getQuantity(longitude, Units.DEGREE),
                Quantities.getQuantity(height, Units.METER));
        LL ll = new LL(
                Quantities.getQuantity(latitude, Units.DEGREE),
                Quantities.getQuantity(longitude, Units.DEGREE));
        assertAlmostEquals(ll, lla.toLL(), "conversion from lla to ll is incorrect");
    }
}
