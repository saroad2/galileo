package org.galileo.internal;

import tech.units.indriya.quantity.Quantities;

import javax.measure.Quantity;
import javax.measure.Unit;
import javax.measure.quantity.Area;
import javax.measure.quantity.Dimensionless;
import javax.measure.quantity.Length;

public final class MeasureUtil {
	private MeasureUtil() {

	}

	public static Quantity<Length> norm(Quantity<Length> x, Quantity<Length> y) {
		return sqrt(sqr(x, Area.class).add(sqr(y, Area.class))).asType(Length.class);
	}

	public static Quantity<Length> norm(Quantity<Length> x, Quantity<Length> y, Quantity<Length> z) {
		return sqrt(sqr(x, Area.class).add(sqr(y, Area.class)).add(sqr(z, Area.class)))
				.asType(Length.class);
	}

	public static <T extends Quantity<T>, U extends Quantity<U>> Quantity<U> sqr(
			Quantity<T> value,
			Class<U> clazz) {
		return sqr(value).asType(clazz);
	}

	public static <T extends Quantity<T>> Quantity<?> sqr(Quantity<T> value) {
		return value.multiply(value);
	}

	public static <T extends Quantity<T>> Quantity<?> sqrt(Quantity<T> squaredDistance) {
		double normValue = Math.sqrt(squaredDistance.getValue().doubleValue());
		Unit<?> distanceUnit = squaredDistance.getUnit().root(2);
		return Quantities.getQuantity(normValue, distanceUnit);
	}

	public static <T extends Quantity<T>> double ratio(
			Quantity<T> a,
			Quantity<T> b) {
		return a.divide(b).asType(Dimensionless.class).getValue().doubleValue();
	}

	public static <Q extends Quantity<Q>> Quantity<Q> zero(Unit<Q> unit) {
		return Quantities.getQuantity(0, unit);
	}
}
