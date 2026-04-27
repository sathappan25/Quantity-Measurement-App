import org.junit.Test;
import static org.junit.Assert.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    // =========================
    // UC8 - Unit Enum Tests
    // =========================

    @Test
    public void testFeetToBaseUnit() {
        assertEquals(1.0,
                QuantityMeasurementApp.LengthUnit.FEET.toBaseUnit(1.0),
                EPSILON);
    }

    @Test
    public void testInchToBaseUnit() {
        assertEquals(1.0,
                QuantityMeasurementApp.LengthUnit.INCH.toBaseUnit(12.0),
                EPSILON);
    }

    @Test
    public void testYardToBaseUnit() {
        assertEquals(3.0,
                QuantityMeasurementApp.LengthUnit.YARD.toBaseUnit(1.0),
                EPSILON);
    }

    @Test
    public void testCmToBaseUnit() {
        assertEquals(1.0,
                QuantityMeasurementApp.LengthUnit.CENTIMETER.toBaseUnit(30.48),
                EPSILON);
    }

    // =========================
    // UC8 - Conversion Tests
    // =========================

    @Test
    public void testConvert_FeetToInch() {
        QuantityMeasurementApp.Quantity q =
                new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.Quantity result = q.convertTo(QuantityMeasurementApp.LengthUnit.INCH);

        assertEquals(12.0, result.getValue(), EPSILON);
    }

    @Test
    public void testConvert_FeetToYard() {
        QuantityMeasurementApp.Quantity q =
                new QuantityMeasurementApp.Quantity(3.0, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.Quantity result = q.convertTo(QuantityMeasurementApp.LengthUnit.YARD);

        assertEquals(1.0, result.getValue(), EPSILON);
    }

    // =========================
    // UC8 - Equality Tests
    // =========================

    @Test
    public void testEquality_FeetAndInch() {
        QuantityMeasurementApp.Quantity q1 =
                new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.Quantity q2 =
                new QuantityMeasurementApp.Quantity(12.0, QuantityMeasurementApp.LengthUnit.INCH);

        assertTrue(q1.equals(q2));
    }

    // =========================
    // UC8 - Addition Tests
    // =========================

    @Test
    public void testAdd_FeetPlusInch_FeetResult() {
        QuantityMeasurementApp.Quantity q1 =
                new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.Quantity q2 =
                new QuantityMeasurementApp.Quantity(12.0, QuantityMeasurementApp.LengthUnit.INCH);

        QuantityMeasurementApp.Quantity result = q1.add(q2, QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    public void testAdd_FeetPlusInch_YardResult() {
        QuantityMeasurementApp.Quantity q1 =
                new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.Quantity q2 =
                new QuantityMeasurementApp.Quantity(12.0, QuantityMeasurementApp.LengthUnit.INCH);

        QuantityMeasurementApp.Quantity result = q1.add(q2, QuantityMeasurementApp.LengthUnit.YARD);

        assertEquals(0.666666, result.getValue(), EPSILON);
    }

    @Test
    public void testAdd_Commutativity() {
        QuantityMeasurementApp.Quantity q1 =
                new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.Quantity q2 =
                new QuantityMeasurementApp.Quantity(12.0, QuantityMeasurementApp.LengthUnit.INCH);

        QuantityMeasurementApp.Quantity r1 = q1.add(q2, QuantityMeasurementApp.LengthUnit.FEET);
        QuantityMeasurementApp.Quantity r2 = q2.add(q1, QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(r1.getValue(), r2.getValue(), EPSILON);
    }

    // =========================
    // UC8 - Negative & Edge Cases
    // =========================

    @Test
    public void testAdd_WithZero() {
        QuantityMeasurementApp.Quantity q1 =
                new QuantityMeasurementApp.Quantity(5.0, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.Quantity q2 =
                new QuantityMeasurementApp.Quantity(0.0, QuantityMeasurementApp.LengthUnit.INCH);

        QuantityMeasurementApp.Quantity result = q1.add(q2);

        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullUnitException() {
        new QuantityMeasurementApp.Quantity(1.0, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullAdditionTargetUnit() {
        QuantityMeasurementApp.Quantity q1 =
                new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.Quantity q2 =
                new QuantityMeasurementApp.Quantity(1.0, QuantityMeasurementApp.LengthUnit.INCH);

        q1.add(q2, null);
    }
}