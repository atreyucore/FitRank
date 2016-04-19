package br.com.fitrank.modelo.apresentacao;

import java.util.List;

import br.com.fitrank.modelo.RankingPessoa;

public class RankingPessoaTela extends RankingPessoa {
	private List<AplicativoTela> listaAplicativosTela;

	public List<AplicativoTela> getListaAplicativosTela() {
		return listaAplicativosTela;
	}

	public void setListaAplicativosTela(List<AplicativoTela> listaAplicativosTela) {
		this.listaAplicativosTela = listaAplicativosTela;
	}

}
