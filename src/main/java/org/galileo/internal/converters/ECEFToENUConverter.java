package org.galileo.internal.converters;

import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.galileo.datum.Datum;
import org.galileo.internal.AngleUtil;
import org.galileo.position.ECEF;
import org.galileo.position.ENU;
import org.galileo.position.LLA;
import tech.units.indriya.quantity.Quantities;

import javax.measure.Unit;
import javax.measure.quantity.Length;

public final class ECEFToENUConverter {

    private ECEFToENUConverter() {

    }

    public static ENU convertToENU(ECEF ecef, Datum datum, LLA origin) {
        Unit<Length> unit = ecef.getX().getUnit();
        RealVector originAsECEFVector = ecefAsVector(origin.toECEF(datum), unit);
        RealMatrix conversionMatrix = getConversionMatrix(origin);
        RealVector conversionVector = ecefAsVector(ecef, unit)
                .subtract(originAsECEFVector);
        RealVector enuAsVector = new LUDecomposition(conversionMatrix.transpose())
                .getSolver()
                .solve(conversionVector);
        return new ENU(
                Quantities.getQuantity(enuAsVector.getEntry(0), unit),
                Quantities.getQuantity(enuAsVector.getEntry(1), unit),
                Quantities.getQuantity(enuAsVector.getEntry(2), unit)
        );
    }

    public static ECEF convertToECEF(ENU enu, Datum datum, LLA origin) {
        Unit<Length> unit = enu.getEast().getUnit();
        RealVector originAsECEFVector = ecefAsVector(origin.toECEF(datum), unit);
        RealMatrix conversionMatrix = getConversionMatrix(origin);
        RealVector enuAsVector = enuAsVector(enu, unit);
        RealVector ecefAsVector = new LUDecomposition(conversionMatrix)
                .getSolver()
                .solve(enuAsVector)
                .add(originAsECEFVector);
        return new ECEF(
                Quantities.getQuantity(ecefAsVector.getEntry(0), unit),
                Quantities.getQuantity(ecefAsVector.getEntry(1), unit),
                Quantities.getQuantity(ecefAsVector.getEntry(2), unit)
        );
    }

    private static RealMatrix getConversionMatrix(LLA origin) {
        double cosLat = AngleUtil.cos(origin.getLatitude());
        double sinLat = AngleUtil.sin(origin.getLatitude());
        double cosLong = AngleUtil.cos(origin.getLongitude());
        double sinLong = AngleUtil.sin(origin.getLongitude());
        return MatrixUtils.createRealMatrix(new double[][]{
                {-sinLong,          cosLong,            0},
                {-sinLat * cosLong, -sinLat * sinLong,  cosLat},
                {cosLat * cosLong,  cosLat * sinLong,    sinLat}
        });
    }

    private static RealVector ecefAsVector(ECEF ecef, Unit<Length> unit) {
        return MatrixUtils.createRealVector(new double[]{
                ecef.getX().to(unit).getValue().doubleValue(),
                ecef.getY().to(unit).getValue().doubleValue(),
                ecef.getZ().to(unit).getValue().doubleValue()
        });
    }

    private static RealVector enuAsVector(ENU enu, Unit<Length> unit) {
        return MatrixUtils.createRealVector(new double[]{
                enu.getEast().to(unit).getValue().doubleValue(),
                enu.getNorth().to(unit).getValue().doubleValue(),
                enu.getUp().to(unit).getValue().doubleValue()
        });
    }
}
