package org.galileo.position;

import org.galileo.datum.Datum;
import org.galileo.datum.Datums;
import org.galileo.internal.converters.ECEFToENUConverter;

import javax.measure.Quantity;
import javax.measure.quantity.Length;
import java.util.Objects;

public class ENU {

    private final Quantity<Length> east;
    private final Quantity<Length> north;
    private final Quantity<Length> up;

    public ENU(Quantity<Length> east, Quantity<Length> north, Quantity<Length> up) {
        this.east = east;
        this.north = north;
        this.up = up;
    }

    // Getters

    public Quantity<Length> getEast() {
        return east;
    }

    public Quantity<Length> getNorth() {
        return north;
    }

    public Quantity<Length> getUp() {
        return up;
    }


    // Conversions

    public LLA toLLA(Datum datum, LLA origin) {
        return toECEF(datum, origin).toLLA(datum);
    }

    public LLA toLLA(LLA origin) {
        return toLLA(Datums.DEFAULT_DATUM, origin);
    }

    public ECEF toECEF(Datum datum, LLA origin) {
        return ECEFToENUConverter.convertToECEF(this, datum, origin);
    }

    public ECEF toECEF(LLA origin) {
        return toECEF(Datums.DEFAULT_DATUM, origin);
    }

    // Hash and Equals

    @Override
    public int hashCode() {
        return Objects.hash(east, north, up);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        ENU other = (ENU) obj;
        return Objects.equals(east, other.east) &&
                Objects.equals(north, other.north) &&
                Objects.equals(up, other.up);
    }

    @Override
    public String toString() {
        return "ECEF{" +
                "east=" + east +
                ", north=" + north +
                ", up=" + up +
                '}';
    }
}
