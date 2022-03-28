package controleFinanceiro;

import java.sql.Connection;
import java.sql.ResultSet;
//import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.sqlite.SQLiteDataSource;

public class sqliteConn {
	
	public void getConnection(){
		SQLiteDataSource ds = null;
		try {
            ds = new SQLiteDataSource();
            ds.setUrl("jdbc:sqlite:C:\\Users\\mathe\\eclipse-workspace\\ControleFinanceiro\\BancoDeDados\\myDB.db");
        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println( "Opened database successfully" );
		String query = "SELECT * FROM Bancos";

		try ( Connection conn = ds.getConnection();
				Statement stmt = conn.createStatement(); ) {
			//int rv = stmt.executeUpdate(query);
			//System.out.println( "executeUpdate() returned " + rs );
			ResultSet rs = stmt.executeQuery(query);
			//int id = rs.getInt("Bancos.BancoID");
			System.out.println(rs);
			rs.next();
			System.out.println(rs);
			//int id = rs.getInt( "BancoID" );
			//String name = rs.getString( "BancoName" );
		    //System.out.println( "Result: ID = " + id + ", NAME = " + name );
			
			//while ( rs.next() ) {
			    //int id = rs.getInt( "BancoID" );
			    //String name = rs.getString( "BancoName" );
			    //System.out.println( "Result: ID = " + id + ", NAME = " + name );
			//}
		} catch ( SQLException esql ) {
			esql.printStackTrace();
			System.exit( 0 );
		}
		//System.out.println( "Created database successfully" );
	}
}