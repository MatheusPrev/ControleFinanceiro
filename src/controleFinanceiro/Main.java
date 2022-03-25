package controleFinanceiro;

import java.awt.Color;
import java.awt.Font;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main {
	
	static Scanner entrada = new Scanner(System.in);
	static JFrame frame = new JFrame();
	static Banco Itau = new Banco("Itau",15495168,13209);
	static Banco Nubank = new Banco("Nubank",9831887,7013524);
	static Banco[] bancos = {Itau,Nubank};

	public static void main(String[] args) {
		
		Bens ItauCC = new Bens(Itau,"Conta corrente",225.32);
		Bens ItauCredito = new Bens(Itau,"Credito",-25.45);
		Bens ItauPrevidencia = new Bens(Itau,"Previdencia",5231.17);
		Bens NubancCC = new Bens(Nubank,"Conta corrente",107.05);
		Bens NubancCredito = new Bens(Nubank,"Credito",-51.14);
		Bens ItauCDB = new Bens(Itau,"CDB",7127.64);
		Bens[] patrimonio = {ItauCC,ItauCredito,ItauPrevidencia,NubancCC,ItauCDB,NubancCredito};
		
		int opc = menu(patrimonio);
		System.out.println(opc);
		
		//MyFrame myFrame = new MyFrame();
		
		/*
		int opcao = 0;
		do {
			opcao = menu(patrimonio);
			switch(opcao) {
			case 0:
				System.out.println("Até logo...");
				break;
			case 1:
				System.out.println("Você escolheu a opção 1");
				break;
			case 2:
				System.out.println("Você escolheu a opção 2");
				//adcionar(myFrame);
				break;
			default:
				System.out.println("Você escolheu uma opção invalida");
				break;
			}
		}while(opcao !=0);
		*/
		entrada.close();
	}
	
	static public int menu(Bens[] patrimonio) {	
		double valorTotal = 0.0;
		for(Bens i : patrimonio) {
			valorTotal = valorTotal + i.valor;
		}
		
		JLabel label1 = new JLabel();
		label1.setText("Total: R$"+ valorTotal);
		label1.setForeground(Color.green);
		label1.setFont(new Font("Arial",Font.BOLD,30));
		
		JLabel[] tituloBanco = new JLabel[bancos.length];
		JLabel[] valoresMenu = new JLabel[patrimonio.length];
		JLabel[] tipoMenu = new JLabel[patrimonio.length];
		for(int i=0;i<bancos.length;i++) {
			tituloBanco[i] = new JLabel();
			tituloBanco[i].setText(bancos[i].banco);
			tituloBanco[i].setFont(new Font("Arial",Font.BOLD,22));
			tituloBanco[i].setForeground(Color.white);
		}
		for(int i=0;i<patrimonio.length;i++) {
		    valoresMenu[i] = new JLabel();
		    tipoMenu[i] = new JLabel();
		    tipoMenu[i].setText(patrimonio[i].tipoConta);
			valoresMenu[i].setText("R$ " + patrimonio[i].valor);
			tipoMenu[i].setFont(new Font("Arial",Font.BOLD,18));
			valoresMenu[i].setFont(new Font("Arial",Font.BOLD,18));
			tipoMenu[i].setForeground(Color.white);
			valoresMenu[i].setForeground(Color.white);
		}
		
		JPanel total = new JPanel();
		total.setBounds(5,25,300,40);
		total.setBackground(null);
		total.add(label1);
		
		JPanel barra = new JPanel();
		barra.setBounds(0,0,500,20);
		barra.setBackground(Color.white);
		
		
		int vertical = 85;
		JPanel[] topico = new JPanel[patrimonio.length];
		JPanel[] coluna1 = new JPanel[patrimonio.length];
		JPanel[] coluna2 = new JPanel[patrimonio.length];
		for(int i=0;i<bancos.length;i++) {
			topico[i] = new JPanel();
			topico[i].setBounds(5,vertical,300,30);
			vertical += 30;
			topico[i].setBackground(bancos[i].secundaria);
			topico[i].add(tituloBanco[i]);
			int k = 0;
			for(int j=0;j<patrimonio.length;j++) {
				if(patrimonio[j].banco == bancos[i]) {
					k++;
					coluna1[j] = new JPanel();
					coluna2[j] = new JPanel();
					coluna1[j].setBounds(5,vertical,200,25);
					coluna2[j].setBounds(205,vertical,100,25);
					vertical += 25;
					if(k%2==0) {
						coluna1[j].setBackground(bancos[i].primaria);
						coluna2[j].setBackground(bancos[i].primaria);
					}else {
						coluna1[j].setBackground(bancos[i].terciaria);
						coluna2[j].setBackground(bancos[i].terciaria);
					}
					coluna1[j].add(tipoMenu[j]);
					coluna2[j].add(valoresMenu[j]);
				}
			}
			vertical += 10;
		}
		
		MyFrame myFrame = new MyFrame();
		myFrame.setTitle("Controle Financeiro - Menu");
		myFrame.add(total);
		myFrame.add(barra);
		for(int i=0;i<bancos.length;i++){
			myFrame.add(topico[i]);
		}
		for(int i=0;i<patrimonio.length;i++){
			myFrame.add(coluna1[i]);
			myFrame.add(coluna2[i]);
		}
		myFrame.setVisible(true);
		
		return 0;
	}
}
