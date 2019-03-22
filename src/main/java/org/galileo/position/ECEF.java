package org.galileo.position;

import java.util.Objects;

import javax.measure.Quantity;
import javax.measure.quantity.Length;

import org.galileo.Constants;
import org.galileo.datum.Datum;
import org.galileo.datum.Datums;
import org.galileo.internal.converters.RelativeCoordinatesConverter;
import org.galileo.internal.converters.ECEFToLLAConverter;
import org.galileo.internal.MeasureUtil;

public class ECEF {

	public static final ECEF EARTH_CENTER = new ECEF(
			Constants.ZERO_METERS,
			Constants.ZERO_METERS,
			Constants.ZERO_METERS);

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

	public LLA toLLA() {
		return toLLA(Datums.DEFAULT_DATUM);
	}

	public ENU toENU(Datum datum, LLA origin) {
		return RelativeCoordinatesConverter.convertToENU(this, datum, origin);
	}

	public ENU toENU(LLA origin) {
		return toENU(Datums.DEFAULT_DATUM, origin);
	}

	public NED toNED(Datum datum, LLA origin) {
		return RelativeCoordinatesConverter.convertToNED(this, datum, origin);
	}

	public NED toNED(LLA origin) {
		return toNED(Datums.DEFAULT_DATUM, origin);
	}

	// Hash and Equals

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
