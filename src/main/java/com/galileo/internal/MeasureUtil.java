package com.galileo.internal;

import javax.measure.Quantity;
import javax.measure.Unit;
import javax.measure.quantity.Area;
import javax.measure.quantity.Length;

import tec.units.ri.quantity.Quantities;

public final class MeasureUtil {
	private MeasureUtil() {

	}

	public static Quantity<Length> norm(Quantity<Length> x, Quantity<Length> y, Quantity<Length> z) {
		return sqrt(sqr(x).asType(Area.class).add(sqr(y).asType(Area.class)).add(sqr(z).asType(Area.class)))
				.asType(Length.class);
	}

	public static <T extends Quantity<T>> Quantity<?> sqr(Quantity<T> value) {
		return value.multiply(value);
	}

	public static <T extends Quantity<T>> Quantity<?> sqrt(Quantity<T> squaredDistance) {
		double normValue = Math.sqrt(squaredDistance.getValue().doubleValue());
		Unit<?> distanceUnit = squaredDistance.getUnit().root(2);
		return Quantities.getQuantity(normValue, distanceUnit);
	}
}
