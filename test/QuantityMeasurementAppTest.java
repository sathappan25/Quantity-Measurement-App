import org.junit.Test;
import static org.junit.Assert.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    @Test
    public void testConversion_FeetToInches() {
        assertEquals(12.0,
                QuantityMeasurementApp.Quantity.convert(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCH),
                EPSILON);
    }

    @Test
    public void testAddition_FeetPlusInches() {
        QuantityMeasurementApp.Quantity q1 =
                new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.Quantity q2 =
                new QuantityMeasurementApp.Quantity(12.0, QuantityMeasurementApp.LengthUnit.INCH);

        QuantityMeasurementApp.Quantity result = q1.add(q2);

        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(QuantityMeasurementApp.LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void testAddition_InchPlusFeet() {
        QuantityMeasurementApp.Quantity q1 =
                new QuantityMeasurementApp.Quantity(12.0, QuantityMeasurementApp.LengthUnit.INCH);

        QuantityMeasurementApp.Quantity q2 =
                new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.Quantity result = q1.add(q2);

        assertEquals(24.0, result.getValue(), EPSILON);
        assertEquals(QuantityMeasurementApp.LengthUnit.INCH, result.getUnit());
    }

    @Test
    public void testAddition_WithZero() {
        QuantityMeasurementApp.Quantity result =
                new QuantityMeasurementApp.Quantity(5.0, QuantityMeasurementApp.LengthUnit.FEET)
                        .add(new QuantityMeasurementApp.Quantity(0.0, QuantityMeasurementApp.LengthUnit.INCH));

        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddition_NullOperand() {
        new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.FEET)
                .add(null);
    }
}