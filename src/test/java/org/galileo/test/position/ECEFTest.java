package org.galileo.test.position;

import javax.measure.Quantity;
import javax.measure.quantity.Length;

import org.galileo.Units;
import org.galileo.position.ECEF;
import org.galileo.test.TestSuite;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tech.units.indriya.quantity.Quantities;

public class ECEFTest extends TestSuite {

	private Quantity<Length> createMeters(double value) {
		return Quantities.getQuantity(value, Units.METER);
	}

	@Test
	public void testConstructor() {
		Quantity<Length> x = createMeters(1045.1244);
		Quantity<Length> y = createMeters(12352.285);
		Quantity<Length> z = createMeters(1423.925);
		ECEF ecef = new ECEF(x, y, z);
		assertQuantitiesEquals("x is different than expected", x, ecef.getX());
		assertQuantitiesEquals("y is different than expected", y, ecef.getY());
		assertQuantitiesEquals("z is different than expected", z, ecef.getZ());
	}

	@Test
	public void testDistanceToEarthCenter() {
		Quantity<Length> x = createMeters(6);
		Quantity<Length> y = createMeters(10);
		Quantity<Length> z = createMeters(15);
		Quantity<Length> distanceToEarthCenter = new ECEF(x, y, z).distanceToEarthCenter();
		assertQuantitiesEquals("distance to earth center is different than expected", createMeters(19), distanceToEarthCenter);
	}

	@Test
	public void testDirectDistanceTo() {
		ECEF point1 = new ECEF(createMeters(1), createMeters(-2), createMeters(4));
		ECEF point2 = new ECEF(createMeters(7), createMeters(8), createMeters(19));
		Quantity<Length> distance = point1.directDistanceTo(point2);
		assertQuantitiesEquals("distance between 2 points is different than expected", createMeters(19), distance);
	}

	@Test
	public void testEquals() {
		ECEF point1 = new ECEF(createMeters(1), createMeters(-2), createMeters(4));
		ECEF point2 = new ECEF(createMeters(1), createMeters(-2), createMeters(4));
		Assertions.assertEquals(point1, point2, "points 1 and 2 are different");
	}

	@Test
	public void testNotEqualsBecauseOfXCoordinate() {
		ECEF point1 = new ECEF(createMeters(1), createMeters(-2), createMeters(4));
		ECEF point2 = new ECEF(createMeters(3), createMeters(-2), createMeters(4));
		Assertions.assertNotEquals(point1, point2,"points 1 and 2 are the same");
	}

	@Test
	public void testNotEqualsBecauseOfYCoordinate() {
		ECEF point1 = new ECEF(createMeters(1), createMeters(-2), createMeters(4));
		ECEF point2 = new ECEF(createMeters(1), createMeters(2), createMeters(4));
		Assertions.assertNotEquals(point1, point2,"points 1 and 2 are the same");
	}

	@Test
	public void testNotEqualsBecauseOfZCoordinate() {
		ECEF point1 = new ECEF(createMeters(1), createMeters(-2), createMeters(4));
		ECEF point2 = new ECEF(createMeters(1), createMeters(-2), createMeters(3));
		Assertions.assertNotEquals(point1, point2,"points 1 and 2 are the same");
	}
}
