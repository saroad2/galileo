package org.galileo.test.datum;

import org.galileo.Units;
import org.galileo.datum.Datums;
import org.galileo.test.TestSuite;
import org.junit.jupiter.api.Test;
import tech.units.indriya.quantity.Quantities;

public class GRS80Test extends TestSuite {

    @Test
    public void testSemiMajorAxis()
    {
        assertQuantitiesEquals(
                "semi major axis is different than expected",
                Quantities.getQuantity(6378137.0, Units.METER),
                Datums.GRS80.getSemiMajorAxis());
    }

    @Test
    public void testSemiMinorAxis()
    {
        assertQuantitiesEquals(
                "semi minor axis is different than expected",
                Quantities.getQuantity(6356752.314140356, Units.METER),
                Datums.GRS80.getSemiMinorAxis());
    }

    @Test
    public void testFlattening()
    {
        assertEquals(
                "flattening is different than expected",
                1/ 298.257222101,
                Datums.GRS80.getFlattening());

    }

    @Test
    public void testEccentricity()
    {
        assertEquals(
                "eccentricity is different than expected",
                0.0818191908426215,
                Datums.GRS80.getEccentricity());

    }
}
