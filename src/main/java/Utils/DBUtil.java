package Utils;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @title: DBUtil
 * @Author Xu
 * @Date: 24/10/2022 下午 2:41
 * @Version 1.0
 */
public class DBUtil {
    public static final String URL = "jdbc:mysql://localhost:3306/oj_system?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    private static volatile DataSource dataSource = null;

    public static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (DBUtil.class) {
                if (dataSource == null) {
                    dataSource = new MysqlDataSource();
                    ((MysqlDataSource) dataSource).setURL(URL);
                    ((MysqlDataSource) dataSource).setUser(USERNAME);
                    ((MysqlDataSource) dataSource).setPassword(PASSWORD);
                }
            }
        }
        return dataSource;
    }

    public static Connection getConnection() {
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void close(Connection connection,
                             PreparedStatement statement,
                             ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}