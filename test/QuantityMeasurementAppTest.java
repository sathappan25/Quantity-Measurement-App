import org.junit.Test;
import static org.junit.Assert.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    @Test
    public void testAddition_ExplicitTargetUnit_Feet() {
        QuantityMeasurementApp.Quantity result =
                new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.FEET)
                        .add(new QuantityMeasurementApp.Quantity(12.0, QuantityMeasurementApp.LengthUnit.INCH),
                                QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), EPSILON);
        assertEquals(QuantityMeasurementApp.LengthUnit.FEET, result.getUnit());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Inches() {
        QuantityMeasurementApp.Quantity result =
                new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.FEET)
                        .add(new QuantityMeasurementApp.Quantity(12.0, QuantityMeasurementApp.LengthUnit.INCH),
                                QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals(24.0, result.getValue(), EPSILON);
        assertEquals(QuantityMeasurementApp.LengthUnit.INCH, result.getUnit());
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Yards() {
        QuantityMeasurementApp.Quantity result =
                new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.FEET)
                        .add(new QuantityMeasurementApp.Quantity(12.0, QuantityMeasurementApp.LengthUnit.INCH),
                                QuantityMeasurementApp.LengthUnit.YARD);

        assertEquals(0.666666, result.getValue(), EPSILON);
        assertEquals(QuantityMeasurementApp.LengthUnit.YARD, result.getUnit());
    }

    @Test
    public void testAddition_Commutativity_WithTargetUnit() {
        QuantityMeasurementApp.Quantity a =
                new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.Quantity b =
                new QuantityMeasurementApp.Quantity(12.0, QuantityMeasurementApp.LengthUnit.INCH);

        QuantityMeasurementApp.Quantity r1 = a.add(b, QuantityMeasurementApp.LengthUnit.YARD);
        QuantityMeasurementApp.Quantity r2 = b.add(a, QuantityMeasurementApp.LengthUnit.YARD);

        assertEquals(r1.getValue(), r2.getValue(), EPSILON);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddition_NullTargetUnit() {
        new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.FEET)
                .add(new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.INCH),
                        null);
    }
}