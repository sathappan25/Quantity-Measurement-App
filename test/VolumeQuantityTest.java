import org.junit.Test;
import static org.junit.Assert.*;

public class VolumeQuantityTest {

    // Equality

    @Test
    public void testLitreToMillilitreEquality() {
        Quantity<VolumeUnit> q1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        assertTrue(q1.equals(q2));
    }

    @Test
    public void testLitreToGallonEquality() {
        Quantity<VolumeUnit> litre = new Quantity<>(3.78541, VolumeUnit.LITRE);
        Quantity<VolumeUnit> gallon = new Quantity<>(1.0, VolumeUnit.GALLON);

        assertTrue(litre.equals(gallon));
    }

    @Test
    public void testVolumeNotEqual() {
        Quantity<VolumeUnit> q1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(2.0, VolumeUnit.LITRE);

        assertFalse(q1.equals(q2));
    }

    // Conversion

    @Test
    public void testLitreToMillilitreConversion() {
        Quantity<VolumeUnit> q = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = q.convertTo(VolumeUnit.MILLILITRE);

        assertEquals(1000.0, result.getValue(), 0.01);
    }

    @Test
    public void testGallonToLitreConversion() {
        Quantity<VolumeUnit> q = new Quantity<>(1.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> result = q.convertTo(VolumeUnit.LITRE);

        assertEquals(3.78541, result.getValue(), 0.01);
    }

    // Addition

    @Test
    public void testAdditionSameUnit() {
        Quantity<VolumeUnit> q1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(2.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> result = q1.add(q2);

        assertEquals(3.0, result.getValue(), 0.01);
    }

    @Test
    public void testAdditionCrossUnit() {
        Quantity<VolumeUnit> q1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> result = q1.add(q2);

        assertEquals(2.0, result.getValue(), 0.01);
    }

    @Test
    public void testAdditionWithTargetUnit() {
        Quantity<VolumeUnit> q1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> q2 = new Quantity<>(1.0, VolumeUnit.GALLON);

        Quantity<VolumeUnit> result = q1.add(q2, VolumeUnit.MILLILITRE);

        assertEquals(4785.41, result.getValue(), 0.01);
    }

    // Cross Category Safety

    @Test
    public void testVolumeVsLength() {
        Quantity<VolumeUnit> v = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<LengthUnit> l = new Quantity<>(1.0, LengthUnit.FEET);

        assertFalse(v.equals(l));
    }

    @Test
    public void testVolumeVsWeight() {
        Quantity<VolumeUnit> v = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<WeightUnit> w = new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertFalse(v.equals(w));
    }
}