package org.galileo.test.position;

import org.galileo.Units;
import org.galileo.position.NED;
import org.galileo.test.TestSuite;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tech.units.indriya.quantity.Quantities;

import javax.measure.Quantity;
import javax.measure.quantity.Length;

public class NEDTest extends TestSuite {

	private Quantity<Length> createMeters(double value) {
		return Quantities.getQuantity(value, Units.METER);
	}

	@Test
	public void testConstructor() {
		Quantity<Length> north = createMeters(1045.1244);
		Quantity<Length> east = createMeters(12352.285);
		Quantity<Length> down = createMeters(1423.925);
		NED enu = new NED(north, east, down);
		assertQuantitiesEquals("north is different than expected", north, enu.getNorth());
		assertQuantitiesEquals("east is different than expected", east, enu.getEast());
		assertQuantitiesEquals("down is different than expected", down, enu.getDown());
	}

	@Test
	public void testEquals() {
		NED point1 = new NED(createMeters(1), createMeters(-2), createMeters(4));
		NED point2 = new NED(createMeters(1), createMeters(-2), createMeters(4));
		Assertions.assertEquals(point1, point2, "points 1 and 2 are different");
	}

	@Test
	public void testNotEqualsBecauseOfXCoordinate() {
		NED point1 = new NED(createMeters(1), createMeters(-2), createMeters(4));
		NED point2 = new NED(createMeters(3), createMeters(-2), createMeters(4));
		Assertions.assertNotEquals(point1, point2,"points 1 and 2 are the same");
	}

	@Test
	public void testNotEqualsBecauseOfYCoordinate() {
		NED point1 = new NED(createMeters(1), createMeters(-2), createMeters(4));
		NED point2 = new NED(createMeters(1), createMeters(2), createMeters(4));
		Assertions.assertNotEquals(point1, point2,"points 1 and 2 are the same");
	}

	@Test
	public void testNotEqualsBecauseOfZCoordinate() {
		NED point1 = new NED(createMeters(1), createMeters(-2), createMeters(4));
		NED point2 = new NED(createMeters(1), createMeters(-2), createMeters(3));
		Assertions.assertNotEquals(point1, point2,"points 1 and 2 are the same");
	}
}
