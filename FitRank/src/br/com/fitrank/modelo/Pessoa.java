package br.com.fitrank.modelo;

/** Entity(name="pessoa")
 *
 */
public class Pessoa {
//	Column(name="id_usuario", Primary Key)
	private String idUsuario;
//	Column(name="nome")
	private String nome;
	
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
