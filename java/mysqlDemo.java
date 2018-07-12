
import java.sql.*;

public class mysqlDemo {

    public static void main(String[] args) {
        //����Connection����
        Connection con;
        //����������
        String driver = "com.mysql.cj.jdbc.Driver";
        //URLָ��Ҫ���ʵ����ݿ���
        String url = "jdbc:mysql://localhost:3306/pet?useSSL=false&serverTimezone=GMT%2B8";
        //MySQL����ʱ���û���
        String user = "root";
        //MySQL����ʱ������
        String password = "xie123";
        //������ѯ�����
        try {
            //������������
            Class.forName(driver);
            //1.getConnection()����������MySQL���ݿ⣡��
            con = DriverManager.getConnection(url,user,password);
            if(!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            //2.����statement���������ִ��SQL��䣡��
            Statement statement = con.createStatement();
            //Ҫִ�е�SQL���
            String sql = "select * from pet_table";
            //3.ResultSet�࣬������Ż�ȡ�Ľ��������
            ResultSet rs = statement.executeQuery(sql);
            System.out.println("-----------------");
            System.out.println("ִ�н��������ʾ:");  
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
            //���ݿ��������쳣����
            System.out.println("Sorry,can`t find the Driver!");   
            e.printStackTrace();   
            } catch(SQLException e) {
            //���ݿ�����ʧ���쳣����
            e.printStackTrace();  
            }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
            System.out.println("���ݿ����ݳɹ���ȡ����");
        }
    }

}