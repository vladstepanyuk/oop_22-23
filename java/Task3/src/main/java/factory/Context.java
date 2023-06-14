package factory;

import factory.exception.ParsConfigException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Context {
    private static final String configFileName = "src/main/resources/configuration.txt";
    private static final String EQUAL = "=";
    private static final int linesNumber = 7;
    private static final int StandardNumber = 1;

    private final HashMap<String, Integer> ConfigMap;


    public Context() throws ParsConfigException {
        ConfigMap = new HashMap<>(linesNumber);
        ConfigMap.put("StorageBodySize", StandardNumber);
        ConfigMap.put("StorageMotorSize", StandardNumber);
        ConfigMap.put("StorageAccessorySize", StandardNumber);
        ConfigMap.put("StorageAutoSize", StandardNumber);
        ConfigMap.put("AccessorySuppliers", StandardNumber);
        ConfigMap.put("Workers", StandardNumber);
        ConfigMap.put("Dealers", StandardNumber);

        try {
            FileInputStream inputStream = new FileInputStream(configFileName);
            InputStreamReader streamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(streamReader);
            String line;
            for (int i = 0; i < linesNumber; i++) {
                line = reader.readLine();
                String[] lineSplit = line.split(EQUAL);

                if (lineSplit.length != 2 || !ConfigMap.containsKey(lineSplit[0])) throw new ParsConfigException("wrong config file format");

                int num = Integer.parseInt(lineSplit[1]);
                if (num < 0) throw new ParsConfigException("wrong config file format");
                ConfigMap.put(lineSplit[0], Integer.parseInt(lineSplit[1]));

            }
            reader.close();
            streamReader.close();
        } catch (ParsConfigException e){
            throw  e;
        } catch (Exception e) {
            throw new ParsConfigException("can't parse config file", e);
        }
    }

    public int getStorageBodySize() {
        return ConfigMap.get("StorageBodySize");
    }
    public int getStorageMotorSize() {
        return ConfigMap.get("StorageMotorSize");
    }
    public int getStorageAccessorySize() {
        return ConfigMap.get("StorageAccessorySize");
    }
    public int getStorageAutoSize() {
        return ConfigMap.get("StorageAutoSize");
    }
    public int getAccessorySuppliers() {
        return ConfigMap.get("AccessorySuppliers");
    }
    public int getWorkers() {
        return ConfigMap.get("Workers");
    }
    public int getDealers() {
        return ConfigMap.get("Dealers");
    }
}
