package calculator.factory;

import calculator.exception.factory.FactoryException;
import calculator.operations.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;


public class FactoryOperations {
    private static final String SPACE  =  " ";
    static HashMap<OperationIds, Class<?>> idsClassHashMap;

    static {
        idsClassHashMap = new HashMap<>();
    }

    public static void getResourceAsStream(InputStream stream) throws IOException, FactoryException {
        InputStreamReader streamReader = new InputStreamReader(stream);
        BufferedReader reader = new BufferedReader(streamReader);
        String line;
        try {
            for (int i = 0; i < OperationIds.values().length; i++) {
                line = reader.readLine();
                String[] lineSplit = line.split(SPACE);
                if (lineSplit.length != 2) throw new FactoryException("wrong config file format");
                idsClassHashMap.put(OperationIds.valueOf(lineSplit[0]), Class.forName(lineSplit[1]));
            }
            streamReader.close();
            reader.close();
        } catch (ClassNotFoundException e){
            throw  new FactoryException("wrong operation class", e);
        }

    }

    public static Operation make(OperationIds id) throws FactoryException {
        try {
            return switch (id) {
                case DEFINE -> (Operation) idsClassHashMap.get(OperationIds.DEFINE).newInstance();
                case DIVISION ->  (Operation) idsClassHashMap.get(OperationIds.DIVISION).newInstance();
                case MULTIPLY ->  (Operation) idsClassHashMap.get(OperationIds.MULTIPLY).newInstance();
                case POP ->  (Operation) idsClassHashMap.get(OperationIds.POP).newInstance();
                case PRINT ->  (Operation) idsClassHashMap.get(OperationIds.PRINT).newInstance();
                case PUSH ->  (Operation) idsClassHashMap.get(OperationIds.PUSH).newInstance();
                case SQRT ->  (Operation) idsClassHashMap.get(OperationIds.SQRT).newInstance();
                case SUBTRACTION ->  (Operation) idsClassHashMap.get(OperationIds.SUBTRACTION).newInstance();
                case SUM ->  (Operation) idsClassHashMap.get(OperationIds.SUM).newInstance();
                default -> null;
            };
        } catch (Exception e){
            throw new FactoryException("can't make operation object", e);
        }

    }
}
