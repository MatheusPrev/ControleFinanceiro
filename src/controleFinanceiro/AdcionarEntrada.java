package controleFinanceiro;

import java.awt.Color;
import java.awt.FlowLayout;
//import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;

public class AdcionarEntrada extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	JComboBox<?> comboTipo;
	JComboBox<String> comboSubtipo;
	JButton novoGasto;

	AdcionarEntrada(){
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(300,200);
		ImageIcon image = new ImageIcon("C:\\Users\\mathe\\eclipse-workspace\\ControleFinanceiro\\midia\\logo.png");
		this.setIconImage(image.getImage());
		this.getContentPane().setBackground(new Color(0x4A4A4A));
		this.setLayout(new FlowLayout());
		//this.setLayout(null);
		//this.setResizable(false);
		
		//baixa dados do banco de dados
		sqliteConn conn = new sqliteConn();
		int qtdTipos = conn.selectQtdTipos();
		String[] tipos = new String[qtdTipos];
		for(int i=0;i<qtdTipos;i++) {
			tipos[i] = conn.baixaTipos(i);
		}
				
		comboTipo = new JComboBox<Object>(tipos);
		comboTipo.addActionListener(this);
		comboTipo.setBounds(5,5,200,15);
		comboSubtipo = new JComboBox<String>(baixaSubTipo());
		comboSubtipo.addActionListener(this);
		comboSubtipo.setBounds(210,5,200,15);
		
		//cria botão para adicionar compra
		novoGasto = new JButton();
		novoGasto.setText("Adicionar nova Entrada");
		novoGasto.setBounds(30, 80, 200, 30);
		novoGasto.addActionListener(this);
			
		//propriedades Frame
		this.setTitle("Adicionar Entrada");
		//this.removeAll();
		this.add(comboTipo);
		this.add(comboSubtipo);
		this.add(novoGasto);
		this.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
        if(e.getSource()==comboTipo) {
        	String[] teste;
        	teste = baixaSubTipo(comboTipo.getSelectedItem().toString());
        	comboSubtipo.removeAllItems();
        	for(String i : teste) {
        		comboSubtipo.addItem(i);
        	}
        	//this.add(comboSubtipo);
        }else if(e.getSource()==novoGasto) {
        	System.out.println(comboTipo.getSelectedItem());
        }
	
	}
	
	public String[] baixaSubTipo(String tipo) {
		sqliteConn conn = new sqliteConn();
		int qtdSubTipos = conn.selectQtdSubTipos(tipo);
		String[] subTipos = new String[qtdSubTipos];
		for(int i=0;i<qtdSubTipos;i++) {
			subTipos[i] = conn.baixaSubTipos(i,tipo);
		}
		return subTipos;
	}
	
	public String[] baixaSubTipo() {
		sqliteConn conn = new sqliteConn();
		int qtdSubTipos = conn.selectQtdSubTipos();
		String[] subTipos = new String[qtdSubTipos];
		for(int i=0;i<qtdSubTipos;i++) {
			subTipos[i] = conn.baixaSubTipos(i);
		}
		return subTipos;
	}
	
}
