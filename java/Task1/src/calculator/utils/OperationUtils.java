package calculator.utils;

import calculator.operations.OperationIds;

import java.util.HashMap;

public class OperationUtils {
    private static final HashMap<String, OperationIds> NameIdMap;
    private static final HashMap<OperationIds, String> idNameMap;

    static {
        idNameMap = new HashMap<>();
        idNameMap.put(OperationIds.DEFINE, "Define");
        idNameMap.put(OperationIds.DIVISION, "/");
        idNameMap.put(OperationIds.MULTIPLY, "*");
        idNameMap.put(OperationIds.POP, "Pop");
        idNameMap.put(OperationIds.PRINT, "Print");
        idNameMap.put(OperationIds.PUSH, "Push");
        idNameMap.put(OperationIds.SQRT, "Sqrt");
        idNameMap.put(OperationIds.SUBTRACTION, "-");
        idNameMap.put(OperationIds.SUM, "+");
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

    public static String getNameById(OperationIds id){
        return idNameMap.get(id);
    }

    public static OperationIds getIdByName(String name){
        return NameIdMap.get(name);
    }

    public static boolean isOperationName(String name){
        return NameIdMap.containsKey(name);
    }
}
