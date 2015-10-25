package dbutils;

import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

/** 第一步创建工具类 */
public class DBUtils {
    /** 创建连接池 */
    public static BasicDataSource ds = null;
    static {
        /** 创建属性集用来提取配置文件 */
        Properties prop = new Properties();
        try {
            /** 提取配置文件 */
            prop.load(DBUtils.class.getClassLoader().getResourceAsStream(
                    "db.properties"));
            /** 建立连接池对象 */
            ds = new BasicDataSource();
            /** 设置驱动名称 */
            ds.setDriverClassName(prop.getProperty("driver"));
            /** 设置服务器连接地址 */
            ds.setUrl(prop.getProperty("url"));
            /** 设置服务器帐号 */
            ds.setUsername(prop.getProperty("user"));
            /** 设置服务器密码 */
            ds.setPassword(prop.getProperty("pwd"));
            /** 设置初始连接数 */
            ds.setInitialSize(Integer.parseInt(prop.getProperty("initialSize")));
            /** 设置最大连接数 */
            ds.setMaxIdle(Integer.parseInt(prop.getProperty("maxIdle")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** 创建获得从连接池连接的方法 */
    public static Connection newManagerSystem_gsl() throws Exception {
        Connection conn = null;
        if (ds != null) {
            conn = ds.getConnection();
        }
        return conn;
    }

    /** 创建连接回归连接池的方法 */
    public static void closeConnection(Connection conn) throws Exception {
        if (conn != null) {
            conn.close();
        }
    }
}
