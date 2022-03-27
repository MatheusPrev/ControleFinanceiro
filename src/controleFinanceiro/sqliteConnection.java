package controleFinanceiro;
import java.sql.*;
//import javax.swing.*;

import javax.swing.JOptionPane;

public class sqliteConnection {
	
	public static Connection ConnectDB() {
		try {
			Class.forName("org.sqlite.jdbc");
		Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\mathe\\eclipse-workspace\\ControleFinanceiro\\BancoDeDados");
		return conn;
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
	
}
