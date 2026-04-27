import org.junit.Test;
import static org.junit.Assert.*;

public class QuantityWeightTest {

    // ---------------- EQUALITY ----------------

    @Test
    public void testKgToKgEqual() {
        assertEquals(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                new QuantityWeight(1.0, WeightUnit.KILOGRAM)
        );
    }

    @Test
    public void testKgToGramEqual() {
        assertEquals(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                new QuantityWeight(1000.0, WeightUnit.GRAM)
        );
    }

    @Test
    public void testPoundToKgEqual() {
        assertEquals(
                new QuantityWeight(2.20462, WeightUnit.POUND),
                new QuantityWeight(1.0, WeightUnit.KILOGRAM)
        );
    }

    // ---------------- CONVERSION ----------------

    @Test
    public void testKgToGramConversion() {
        QuantityWeight q = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        assertEquals(1000.0, q.convertTo(WeightUnit.GRAM).getValue(), 0.0001);
    }

    @Test
    public void testGramToKgConversion() {
        QuantityWeight q = new QuantityWeight(1000.0, WeightUnit.GRAM);
        assertEquals(1.0, q.convertTo(WeightUnit.KILOGRAM).getValue(), 0.0001);
    }

    @Test
    public void testKgToPoundConversion() {
        QuantityWeight q = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        assertEquals(2.20462, q.convertTo(WeightUnit.POUND).getValue(), 0.0001);
    }

    // ---------------- ADDITION ----------------

    @Test
    public void testAddKgAndGram_DefaultUnit() {
        QuantityWeight result =
                new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                        .add(new QuantityWeight(1000.0, WeightUnit.GRAM));

        assertEquals(2.0, result.getValue(), 0.0001);
        assertEquals(WeightUnit.KILOGRAM, result.getUnit());
    }

    @Test
    public void testAddKgAndGram_TargetGram() {
        QuantityWeight result =
                new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                        .add(new QuantityWeight(1000.0, WeightUnit.GRAM), WeightUnit.GRAM);

        assertEquals(2000.0, result.getValue(), 0.0001);
        assertEquals(WeightUnit.GRAM, result.getUnit());
    }

    @Test
    public void testAddKgAndPound() {
        QuantityWeight result =
                new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                        .add(new QuantityWeight(2.20462, WeightUnit.POUND));

        assertEquals(2.0, result.getValue(), 0.01);
    }

    // ---------------- EDGE CASES ----------------

    @Test
    public void testZeroValue() {
        QuantityWeight result =
                new QuantityWeight(5.0, WeightUnit.KILOGRAM)
                        .add(new QuantityWeight(0.0, WeightUnit.GRAM));

        assertEquals(5.0, result.getValue(), 0.0001);
    }

    @Test
    public void testNegativeValue() {
        QuantityWeight result =
                new QuantityWeight(5.0, WeightUnit.KILOGRAM)
                        .add(new QuantityWeight(-2000.0, WeightUnit.GRAM));

        assertEquals(3.0, result.getValue(), 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullUnitException() {
        new QuantityWeight(1.0, null);
    }
}