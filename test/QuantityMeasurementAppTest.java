/**
 * QuantityMeasurementAppTest – Unit Tests for UC1: Feet measurement equality
 * 
 * Tests the equality implementation of the Feet inner class
 */
package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.apps.quantitymeasurement.QuantityMeasurementApp.Feet;

public class QuantityMeasurementAppTest {
    
    /**
     * Test Case 1: testFeetEquality_SameValue
     * Verifies that two Feet objects with the same value are considered equal
     * Tests: equals() returns true when comparing 1.0 ft with 1.0 ft
     */
    @Test
    public void testFeetEquality_SameValue() {
        // Given: Two Feet objects with the same value
        Feet feet1 = new Feet(1.0);
        Feet feet2 = new Feet(1.0);
        
        // When: Compare the two Feet objects
        boolean result = feet1.equals(feet2);
        
        // Then: Result should be true
        assertTrue(result, "Two Feet objects with value 1.0 should be equal");
    }
    
    /**
     * Test Case 2: testFeetEquality_DifferentValue
     * Verifies that two Feet objects with different values are not equal
     * Tests: equals() returns false when comparing 1.0 ft with 2.0 ft
     */
    @Test
    public void testFeetEquality_DifferentValue() {
        // Given: Two Feet objects with different values
        Feet feet1 = new Feet(1.0);
        Feet feet2 = new Feet(2.0);
        
        // When: Compare the two Feet objects
        boolean result = feet1.equals(feet2);
        
        // Then: Result should be false
        assertFalse(result, "Two Feet objects with different values (1.0 and 2.0) should not be equal");
    }
    
    /**
     * Test Case 3: testFeetEquality_NullComparison
     * Verifies that a Feet object is not equal to null
     * Tests: equals() returns false when comparing with null
     */
    @Test
    public void testFeetEquality_NullComparison() {
        // Given: A Feet object and a null reference
        Feet feet = new Feet(1.0);
        
        // When: Compare with null
        boolean result = feet.equals(null);
        
        // Then: Result should be false
        assertFalse(result, "A Feet object should not be equal to null");
    }
    
    /**
     * Test Case 4: testFeetEquality_DifferentClass
     * Verifies that a Feet object is not equal to an object of a different class
     * Tests: equals() returns false when comparing with a non-Feet object
     */
    @Test
    public void testFeetEquality_DifferentClass() {
        // Given: A Feet object and a String object
        Feet feet = new Feet(1.0);
        String nonFeetObject = "1.0";
        
        // When: Compare Feet object with a different class object
        boolean result = feet.equals(nonFeetObject);
        
        // Then: Result should be false
        assertFalse(result, "A Feet object should not be equal to a String object");
    }
    
    /**
     * Test Case 5: testFeetEquality_SameReference
     * Verifies that a Feet object is equal to itself (reflexive property)
     * Tests: equals() returns true when comparing an object with itself
     */
    @Test
    public void testFeetEquality_SameReference() {
        // Given: A Feet object
        Feet feet = new Feet(1.0);
        
        // When: Compare the object with itself
        boolean result = feet.equals(feet);
        
        // Then: Result should be true (reflexive property)
        assertTrue(result, "A Feet object should be equal to itself");
    }
    
    /**
     * Test Case 6: testFeetEquality_SymmetricProperty
     * Verifies symmetric property: if a.equals(b) then b.equals(a)
     */
    @Test
    public void testFeetEquality_SymmetricProperty() {
        // Given: Two Feet objects with the same value
        Feet feet1 = new Feet(5.0);
        Feet feet2 = new Feet(5.0);
        
        // When: Compare both directions
        boolean result1 = feet1.equals(feet2);
        boolean result2 = feet2.equals(feet1);
        
        // Then: Both should be true (symmetric property)
        assertTrue(result1 && result2, "Equality should be symmetric: if feet1.equals(feet2) then feet2.equals(feet1)");
    }
    
    /**
     * Test Case 7: testFeetEquality_TransitiveProperty
     * Verifies transitive property: if a.equals(b) and b.equals(c) then a.equals(c)
     */
    @Test
    public void testFeetEquality_TransitiveProperty() {
        // Given: Three Feet objects with the same value
        Feet feet1 = new Feet(5.0);
        Feet feet2 = new Feet(5.0);
        Feet feet3 = new Feet(5.0);
        
        // When: Compare them transitively
        boolean result1 = feet1.equals(feet2);
        boolean result2 = feet2.equals(feet3);
        boolean result3 = feet1.equals(feet3);
        
        // Then: All comparisons should be true (transitive property)
        assertTrue(result1 && result2 && result3, "Equality should be transitive: if feet1.equals(feet2) and feet2.equals(feet3) then feet1.equals(feet3)");
    }
    
    /**
     * Test Case 8: testFeetEquality_FloatingPointPrecision
     * Verifies that floating point values are compared correctly using Double.compare()
     */
    @Test
    public void testFeetEquality_FloatingPointPrecision() {
        // Given: Two Feet objects with precise floating point values
        Feet feet1 = new Feet(1.5);
        Feet feet2 = new Feet(1.5);
        
        // When: Compare the two Feet objects
        boolean result = feet1.equals(feet2);
        
        // Then: Result should be true
        assertTrue(result, "Two Feet objects with floating point values (1.5 and 1.5) should be equal");
    }
}