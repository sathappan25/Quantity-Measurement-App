import org.junit.Test;
import static org.junit.Assert.*;

public class QuantityMeasurementAppTest {

    // -------- FEET TESTS --------

    @Test
    public void testFeetEquality_SameValue() {
        assertTrue("1.0 ft should equal 1.0 ft",
                QuantityMeasurementApp.areFeetEqual(1.0, 1.0));
    }

    @Test
    public void testFeetEquality_DifferentValue() {
        assertFalse("1.0 ft should not equal 2.0 ft",
                QuantityMeasurementApp.areFeetEqual(1.0, 2.0));
    }

    @Test
    public void testFeetEquality_NullComparison() {
        QuantityMeasurementApp.Feet f = new QuantityMeasurementApp.Feet(1.0);
        assertFalse("Feet should not equal null", f.equals(null));
    }

    @Test
    public void testFeetEquality_SameReference() {
        QuantityMeasurementApp.Feet f = new QuantityMeasurementApp.Feet(1.0);
        assertTrue("Same reference should be equal", f.equals(f));
    }

    @Test
    public void testFeetEquality_NonNumericInput() {
        QuantityMeasurementApp.Feet f = new QuantityMeasurementApp.Feet(1.0);
        assertFalse("Feet should not equal non-numeric type", f.equals("abc"));
    }

    // -------- INCHES TESTS --------

    @Test
    public void testInchesEquality_SameValue() {
        assertTrue("1.0 inch should equal 1.0 inch",
                QuantityMeasurementApp.areInchesEqual(1.0, 1.0));
    }

    @Test
    public void testInchesEquality_DifferentValue() {
        assertFalse("1.0 inch should not equal 2.0 inch",
                QuantityMeasurementApp.areInchesEqual(1.0, 2.0));
    }

    @Test
    public void testInchesEquality_NullComparison() {
        QuantityMeasurementApp.Inches i = new QuantityMeasurementApp.Inches(1.0);
        assertFalse("Inches should not equal null", i.equals(null));
    }

    @Test
    public void testInchesEquality_SameReference() {
        QuantityMeasurementApp.Inches i = new QuantityMeasurementApp.Inches(1.0);
        assertTrue("Same reference should be equal", i.equals(i));
    }

    @Test
    public void testInchesEquality_NonNumericInput() {
        QuantityMeasurementApp.Inches i = new QuantityMeasurementApp.Inches(1.0);
        assertFalse("Inches should not equal non-numeric type", i.equals("xyz"));
    }
}