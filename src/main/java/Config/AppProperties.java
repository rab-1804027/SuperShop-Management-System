package Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

public class AppProperties {
    private static final Properties properties = new Properties();
    private static Logger logger = LoggerFactory.getLogger(AppProperties.class);
    private static final AppProperties singleObject = new AppProperties();


    public static AppProperties getSingleObject(){
        return singleObject;
    }

    private AppProperties() {
        try (InputStream input = AppProperties.class.getClassLoader().getResourceAsStream("App.properties")) {
            properties.load(input);
        } catch (Exception e) {
            logger.error("Sorry, unable to find App.properties", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key); // Get the value for the given key
    }
}
