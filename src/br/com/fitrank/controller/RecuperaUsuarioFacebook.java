package br.com.fitrank.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fitrank.modelo.Pessoa;
import br.com.fitrank.service.PessoaServico;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.User;


//@WebServlet("/RecuperaUsuarioFacebook")
public class RecuperaUsuarioFacebook extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
    public RecuperaUsuarioFacebook() {
      
    }

   
  protected void inicia(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	   
	   FacebookClient facebookClient = new DefaultFacebookClient(request.getParameter("token"));
	   User facebookUser = facebookClient.fetchObject("me", User.class);
	   Connection<User> friendsFB = facebookClient.fetchConnection("me/friends", User.class, Parameter.with("fields", "name, id, picture"));

	   String[] profPicUrls = new String[friendsFB.getData().size()];
	   String[] ids = new String[friendsFB.getData().size()];
	   for (int i=0;i < friendsFB.getData().size(); i++) {
		  String id = friendsFB.getData().get(i).getId();
		  ids[i] = id;
		  profPicUrls[i] = friendsFB.getData().get(i).getPicture() != null ? friendsFB.getData().get(i).getPicture().getUrl() : "imagem/401.png" ;
		 
	   }
//	   runsJson = runsJson + "]}";
	   
//	   Usuario usuario = new Usuario();
	   Pessoa pessoa = new Pessoa();
	   PessoaServico pessoaServico = new PessoaServico();
	   if(facebookUser.getId()!=null && !facebookUser.getId().equals("")){
		   pessoa.setId_usuario(facebookUser.getId());
	   }
		
	   if(facebookUser.getFirstName()!=null){
		   pessoa.setNome(facebookUser.getName());
	   }
	   pessoa = pessoaServico.adicionaPessoaServico(pessoa);
       
	   //teste crud pessoa
//	   Pessoa pessoaSelecionada =  new Pessoa();
	   
//	   pessoaSelecionada = pessoaServico.lePessoaServico(facebookUser);
//	   pessoaSelecionada = pessoaServico.atualizaPessoaServico(facebookUser);
	   
//	   boolean removido;
//	   removido = pessoaServico.removePessoaFromIdServico(facebookUser);
//	   request.setAttribute("nome_usuario", usuario.getNome());
//	   request.setAttribute("id_usuario", usuario.getId());
//	   request.setAttribute("email_usuario", usuario.getEmail());
//	   request.setAttribute("dtnasc_usuario", usuario.getDataNascimento());
	  /* request.setAttribute("friendsCount", friendsFB.getData().size());
	   request.setAttribute("friendsNames", friendsName);
	   request.setAttribute("myPosts", postsString);
	   */
	   request.setAttribute("token", request.getParameter("token"));
	   request.setAttribute("ids", ids);
	   request.setAttribute("profPicUrl", profPicUrls);
	   
	   
	   RequestDispatcher rd = request.getRequestDispatcher("/escolheUsuario.jsp");  
	   rd.forward(request,response);  
   }
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		inicia(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		inicia(request, response);
	}

}
