package org.galileo.datum;

import javax.measure.Quantity;
import javax.measure.quantity.Angle;
import javax.measure.quantity.Length;

public interface Datum {
   Quantity<Length> getSemiMajorAxis();
   Quantity<Length> getSemiMinorAxis();
   double getFlattening();
   double getEccentricity();
   Quantity<Length> radiusAtLatitude(Quantity<Angle> latitude);
}
