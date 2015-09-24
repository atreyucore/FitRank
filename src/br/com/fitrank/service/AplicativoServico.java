package br.com.fitrank.service;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.fitrank.modelo.Aplicativo;
import br.com.fitrank.persistencia.AplicativoDAO;


public class AplicativoServico {
	
	private AplicativoDAO aplicativoDAO;
//	private Aplicativo aplicativo;
//
	public Aplicativo adicionaAplicativoServico(Aplicativo aplicativo){
	
//		pessoa = new Pessoa();
		this.aplicativoDAO = new AplicativoDAO();
		
//		if(usuarioFacebook.getId()!=null && !usuarioFacebook.getId().equals(""))
//			pessoa.setIdUsuario(usuarioFacebook.getId());
//		
//		if(usuarioFacebook.getFirstName()!=null)
//			pessoa.setNome(usuarioFacebook.getName());
//		
		
	    try {
	    	return aplicativoDAO.adicionaAplicativo(aplicativo);
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
	    
	}
	public ArrayList<Aplicativo> adicionaAplicativosServico(ArrayList<Aplicativo> aplicativos){
		
			this.aplicativoDAO = new AplicativoDAO();
				
		    try {
				return aplicativoDAO.adicionaAplicativos(aplicativos);
			} catch (SQLException e) {
				
				e.printStackTrace();
				return null;
			}
		    
		}
	
//	public Pessoa atualizaPessoaServico(User usuarioFacebook){
//		
//		pessoa = new Pessoa();
//		this.pessoaDAO = new PessoaDAO();
//		
//		if(usuarioFacebook.getId()!=null && !usuarioFacebook.getId().equals(""))
//			pessoa.setIdUsuario(usuarioFacebook.getId());
//		
//		if(usuarioFacebook.getFirstName()!=null)
//			pessoa.setNome(usuarioFacebook.getName());
//		
//		
//	    try {
//			return pessoaDAO.atualizaPessoa(pessoa);
//		} catch (SQLException e) {
//			
//			e.printStackTrace();
//			return null;
//		}
//	    
//	}
//
	public Aplicativo leAplicativoServico(String id_aplicativo){
		
		this.aplicativoDAO = new AplicativoDAO();
		
	    try {
			return aplicativoDAO.leAplicativo(id_aplicativo);
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Aplicativo> leListaAplicativosServico(ArrayList<Aplicativo> aplicativos){
		
		this.aplicativoDAO = new AplicativoDAO();
		
	    try {
			return aplicativoDAO.leListaAplicativos(aplicativos);
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
//			pessoa.setIdUsuario(usuarioFacebook.getId());
//		
//	    try {
//			return pessoaDAO.removePessoaFromId(pessoa);
//		} catch (SQLException e) {
//			
//			e.printStackTrace();
//			return false;
//		}
//	}
}
