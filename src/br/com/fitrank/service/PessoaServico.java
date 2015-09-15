package br.com.fitrank.service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.fitrank.modelo.Amizade;
import br.com.fitrank.modelo.Configuracao;
import br.com.fitrank.modelo.Pessoa;
import br.com.fitrank.persistencia.PessoaDAO;
import br.com.fitrank.util.ConstantesFitRank;
import br.com.fitrank.util.StringUtil;

import com.restfb.types.User;

public class PessoaServico {
	
	private PessoaDAO pessoaDAO;
	private Pessoa pessoa;
	private AmizadeServico amizadeServico;
	private ConfiguracaoServico configuracaoServico;

	public Pessoa adicionaPessoaServico(User usuarioFacebook){
	
		pessoa = new Pessoa();
		this.pessoaDAO = new PessoaDAO();
		
		if(usuarioFacebook.getId()!=null && !usuarioFacebook.getId().equals(""))
			pessoa.setId_usuario(usuarioFacebook.getId());
		
		if(usuarioFacebook.getFirstName()!=null)
			pessoa.setNome(usuarioFacebook.getName());
			
		
		SimpleDateFormat formatter = new SimpleDateFormat(ConstantesFitRank.FORMATO_DATA);
		String formattedDate = formatter.format(new Date());
		pessoa.setData_cadastro(formattedDate);
		
	    try {
			return pessoaDAO.adicionaPessoa(pessoa);
		} catch (SQLException e) {

			e.printStackTrace();
			return null;
		}
	    
	}
	
	public Pessoa atualizaPessoaServico(User usuarioFacebook){
		
		pessoa = new Pessoa();
		this.pessoaDAO = new PessoaDAO();
		
		if(usuarioFacebook.getId()!=null && !usuarioFacebook.getId().equals("")){
			pessoa.setId_usuario(usuarioFacebook.getId());
		}
		
		if(usuarioFacebook.getFirstName()!=null){
			pessoa.setNome(usuarioFacebook.getName());
		}
		
	    try {
			pessoa = pessoaDAO.atualizaPessoa(pessoa);
			preenchePessoa(pessoa);
			
			return pessoa;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	    
	}

	public Pessoa lePessoaServico(User usuarioFacebook){
		
		pessoa = new Pessoa();
		this.pessoaDAO = new PessoaDAO();
		
		if(!StringUtil.isEmptyOrNull(usuarioFacebook.getId())){
			pessoa.setId_usuario(usuarioFacebook.getId());
		}
		
	    try {
			pessoa =  pessoaDAO.lePessoa(pessoa.getId_usuario());
			preenchePessoa(pessoa);
			
			return pessoa;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private void preenchePessoa(Pessoa pessoa) throws SQLException{
		if(pessoa != null){
			preencheListaAmigosPessoa(pessoa);
			recuperaConfiguracaoFavoritaPessoa(pessoa);
		}
	}
	
	private void preencheListaAmigosPessoa(Pessoa pessoa) throws SQLException{
		this.amizadeServico = new AmizadeServico();
		List<Amizade> listaAmizades = amizadeServico.listaAmizades(pessoa.getId_usuario());
		
		ArrayList<Pessoa> amigosPessoa = new ArrayList<Pessoa>();
		
		for (Amizade amizade : listaAmizades) {
			Pessoa amigo = pessoaDAO.lePessoa(amizade.getId_amigo());
			amigosPessoa.add(amigo);
		}
		
		pessoa.setAmigos(amigosPessoa);
	}
	
	private void recuperaConfiguracaoFavoritaPessoa(Pessoa pessoa) throws SQLException{
		this.configuracaoServico = new ConfiguracaoServico();
		Configuracao configuracao = configuracaoServico.leConfiguracaoFavorita(pessoa.getId_usuario());
		pessoa.setConfiguracaoFavorita(configuracao);
	}
//	
//	public boolean removePessoaFromIdServico(User usuarioFacebook){
//		
//		pessoa = new Pessoa();
//		this.pessoaDAO = new PessoaDAO();
//		
//		if(usuarioFacebook.getId()!=null && !usuarioFacebook.getId().equals(""))
//			pessoa.setId_usuario(usuarioFacebook.getId());
//		
//	    try {
//			return pessoaDAO.removePessoaFromId(pessoa);
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return false;
//		}
//	}

}
