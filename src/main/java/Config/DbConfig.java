package Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConfig {

    private static final Logger logger = LoggerFactory.getLogger(DbConfig.class);
    private static DbConfig singleObject = new DbConfig();

    static ApplicationProperties properties = ApplicationProperties.getSingleObject();

    private DbConfig() {}
    public static Connection connectDb() {
        Connection con = null;
        try {
            Class.forName(ApplicationProperties.getProperty("db.mysql.driver"));
            con = DriverManager.getConnection(ApplicationProperties.getProperty("db.mysql.url"), ApplicationProperties.getProperty("db.mysql.username"), ApplicationProperties.getProperty("db.mysql.password"));
        } catch (Exception e) {
            logger.error("Database connection failed", e.getMessage());
        }
        return con;
    }
}
