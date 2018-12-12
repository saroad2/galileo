package org.galileo.position;

import java.util.Objects;

import javax.measure.Quantity;
import javax.measure.quantity.Length;

import org.galileo.datum.Datum;
import org.galileo.internal.ECEFToLLAConverter;
import org.galileo.internal.MeasureUtil;
import tech.units.indriya.quantity.Quantities;
import tech.units.indriya.unit.Units;

public class ECEF {

	public static final ECEF EARTH_CENTER = new ECEF(
			Quantities.getQuantity(0, Units.METRE),
			Quantities.getQuantity(0, Units.METRE),
			Quantities.getQuantity(0, Units.METRE));

	private final Quantity<Length> x;
	private final Quantity<Length> y;
	private final Quantity<Length> z;

	public ECEF(Quantity<Length> x, Quantity<Length> y, Quantity<Length> z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	// Getters

	public Quantity<Length> getX() {
		return x;
	}

	public Quantity<Length> getY() {
		return y;
	}

	public Quantity<Length> getZ() {
		return z;
	}

	// Distances

	public Quantity<Length> directDistanceTo(ECEF ecef) {
		return MeasureUtil.norm(x.subtract(ecef.x), y.subtract(ecef.y), z.subtract(ecef.z));
	}

	public Quantity<Length> distanceToEarthCenter() {
		return MeasureUtil.norm(x, y, z);
	}


	// Conversions

	public LLA toLLA(Datum datum) {
		return ECEFToLLAConverter.convert(this, datum);
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y, z);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		ECEF other = (ECEF) obj;
		return Objects.equals(x, other.x) && Objects.equals(y, other.y) && Objects.equals(z, other.z);
	}

	@Override
	public String toString() {
		return "ECEF{" +
				"x=" + x +
				", y=" + y +
				", z=" + z +
				'}';
	}
}
