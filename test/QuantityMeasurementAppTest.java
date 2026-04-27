import org.junit.Test;
import static org.junit.Assert.*;

public class QuantityMeasurementAppTest {

    // Same-unit equality

    @Test
    public void testEquality_FeetToFeet_SameValue() {
        assertTrue(QuantityMeasurementApp.areEqual(
                1.0, QuantityMeasurementApp.LengthUnit.FEET,
                1.0, QuantityMeasurementApp.LengthUnit.FEET));
    }

    @Test
    public void testEquality_InchToInch_SameValue() {
        assertTrue(QuantityMeasurementApp.areEqual(
                1.0, QuantityMeasurementApp.LengthUnit.INCH,
                1.0, QuantityMeasurementApp.LengthUnit.INCH));
    }

    // Cross-unit equality

    @Test
    public void testEquality_FeetToInch_EquivalentValue() {
        assertTrue(QuantityMeasurementApp.areEqual(
                1.0, QuantityMeasurementApp.LengthUnit.FEET,
                12.0, QuantityMeasurementApp.LengthUnit.INCH));
    }

    @Test
    public void testEquality_InchToFeet_EquivalentValue() {
        assertTrue(QuantityMeasurementApp.areEqual(
                12.0, QuantityMeasurementApp.LengthUnit.INCH,
                1.0, QuantityMeasurementApp.LengthUnit.FEET));
    }

    // Different values

    @Test
    public void testEquality_FeetToFeet_DifferentValue() {
        assertFalse(QuantityMeasurementApp.areEqual(
                1.0, QuantityMeasurementApp.LengthUnit.FEET,
                2.0, QuantityMeasurementApp.LengthUnit.FEET));
    }

    @Test
    public void testEquality_InchToInch_DifferentValue() {
        assertFalse(QuantityMeasurementApp.areEqual(
                1.0, QuantityMeasurementApp.LengthUnit.INCH,
                2.0, QuantityMeasurementApp.LengthUnit.INCH));
    }

    // Null handling

    @Test
    public void testEquality_NullComparison() {
        QuantityMeasurementApp.Quantity q =
                new QuantityMeasurementApp.Quantity(1.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        assertFalse(q.equals(null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEquality_NullUnit() {
        new QuantityMeasurementApp.Quantity(1.0, null);
    }

    // Same reference

    @Test
    public void testEquality_SameReference() {
        QuantityMeasurementApp.Quantity q =
                new QuantityMeasurementApp.Quantity(1.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        assertTrue(q.equals(q));
    }
}