import org.junit.Test;
import static org.junit.Assert.*;

public class QuantityTest {

    // Equality Tests
    @Test
    public void testLengthEquality() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);
        assertTrue(q1.equals(q2));
    }

    @Test
    public void testWeightEquality() {
        Quantity<WeightUnit> q1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> q2 = new Quantity<>(1000.0, WeightUnit.GRAM);
        assertTrue(q1.equals(q2));
    }

    @Test
    public void testCrossCategoryEquality() {
        Quantity<LengthUnit> l = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<WeightUnit> w = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        assertFalse(l.equals(w));
    }

    // Conversion Tests
    @Test
    public void testLengthConversion() {
        Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = q.convertTo(LengthUnit.INCHES);
        assertEquals(12.0, result.getValue(), 0.01);
    }

    @Test
    public void testWeightConversion() {
        Quantity<WeightUnit> q = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> result = q.convertTo(WeightUnit.GRAM);
        assertEquals(1000.0, result.getValue(), 0.01);
    }

    // Addition Tests
    @Test
    public void testLengthAddition() {
        Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = q1.add(q2, LengthUnit.FEET);
        assertEquals(2.0, result.getValue(), 0.01);
    }

    @Test
    public void testWeightAddition() {
        Quantity<WeightUnit> q1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> q2 = new Quantity<>(1000.0, WeightUnit.GRAM);

        Quantity<WeightUnit> result = q1.add(q2, WeightUnit.KILOGRAM);
        assertEquals(2.0, result.getValue(), 0.01);
    }

    // Constructor Validation
    @Test(expected = IllegalArgumentException.class)
    public void testNullUnit() {
        new Quantity<>(1.0, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidValue() {
        new Quantity<>(Double.NaN, LengthUnit.FEET);
    }
}