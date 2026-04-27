public class QuantityMeasurementApp {

    // Base unit = FEET
    public enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CENTIMETER(0.0328084);

        private final double toFeetFactor;

        LengthUnit(double toFeetFactor) {
            this.toFeetFactor = toFeetFactor;
        }

        public double toFeet(double value) {
            return value * toFeetFactor;
        }

        public double fromFeet(double feetValue) {
            return feetValue / toFeetFactor;
        }
    }

    public static class Quantity {
        private final double value;
        private final LengthUnit unit;

        public Quantity(double value, LengthUnit unit) {
            if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
            if (!Double.isFinite(value)) throw new IllegalArgumentException("Invalid value");

            this.value = value;
            this.unit = unit;
        }
        public Quantity add(Quantity other, LengthUnit targetUnit) {
           if (other == null || targetUnit == null)
                 throw new IllegalArgumentException("Invalid input");

           if (!Double.isFinite(this.value) || !Double.isFinite(other.value))
                 throw new IllegalArgumentException("Invalid value");

           double sumInFeet = this.toBaseUnit() + other.toBaseUnit();
           double result = targetUnit.fromFeet(sumInFeet);

           return new Quantity(result, targetUnit);
    }

        public double getValue() {
            return value;
        }

        public LengthUnit getUnit() {
            return unit;
        }

        public double toBaseUnit() {
            return unit.toFeet(value);
        }

         public static double convert(double value, LengthUnit source, LengthUnit target) {
            if (source == null || target == null)
                throw new IllegalArgumentException("Units cannot be null");

            if (!Double.isFinite(value))
                throw new IllegalArgumentException("Invalid value");

            double base = source.toFeet(value);
            return target.fromFeet(base);
        }

        // Convert instance
        public Quantity convertTo(LengthUnit targetUnit) {
            if (targetUnit == null)
                throw new IllegalArgumentException("Target unit cannot be null");

            double converted = convert(this.value, this.unit, targetUnit);
            return new Quantity(converted, targetUnit);
        }

        // Addition (UC6)
        public Quantity add(Quantity other) {
            if (other == null)
                throw new IllegalArgumentException("Other quantity cannot be null");

            double sumFeet = this.toBaseUnit() + other.toBaseUnit();
            double result = this.unit.fromFeet(sumFeet);

            return new Quantity(result, this.unit);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;

            Quantity other = (Quantity) obj;
            return Math.abs(this.toBaseUnit() - other.toBaseUnit()) < 1e-6;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(toBaseUnit());
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }
    }

    public static void main(String[] args) {
        System.out.println(Quantity.convert(1.0, LengthUnit.FEET, LengthUnit.INCH)); // 12
    }
}