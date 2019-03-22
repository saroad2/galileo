package org.galileo.internal;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealVector;
import org.galileo.position.ECEF;
import org.galileo.position.ENU;
import org.galileo.position.NED;
import tech.units.indriya.quantity.Quantities;

import javax.measure.Unit;
import javax.measure.quantity.Length;

public final class LinearAlgebraUtil {

    private LinearAlgebraUtil() {

    }

    public static RealVector asVector(ECEF ecef, Unit<Length> unit) {
        return MatrixUtils.createRealVector(new double[]{
                ecef.getX().to(unit).getValue().doubleValue(),
                ecef.getY().to(unit).getValue().doubleValue(),
                ecef.getZ().to(unit).getValue().doubleValue()
        });
    }

    public static RealVector asVector(ENU enu, Unit<Length> unit) {
        return MatrixUtils.createRealVector(new double[]{
                enu.getEast().to(unit).getValue().doubleValue(),
                enu.getNorth().to(unit).getValue().doubleValue(),
                enu.getUp().to(unit).getValue().doubleValue()
        });
    }

    public static RealVector asVector(NED ned, Unit<Length> unit) {
        return MatrixUtils.createRealVector(new double[]{
                ned.getNorth().to(unit).getValue().doubleValue(),
                ned.getEast().to(unit).getValue().doubleValue(),
                ned.getDown().to(unit).getValue().doubleValue()
        });
    }

    public static ECEF asECEF(RealVector vector, Unit<Length> unit) {
        return new ECEF(
                Quantities.getQuantity(vector.getEntry(0), unit),
                Quantities.getQuantity(vector.getEntry(1), unit),
                Quantities.getQuantity(vector.getEntry(2), unit)
        );
    }

    public static ENU asENU(RealVector vector, Unit<Length> unit) {
        return new ENU(
                Quantities.getQuantity(vector.getEntry(0), unit),
                Quantities.getQuantity(vector.getEntry(1), unit),
                Quantities.getQuantity(vector.getEntry(2), unit)
        );
    }

    public static NED asNED(RealVector vector, Unit<Length> unit) {
        return new NED(
                Quantities.getQuantity(vector.getEntry(0), unit),
                Quantities.getQuantity(vector.getEntry(1), unit),
                Quantities.getQuantity(vector.getEntry(2), unit)
        );
    }
}
