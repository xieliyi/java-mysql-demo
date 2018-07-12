
import java.sql.*;

public class mysqlDemo {

    public static void main(String[] args) {
        //声明Connection对象
        Connection con;
        //驱动程序名
        String driver = "com.mysql.cj.jdbc.Driver";
        //URL指向要访问的数据库名
        String url = "jdbc:mysql://localhost:3306/pet?useSSL=false&serverTimezone=GMT%2B8";
        //MySQL配置时的用户名
        String user = "root";
        //MySQL配置时的密码
        String password = "xie123";
        //遍历查询结果集
        try {
            //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = con.createStatement();
            //要执行的SQL语句
            String sql = "select * from pet_table";
            //3.ResultSet类，用来存放获取的结果集！！
            ResultSet rs = statement.executeQuery(sql);
            System.out.println("-----------------");
            System.out.println("执行结果如下所示:");  
            System.out.println("-----------------");  

            while(rs.next()){
                String id  = rs.getString("pet_id");
                String sn  = rs.getString("pet_sn");
                String name = rs.getString("pet_name");
                String mobile = rs.getString("pet_mobile");
				String address = rs.getString("pet_address");
	
                System.out.print("id: " + id);
                System.out.print(", sn: " + sn);
                System.out.print(", name: " + name);
                System.out.print(", mobile: " + mobile);
				System.out.print(", address: " + address);
                System.out.print("\n");
            }
            rs.close();
            con.close();
        } catch(ClassNotFoundException e) {   
            //数据库驱动类异常处理
            System.out.println("Sorry,can`t find the Driver!");   
            e.printStackTrace();   
            } catch(SQLException e) {
            //数据库连接失败异常处理
            e.printStackTrace();  
            }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
            System.out.println("数据库数据成功获取！！");
        }
    }

}