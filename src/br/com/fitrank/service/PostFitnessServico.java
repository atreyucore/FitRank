package br.com.fitrank.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fitrank.modelo.PostFitness;
import br.com.fitrank.persistencia.PostFitnessDAO;

public class PostFitnessServico {
	
	private PostFitnessDAO postFitnessDAO;
	
	public PostFitness adicionaPostFitnessServico(PostFitness postFitness) {
		
		this.postFitnessDAO = new PostFitnessDAO();
		
	    try {
	    	return postFitnessDAO.adicionaPostFitness(postFitness);
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean adicionaListaPostFitnessServico(ArrayList<PostFitness> listaPostFitness) {
		
		this.postFitnessDAO = new PostFitnessDAO();
//		int postInserido = 0;
//		int errosInsercao = 0;
//		int totalInserts = 0;
//		
//		for (PostFitness postFitness : listaPostFitness) {
//		    try {
//		    	totalInserts++;
//	    		if(postFitnessDAO.adicionaListaPostFitness(postFitness)) {
//	    			postInserido++;
//	    		} else {
//	    			errosInsercao++;
//	    		}
//			} catch (SQLException e) {
//				errosInsercao++;
//			}
//		}
//		Logger.insertLog("\n\nPosts inseridos:    " + postInserido);
//		Logger.insertLog("Erros de insercao:  " + errosInsercao);
//		Logger.insertLog("Total de insercoes: " + totalInserts);
//		
//		return postInserido > 0;
		
		try {
			return postFitnessDAO.adicionaListaPostFitness(listaPostFitness);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public List<PostFitness> lePostFitnessPorIdPessoa(String idPessoa) {
		this.postFitnessDAO = new PostFitnessDAO();
		
		try {
	    	return postFitnessDAO.lePostFitnessPorIdPessoa(idPessoa);
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
	}
	
	public String leModalidadeComMaisAtividades(String idPessoa) {
		
		try {
	    	return postFitnessDAO.leModalidadeComMaisAtividades(idPessoa);
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
	}
	
	public String obtemDataPostMaisRecente(String idPessoa) {
		this.postFitnessDAO = new PostFitnessDAO();
		
		try {
	    	return postFitnessDAO.obtemDataPostMaisRecente(idPessoa);
	    	
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}