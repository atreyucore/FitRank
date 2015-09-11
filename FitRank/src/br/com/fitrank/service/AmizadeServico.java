package br.com.fitrank.service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.fitrank.modelo.Amizade;
import br.com.fitrank.modelo.Pessoa;
import br.com.fitrank.persistencia.AmizadeDAO;
import br.com.fitrank.util.ConstantesFitRank;

public class AmizadeServico {
	
	private AmizadeDAO amizadeDAO = new AmizadeDAO();
	private Amizade amizade;
	
	public Amizade adicionaAmizadeServico(String idPessoa, String idAmigo){
		
		amizade = new Amizade();
		
		SimpleDateFormat formatter = new SimpleDateFormat(ConstantesFitRank.FORMATO_DATA);
		String formattedDate = formatter.format(new Date());
		amizade.setData_amizade(formattedDate);
		
	    try {
	    	//cada amizade e registrada duas vezes no banco, uma para cada usuario
	    	//primeiro registro
	    	amizade.setId_pessoa(idAmigo);
			amizade.setId_amigo(idPessoa);
			amizadeDAO.adicionaAmizade(amizade);
	    	
			//segundo registro
	    	amizade.setId_pessoa(idPessoa);
			amizade.setId_amigo(idAmigo);
	    	
			return amizadeDAO.adicionaAmizade(amizade);
		} catch (SQLException e) {

			e.printStackTrace();
			return null;
		}
	    
	}
	
	public List<Amizade> listaAmizades(String idPessoa){
		ArrayList<Amizade> listaAmigos;
		try {
			listaAmigos = (ArrayList<Amizade>) amizadeDAO.listaAmizades(idPessoa);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return listaAmigos;
	}
}
