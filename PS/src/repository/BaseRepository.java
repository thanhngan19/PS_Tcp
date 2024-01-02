package repository;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
public class BaseRepository{
    private static final String DB_URL = "jdbc:mysql://localhost:3306/phone";
    private static final String USER = "root";
    private static final String PASS = "";
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;
    static {
        config.setJdbcUrl(DB_URL);
        config.setUsername(USER);
        config.setPassword(PASS);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "4096");
        config.setConnectionTimeout(1500000000);
        config.setMaximumPoolSize(1000000000);
        ds = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
