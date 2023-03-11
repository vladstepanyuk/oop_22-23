package calculator.factory;

import calculator.operations.*;


public class FactoryOperations {
//    private static final int DEFINE_ID = DEFINE.ordinal();
//    private final int DIVISION_ID = DIVISION.ordinal();
//    private final int MULTIPLY_ID = MULTIPLY.ordinal();
//    private final int POP_ID = POP.ordinal();
//    private final int PUSH_ID = PUSH.ordinal();
//    private final int PRINT_ID = PRINT.ordinal();
//    private final int SQRT_ID = SQRT.ordinal();
//    private final int SUM_ID = SUM.ordinal();
//    private final int SUBTRACTION_ID = SUBTRACTION.ordinal();

    public static Operation make(OperationIds id) {
        return switch (id) {
            case DEFINE -> new Define();
            case DIVISION -> new Division();
            case MULTIPLY -> new Multiply();
            case POP -> new Pop();
            case PRINT -> new Print();
            case PUSH -> new Push();
            case SQRT -> new Sqrt();
            case SUBTRACTION -> new Subtraction();
            case SUM -> new Sum();
            default -> null;
        };
    }
}
