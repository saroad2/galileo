package org.galileo.test.position;

import org.galileo.Units;
import org.galileo.position.ENU;
import org.galileo.test.TestSuite;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tech.units.indriya.quantity.Quantities;

import javax.measure.Quantity;
import javax.measure.quantity.Length;

public class ENUTest extends TestSuite {

	private Quantity<Length> createMeters(double value) {
		return Quantities.getQuantity(value, Units.METER);
	}

	@Test
	public void testConstructor() {
		Quantity<Length> east = createMeters(1045.1244);
		Quantity<Length> north = createMeters(12352.285);
		Quantity<Length> up = createMeters(1423.925);
		ENU enu = new ENU(east, north, up);
		assertQuantitiesEquals("east is different than expected", east, enu.getEast());
		assertQuantitiesEquals("north is different than expected", north, enu.getNorth());
		assertQuantitiesEquals("up is different than expected", up, enu.getUp());
	}

	@Test
	public void testEquals() {
		ENU point1 = new ENU(createMeters(1), createMeters(-2), createMeters(4));
		ENU point2 = new ENU(createMeters(1), createMeters(-2), createMeters(4));
		Assertions.assertEquals(point1, point2, "points 1 and 2 are different");
	}

	@Test
	public void testNotEqualsBecauseOfXCoordinate() {
		ENU point1 = new ENU(createMeters(1), createMeters(-2), createMeters(4));
		ENU point2 = new ENU(createMeters(3), createMeters(-2), createMeters(4));
		Assertions.assertNotEquals(point1, point2,"points 1 and 2 are the same");
	}

	@Test
	public void testNotEqualsBecauseOfYCoordinate() {
		ENU point1 = new ENU(createMeters(1), createMeters(-2), createMeters(4));
		ENU point2 = new ENU(createMeters(1), createMeters(2), createMeters(4));
		Assertions.assertNotEquals(point1, point2,"points 1 and 2 are the same");
	}

	@Test
	public void testNotEqualsBecauseOfZCoordinate() {
		ENU point1 = new ENU(createMeters(1), createMeters(-2), createMeters(4));
		ENU point2 = new ENU(createMeters(1), createMeters(-2), createMeters(3));
		Assertions.assertNotEquals(point1, point2,"points 1 and 2 are the same");
	}
}
