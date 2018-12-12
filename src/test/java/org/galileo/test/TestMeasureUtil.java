package org.galileo.test;

import javax.measure.Quantity;
import javax.measure.quantity.Area;
import javax.measure.quantity.Length;

import org.galileo.internal.MeasureUtil;

import org.junit.jupiter.api.Test;
import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;

public class TestMeasureUtil extends TestSuite {

	@Test
	public void testSqrMeters() {
		assertQuantitiesEquals(
				"sqr returns wrong result",
				Quantities.getQuantity(9, Units.SQUARE_METRE),
				MeasureUtil.sqr(Quantities.getQuantity(3, Units.METRE)).asType(Area.class));
	}

	@Test
	public void testSqrtMeters() {
		assertQuantitiesEquals(
				"sqrt returns wrong result",
				Quantities.getQuantity(3, Units.METRE),
				MeasureUtil.sqrt(Quantities.getQuantity(9, Units.SQUARE_METRE)).asType(Length.class));
	}

	@Test
	public void testNorm() {
		Quantity<Length> x = Quantities.getQuantity(6, Units.METRE);
		Quantity<Length> y = Quantities.getQuantity(10, Units.METRE);
		Quantity<Length> z = Quantities.getQuantity(15, Units.METRE);
		assertQuantitiesEquals(
				"norm is different than expected",
				Quantities.getQuantity(19, Units.METRE),
				MeasureUtil.norm(x, y, z));
	}

}
