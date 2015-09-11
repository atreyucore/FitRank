package br.com.fitrank.service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.fitrank.modelo.Amizade;
import br.com.fitrank.persistencia.AmizadeDAO;
import br.com.fitrank.util.ConstantesFitRank;

public class AmizadeServico {
	
	private AmizadeDAO amizadeDAO;
	private Amizade amizade;
	
	public Amizade adicionaAmizadeServico(String idPessoa, String idAmigo){
		
		amizade = new Amizade();
		this.amizadeDAO = new AmizadeDAO();
		
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
}
