package br.com.dttsistemas.bot.modelo;

public enum TipoArquivo {
	
	IMG("IMG"),
	PDF("PDF");
	
	private String descricao;
	
	TipoArquivo(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}
