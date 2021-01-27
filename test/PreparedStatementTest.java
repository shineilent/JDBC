import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.util.Date;
import java.util.Properties;

/**
 * @ClassName PreparedStatementTest
 * @Description TODO
 * @Author 天马行空
 * @Date 2021/1/24 1:44
 * @Version 1.0
 **/
public class PreparedStatementTest {
    @Test
    // 向表中添加 一条数据
    public void testInsert() throws SQLException, ClassNotFoundException, IOException, ParseException {
        // 1.读取配置文件的4个信息
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");

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
//        System.out.println(connection);


        //  4. 预编译sql语句，返回 prepareStatement 的实例
        String sql = "insert into customers(name,email,birth)values(?,?,?)"; // ? 占位符
        PreparedStatement ps = connection.prepareStatement(sql);

        // 5.填充占位符
        ps.setString(1,"Jason");
        ps.setString(2,"mz@Outlook.com");


        // 设置好日期的格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = sdf.parse("1995-01-18");
        // 把  java.util.Date 转换为 java.sql.Date ,两个的日期的毫秒数（getTime()）是一样的
        ps.setDate(3, new Date(date.getTime()));



        // 6. 执行操作
        ps.execute();

        //  7.资源的关闭
        ps.close();
        connection.close();



    }
}
