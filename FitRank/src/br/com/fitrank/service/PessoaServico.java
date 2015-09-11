package br.com.fitrank.service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.fitrank.modelo.Amizade;
import br.com.fitrank.modelo.Pessoa;
import br.com.fitrank.persistencia.PessoaDAO;

import com.restfb.types.User;

public class PessoaServico {
	
	private PessoaDAO pessoaDAO;
	private AmizadeServico amizadeServico;
	private Pessoa pessoa;

	public Pessoa adicionaPessoaServico(User usuarioFacebook){
	
		pessoa = new Pessoa();
		this.pessoaDAO = new PessoaDAO();
		
		if(usuarioFacebook.getId()!=null && !usuarioFacebook.getId().equals(""))
			pessoa.setId_usuario(usuarioFacebook.getId());
		
		if(usuarioFacebook.getFirstName()!=null)
			pessoa.setNome(usuarioFacebook.getName());
			
		
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy"); //TODO gabriel: formato da data com barras?
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
		
		if(usuarioFacebook.getId()!=null && !usuarioFacebook.getId().equals(""))
			pessoa.setId_usuario(usuarioFacebook.getId());
		
		if(usuarioFacebook.getFirstName()!=null)
			pessoa.setNome(usuarioFacebook.getName());
		
		
	    try {
			return pessoaDAO.atualizaPessoa(pessoa);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	    
	}

	public Pessoa lePessoaServico(User usuarioFacebook){
		
		pessoa = new Pessoa();
		this.pessoaDAO = new PessoaDAO();
		
		if(usuarioFacebook.getId()!=null && !usuarioFacebook.getId().equals(""))
			pessoa.setId_usuario(usuarioFacebook.getId());
		
	    try {
			pessoa =  pessoaDAO.lePessoa(pessoa.getId_usuario());
			
			if(pessoa != null){
				amizadeServico = new AmizadeServico();
				List<Amizade> listaAmizades = amizadeServico.listaAmizades(pessoa.getId_usuario());
				
				ArrayList<Pessoa> amigosPessoa = new ArrayList<Pessoa>();
				
				for (Amizade amizade : listaAmizades) {
					Pessoa amigo = pessoaDAO.lePessoa(amizade.getId_amigo());
					amigosPessoa.add(amigo);
				}
				
				pessoa.setAmigos(amigosPessoa);
			}
			
			return pessoa;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
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
