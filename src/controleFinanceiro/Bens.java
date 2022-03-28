package controleFinanceiro;

public class Bens {
	String banco;
	String tipoConta;
	double valor;
	
	public Bens(String banco, String tipoConta, double valor) {
		this.banco = banco;
		this.tipoConta = tipoConta;
		this.valor = valor;
	}
	
	public void atualizarValor(double valorCompra) {
		this.valor = this.valor + valorCompra;
	}

}
