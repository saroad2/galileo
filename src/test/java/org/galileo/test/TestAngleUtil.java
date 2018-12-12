package org.galileo.test;

import org.galileo.Units;
import org.galileo.internal.AngleUtil;
import org.junit.jupiter.api.Test;
import tech.units.indriya.quantity.Quantities;

public class TestAngleUtil extends TestSuite{

    // Sin

    @Test
    public void testSinRadians() {
        assertEquals("sin of radians is different than expected",
                0.5,
                AngleUtil.sin(Quantities.getQuantity(Math.PI / 6, Units.RADIAN))
                );
    }

    @Test
    public void testSinDegrees() {
        assertEquals("sin of degrees is different than expected",
                0.5,
                AngleUtil.sin(Quantities.getQuantity(30, Units.DEGREE))
                );
    }

    @Test
    public void testSinRevolutions() {
        assertEquals("sin of revolution is different than expected",
                0.5,
                AngleUtil.sin(Quantities.getQuantity(1 / 12.0, Units.REVOLUTION))
                );
    }

    // Cos

    @Test
    public void testCosRadians() {
        assertEquals("cos of radians is different than expected",
                0.5,
                AngleUtil.cos(Quantities.getQuantity(Math.PI / 3, Units.RADIAN))
                );
    }

    @Test
    public void testCosDegrees() {
        assertEquals("cos of degrees is different than expected",
                0.5,
                AngleUtil.cos(Quantities.getQuantity(60, Units.DEGREE))
                );
    }

    @Test
    public void testCosRevolutions() {
        assertEquals("cos of revolution is different than expected",
                0.5,
                AngleUtil.cos(Quantities.getQuantity(1 / 6.0, Units.REVOLUTION))
                );
    }

    // Tan

    @Test
    public void testTanRadians() {
        assertEquals("tan of radians is different than expected",
                1,
                AngleUtil.tan(Quantities.getQuantity(Math.PI / 4, Units.RADIAN))
                );
    }

    @Test
    public void testTanDegrees() {
        assertEquals("tan of degrees is different than expected",
                1,
                AngleUtil.tan(Quantities.getQuantity(45, Units.DEGREE))
                );
    }

    @Test
    public void testTanRevolutions() {
        assertEquals("tan of revolution is different than expected",
                1,
                AngleUtil.tan(Quantities.getQuantity(1 / 8.0, Units.REVOLUTION))
                );
    }

    // Asin

    @Test
    public void testAsinRadians() {
        assertQuantitiesEquals(
                "asin as radians is different than expected",
                Quantities.getQuantity(Math.PI / 6, Units.RADIAN),
                AngleUtil.asin(0.5, Units.RADIAN)
        );
    }

    @Test
    public void testAsinDegrees() {
        assertQuantitiesEquals(
                "asin as degrees is different than expected",
                Quantities.getQuantity(30, Units.DEGREE),
                AngleUtil.asin(0.5, Units.DEGREE)
        );
    }

    @Test
    public void testAsinRevolutions() {
        assertQuantitiesEquals(
                "asin as revolutions is different than expected",
                Quantities.getQuantity(1 / 12.0, Units.REVOLUTION),
                AngleUtil.asin(0.5, Units.REVOLUTION)
        );
    }

    // Acos

    @Test
    public void testAcosRadians() {
        assertQuantitiesEquals(
                "acos as radians is different than expected",
                Quantities.getQuantity(Math.PI / 3, Units.RADIAN),
                AngleUtil.acos(0.5, Units.RADIAN)
        );
    }

    @Test
    public void testAcosDegrees() {
        assertQuantitiesEquals(
                "acos as degrees is different than expected",
                Quantities.getQuantity(60, Units.DEGREE),
                AngleUtil.acos(0.5, Units.DEGREE)
        );
    }

    @Test
    public void testAcosRevolutions() {
        assertQuantitiesEquals(
                "acos as revolutions is different than expected",
                Quantities.getQuantity(1 / 6.0, Units.REVOLUTION),
                AngleUtil.acos(0.5, Units.REVOLUTION)
        );
    }

    // Atan

    @Test
    public void testAtanRadians() {
        assertQuantitiesEquals(
                "atan as radians is different than expected",
                Quantities.getQuantity(Math.PI / 4, Units.RADIAN),
                AngleUtil.atan(1, Units.RADIAN)
        );
    }

    @Test
    public void testAtanDegrees() {
        assertQuantitiesEquals(
                "atan as degrees is different than expected",
                Quantities.getQuantity(45, Units.DEGREE),
                AngleUtil.atan(1, Units.DEGREE)
        );
    }

    @Test
    public void testAtanRevolutions() {
        assertQuantitiesEquals(
                "atan as revolutions is different than expected",
                Quantities.getQuantity(1 / 8.0, Units.REVOLUTION),
                AngleUtil.atan(1, Units.REVOLUTION)
        );
    }

    // Atan2 of Doubles

    @Test
    public void testDoublesAtan2Radians() {
        assertQuantitiesEquals(
                "atan2 of doubles as radians is different than expected",
                Quantities.getQuantity(Math.PI / 6, Units.RADIAN),
                AngleUtil.atan2(1, Math.sqrt(3), Units.RADIAN)
        );
    }

    @Test
    public void testDoublesAtan2Degrees() {
        assertQuantitiesEquals(
                "atan2 of doubles as degrees is different than expected",
                Quantities.getQuantity(30, Units.DEGREE),
                AngleUtil.atan2(1, Math.sqrt(3),Units.DEGREE)
        );
    }

    @Test
    public void testDoublesAtan2Revolutions() {
        assertQuantitiesEquals(
                "atan2 of doubles as revolutions is different than expected",
                Quantities.getQuantity(1 / 12.0, Units.REVOLUTION),
                AngleUtil.atan2(1, Math.sqrt(3), Units.REVOLUTION)
        );
    }

    // Atan2 of Quantities

    @Test
    public void testQuantitiesAtan2Radians() {
        assertQuantitiesEquals(
                "atan2 of quantities as radians is different than expected",
                Quantities.getQuantity(Math.PI / 6, Units.RADIAN),
                AngleUtil.atan2(
                        Quantities.getQuantity(1, Units.METER),
                        Quantities.getQuantity(Math.sqrt(3), Units.METER),
                        Units.RADIAN)
        );
    }

    @Test
    public void testQuantitiesAtan2Degrees() {
        assertQuantitiesEquals(
                "atan2 of quantities as degrees is different than expected",
                Quantities.getQuantity(30, Units.DEGREE),
                AngleUtil.atan2(
                        Quantities.getQuantity(1, Units.METER),
                        Quantities.getQuantity(Math.sqrt(3), Units.METER),
                        Units.DEGREE)
        );
    }

    @Test
    public void testQuantitiesAtan2Revolutions() {
        assertQuantitiesEquals(
                "atan2 of quantities as revolutions is different than expected",
                Quantities.getQuantity(1 / 12.0, Units.REVOLUTION),
                AngleUtil.atan2(
                        Quantities.getQuantity(1, Units.METER),
                        Quantities.getQuantity(Math.sqrt(3), Units.METER),
                        Units.REVOLUTION)
        );
    }
}
