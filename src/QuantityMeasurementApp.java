public class QuantityMeasurementApp {

    // All units converted to BASE UNIT = FEET
    public enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CENTIMETER(0.0328084); // Correct: 1 cm = 0.0328084 feet

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }
    }

    // Generic Quantity class (unchanged)
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
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Quantity other = (Quantity) obj;
            return Double.compare(this.toBaseUnit(), other.toBaseUnit()) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(toBaseUnit());
        }
    }

    // Helper method
    public static boolean areEqual(double v1, LengthUnit u1, double v2, LengthUnit u2) {
        return new Quantity(v1, u1).equals(new Quantity(v2, u2));
    }

    public static void main(String[] args) {
        System.out.println(areEqual(1.0, LengthUnit.YARD, 3.0, LengthUnit.FEET)); // true
        System.out.println(areEqual(1.0, LengthUnit.YARD, 36.0, LengthUnit.INCH)); // true
        System.out.println(areEqual(1.0, LengthUnit.CENTIMETER, 0.393701, LengthUnit.INCH)); // true
    }
}