public class Quantity<U extends Enum<U> & IMeasurable> {

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }
    public Quantity<U> convertTo(U targetUnit) {

    if (targetUnit == null) {
        throw new IllegalArgumentException("Target unit cannot be null");
    }

    // convert current value → base unit
    double base = unit.convertToBaseUnit(this.value);

    // convert base → target unit
    double converted = targetUnit.convertFromBaseUnit(base);

    return new Quantity<>(converted, targetUnit);
    }

    // ---------------- DRY HELPERS (UC13 CORE) ----------------

    private void validate(Quantity<U> other) {
        if (other == null) {
            throw new IllegalArgumentException("Quantity cannot be null");
        }

        if (Double.isNaN(value) || Double.isInfinite(value)
                || Double.isNaN(other.value) || Double.isInfinite(other.value)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }
    }

    private double toBase(double value, U unit) {
        return unit.convertToBaseUnit(value);
    }

    private double fromBase(double value, U unit) {
        return unit.convertFromBaseUnit(value);
    }

    private double performBaseArithmetic(Quantity<U> other,
                                         ArithmeticOperation op) {

        validate(other);

        double a = toBase(this.value, this.unit);
        double b = toBase(other.value, other.unit);

        return op.compute(a, b);
    }

    // ---------------- ADDITION (kept for UC13 compatibility if needed) ----------------

    public Quantity<U> add(Quantity<U> other) {
        double resultBase = performBaseArithmetic(other, ArithmeticOperation.ADD);
        double result = fromBase(resultBase, this.unit);
        return new Quantity<>(result, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        double resultBase = performBaseArithmetic(other, ArithmeticOperation.ADD);
        double result = fromBase(resultBase, targetUnit);
        return new Quantity<>(result, targetUnit);
    }

    // ---------------- SUBTRACTION ----------------

    public Quantity<U> subtract(Quantity<U> other) {
        double resultBase = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);
        double result = fromBase(resultBase, this.unit);
        return new Quantity<>(result, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        double resultBase = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);
        double result = fromBase(resultBase, targetUnit);
        return new Quantity<>(result, targetUnit);
    }

    // ---------------- DIVISION ----------------

    public double divide(Quantity<U> other) {
        validate(other);

        double a = toBase(this.value, this.unit);
        double b = toBase(other.value, other.unit);

        return ArithmeticOperation.DIVIDE.compute(a, b);
    }
}
