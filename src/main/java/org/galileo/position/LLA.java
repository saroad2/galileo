package org.galileo.position;

import org.galileo.datum.Datum;
import org.galileo.datum.Datums;
import org.galileo.internal.AngleUtil;

import java.util.Objects;

import javax.measure.Quantity;
import javax.measure.quantity.Angle;
import javax.measure.quantity.Length;

public class LLA {

	private final Quantity<Angle> latitude;
	private final Quantity<Angle> longitude;
	private final Quantity<Length> altitude;

	public LLA(Quantity<Angle> latitude, Quantity<Angle> longitude, Quantity<Length> altitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude = altitude;
	}

	// Getters

	public Quantity<Angle> getLatitude() {
		return latitude;
	}

	public Quantity<Angle> getLongitude() {
		return longitude;
	}

	public Quantity<Length> getAltitude() {
		return altitude;
	}

	// Conversions

	public ECEF toECEF(Datum datum)
	{
		Quantity<Length> radiusAtLatitude = datum.radiusAtLatitude(latitude);
		double cosLatitude = AngleUtil.cos(latitude);
		return new ECEF(
				altitude.add(radiusAtLatitude).multiply(cosLatitude).multiply(AngleUtil.cos(longitude)),
				altitude.add(radiusAtLatitude).multiply(cosLatitude).multiply(AngleUtil.sin(longitude)),
				radiusAtLatitude.multiply(Math.pow(1-datum.getFlattening(),2)).add(altitude).multiply(AngleUtil.sin(latitude)));
	}

	public ECEF toECEF() {
		return toECEF(Datums.DEFAULT_DATUM);
	}

	// Hash and Equals

	@Override
	public int hashCode() {
		return Objects.hash(latitude, longitude, altitude);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		LLA other = (LLA) obj;
		return Objects.equals(latitude, other.latitude) &&
				Objects.equals(longitude, other.longitude) &&
				Objects.equals(altitude, other.altitude);
	}

	@Override
	public String toString() {
		return "LLA{" +
				"latitude=" + latitude +
				", longitude=" + longitude +
				", altitude=" + altitude +
				'}';
	}
}
