package utils.helpers;

import utils.enums.PathPropertiesEnum;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

    private final String BASE = "properties/";
    private PropertiesLoader instance = null;
    private Properties properties;

    public PropertiesLoader getInstance(PathPropertiesEnum path) {
        instance = new PropertiesLoader();
        instance.load(BASE + (path.getPath()));
        return instance;
    }

    public PropertiesLoader getInstance() {
        if (instance == null) {
            instance = new PropertiesLoader();
            instance.load(BASE + utils.enums.PathPropertiesEnum.LOGIN.getPath());
        }
        return instance;
    }

    public String getValue(String path, String key) {
        try {
            load(BASE + (path));
            return properties.getProperty(key);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getValue(String key) {
        try {
            return properties.getProperty(key);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void defaultLoad() {
        load(PathPropertiesEnum.LOGIN.getPath());
    }

    private void load(String path) {
        try {
            properties = new Properties();
            properties.load(this.getClass().getClassLoader().getResourceAsStream(path));
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream stream = loader.getResourceAsStream(path);
            properties.load(stream);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
