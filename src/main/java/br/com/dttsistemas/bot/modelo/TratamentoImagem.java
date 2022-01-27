package br.com.dttsistemas.bot.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="tratamento_imagem")
public class TratamentoImagem {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idTratamento_imagem;
	private Integer idGrupoConta;
	private Integer idConta;	
	private String idCliente;
	private Date data_envio;
	private Integer idFuncionario_Cad;
	private String legenda;	
	@Transient
	private String nomeOriginal;
	private String nomeImagem;
	
	@Enumerated(EnumType.STRING)
	private TipoArquivo tipoArquivo;
	
	@Column(name = "aws_send")
	private boolean awsSend;

	public Integer getIdTratamento_imagem() {
		return idTratamento_imagem;
	}

	public void setIdTratamento_imagem(Integer idTratamento_imagem) {
		this.idTratamento_imagem = idTratamento_imagem;
	}

	public Integer getIdGrupoConta() {
		return idGrupoConta;
	}

	public void setIdGrupoConta(Integer idGrupoConta) {
		this.idGrupoConta = idGrupoConta;
	}

	public Integer getIdConta() {
		return idConta;
	}

	public void setIdConta(Integer idConta) {
		this.idConta = idConta;
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public Date getData_envio() {
		return data_envio;
	}

	public void setData_envio(Date data_envio) {
		this.data_envio = data_envio;
	}

	public Integer getIdFuncionario_Cad() {
		return idFuncionario_Cad;
	}

	public void setIdFuncionario_Cad(Integer idFuncionario_Cad) {
		this.idFuncionario_Cad = idFuncionario_Cad;
	}

	public String getLegenda() {
		return legenda;
	}

	public void setLegenda(String legenda) {
		this.legenda = legenda;
	}

	public String getNomeOriginal() {
		return nomeOriginal;
	}

	public void setNomeOriginal(String nomeOriginal) {
		this.nomeOriginal = nomeOriginal;
	}

	public String getNomeImagem() {
		return nomeImagem;
	}

	public void setNomeImagem(String nomeImagem) {
		this.nomeImagem = nomeImagem;
	}

	public TipoArquivo getTipoArquivo() {
		return tipoArquivo;
	}

	public void setTipoArquivo(TipoArquivo tipoArquivo) {
		this.tipoArquivo = tipoArquivo;
	}

	public boolean isAwsSend() {
		return awsSend;
	}

	public void setAwsSend(boolean awsSend) {
		this.awsSend = awsSend;
	}

	@Override
	public String toString() {
		return "TratamentoImagem [idTratamento_imagem=" + idTratamento_imagem + ", idGrupoConta=" + idGrupoConta
				+ ", idConta=" + idConta + ", idCliente=" + idCliente + ", data_envio=" + data_envio
				+ ", idFuncionario_Cad=" + idFuncionario_Cad + ", legenda=" + legenda + ", nomeOriginal=" + nomeOriginal
				+ ", nomeImagem=" + nomeImagem + ", tipoArquivo=" + tipoArquivo + ", awsSend=" + awsSend + "]";
	}
	
	
}
