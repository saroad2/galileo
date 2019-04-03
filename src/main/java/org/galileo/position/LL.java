package org.galileo.position;

import org.galileo.Constants;
import org.galileo.datum.Datum;
import org.galileo.datum.Datums;

import javax.measure.Quantity;
import javax.measure.quantity.Angle;
import java.util.Objects;

public class LL {

	private final Quantity<Angle> latitude;
	private final Quantity<Angle> longitude;

	public LL(Quantity<Angle> latitude, Quantity<Angle> longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}

	// Getters

	public Quantity<Angle> getLatitude() {
		return latitude;
	}

	public Quantity<Angle> getLongitude() {
		return longitude;
	}


	// Conversions

	public LLA toLLA() {
		return new LLA(getLatitude(), getLongitude(), Constants.ZERO_METERS);
	}

	public ECEF toECEF(Datum datum)
	{
		return toLLA().toECEF(datum);
	}

	public ECEF toECEF() {
		return toECEF(Datums.DEFAULT_DATUM);
	}

	public ENU toENU(Datum datum, LLA origin) {
		return toECEF(datum).toENU(datum, origin);
	}

	public ENU toENU(LLA origin) {
		return toENU(Datums.DEFAULT_DATUM, origin);
	}

	public NED toNED(Datum datum, LLA origin) {
		return toECEF(datum).toNED(datum, origin);
	}

	public NED toNED(LLA origin) {
		return toNED(Datums.DEFAULT_DATUM, origin);
	}

	// Hash and Equals

	@Override
	public int hashCode() {
		return Objects.hash(latitude, longitude);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		LL other = (LL) obj;
		return Objects.equals(latitude, other.latitude) &&
				Objects.equals(longitude, other.longitude);
	}

	@Override
	public String toString() {
		return "LLA{" +
				"latitude=" + latitude +
				", longitude=" + longitude +
				'}';
	}
}
