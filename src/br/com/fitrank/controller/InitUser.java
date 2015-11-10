package br.com.fitrank.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fitrank.modelo.Pessoa;
import br.com.fitrank.service.AmizadeServico;
import br.com.fitrank.service.PessoaServico;
import br.com.fitrank.util.ConstantesFitRank;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.User;

public class InitUser extends HttpServlet {

	private static final long serialVersionUID = 1L;

	PessoaServico pessoaServico = new PessoaServico();
	AmizadeServico amizadeServico = new AmizadeServico();

	public InitUser() {

	}

	protected void inicia(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	   
	   FacebookClient facebookClient = new DefaultFacebookClient(request.getParameter("token"));

	   User facebookUser = facebookClient.fetchObject("me", User.class);
	   
	   Connection<User> friendsFB = facebookClient.fetchConnection("me/friends", User.class, Parameter.with("fields", "name, id"));
	   
	   Pessoa pessoa = new Pessoa();
	   
	   if(facebookUser.getId()!=null && !facebookUser.getId().equals("")){
		   pessoa.setId_usuario(facebookUser.getId());
	   }
		
	   if(facebookUser.getFirstName()!=null){
		   pessoa.setNome(facebookUser.getName());
	   }
	   
	   if(facebookUser.getGender()!=null){
		   if(facebookUser.getGender().equalsIgnoreCase(ConstantesFitRank.FACEBOOK_FEMALE_GENDER)){
			   pessoa.setGenero(ConstantesFitRank.SEXO_FEMININO);
		   } else if(facebookUser.getGender().equalsIgnoreCase(ConstantesFitRank.FACEBOOK_MALE_GENDER)){
			   pessoa.setGenero(ConstantesFitRank.SEXO_MASCULINO);
		   }
	   }
	   
	   if(facebookUser.getBirthdayAsDate()!=null){
		   pessoa.setData_nascimento(facebookUser.getBirthdayAsDate());
	   }
	   
	   Pessoa usuarioExistente = pessoaServico.lePessoaServico(facebookUser);
	   
	   if (usuarioExistente == null ) {
		   pessoa = pessoaServico.adicionaPessoaServico(pessoa);
	   } else {
		   pessoa.setData_ultima_atualizacao_runs(usuarioExistente.getData_ultima_atualizacao_runs());
		   pessoa.setData_ultima_atualizacao_walks(usuarioExistente.getData_ultima_atualizacao_walks());
		   pessoa.setData_ultima_atualizacao_bikes(usuarioExistente.getData_ultima_atualizacao_bikes());
		   pessoa.setRank_anual(usuarioExistente.getRank_anual());
		   pessoa = pessoaServico.atualizaPessoaServico(pessoa);
	   }
	   	
//	   String[] profPicUrls = new String[friendsFB.getData().size()];
//	   String[] ids = new String[friendsFB.getData().size()];
//	   for (int i=0;i < friendsFB.getData().size(); i++) {
	   for ( User friendFB : friendsFB.getData()) {
//		  String idAmigo = friendsFB.getData().get(i).getId();
		   
		  atualizaAmizadeUsuario(facebookUser, friendFB);

//		  ids[i] = id;
//		  profPicUrls[i] = friendsFB.getData().get(i).getPicture() != null ? friendsFB.getData().get(i).getPicture().getUrl() : "imagem/401.png" ;
		 
	   }
	   
	   
		   
	   
//TODO gvsribeiro utilizar dados da configuracao do usuario para montar proxima tela 
	   request.setAttribute("token", request.getParameter("token"));
	   
	   RequestDispatcher rd = request.getRequestDispatcher("/escolheModalidade.jsp");  
	   rd.forward(request,response);  
   }

	private void atualizaAmizadeUsuario(User facebookUser, User friendFB) {
		
		String idAmigo = friendFB.getId();
		
		if (amizadeServico.leAmizadeServico(facebookUser.getId(), idAmigo) == null) {
			amizadeServico.adicionaAmizadeServico(facebookUser.getId(), idAmigo);
		}
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		inicia(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		inicia(request, response);
	}

}
