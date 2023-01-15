package aplikasiobjekwisata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {
    
    static Connection conn;
	public static Connection getConnetion(){
            try{
                    conn = DriverManager.getConnection("jdbc:mysql://localhost/rentalmobilyosi", "root", "");
            } catch(Exception e) {
                    System.out.println(e.toString());
            }
	return conn;
    }
    
}
