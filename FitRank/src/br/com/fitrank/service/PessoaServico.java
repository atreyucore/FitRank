package br.com.fitrank.service;

import java.sql.SQLException;

import br.com.fitrank.modelo.Pessoa;
import br.com.fitrank.persistencia.PessoaDAO;

import com.restfb.types.User;

public class PessoaServico {
	
	private PessoaDAO pessoaDAO;
	private Pessoa pessoa;

	public Pessoa adicionaPessoaServico(User usuarioFacebook){
	
		pessoa = new Pessoa();
		this.pessoaDAO = new PessoaDAO();
		
		if(usuarioFacebook.getId()!=null && !usuarioFacebook.getId().equals(""))
			pessoa.setIdUsuario(usuarioFacebook.getId());
		
		if(usuarioFacebook.getFirstName()!=null)
			pessoa.setNome(usuarioFacebook.getName());
		
		
	    try {
			return pessoaDAO.adicionaPessoa(pessoa);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	    
	}
	
	public Pessoa atualizaPessoaServico(User usuarioFacebook){
		
		pessoa = new Pessoa();
		this.pessoaDAO = new PessoaDAO();
		
		if(usuarioFacebook.getId()!=null && !usuarioFacebook.getId().equals(""))
			pessoa.setIdUsuario(usuarioFacebook.getId());
		
		if(usuarioFacebook.getFirstName()!=null)
			pessoa.setNome(usuarioFacebook.getName());
		
		
	    try {
			return pessoaDAO.atualizaPessoa(pessoa);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	    
	}

	public Pessoa lePessoaServico(User usuarioFacebook){
		
		pessoa = new Pessoa();
		this.pessoaDAO = new PessoaDAO();
		
		if(usuarioFacebook.getId()!=null && !usuarioFacebook.getId().equals(""))
			pessoa.setIdUsuario(usuarioFacebook.getId());
		
	    try {
			return pessoaDAO.lePessoa(pessoa);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean removePessoaFromIdServico(User usuarioFacebook){
		
		pessoa = new Pessoa();
		this.pessoaDAO = new PessoaDAO();
		
		if(usuarioFacebook.getId()!=null && !usuarioFacebook.getId().equals(""))
			pessoa.setIdUsuario(usuarioFacebook.getId());
		
	    try {
			return pessoaDAO.removePessoaFromId(pessoa);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

}
