package controleFinanceiro;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MyFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	MyFrame(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,500);
		//this.setVisible(true);
		ImageIcon image = new ImageIcon("C:\\Users\\mathe\\eclipse-workspace\\ControleFinanceiro\\midia\\logo.png");
		this.setIconImage(image.getImage());
		this.getContentPane().setBackground(new Color(0x4A4A4A));
		this.setLayout(null);
	}
	
}
