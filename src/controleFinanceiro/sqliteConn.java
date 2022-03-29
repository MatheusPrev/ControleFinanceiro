package controleFinanceiro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
//import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//import org.sqlite.SQLiteDataSource;

public class sqliteConn {
	
	private Connection connect() {
		String url = "jdbc:sqlite:C:\\Users\\mathe\\eclipse-workspace\\ControleFinanceiro\\BancoDeDados\\myDB.db";
        
		Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
	}
	
	public Banco baixaBancos(int linhaProc){
		
		int linhaSelect = 0;
		String nome = "";
    	int cor1=0;
    	int cor2=0;
    	int cor3=0;
    	
        String sql = "SELECT BancoID, BancoName, Cor1, Cor2, Cor3 FROM Bancos WHERE Ordem <> 0 ORDER BY Ordem";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
            	if(linhaSelect == linhaProc) {
                    nome = rs.getString("BancoName");
                    cor1 = rs.getInt("Cor1");
                    cor2 = rs.getInt("Cor2");
                    cor3 = rs.getInt("Cor3");
            	}
            	linhaSelect ++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        Banco temp = new Banco(nome,cor1,cor2,cor3);
        return temp;
    }
	
	public int selectQtdBancos(){
        String sql = "SELECT count(BancoID) as Qtd FROM Bancos WHERE Ordem <> 0";
        
        int i = 0;
    	int Qtd = 0;
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
            	Qtd = rs.getInt("Qtd");
            	i++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if(i==1) {
        	return Qtd;
        }else {
        	return 0;
        }
    }
	
public String baixaBarraOpc(int linhaProc){
		
		int linhaSelect = 0;
		String nome = "";
    	
        String sql = "SELECT nome FROM BarraDeOpc WHERE ordem <> 0 ORDER BY ordem";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
            	if(linhaSelect == linhaProc) {
                    nome = rs.getString("nome");
            	}
            	linhaSelect ++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return nome;
    }
	
	public int selectBarraOpc(){
        String sql = "SELECT count(nome) as Qtd FROM BarraDeOpc WHERE ordem <> 0";
        
        int i = 0;
    	int Qtd = 0;
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
            	Qtd = rs.getInt("Qtd");
            	i++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if(i==1) {
        	return Qtd;
        }else {
        	return 0;
        }
    }
	
public Bens baixaBens(int linhaProc){
	int linhaSelect = 0;
	String banco = "";
	String tipoConta = "";
	double valor = 0;
	
    String sql = "SELECT banco, tipo, valor FROM Bens WHERE ativa = 1";
    
    try (Connection conn = this.connect();
         Statement stmt  = conn.createStatement();
         ResultSet rs    = stmt.executeQuery(sql)){
        
        // loop through the result set
        while (rs.next()) {
        	if(linhaSelect == linhaProc) {
                banco = rs.getString("banco");
                tipoConta = rs.getString("tipo");
                valor = rs.getDouble("valor");
        	}
        	linhaSelect ++;
        }
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }
    Bens temp = new Bens(banco,tipoConta,valor);
    return temp;
    }
	
	public int selectQtdBens(){
        String sql = "SELECT count(tipo) as Qtd FROM Bens WHERE ativa = 1";
        
        int i = 0;
    	int Qtd = 0;
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
            	Qtd = rs.getInt("Qtd");
            	i++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        if(i==1) {
        	return Qtd;
        }else {
        	return 0;
        }
    }
}