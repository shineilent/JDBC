import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @ClassName ConnectionTest
 * @Description TODO
 * @Author 天马行空
 * @Date 2021/1/23 15:24
 * @Version 1.0
 **/
public class ConnectionTest {
    @Test
    public void testConnection1() throws SQLException {
        Driver driver = new com.mysql.cj.jdbc.Driver();
        // test---指的是数据库的名字
        String url = "jdbc:mysql://localhost:3306/test";
        // 将用户名和密码封装到  Properties 中
        Properties info = new Properties();
        // todo  "user" 和 "password"的 key 不能变，不能写成 “Username”或其它的 key
        info.setProperty("user", "root");
        info.setProperty("password", "123456");

        Connection conn = driver.connect(url, info);
        System.out.println(conn);

    }

    @Test
    public void testConnection2() throws IOException, ClassNotFoundException, SQLException {
        //  ConnectionTest.class.getClassLoader() --- 调用的是类加载器
        // 1.读取配置文件的4个信息
        InputStream is = ConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties");

        Properties pros = new Properties();
        pros.load(is);
        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        String url = pros.getProperty("url");
        String driverClass = pros.getProperty("driverClass");

        //  2.加载驱动
        Class.forName(driverClass);

        //  3. 获取连接
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);

    }
}
