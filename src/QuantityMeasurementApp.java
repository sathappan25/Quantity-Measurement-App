public class QuantityMeasurementApp {

    // =========================
    // Standalone Unit Enum
    // =========================
    public enum LengthUnit {

        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CENTIMETER(1.0 / 30.48);

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toBaseUnit(double value) {
            return value * toFeetFactor;
        }

        public double fromBaseUnit(double baseValue) {
            return baseValue / toFeetFactor;
        }
    }

    // =========================
    // Quantity Class
    // =========================
    public static class Quantity {

        private final double value;
        private final LengthUnit unit;

        public Quantity(double value, LengthUnit unit) {
            if (unit == null || !Double.isFinite(value)) {
                throw new IllegalArgumentException("Invalid input");
            }
            this.value = value;
            this.unit = unit;
        }

        public double toBase() {
            return unit.toBaseUnit(value);
        }

        // UC5 - conversion
        public Quantity convertTo(LengthUnit targetUnit) {
            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }

            double base = unit.toBaseUnit(value);
            double result = targetUnit.fromBaseUnit(base);

            return new Quantity(result, targetUnit);
        }

        // UC6 - default target (first operand unit)
        public Quantity add(Quantity other) {
            return add(other, this.unit);
        }

        // UC7 + UC8 - explicit target unit
        public Quantity add(Quantity other, LengthUnit targetUnit) {
            if (other == null || targetUnit == null) {
                throw new IllegalArgumentException("Invalid input");
            }

            double sumBase = this.toBase() + other.toBase();
            double result = targetUnit.fromBaseUnit(sumBase);

            return new Quantity(result, targetUnit);
        }

        public double getValue() {
            return value;
        }

        public LengthUnit getUnit() {
            return unit;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Quantity)) return false;

            Quantity other = (Quantity) obj;
            return Math.abs(this.toBase() - other.toBase()) < 1e-6;
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }
    }
}