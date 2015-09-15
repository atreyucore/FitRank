package br.com.fitrank.modelo;

import java.util.List;

/** Entity(name="pessoa")
 *
 */
public class Pessoa {
//	Column(name="id_usuario", Primary Key)
	private String id_usuario;
//	Column(name="data_cadastro")
	private String data_cadastro;
//	Column(name="nome")
	private String nome;
//	Column(name="data_ultimo_login")	
	private String data_ultimo_login;
	
	private Configuracao configuracaoFavorita;

	private List<Pessoa> amigos;
	
	public String getData_cadastro() {
		return data_cadastro;
	}
	public void setData_cadastro(String data_cadastro) {
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
	public String getData_ultimo_login() {
		return data_ultimo_login;
	}
	public void setData_ultimo_login(String data_ultimo_login) {
		this.data_ultimo_login = data_ultimo_login;
	}
	public Configuracao getConfiguracaoFavorita() {
		return configuracaoFavorita;
	}
	public void setConfiguracaoFavorita(Configuracao configuracaoFavorita) {
		this.configuracaoFavorita = configuracaoFavorita;
	}
	
}
