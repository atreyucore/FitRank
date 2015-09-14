package br.com.fitrank.modelo;

/** Entity(name="aplicativo")
 *
 */
public class Aplicativo {
//	Column(name="id_aplicativo", Primary Key)
	private int id_aplicativo;
//	Column(name="nome")
	private String nome;
	
	public Integer getIdAplicativo() {
		return id_aplicativo;
	}
	public void setIdAplicativo(Integer id_aplicativo) {
		this.id_aplicativo = id_aplicativo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
