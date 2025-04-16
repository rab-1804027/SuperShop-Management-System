package Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;

import static com.mysql.cj.conf.PropertyKey.logger;

public class DbConfig {

    private static final Logger logger = LoggerFactory.getLogger(DbConfig.class);
    private static DbConfig singleObject = new DbConfig();

    static AppProperties properties = AppProperties.getSingleObject();

    private DbConfig() {}
    public static Connection connectDb() {
        Connection con = null;
        try {
            Class.forName(AppProperties.getProperty("db.mysql.driver"));
            con = DriverManager.getConnection(AppProperties.getProperty("db.mysql.url"), AppProperties.getProperty("db.mysql.username"), AppProperties.getProperty("db.mysql.password"));
        } catch (Exception e) {
            logger.error("Database connection failed", e.getMessage());
        }
        return con;
    }
}
