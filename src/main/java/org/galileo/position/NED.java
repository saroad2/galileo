package org.galileo.position;

import org.galileo.datum.Datum;
import org.galileo.datum.Datums;
import org.galileo.internal.converters.RelativeCoordinatesConverter;

import javax.measure.Quantity;
import javax.measure.quantity.Length;
import java.util.Objects;

public class NED {

    private final Quantity<Length> north;
    private final Quantity<Length> east;
    private final Quantity<Length> down;

    public NED(Quantity<Length> north, Quantity<Length> east, Quantity<Length> down) {
        this.east = east;
        this.north = north;
        this.down = down;
    }

    // Getters

    public Quantity<Length> getNorth() {
        return north;
    }

    public Quantity<Length> getEast() {
        return east;
    }

    public Quantity<Length> getDown() {
        return down;
    }


    // Conversions

    public LLA toLLA(Datum datum, LLA origin) {
        return toECEF(datum, origin).toLLA(datum);
    }

    public LLA toLLA(LLA origin) {
        return toLLA(Datums.DEFAULT_DATUM, origin);
    }

    public ECEF toECEF(Datum datum, LLA origin) {
        return RelativeCoordinatesConverter.convertToECEF(this, datum, origin);
    }

    public ECEF toECEF(LLA origin) {
        return toECEF(Datums.DEFAULT_DATUM, origin);
    }

    public ENU toENU() {
        return new ENU(east, north, down.negate());
    }

    // Hash and Equals

    @Override
    public int hashCode() {
        return Objects.hash(north, east, down);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        NED other = (NED) obj;
        return Objects.equals(north, other.north) &&
                Objects.equals(east, other.east) &&
                Objects.equals(down, other.down);
    }

    @Override
    public String toString() {
        return "ECEF{" +
                "north=" + north +
                ", east=" + east +
                ", down=" + down +
                '}';
    }
}
