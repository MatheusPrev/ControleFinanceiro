package controleFinanceiro;

import java.awt.Color;

public class Banco {
	String banco;
	Color primaria;
	Color secundaria;
	Color terciaria;
	
	public Banco(String banco, int cor1, int cor2) {
		this.banco = banco;
		this.primaria = new Color(cor1);
		this.secundaria = new Color(cor2);
		int temp = cor1 + 1052688;
		this.terciaria = new Color(temp);
	}
}
