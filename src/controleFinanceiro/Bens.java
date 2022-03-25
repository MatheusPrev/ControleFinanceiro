package controleFinanceiro;

public class Bens {
	Banco banco;
	String tipoConta;
	double valor;
	
	public Bens(Banco banco, String tipoConta, double valor) {
		this.banco = banco;
		this.tipoConta = tipoConta;
		this.valor = valor;
	}
	
	public void atualizarValor(double valorCompra) {
		this.valor = this.valor + valorCompra;
	}

}
