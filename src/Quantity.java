import java.util.Objects;

public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if (unit == null || Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Invalid value or unit");
        }
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    private double toBase() {
        return unit.convertToBaseUnit(value);
    }

    public Quantity<U> convertTo(U targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        double base = toBase();
        double converted = targetUnit.convertFromBaseUnit(base);
        return new Quantity<>(converted, targetUnit);
    }

    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        validate(other, targetUnit);

        double resultBase = this.toBase() + other.toBase();
        double result = targetUnit.convertFromBaseUnit(resultBase);

        return new Quantity<>(result, targetUnit);
    }

    // ================= UC12 =================

    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        validate(other, targetUnit);

        double resultBase = this.toBase() - other.toBase();
        double result = targetUnit.convertFromBaseUnit(resultBase);

        return new Quantity<>(round(result), targetUnit);
    }

    public double divide(Quantity<U> other) {
        if (other == null) {
            throw new IllegalArgumentException("Null not allowed");
        }

        if (!this.unit.getClass().equals(other.unit.getClass())) {
            throw new IllegalArgumentException("Different measurement categories");
        }

        double divisor = other.toBase();
        if (divisor == 0) {
            throw new ArithmeticException("Division by zero");
        }

        return this.toBase() / divisor;
    }

    // =======================================

    private void validate(Quantity<U> other, U targetUnit) {
        if (other == null || targetUnit == null) {
            throw new IllegalArgumentException("Null not allowed");
        }

        if (!this.unit.getClass().equals(other.unit.getClass())) {
            throw new IllegalArgumentException("Different measurement categories");
        }
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Quantity<?>)) return false;

        Quantity<?> other = (Quantity<?>) obj;

        if (!this.unit.getClass().equals(other.unit.getClass())) {
            return false;
        }

        return Double.compare(this.toBase(), other.toBase()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(toBase(), unit.getClass());
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}