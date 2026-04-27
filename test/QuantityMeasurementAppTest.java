import org.junit.Test;
import static org.junit.Assert.*;

public class QuantityMeasurementAppTest {

    // -------- YARD TESTS --------

    @Test
    public void testEquality_YardToYard_SameValue() {
        assertTrue(QuantityMeasurementApp.areEqual(
                1.0, QuantityMeasurementApp.LengthUnit.YARD,
                1.0, QuantityMeasurementApp.LengthUnit.YARD));
    }

    @Test
    public void testEquality_YardToYard_DifferentValue() {
        assertFalse(QuantityMeasurementApp.areEqual(
                1.0, QuantityMeasurementApp.LengthUnit.YARD,
                2.0, QuantityMeasurementApp.LengthUnit.YARD));
    }

    @Test
    public void testEquality_YardToFeet_EquivalentValue() {
        assertTrue(QuantityMeasurementApp.areEqual(
                1.0, QuantityMeasurementApp.LengthUnit.YARD,
                3.0, QuantityMeasurementApp.LengthUnit.FEET));
    }

    @Test
    public void testEquality_YardToInches_EquivalentValue() {
        assertTrue(QuantityMeasurementApp.areEqual(
                1.0, QuantityMeasurementApp.LengthUnit.YARD,
                36.0, QuantityMeasurementApp.LengthUnit.INCH));
    }

    @Test
    public void testEquality_YardToFeet_NonEquivalentValue() {
        assertFalse(QuantityMeasurementApp.areEqual(
                1.0, QuantityMeasurementApp.LengthUnit.YARD,
                2.0, QuantityMeasurementApp.LengthUnit.FEET));
    }

    // -------- CM TESTS --------

    @Test
    public void testEquality_CentimeterToCentimeter_SameValue() {
        assertTrue(QuantityMeasurementApp.areEqual(
                2.0, QuantityMeasurementApp.LengthUnit.CENTIMETER,
                2.0, QuantityMeasurementApp.LengthUnit.CENTIMETER));
    }

    @Test
    public void testEquality_CentimeterToInches_EquivalentValue() {
        assertTrue(QuantityMeasurementApp.areEqual(
                1.0, QuantityMeasurementApp.LengthUnit.CENTIMETER,
                0.393701, QuantityMeasurementApp.LengthUnit.INCH));
    }

    @Test
    public void testEquality_CentimeterToFeet_NonEquivalentValue() {
        assertFalse(QuantityMeasurementApp.areEqual(
                1.0, QuantityMeasurementApp.LengthUnit.CENTIMETER,
                1.0, QuantityMeasurementApp.LengthUnit.FEET));
    }

    // -------- TRANSITIVE --------

    @Test
    public void testEquality_MultiUnit_TransitiveProperty() {
        QuantityMeasurementApp.Quantity yard =
                new QuantityMeasurementApp.Quantity(1.0,
                        QuantityMeasurementApp.LengthUnit.YARD);

        QuantityMeasurementApp.Quantity feet =
                new QuantityMeasurementApp.Quantity(3.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.Quantity inch =
                new QuantityMeasurementApp.Quantity(36.0,
                        QuantityMeasurementApp.LengthUnit.INCH);

        assertTrue(yard.equals(feet));
        assertTrue(feet.equals(inch));
        assertTrue(yard.equals(inch));
    }

    // -------- EDGE CASES --------

    @Test(expected = IllegalArgumentException.class)
    public void testEquality_NullUnit() {
        new QuantityMeasurementApp.Quantity(1.0, null);
    }

    @Test
    public void testEquality_SameReference() {
        QuantityMeasurementApp.Quantity q =
                new QuantityMeasurementApp.Quantity(2.0,
                        QuantityMeasurementApp.LengthUnit.YARD);

        assertTrue(q.equals(q));
    }

    @Test
    public void testEquality_NullComparison() {
        QuantityMeasurementApp.Quantity q =
                new QuantityMeasurementApp.Quantity(2.0,
                        QuantityMeasurementApp.LengthUnit.YARD);

        assertFalse(q.equals(null));
    }

    // -------- COMPLEX --------

    @Test
    public void testEquality_AllUnits_ComplexScenario() {
        assertTrue(QuantityMeasurementApp.areEqual(
                2.0, QuantityMeasurementApp.LengthUnit.YARD,
                6.0, QuantityMeasurementApp.LengthUnit.FEET));

        assertTrue(QuantityMeasurementApp.areEqual(
                6.0, QuantityMeasurementApp.LengthUnit.FEET,
                72.0, QuantityMeasurementApp.LengthUnit.INCH));
    }
}