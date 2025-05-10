package Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

public class ApplicationProperties {
    private static final Properties properties = new Properties();
    private static final Logger logger = LoggerFactory.getLogger(ApplicationProperties.class);
    private static final ApplicationProperties singleObject = new ApplicationProperties();


    public static ApplicationProperties getSingleObject(){
        return singleObject;
    }

    private ApplicationProperties() {
        try (InputStream input = ApplicationProperties.class.getClassLoader().getResourceAsStream("application.properties")) {
            properties.load(input);
        } catch (Exception e) {
            logger.error("Sorry, unable to find application.properties", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key); // Get the value for the given key
    }
}
