import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @ClassName DBUtils
 * @Description TODO
 * @Author 天马行空
 * @Date 2021/1/27 7:46
 * @Version 1.0
 **/
public class DBUtils_QueryRunnerTest {
    @Test
    public void InsertTest() throws SQLException, IOException, ClassNotFoundException {
        QueryRunner runner = new QueryRunner();

        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");

        Properties pros = new Properties();
        pros.load(is);

        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        String url = pros.getProperty("url");
        String driverClass = pros.getProperty("driverClass");


        Class.forName(driverClass);
        Connection conn = DriverManager.getConnection(url, user, password);
        String sql = "insert into customers(name,email,birth)values(?,?,?)";


        int i = runner.update(conn, sql, "马峥", "mazheng@126.com", "1995-01-18");
        System.out.println("添加了"+ i + "条记录");
        conn.close();
    }

    @Test
    public void QueryTest(){

    }
}
