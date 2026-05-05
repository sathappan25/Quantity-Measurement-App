public class QuantityMeasurementApp {

    public static void main(String[] args) {

        Quantity<LengthUnit> q1 =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<LengthUnit> q2 =
                new Quantity<>(6.0, LengthUnit.INCHES);

        System.out.println("Subtract FEET: " +
                q1.subtract(q2).getValue());

        System.out.println("Subtract INCHES: " +
                q1.subtract(q2, LengthUnit.INCHES).getValue());

        System.out.println("Divide: " +
                q1.divide(q2));
    }
}