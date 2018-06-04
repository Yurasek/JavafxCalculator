package sample;

public class Operators {
    public int result = 0;

    public String makeResult(String operand, String operator){
        int toInteger = Integer.parseInt(operand);
        switch(operator) {
            case "+":
                result = operatorAdd(toInteger);
                break;
            case "*":
                result = operatorMultiply(toInteger);
                break;
            case "/":
                result = operatorDivide(toInteger);
                break;
            case "-":
                result = operatorSubstract(toInteger);
                break;
        }
        return Integer.toString(result);
    }

    private int operatorAdd(int toInt){
        result += toInt;
        return result;
    }
    private int operatorMultiply(int toInt){
        result *= toInt;
        return result;
    }
    private int operatorDivide(int toInt){
        result /= toInt;
        return result;
    }
    private int operatorSubstract(int toInt){
        result -= toInt;
        return result;
    }
}
