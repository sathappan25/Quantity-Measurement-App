import org.junit.Test;
import static org.junit.Assert.*;

public class QuantityArithmeticTest {

    // -------- SUBTRACTION --------

    @Test
    public void testSubtractionSameUnit() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);

        Quantity<LengthUnit> result = q1.subtract(q2);

        assertEquals(5.0, result.getValue(), 0.01);
    }

    @Test
    public void testSubtractionCrossUnit() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(6.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = q1.subtract(q2);

        assertEquals(9.5, result.getValue(), 0.01);
    }

    @Test
    public void testSubtractionWithTargetUnit() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(6.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = q1.subtract(q2, LengthUnit.INCHES);

        assertEquals(114.0, result.getValue(), 0.01);
    }

    @Test
    public void testSubtractionNegativeResult() {
        Quantity<WeightUnit> q1 = new Quantity<>(2.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> q2 = new Quantity<>(5.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> result = q1.subtract(q2);

        assertEquals(-3.0, result.getValue(), 0.01);
    }

    @Test
    public void testSubtractionZeroResult() {
        Quantity<VolumeUnit> q1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result = q1.subtract(q2);

        assertEquals(0.0, result.getValue(), 0.01);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSubtractionCrossCategory() {
    Quantity<LengthUnit> l = new Quantity<>(10.0, LengthUnit.FEET);

    // simulate invalid category via raw type but safely
    Quantity<?> w = new Quantity<>(5.0, WeightUnit.KILOGRAM);

    l.subtract((Quantity<LengthUnit>) w);
}

    // -------- DIVISION --------

    @Test
    public void testDivisionSameUnit() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(2.0, LengthUnit.FEET);

        assertEquals(5.0, q1.divide(q2), 0.01);
    }

    @Test
    public void testDivisionCrossUnit() {
        Quantity<LengthUnit> q1 = new Quantity<>(24.0, LengthUnit.INCHES);
        Quantity<LengthUnit> q2 = new Quantity<>(2.0, LengthUnit.FEET);

        assertEquals(1.0, q1.divide(q2), 0.01);
    }

    @Test
    public void testDivisionLessThanOne() {
        Quantity<VolumeUnit> q1 = new Quantity<>(5.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(10.0, VolumeUnit.LITRE);

        assertEquals(0.5, q1.divide(q2), 0.01);
    }

    @Test
    public void testDivisionEqual() {
        Quantity<WeightUnit> q1 = new Quantity<>(1000.0, WeightUnit.GRAM);
        Quantity<WeightUnit> q2 = new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertEquals(1.0, q1.divide(q2), 0.01);
    }

    @Test(expected = ArithmeticException.class)
    public void testDivisionByZero() {
        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(0.0, LengthUnit.FEET);

        q1.divide(q2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDivisionCrossCategory() {
    Quantity<LengthUnit> l = new Quantity<>(10.0, LengthUnit.FEET);

    Quantity<?> w = new Quantity<>(5.0, WeightUnit.KILOGRAM);

    l.divide((Quantity<LengthUnit>) w);
}
}