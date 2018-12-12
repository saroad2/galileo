package org.galileo;

import tech.units.indriya.AbstractSystemOfUnits;
import tech.units.indriya.format.SimpleUnitFormat;

import javax.measure.Quantity;
import javax.measure.Unit;
import javax.measure.quantity.Angle;
import javax.measure.quantity.Dimensionless;
import javax.measure.quantity.Length;
import javax.measure.spi.SystemOfUnits;

import static tech.units.indriya.AbstractUnit.ONE;

public class Units extends AbstractSystemOfUnits {

    protected static final Units INSTANCE = new Units();

    protected Units() {
        super();
    }
    public static SystemOfUnits getInstance() {
        return INSTANCE;
    }

    public static final Unit<Length> METER = addUnit(
            tech.units.indriya.unit.Units.METRE,
            Length.class,
            "m");

    public static final Unit<Angle> RADIAN = addUnit(
            tech.units.indriya.unit.Units.RADIAN,
            Angle.class,
            "rad");
    public static final Unit<Angle> REVOLUTION = addUnit(
            RADIAN.multiply(2).multiply(Math.PI).asType(Angle.class),
            Angle.class,
            "rev");
    public static final Unit<Angle> DEGREE = addUnit(
            REVOLUTION.divide(360).asType(Angle.class),
            Angle.class,
            "deg");

    @Override
    public String getName() {
        return Units.class.getSimpleName();
    }

    static {
        // have to add AbstractUnit.ONE as Dimensionless, too
        addUnit(ONE);
        INSTANCE.quantityToUnit.put(Dimensionless.class, ONE);
    }

    /**
     * Adds a new unit not mapped to any specified quantity type.
     *
     * @param unit
     *          the unit being added.
     * @return <code>unit</code>.
     */
    private static <U extends Unit<?>> U addUnit(U unit) {
        INSTANCE.units.add(unit);
        return unit;
    }

    /**
     * Adds a new unit and maps it to the specified quantity type.
     *
     * @param unit
     *          the unit being added.
     * @param type
     *          the quantity type.
     * @return <code>unit</code>.
     */
    private static <U extends Unit<?>> U addUnit(U unit, Class<? extends Quantity<?>> type, String name) {
        INSTANCE.units.add(unit);
        INSTANCE.quantityToUnit.put(type, unit);
        SimpleUnitFormat.getInstance().label(unit, name);
        return unit;
    }
}
