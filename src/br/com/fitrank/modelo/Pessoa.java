package br.com.fitrank.modelo;

import java.util.Date;
import java.util.List;

import br.com.fitrank.util.DateConversor;

/** Entity(name="pessoa")
 *
 */
public class Pessoa {
//	Column(name="id_usuario", Primary Key)
	private String id_usuario;
//	Column(name="data_cadastro")
	private Date data_cadastro;
//	Column(name="nome")
	private String nome;
//	Column(name="data_ultimo_login")	
	private Date data_ultimo_login;
	
	private Configuracao configuracaoFavorita;

	private List<Pessoa> amigos;
	
	public Date getData_cadastro() {
		return data_cadastro;
	}
	public void setData_cadastro(Date data_cadastro) {
		this.data_cadastro = data_cadastro;
	}	
	public String getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(String id_usuario) {
		this.id_usuario = id_usuario;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Pessoa> getAmigos() {
		return amigos;
	}
	public void setAmigos(List<Pessoa> amigos) {
		this.amigos = amigos;
	}
	public Date getData_ultimo_login() {
		return data_ultimo_login;
	}
	public void setData_ultimo_login(Date data_ultimo_login) {
		this.data_ultimo_login = data_ultimo_login;
	}
	public Configuracao getConfiguracaoFavorita() {
		return configuracaoFavorita;
	}
	public void setConfiguracaoFavorita(Configuracao configuracaoFavorita) {
		this.configuracaoFavorita = configuracaoFavorita;
	}
	public String getDataCadastroFormatada() {
		return DateConversor.DateToString(data_cadastro);
	}
	public String getDataUltimoLoginFormatada() {
		return DateConversor.DateToString(data_ultimo_login);
	}
}
