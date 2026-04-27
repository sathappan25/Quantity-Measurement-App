import org.junit.Test;
import static org.junit.Assert.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    @Test
    public void testConversion_FeetToInches() {
        assertEquals(12.0,
                QuantityMeasurementApp.convert(1.0,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCH),
                EPSILON);
    }

    @Test
    public void testConversion_InchesToFeet() {
        assertEquals(2.0,
                QuantityMeasurementApp.convert(24.0,
                        QuantityMeasurementApp.LengthUnit.INCH,
                        QuantityMeasurementApp.LengthUnit.FEET),
                EPSILON);
    }

    @Test
    public void testConversion_YardsToInches() {
        assertEquals(36.0,
                QuantityMeasurementApp.convert(1.0,
                        QuantityMeasurementApp.LengthUnit.YARD,
                        QuantityMeasurementApp.LengthUnit.INCH),
                EPSILON);
    }

    @Test
    public void testConversion_InchesToYards() {
        assertEquals(2.0,
                QuantityMeasurementApp.convert(72.0,
                        QuantityMeasurementApp.LengthUnit.INCH,
                        QuantityMeasurementApp.LengthUnit.YARD),
                EPSILON);
    }

    @Test
    public void testConversion_CentimeterToInches() {
        assertEquals(1.0,
                QuantityMeasurementApp.convert(2.54,
                        QuantityMeasurementApp.LengthUnit.CENTIMETER,
                        QuantityMeasurementApp.LengthUnit.INCH),
                EPSILON);
    }

    @Test
    public void testConversion_FeetToYard() {
        assertEquals(2.0,
                QuantityMeasurementApp.convert(6.0,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.YARD),
                EPSILON);
    }

    @Test
    public void testConversion_RoundTrip_PreservesValue() {
        double value = 5.0;

        double result = QuantityMeasurementApp.convert(
                QuantityMeasurementApp.convert(value,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCH),
                QuantityMeasurementApp.LengthUnit.INCH,
                QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(value, result, EPSILON);
    }

    @Test
    public void testConversion_ZeroValue() {
        assertEquals(0.0,
                QuantityMeasurementApp.convert(0.0,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCH),
                EPSILON);
    }

    @Test
    public void testConversion_NegativeValue() {
        assertEquals(-12.0,
                QuantityMeasurementApp.convert(-1.0,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCH),
                EPSILON);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConversion_InvalidUnit_Throws() {
        QuantityMeasurementApp.convert(1.0, null,
                QuantityMeasurementApp.LengthUnit.FEET);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConversion_NaNOrInfinite_Throws() {
        QuantityMeasurementApp.convert(Double.NaN,
                QuantityMeasurementApp.LengthUnit.FEET,
                QuantityMeasurementApp.LengthUnit.INCH);
    }
}