package org.galileo.internal.converters;

import static org.galileo.internal.MathUtil.sqr;
import static org.galileo.internal.MathUtil.cube;
import static org.galileo.internal.MathUtil.cubicRoot;
import static org.galileo.internal.MeasureUtil.norm;
import static org.galileo.internal.MeasureUtil.ratio;
import static java.lang.Math.sqrt;

import org.galileo.Constants;
import org.galileo.Units;
import org.galileo.datum.Datum;
import org.galileo.internal.AngleUtil;
import org.galileo.internal.MeasureUtil;
import org.galileo.position.ECEF;
import org.galileo.position.LLA;

import javax.measure.Quantity;
import javax.measure.quantity.Angle;
import javax.measure.quantity.Length;

public final class ECEFToLLAConverter {

    private ECEFToLLAConverter() {

    }

    /**
     * based on the formula from:
     * https://en.wikipedia.org/wiki/Geographic_coordinate_conversion#From_ECEF_to_geodetic_coordinates
     * */
    public static LLA convert(ECEF ecef, Datum datum) {
        double zByASquared = sqr(ratio(ecef.getZ(), datum.getSemiMajorAxis()));
        double eccentricitySquared = sqr(datum.getEccentricity());
        double etta = (1 - eccentricitySquared) * zByASquared;
        Quantity<Length> radius2D = MeasureUtil.norm(ecef.getX(), ecef.getY());
        double radius2DByASquared = sqr(ratio(radius2D, datum.getSemiMajorAxis()));
        double eccentricityPow4 = sqr(eccentricitySquared);
        double s = eccentricityPow4 * etta * radius2DByASquared / 4;
        double rho = (radius2DByASquared + etta - eccentricityPow4) / 6;
        double rhoCubed = cube(rho);
        double t = cubicRoot(rhoCubed + s + sqrt(s * (s + 2 * rhoCubed)));
        double u = rho + t + sqr(rho) / t;
        double v = sqrt(sqr(u) + eccentricityPow4 * etta);
        double w = eccentricitySquared * (u + v - etta) / (2 * v);
        double k = 1 + eccentricitySquared * (sqrt(u + v + sqr(w)) + w) / (u + v);
        double k0 = 1 / (1 - eccentricitySquared);
        Quantity<Length> height = norm(ecef.getZ().multiply(k), radius2D)
                .divide(eccentricitySquared)
                .multiply(1 / k - 1 / k0);
        Quantity<Angle> latitude = calculateLatitude(ecef.getZ(), radius2D, k);
        Quantity<Angle> longitude = AngleUtil.atan2(ecef.getY(), ecef.getX(), Units.DEGREE);
        return new LLA(latitude, longitude, height);
    }

    private static Quantity<Angle> calculateLatitude(Quantity<Length> z, Quantity<Length> radius2D, double k) {
        if (radius2D.getValue().doubleValue() != 0) {
            return AngleUtil.atan(ratio(z, radius2D) * k, Units.DEGREE);
        }
        if (z.getValue().doubleValue() > 0) {
            return Constants.NINETY_DEGREES;
        }
        return Constants.NINETY_DEGREES.negate();
    }
}
