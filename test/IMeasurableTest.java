import org.junit.Test;
import static org.junit.Assert.*;

public class IMeasurableTest {

    @Test
    public void testWeightUnitImplementation() {
        assertEquals(1.0, WeightUnit.KILOGRAM.getConversionFactor(), 0.0);
        assertEquals("KILOGRAM", WeightUnit.KILOGRAM.getUnitName());
    }

    @Test
    public void testLengthUnitImplementation() {
        assertEquals(0.3048, LengthUnit.FEET.getConversionFactor(), 0.0);
        assertEquals("FEET", LengthUnit.FEET.getUnitName());
    }

    @Test
    public void testConversionConsistency() {
        double kg = WeightUnit.GRAM.convertToBaseUnit(1000);
        assertEquals(1.0, kg, 0.0001);

        double gram = WeightUnit.GRAM.convertFromBaseUnit(1);
        assertEquals(1000.0, gram, 0.0001);
    }
}