package org.galileo.internal.converters;

import static org.galileo.internal.LinearAlgebraUtil.asVector;
import static org.galileo.internal.LinearAlgebraUtil.asENU;
import static org.galileo.internal.LinearAlgebraUtil.asECEF;

import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.galileo.datum.Datum;
import org.galileo.internal.AngleUtil;
import org.galileo.position.ECEF;
import org.galileo.position.ENU;
import org.galileo.position.LLA;
import org.galileo.position.NED;

import javax.measure.Unit;
import javax.measure.quantity.Length;

public final class RelativeCoordinatesConverter {

    private RelativeCoordinatesConverter() {

    }

    public static ENU convertToENU(ECEF ecef, Datum datum, LLA origin) {
        Unit<Length> unit = ecef.getX().getUnit();
        RealVector originAsECEFVector = asVector(origin.toECEF(datum), unit);
        RealMatrix conversionMatrix = getENUConversionMatrix(origin);
        RealVector conversionVector = asVector(ecef, unit)
                .subtract(originAsECEFVector);
        RealVector enuAsVector = new LUDecomposition(conversionMatrix.transpose())
                .getSolver()
                .solve(conversionVector);
        return asENU(enuAsVector, unit);
    }

    public static NED convertToNED(ECEF ecef, Datum datum, LLA origin) {
        return convertToENU(ecef, datum, origin).toNED();
    }

    public static ECEF convertToECEF(ENU enu, Datum datum, LLA origin) {
        Unit<Length> unit = enu.getEast().getUnit();
        RealVector originAsECEFVector = asVector(origin.toECEF(datum), unit);
        RealMatrix conversionMatrix = getENUConversionMatrix(origin);
        RealVector enuAsVector = asVector(enu, unit);
        return convertToECEF(originAsECEFVector, enuAsVector, conversionMatrix, unit);
    }

    public static ECEF convertToECEF(NED ned, Datum datum, LLA origin) {
        return convertToECEF(ned.toENU(), datum, origin);
    }

    // Private Methods

    private static ECEF convertToECEF(
            RealVector originAsECEFVector,
            RealVector positionAsVector,
            RealMatrix conversionMatrix,
            Unit<Length> unit) {
        RealVector ecefAsVector = new LUDecomposition(conversionMatrix)
                .getSolver()
                .solve(positionAsVector)
                .add(originAsECEFVector);
        return asECEF(ecefAsVector, unit);
    }

    private static RealMatrix getENUConversionMatrix(LLA origin) {
        double cosLat = AngleUtil.cos(origin.getLatitude());
        double sinLat = AngleUtil.sin(origin.getLatitude());
        double cosLong = AngleUtil.cos(origin.getLongitude());
        double sinLong = AngleUtil.sin(origin.getLongitude());
        return MatrixUtils.createRealMatrix(new double[][]{
                {-sinLong           , cosLong           , 0},
                {-sinLat * cosLong  , -sinLat * sinLong , cosLat},
                {cosLat * cosLong   , cosLat * sinLong  , sinLat}
        });
    }

}
