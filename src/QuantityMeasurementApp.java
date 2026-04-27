public class QuantityMeasurementApp {

    public static QuantityWeight create(double value, WeightUnit unit) {
        return new QuantityWeight(value, unit);
    }

    public static QuantityWeight add(QuantityWeight a, QuantityWeight b, WeightUnit targetUnit) {
        return a.add(b, targetUnit);
    }

    public static QuantityWeight convert(QuantityWeight q, WeightUnit target) {
        return q.convertTo(target);
    }
}