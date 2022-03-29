package controleFinanceiro;

import java.awt.Color;
import java.awt.Font;
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
		
		int opc = menu(patrimonio);
		System.out.println(opc);
		
	}
	
	static public int menu(Bens[] patrimonio) {	
		//baixa dados do banco de dados
		sqliteConn conn = new sqliteConn();
		int qtdBancos = conn.selectQtdBancos();
		Banco[] bancos = new Banco[qtdBancos];
		for(int i=0;i<qtdBancos;i++) {
			bancos[i] = conn.baixaBancos(i);
		}
		int qtdBarraOpc = conn.selectBarraOpc();
		String[] BarraOpc = new String[qtdBarraOpc];
		System.out.println(qtdBarraOpc);
		for(int i=0;i<qtdBarraOpc;i++) {
			System.out.println(i);
			BarraOpc[i] = conn.baixaBarraOpc(i);
		}
		
		//declara novo frame
		MyFrame myFrame = new MyFrame();
				
		//calcula valor total
		double valorTotal = 0.0;
		for(Bens i : patrimonio) {
			valorTotal = valorTotal + i.valor;
		}
		
		//cria labels da barra de Op��es
		JLabel[] labelBarra = new JLabel[BarraOpc.length];
		for(int i=0;i<BarraOpc.length;i++) {
				labelBarra[i] = new JLabel();
				labelBarra[i].setText(BarraOpc[i]);
		}
		
		//cria label do valor total
		JLabel label1 = new JLabel();
		label1.setText("Total: R$"+ valorTotal);
		label1.setForeground(Color.green);
		label1.setFont(new Font("Arial",Font.BOLD,30));
		
		//cria uma linha para cada label de bens
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
		
		//cria painel para valor total
		JPanel total = new JPanel();
		total.setBounds(5,25,300,40);
		total.setBackground(null);
		total.add(label1);
		
		//cria painel de barra de menu
		JPanel[] panelBarra = new JPanel[qtdBarraOpc];
		int tamanhoBarra= myFrame.getWidth()/qtdBarraOpc;
		for(int i=0;i<qtdBarraOpc;i++) {
			panelBarra[i] = new JPanel();
			panelBarra[i].setBounds(i*tamanhoBarra,0,tamanhoBarra,20);
			panelBarra[i].setBackground(Color.white);
			panelBarra[i].add(labelBarra[i]);
		}
		
		//cria um painel para cada linha em bens
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
				if(patrimonio[j].banco.equals(bancos[i].banco)) {
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
		
		//propriedades Frame
		myFrame.setTitle("Controle Financeiro - Menu");
		myFrame.add(total);
		for(int i=0;i<qtdBarraOpc;i++) {
			myFrame.add(panelBarra[i]);
		}
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
