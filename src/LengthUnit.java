public enum LengthUnit implements IMeasurable {

    FEET(0.3048),
    INCHES(0.0254),
    YARD(0.9144),
    CENTIMETER(0.01);

    private final double factor;

    LengthUnit(double factor) {
        this.factor = factor;
    }

    @Override
    public double getConversionFactor() {
        return factor;
    }

    @Override
    public String getUnitName() {
        return name();
    }
}