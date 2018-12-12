package org.galileo.test.internal;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import javax.measure.Quantity;

public class QuantityMatcher<Q extends Quantity<Q>> extends BaseMatcher<Quantity<Q>> {

    private static final double EPSILON = 1e-6;
    private Quantity<Q> expected;

    private QuantityMatcher(Quantity<Q> expected) {
        super();
        this.expected = expected;
    }

    public static <Q1 extends Quantity<Q1>> QuantityMatcher<Q1> getQuantityMatcher(Quantity<Q1> expected) {
        return new QuantityMatcher<>(expected);
    }

    @Override
    public boolean matches(Object item) {
        Quantity<Q> actual = (Quantity<Q>)item;

        return isEqualUnits(actual) && isEqualValues(actual);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("Should be equal to quantity ");
        description.appendValue(expected);
    }

    private boolean isEqualValues(Quantity<Q> actual) {
        double error = Math.abs(actual.getValue().doubleValue() - expected.getValue().doubleValue());
        return error < EPSILON;
    }

    private boolean isEqualUnits(Quantity<Q> actual) {
        return actual.getUnit().equals(expected.getUnit());
    }
}
