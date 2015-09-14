package br.com.fitrank.modelo;

/** Entity(name="post_fitness")
 *
 */
public class PostFitness {
	
	private String	id_publicacao;
	
	private String  id_pessoa;
	
	private String  data_inicio_corrida;
	
	private String  data_fim_corrida;
	
	private int  	id_app;
	
	private float  	distancia_percorrida;
	
	private float  	duracao;
	
	private String 	data_publicacao;
	
	private String  url;
	
	private String 	modalidade;
	
	public String getId_publicacao() {
		return id_publicacao;
	}
	public void setId_publicacao(String id_publicacao) {
		this.id_publicacao = id_publicacao;
	}
	public String getId_pessoa() {
		return id_pessoa;
	}
	public void setId_pessoa(String id_pessoa) {
		this.id_pessoa = id_pessoa;
	}
	public String getData_inicio_corrida() {
		return data_inicio_corrida;
	}
	public void setData_inicio_corrida(String data_inicio_corrida) {
		this.data_inicio_corrida = data_inicio_corrida;
	}
	public String getData_fim_corrida() {
		return data_fim_corrida;
	}
	public void setData_fim_corrida(String data_fim_corrida) {
		this.data_fim_corrida = data_fim_corrida;
	}
	public int getId_app() {
		return id_app;
	}
	public void setId_app(int id_app) {
		this.id_app = id_app;
	}
	public float getDistancia_percorrida() {
		return distancia_percorrida;
	}
	public void setDistancia_percorrida(float distancia_percorrida) {
		this.distancia_percorrida = distancia_percorrida;
	}
	public float getDuracao() {
		return duracao;
	}
	public void setDuracao(float duracao) {
		this.duracao = duracao;
	}
	public String getData_publicacao() {
		return data_publicacao;
	}
	public void setData_publicacao(String data_publicacao) {
		this.data_publicacao = data_publicacao;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getModalidade() {
		return modalidade;
	}
	public void setModalidade(String modalidade) {
		this.modalidade = modalidade;
	}
	
}
