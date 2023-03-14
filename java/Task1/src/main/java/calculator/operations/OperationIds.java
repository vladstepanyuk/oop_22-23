package calculator.operations;

import calculator.exception.operation.NoSuchOperationException;

import java.util.HashMap;

public enum OperationIds {
    DEFINE("Define"),
    DIVISION("/"),
    MULTIPLY("*"),
    POP("Pop"),
    PRINT("Print"),
    PUSH("Push"),
    SQRT("Sqrt"),
    SUBTRACTION("-"),
    SUM("+");

    private final String name;
    private static final HashMap<String, OperationIds> NameIdMap;
    static {
        NameIdMap = new HashMap<>();
        NameIdMap.put("Define", OperationIds.DEFINE);
        NameIdMap.put("/", OperationIds.DIVISION);
        NameIdMap.put("*", OperationIds.MULTIPLY);
        NameIdMap.put("Pop", OperationIds.POP);
        NameIdMap.put("Print", OperationIds.PRINT);
        NameIdMap.put("Push", OperationIds.PUSH);
        NameIdMap.put("Sqrt", OperationIds.SQRT);
        NameIdMap.put("-", OperationIds.SUBTRACTION);
        NameIdMap.put("+", OperationIds.SUM);
    }
    OperationIds(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public static OperationIds getIdByName(String name) throws NoSuchOperationException {
        if (!NameIdMap.containsKey(name)) throw new NoSuchOperationException(name);
        return NameIdMap.get(name);
    }
}
