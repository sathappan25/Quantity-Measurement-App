import java.util.Objects;

public class QuantityWeight {

    private final double value;
    private final WeightUnit unit;

    public QuantityWeight(double value, WeightUnit unit) {
        if (unit == null || Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Invalid value or unit");
        }
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public WeightUnit getUnit() {
        return unit;
    }

    private double toKg() {
        return unit.convertToBaseUnit(value);
    }

    public QuantityWeight convertTo(WeightUnit targetUnit) {
        double kg = toKg();
        double converted = targetUnit.convertFromBaseUnit(kg);
        return new QuantityWeight(converted, targetUnit);
    }

    public QuantityWeight add(QuantityWeight other, WeightUnit targetUnit) {
        if (other == null || targetUnit == null) {
            throw new IllegalArgumentException("Null not allowed");
        }

        double sumKg = this.toKg() + other.toKg();
        double result = targetUnit.convertFromBaseUnit(sumKg);

        return new QuantityWeight(result, targetUnit);
    }

    public QuantityWeight add(QuantityWeight other) {
        return add(other, this.unit);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (!(obj instanceof QuantityWeight)) return false;

        QuantityWeight other = (QuantityWeight) obj;

        return Double.compare(this.toKg(), other.toKg()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(toKg());
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}