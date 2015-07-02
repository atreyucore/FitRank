package br.com.fitrank.service;

import java.sql.SQLException;

import br.com.fitrank.modelo.Pessoa;
import br.com.fitrank.persistencia.PessoaDAO;

import com.restfb.types.User;

public class PessoaServico {
	
//private EntityManager em;	
	
public PessoaServico(){
// this.em = new JPAUtil().getEntityManager();
}

/*
 * @Parametro:Novo Usuário do Facebook
 */
public Pessoa persistePessoaServico(User usuarioFacebook){
	
	
	//O Usuario que será persistido
	Pessoa pessoa = new Pessoa();
	
	//sempre necessário ter o id do usuario para persistir no SGBD
	if(usuarioFacebook.getId()!=null && !usuarioFacebook.getId().equals(""))
		pessoa.setIdUsuario(usuarioFacebook.getId());
	
//	if(usuarioFacebook.getEmail()!=null)
//		usuario.setEmail(usuarioFacebook.getEmail());

	if(usuarioFacebook.getFirstName()!=null)
		pessoa.setNome(usuarioFacebook.getName());
	
//	if(usuarioFacebook.getBirthday()!=null)
//	   usuario.setDataNascimento(usuarioFacebook.getBirthday());
	
	PessoaDAO persiste = new PessoaDAO();
    try {
		return persiste.persistePessoa(pessoa);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
    
}

}
