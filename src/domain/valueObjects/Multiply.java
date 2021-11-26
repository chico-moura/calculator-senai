package domain.valueObjects;


public class Multiply extends Operation {

    @Override
    public Number solve(Number firstNumber, Number secondNumber) {
        return firstNumber.multiply(secondNumber);
    }

    @Override
    public String toString() {
        return "✕";
    }
    
    @Override
    public boolean isMultiply() {
        return true;
    }
    
}
