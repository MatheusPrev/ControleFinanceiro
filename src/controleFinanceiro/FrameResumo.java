package controleFinanceiro;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FrameResumo extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	FrameResumo(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(700,400);
		ImageIcon image = new ImageIcon("C:\\Users\\mathe\\eclipse-workspace\\ControleFinanceiro\\midia\\logo.png");
		this.setIconImage(image.getImage());
		this.getContentPane().setBackground(new Color(0x4A4A4A));
		this.setLayout(null);
		this.setResizable(false);
		
		//baixa dados do banco de dados
		sqliteConn conn = new sqliteConn();
		int qtdBancos = conn.selectQtdBancos();
		Banco[] bancos = new Banco[qtdBancos];
		for(int i=0;i<qtdBancos;i++) {
			bancos[i] = conn.baixaBancos(i);
		}
		int qtdBarraOpc = conn.selectBarraOpc();
		String[] BarraOpc = new String[qtdBarraOpc];
		for(int i=0;i<qtdBarraOpc;i++) {
			BarraOpc[i] = conn.baixaBarraOpc(i);
		}
		int qtdBens = conn.selectQtdBens();
		Bens[] patrimonio = new Bens[qtdBens];
		for(int i=0;i<qtdBens;i++) {
			patrimonio[i] = conn.baixaBens(i);
		}
						
		//calcula valor total
		double valorTotal = 0.0;
		for(Bens i : patrimonio) {
			valorTotal = valorTotal + i.valor;
		}
		
		//cria buttons da barra de Opções
		JButton[] buttonBarra = new JButton[BarraOpc.length];
		int tamanhoBarra= this.getWidth()/qtdBarraOpc;
		for(int i=0;i<BarraOpc.length;i++) {
			buttonBarra[i] = new JButton();
			buttonBarra[i].setText(BarraOpc[i]);
			buttonBarra[i].setBounds(i*tamanhoBarra,0,tamanhoBarra,20);
			buttonBarra[i].addActionListener(this);
		}
			
		//cria label do valor total
		JLabel label1 = new JLabel();
		label1.setText(String.format("Total: R$%.2f",valorTotal));
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
		
		//cria botão para adicionar compra
				vertical -= 35;
				JButton novoGasto = new JButton();
				novoGasto.setText("Adicionar nova Entrada");
				novoGasto.setBounds(400, vertical, 200, 30);
				novoGasto.addActionListener(this);
			
		//propriedades Frame
		this.setTitle("Controle Financeiro - Menu");
		this.add(total);
		for(int i=0;i<qtdBarraOpc;i++) {
			this.add(buttonBarra[i]);
		}
		this.add(novoGasto);
		for(int i=0;i<bancos.length;i++){
			this.add(topico[i]);
		}
		for(int i=0;i<patrimonio.length;i++){
			this.add(coluna1[i]);
			this.add(coluna2[i]);
		}
		this.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
    	String texto = e.getSource().toString();
    	String[] textoSeparado = texto.split(",");
    	texto = textoSeparado[25];
    	textoSeparado = texto.split("=");
    	texto = textoSeparado[1];
        switch(texto) {
        case "Atualizar":
        	System.out.println("1");
        	break;
		case "Extrato":
			System.out.println("2");
			break;
		case "Ctl. Mensal":
			System.out.println("3");
			break;
		case "Investimentos":
			System.out.println("4");
			break;
		case "Previsões":
			System.out.println("5");
			break;
		case "Compras Ftr.":
			System.out.println("6");
			break;
		case "Configs.":
			System.out.println("7");
			break;
		case "Adicionar nova Entrada":
			System.out.println("8");
			AdcionarEntrada novaEntrada = new AdcionarEntrada();
			novaEntrada.setVisible(true);
			break;
    }
	
	}
	
}
