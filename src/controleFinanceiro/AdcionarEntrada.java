package controleFinanceiro;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AdcionarEntrada extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	JComboBox comboTipo;
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
				
		comboTipo = new JComboBox(tipos);
		comboTipo.addActionListener(this);
		
		//cria botão para adicionar compra
		novoGasto = new JButton();
		novoGasto.setText("Adicionar nova Entrada");
		novoGasto.setBounds(30, 80, 200, 30);
		novoGasto.addActionListener(this);
			
		//propriedades Frame
		this.setTitle("Adicionar Entrada");
		this.add(comboTipo);
		this.add(novoGasto);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
        if(e.getSource()==comboTipo) {
        	System.out.println(comboTipo.getSelectedItem());
        }else if(e.getSource()==novoGasto) {
        	System.out.println(comboTipo.getSelectedItem());
        }
	
	}
	
}
