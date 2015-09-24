package br.com.fitrank.modelo;

/** Entity(name="aplicativo")
 *
 */
public class Aplicativo {
//	Column(name="id_aplicativo", Primary Key)
	private String id_aplicativo;
//	Column(name="nome")
	private String nome;
	
	public String getId_aplicativo() {
		return id_aplicativo;
	}
	public void setId_aplicativo(String id_aplicativo) {
		this.id_aplicativo = id_aplicativo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
