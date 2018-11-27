package org.galileo.test;

import static org.junit.Assert.assertEquals;

import javax.measure.Quantity;
import javax.measure.quantity.Length;

import org.junit.Test;

import com.galileo.position.ECEF;

import tec.units.ri.quantity.Quantities;
import tec.units.ri.unit.Units;

public class ECEFTest {

	private Quantity<Length> createMeters(double value) {
		return Quantities.getQuantity(value, Units.METRE);
	}

	@Test
	public void testConstructor() {
		Quantity<Length> x = createMeters(1045.1244);
		Quantity<Length> y = createMeters(12352.285);
		Quantity<Length> z = createMeters(1423.925);
		ECEF ecef = new ECEF(x, y, z);
		assertEquals("x is different than expected", x, ecef.getX());
		assertEquals("y is different than expected", y, ecef.getY());
		assertEquals("z is different than expected", z, ecef.getZ());
	}

	@Test
	public void testDistanceToEarthCenter() {
		Quantity<Length> x = createMeters(6);
		Quantity<Length> y = createMeters(10);
		Quantity<Length> z = createMeters(15);
		Quantity<Length> distanceToEarthCenter = new ECEF(x, y, z).distanceToEarthCenter();
		assertEquals("distance to earth center is different than expected", createMeters(19), distanceToEarthCenter);
	}

	@Test
	public void testDirectDistanceTo() {
		ECEF point1 = new ECEF(createMeters(1), createMeters(-2), createMeters(4));
		ECEF point2 = new ECEF(createMeters(7), createMeters(8), createMeters(19));
		Quantity<Length> distance = point1.directDistanceTo(point2);
		assertEquals("distance to earth center is different than expected", createMeters(19), distance);
	}
}
