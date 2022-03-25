package controleFinanceiro;

public class EntradaExtrato {
	String tipo;
	String subtipo;
	String detalhe;
	String bancoPagamento;
	String formaPagamento;
	double valor;
	
	public EntradaExtrato(String tipo, String subtipo, String detalhe, String bancoPagamento, String formaPagamento, double valor) {
		this.tipo = tipo;
		this.subtipo = subtipo;
		this.bancoPagamento = bancoPagamento;
		this.formaPagamento = formaPagamento;
		this.valor = valor;
	}
}
