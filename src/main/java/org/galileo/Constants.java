package org.galileo;

import org.galileo.internal.MeasureUtil;
import tech.units.indriya.quantity.Quantities;

import javax.measure.Quantity;
import javax.measure.quantity.Angle;
import javax.measure.quantity.Length;

public final class Constants {
    private Constants() {

    }

    public static final Quantity<Length> ZERO_METERS = MeasureUtil.zero(Units.METER);

    public static final Quantity<Angle> ZERO_DEGREES = MeasureUtil.zero(Units.DEGREE);
    public static final Quantity<Angle> NINETY_DEGREES = Quantities.getQuantity(90, Units.DEGREE);
    public static final Quantity<Angle> ONE_HUNDRED_AND_EIGHTY_DEGREES = Quantities.getQuantity(
            180,
            Units.DEGREE);
}
