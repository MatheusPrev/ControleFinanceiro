package controleFinanceiro;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
//import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main {

	public static void main(String[] args) {	
		Bens ItauCC = new Bens("Itau","Conta corrente",225.32);
		Bens ItauCredito = new Bens("Itau","Credito",-25.45);
		Bens ItauPrevidencia = new Bens("Itau","Previdencia",5231.17);
		Bens NubancCC = new Bens("Nubank","Conta corrente",107.05);
		Bens NubancCredito = new Bens("Nubank","Credito",-51.14);
		Bens ItauCDB = new Bens("Itau","CDB",7127.64);
		Bens[] patrimonio = {ItauCC,ItauCredito,ItauPrevidencia,NubancCC,ItauCDB,NubancCredito};
		
		MyFrame Menu = new MyFrame(patrimonio);
		
		System.out.println("tchau");
		
	}
	
}
