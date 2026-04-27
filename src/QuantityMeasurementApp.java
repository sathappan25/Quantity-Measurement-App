public class QuantityMeasurementApp {

    // Enum for units with conversion to base unit (feet)
    public enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0);

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }
    }

    // Generic Quantity class
    public static class Quantity {
        private final double value;
        private final LengthUnit unit;

        public Quantity(double value, LengthUnit unit) {
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            this.value = value;
            this.unit = unit;
        }

        public double toBaseUnit() {
            return unit.toFeet(value);
        }

        @Override
        public boolean equals(Object obj) {
            // Same reference
            if (this == obj) return true;

            // Null & type check
            if (obj == null || getClass() != obj.getClass()) return false;

            Quantity other = (Quantity) obj;

            // Compare after converting to base unit (feet)
            return Double.compare(this.toBaseUnit(), other.toBaseUnit()) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(toBaseUnit());
        }
    }

    // Helper method for easy comparison
    public static boolean areEqual(double v1, LengthUnit u1, double v2, LengthUnit u2) {
        return new Quantity(v1, u1).equals(new Quantity(v2, u2));
    }

    public static void main(String[] args) {
        System.out.println("1 ft == 12 inch ? " +
                areEqual(1.0, LengthUnit.FEET, 12.0, LengthUnit.INCH));

        System.out.println("1 inch == 1 inch ? " +
                areEqual(1.0, LengthUnit.INCH, 1.0, LengthUnit.INCH));
    }
}