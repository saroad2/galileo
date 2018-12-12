package org.galileo.test.position;

import javax.measure.Quantity;
import javax.measure.quantity.Angle;
import javax.measure.quantity.Length;

import org.galileo.Units;
import org.galileo.test.TestSuite;

import org.galileo.position.LLA;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tech.units.indriya.quantity.Quantities;

public class LLATest extends TestSuite {

	private Quantity<Length> createMeters(double value) {
		return Quantities.getQuantity(value, Units.METER);
	}

	private Quantity<Angle> createDegrees(double value) {
		return Quantities.getQuantity(value, Units.DEGREE);
	}

	@Test
	public void testConstructor() {
		Quantity<Angle> latitude = createDegrees(1045.1244);
		Quantity<Angle> longitude = createDegrees(12352.285);
		Quantity<Length> altitude = createMeters(1423.925);
		LLA lla = new LLA(latitude, longitude, altitude);
		assertQuantitiesEquals("latitude is different than expected", latitude, lla.getLatitude());
		assertQuantitiesEquals("longitude is different than expected", longitude, lla.getLongitude());
		assertQuantitiesEquals("altitude is different than expected", altitude, lla.getAltitude());
	}

	@Test
	public void testEquals() {
		LLA point1 = new LLA(createDegrees(1), createDegrees(-2), createMeters(4));
		LLA point2 = new LLA(createDegrees(1), createDegrees(-2), createMeters(4));
		Assertions.assertEquals(point1, point2,"points 1 and 2 are different");
	}

	@Test
	public void testNotEqualsBecauseOfLatitude() {
		LLA point1 = new LLA(createDegrees(1), createDegrees(-2), createMeters(4));
		LLA point2 = new LLA(createDegrees(3), createDegrees(-2), createMeters(4));
		Assertions.assertNotEquals(point1, point2, "points 1 and 2 are the same");
	}

	@Test
	public void testNotEqualsBecauseOfLongitude() {
		LLA point1 = new LLA(createDegrees(1), createDegrees(-2), createMeters(4));
		LLA point2 = new LLA(createDegrees(1), createDegrees(2), createMeters(4));
		Assertions.assertNotEquals(point1, point2, "points 1 and 2 are the same");
	}

	@Test
	public void testNotEqualsBecauseOfAltitude() {
		LLA point1 = new LLA(createDegrees(1), createDegrees(-2), createMeters(4));
		LLA point2 = new LLA(createDegrees(1), createDegrees(-2), createMeters(3));
		Assertions.assertNotEquals(point1, point2, "points 1 and 2 are the same");
	}
}
