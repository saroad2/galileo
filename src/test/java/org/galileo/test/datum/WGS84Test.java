package org.galileo.test.datum;

import org.galileo.Units;
import org.galileo.datum.Datums;
import org.galileo.test.TestSuite;
import org.junit.jupiter.api.Test;
import tech.units.indriya.quantity.Quantities;

public class WGS84Test extends TestSuite {

    @Test
    public void testSemiMajorAxis()
    {
        assertQuantitiesEquals(
                "semi major axis is different than expected",
                Quantities.getQuantity(6378137.0, Units.METER),
                Datums.WGS84.getSemiMajorAxis());
    }

    @Test
    public void testSemiMinorAxis()
    {
        assertQuantitiesEquals(
                "semi minor axis is different than expected",
                Quantities.getQuantity(6356752.314245, Units.METER),
                Datums.WGS84.getSemiMinorAxis());
    }

    @Test
    public void testFlattening()
    {
        assertEquals(
                "flattening is different than expected",
                1/ 298.257223563,
                Datums.WGS84.getFlattening());

    }

    @Test
    public void testEccentricity()
    {
        assertEquals(
                "eccentricity is different than expected",
                0.0818191908426215,
                Datums.WGS84.getEccentricity());

    }
}
