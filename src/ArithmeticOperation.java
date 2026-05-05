public enum ArithmeticOperation {

    ADD {
        @Override
        public double compute(double a, double b) {
            return a + b;
        }
    },

    SUBTRACT {
        @Override
        public double compute(double a, double b) {
            return a - b;
        }
    },

    DIVIDE {
        @Override
        public double compute(double a, double b) {
            if (b == 0) {
                throw new ArithmeticException("Division by zero");
            }
            return a / b;
        }
    };

    public abstract double compute(double a, double b);
}