package br.com.fitrank.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fitrank.modelo.PostFitness;
import br.com.fitrank.modelo.fb.PostFitness.PostFitnessFB;
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
		int postInserido = 0;
		int errosInsercao = 0;
		int totalInserts = 0;
		
		for (PostFitness postFitness : listaPostFitness) {
		    try {
		    	totalInserts++;
	    		if(postFitnessDAO.adicionaListaPostFitness(postFitness)) {
	    			postInserido++;
	    		}
			} catch (SQLException e) {
				errosInsercao++;
			}
		}
		System.out.println("\n\nPosts inseridos:    " + postInserido);
		System.out.println("Erros de insercao:  " + errosInsercao);
		System.out.println("Total de insercoes: " + totalInserts);
		
		return postInserido > 0;
	}
	
	public List<PostFitness> lePostFitnessPorIdPessoa(int idPessoa) {
		this.postFitnessDAO = new PostFitnessDAO();
		
		try {
	    	return postFitnessDAO.lePostFitnessPorIdPessoa(idPessoa);
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
	}
}