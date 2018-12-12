package org.galileo.test.datum;

import org.galileo.Units;
import org.galileo.datum.Datums;
import org.galileo.test.TestSuite;
import org.junit.jupiter.api.Test;
import tech.units.indriya.quantity.Quantities;

public class ED50Test extends TestSuite {

    @Test
    public void testSemiMajorAxis()
    {
        assertQuantitiesEquals(
                "semi major axis is different than expected",
                Quantities.getQuantity(6378.388, Units.METER),
                Datums.ED50.getSemiMajorAxis());
    }

    @Test
    public void testSemiMinorAxis()
    {
        assertQuantitiesEquals(
                "semi minor axis is different than expected",
                Quantities.getQuantity(6356.911946127946, Units.METER),
                Datums.ED50.getSemiMinorAxis());
    }

    @Test
    public void testFlattening()
    {
        assertEquals(
                "flattening is different than expected",
                1/ 297.0,
                Datums.ED50.getFlattening());

    }

    @Test
    public void testEccentricity()
    {
        assertEquals(
                "eccentricity is different than expected",
                0.08199188997902958,
                Datums.ED50.getEccentricity());

    }
}
