package br.com.app.model;

public class MensagemView {
	
	public enum TIPO {
		INFO, SUCESSO, ERRO
	}
	
	private TIPO tipo;
	private String mensagem;
	
	
	private MensagemView(TIPO tipo, String mensagem) {
		super();
		this.tipo = tipo;
		this.mensagem = mensagem;
	}


	public static MensagemView buildInfo(String mensagem) {
		return new MensagemView(TIPO.INFO, mensagem);
	}
	public static MensagemView buildSucesso(String mensagem) {
		return new MensagemView(TIPO.SUCESSO, mensagem);
	}
	public static MensagemView buildErro(String mensagem) {
		return new MensagemView(TIPO.ERRO, mensagem);
	}
	
	public boolean getInfo() {
		return this.tipo.equals(TIPO.INFO);
	}
	public boolean getErro() {
		return this.tipo.equals(TIPO.ERRO);
	}
	public boolean getSucesso() {
		return this.tipo.equals(TIPO.SUCESSO);
	}

	public String getMensagem() {
		return mensagem;
	}
		
}

