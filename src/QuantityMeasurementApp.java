/**
 * QuantityMeasurementApp – UC1: Feet measurement equality
 * 
 * This class is responsible for checking the equality of two numerical values
 * measured in feet in the Quantity Measurement Application.
 */
package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {
    
    /**
     * Inner class to represent Feet measurement
     * Provides immutability and encapsulation for feet values
     */
    public static class Feet {
        private final double value;
        
        /**
         * Constructor to initialize Feet with a double value
         * @param value the measurement in feet
         */
        public Feet(double value) {
            this.value = value;
        }
        
        /**
         * Override equals() method to compare two Feet objects
         * Implements the Equality Contract:
         * - Reflexive: a.equals(a) returns true
         * - Symmetric: if a.equals(b) then b.equals(a)
         * - Transitive: if a.equals(b) and b.equals(c) then a.equals(c)
         * - Consistent: multiple calls return the same result
         * 
         * Important Checks:
         * 1. Reference Check: If both references point to the same object, return true
         * 2. Null Check: If the compared object is null, return false
         * 3. Type Check: If the objects are not of the same type, return false
         * 4. Value Comparison: Use Double.compare() for precise comparison
         */
        @Override
        public boolean equals(Object obj) {
            // Step 1: Reference Check - if both references point to the same object
            if (this == obj) {
                return true;
            }
            
            // Step 2: Null Check - if the compared object is null
            if (obj == null) {
                return false;
            }
            
            // Step 3: Type Check - if objects are not of the same type
            if (this.getClass() != obj.getClass()) {
                return false;
            }
            
            // Step 4: Value Comparison - safely cast and compare double values
            Feet other = (Feet) obj;
            return Double.compare(this.value, other.value) == 0;
        }
    }
    
    /**
     * Main method to demonstrate Feet equality check
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        // Test Case 1: Same Values
        Feet feet1 = new Feet(1.0);
        Feet feet2 = new Feet(1.0);
        System.out.println("Input: 1.0 ft and 1.0 ft");
        System.out.println("Output: " + (feet1.equals(feet2) ? "Equal (true)" : "Not Equal (false)"));
    }
}