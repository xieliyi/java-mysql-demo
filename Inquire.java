import java.sql.*;
 
public class Inquire {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306/pet?useSSL=false&serverTimezone=GMT%2B8";
 
    static final String USER = "root";
    static final String PASS = "xie123";
 
    public static void main(String[] args) {
    	
    	if( args != null && args.length == 1) {
    		
    		System.out.println("args:");
    		for(int i=0;i<args.length;i++) {
    			
    			System.out.print(args[i]);
    			System.out.print(",  ");
    		}
    		System.out.print("\n");
    	}
    	else {

    		System.out.println("args is error!");
    		return;
    	}
    	
    	String pet_sn = args[0];
    	
        Connection conn = null;
        Statement stmt = null;
        try {
        	
            Class.forName(JDBC_DRIVER);
        
            System.out.println("connecting...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
			if(!conn.isClosed()) {
				
				System.out.println("Succeeded connecting to the Database!");
			}
			
            System.out.println("create Statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM pet_table where pet_sn = '" + pet_sn + "'";
            ResultSet rs = stmt.executeQuery(sql);
            rs.last();
			System.out.println("execute Query: record = " + rs.getRow() );
			rs.beforeFirst();
			System.out.println("------------------------------------------------");
            while(rs.next()){
                String id  = rs.getString("pet_id");
                String sn  = rs.getString("pet_sn");
                String name = rs.getString("pet_name");
                String mobile = rs.getString("pet_mobile");
				String address = rs.getString("pet_address");
	
                System.out.println("id:      " + id);
                System.out.println("sn:      " + sn);
                System.out.println("name:    " + name);
                System.out.println("mobile:  " + mobile);
				System.out.println("address: " + address);
            }
			System.out.println("------------------------------------------------");
			
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null) 
                {
                	stmt.close();
                }
            }catch(SQLException se2){
            	se2.printStackTrace();
            }
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}