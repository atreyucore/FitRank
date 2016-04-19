package br.com.fitrank.modelo.apresentacao;

import java.util.List;

import br.com.fitrank.modelo.RankingPessoa;

public class RankingPessoaTela extends RankingPessoa {
	private List<AplicativoTela> listaAplicativosTela;
	
	public RankingPessoaTela(RankingPessoa rankingPessoa){
		this.setColocacao(rankingPessoa.getColocacao());
		this.setDistancia_percorrida(rankingPessoa.getDistancia_percorrida());
		this.setId_pessoa(rankingPessoa.getId_pessoa());
		this.setId_ranking(rankingPessoa.getId_ranking());
		this.setPessoa(rankingPessoa.getPessoa());
		this.setQuantidade_corridas(rankingPessoa.getQuantidade_corridas());
		this.setResultado(rankingPessoa.getResultado());
		this.setVelocidade_media(rankingPessoa.getVelocidade_media());
	}

	public List<AplicativoTela> getListaAplicativosTela() {
		return listaAplicativosTela;
	}

	public void setListaAplicativosTela(List<AplicativoTela> listaAplicativosTela) {
		this.listaAplicativosTela = listaAplicativosTela;
	}

}
