package t02;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesReader {
    Map<Object, Object> properties;

    public Map<Object, Object> getProperties() {
        return properties;
    }

    void readAll(String filename) {
        try (FileInputStream f = new FileInputStream(filename)) {
            Properties p = new Properties();
            p.load(f);
            properties = new HashMap<>();
            properties.putAll(p);
        } catch (FileNotFoundException | IllegalArgumentException e) {
//            e.printStackTrace();
            properties = null;
        } catch (IOException e) {
            e.printStackTrace();
            properties = null;
        }
    }

    @SuppressWarnings("unchecked")
    public String readValue(String filename, String key) {
        if (key == null) throw new IllegalArgumentException("The property key mustn't be null.");

        if (properties == null) readAll(filename);

        return (String) properties.getOrDefault(key, "No value found for the key " + key);
    }
}
