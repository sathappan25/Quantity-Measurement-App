public class QuantityMeasurementApp {

    public static <U extends IMeasurable> Quantity<U> create(double value, U unit) {
        return new Quantity<>(value, unit);
    }

    public static <U extends IMeasurable> Quantity<U> add(
            Quantity<U> a,
            Quantity<U> b,
            U targetUnit
    ) {
        return a.add(b, targetUnit);
    }

    public static <U extends IMeasurable> Quantity<U> convert(
            Quantity<U> q,
            U target
    ) {
        return q.convertTo(target);
    }
}