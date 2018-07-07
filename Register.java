import java.sql.*;
 
public class Register {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306/pet?useSSL=false&serverTimezone=GMT%2B8";
 
    static final String USER = "root";
    static final String PASS = "xie123";
 
    public static void main(String[] args) {
    	
    	if( args != null && args.length==2 ) {
    		
    		System.out.println("input args:");
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
    	
    	String username = args[0];
    	String password = args[1];
    	boolean registered = false;
    	
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
            sql = "SELECT * FROM user_table where username = '" + username + "'";
            ResultSet rs = stmt.executeQuery(sql);
            rs.last();
            if(rs.getRow() > 0)
            {
            	System.out.println("This name registered!");
            }
            else
            {
            	PreparedStatement pStmt = null;
                try
                {
                	sql = "insert into user_table (username,password) values (?,?)";
                    pStmt = conn.prepareStatement(sql);
                    pStmt.setString(1, username);
                    pStmt.setString(2, password);
                    pStmt.executeUpdate();
                    pStmt.close();
                    registered = true;
                }catch(SQLException e){
                    e.printStackTrace();
                }catch (Exception e) {
                    e.printStackTrace();
                }finally{
                    try{
                        if(pStmt!=null) 
                        {
                        	pStmt.close();
                        }
                    }catch(SQLException se2){
                    	se2.printStackTrace();
                    }
                }
            }
			
			if(registered)
			{
				System.out.println("register success!");
			}
			else
			{
				System.out.println("register failed!");
			}
			
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