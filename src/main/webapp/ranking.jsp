<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import="br.com.fitrank.modelo.RankingPessoa" %>
	<%@ page import="java.util.List" %>
	<%@ page import="java.util.ArrayList" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">



<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Escolher a configuração do Ranking</title>
		<script type="text/javascript" src="js/jquery-1.11.2.js"></script>
		<script src="http://connect.facebook.net/pt_BR/all.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				$(".rankingLine>td>img").one("load", function() {
					buscaInformacoesPerfil(this);
				}).each(function() {
				  if(this.complete) $(this).load();
				});
				
				
				$(".ranking").css("display","none");
			    preparaConfiguracao();
			    
			});
			
			function preparaConfiguracao() {
				
				switch("<%=(String) request.getAttribute("modalidade")%>") {
					case "R":
						$(".run").css("display","");
						break;
					case "W":
						$(".walk").css("display","");
						break;
					case "B":
						$(".bike").css("display","");
						break;
					default:
						break;
				}
				switch("<%=(String) request.getAttribute("modo")%>") {
					case "velocidade":
						$(".velocidade").css("display","");
						break;
					case "distancia":
						$(".distancia").css("display","");
						break;
					default:
						break;
				}
				
				switch("<%=(String) request.getAttribute("turno")%>") {
					case "dia":
						$(".dia").css("display","");
						break;
					case "noite":
						$(".noite").css("display","");
						break;
					default:
						break;
				}
				
				$(".calendario").css("display","");
				
				switch("<%=(String) request.getAttribute("periodo")%>") {
					case "0":
						$(".chosenPeriod").text("Dia");
						break;
					case "1":
						$(".chosenPeriod").text("Semana");
						break;
					case "2":
						$(".chosenPeriod").text("Mês");
						break;
					default:
						break;
				}
			} 
			
			function buscaInformacoesPerfil(element) {
				var idUsuario = $(element).attr("data-id_pessoa");
				
				FB.api(
					  '/' + idUsuario + '/picture',
					  'GET',
					  { "type" : "normal", 
						"access_token" : '<%=(String) request.getParameter("token")%>'},
					  function(response) {
						  $(element).attr("src",response.data.url)
					  }
					);
				
				FB.api(
					  '/' + idUsuario ,
					  'GET',
					  { "fieds" : "name", 
						"access_token" : '<%=(String) request.getParameter("token")%>'},
					  function(response) {
						  $($(element).next()[0]).text(response.name);
					  }
					);
			}
			
			function buscaNomePerfil(element) {
				var idUsuario = $(element).attr("data-id_pessoa");
				
				FB.api(
					  '/' + idUsuario ,
					  'GET',
					  { "fieds" : "name", 
						"access_token" : '<%=(String) request.getParameter("token")%>'},
					  function(response) {
						  $(element).text(response.data.name);
					  }
					);
			}
		</script>
		<link rel="stylesheet" type="text/css" href="./style/css/FitRank.css">
	</head>

	<body>
		<div class="wrapper">
			<div class="preheader"></div>
			<div class="content">
				<div class="headerContent">
					<div class="siteHeader">
						<div>
							<span class="logo"> 
								FitRank<sup class="supCopy">&copy;</sup>
							</span> 
						</div>
					</div>
					<div class="circles">
						<div class="circle walk ranking">
							<img src="imagem/man-silhouette1.png">
						</div>
						
						<div class="circle run ranking" >
							<img src="imagem/sprint.png">
						</div>
						
						<div class="circle bike ranking" >
							<img src="imagem/bike11.png">
						</div>
						
						<div class="circle velocidade ranking" >
							<img src="imagem/speedometer14.png"> 
						</div>

						<div class="circle distancia ranking" >
							<img src="imagem/races.png">
						</div>

						<div class="circle dia ranking" >
							<img src="imagem/sun95.png"> 
						</div>

						<div class="circle noite ranking" >
							<img src="imagem/camera70.png">
						</div>
						
						
						
						<div class="circle calendario ranking smallTile">
  	 						<img src="imagem/calendar157.png">
  	 						<p class="capsula chosenPeriod"></p>
  	 					</div>
					</div>
					
					<div class="footer"></div>
				</div>
				<div class="ranks">
					<table class="tableRank">
						<tr>
							<th>Perfil</th>
							<th>Velocidade Média</th>
						</tr>
						
 						<% 
						ArrayList<RankingPessoa> listaRankingPessoa = (ArrayList<RankingPessoa>) request.getAttribute("listaRanking");
						
						 for(RankingPessoa pessoa : listaRankingPessoa){ 
							 out.println("<tr class='rankingLine'>");
							 out.println("<td>");
							 out.println("<img align='middle' data-id_pessoa='" + pessoa.getId_pessoa() + "' src='imagem/default_photo.png' />");
							 out.println("<span class='profileName' data-id_pessoa='" + pessoa.getId_pessoa() + "' ></span>");
							 out.println("</td>");
							 out.println("<td class='measure'><span>" + pessoa.getResultado()  + " km/h</span></td>");
							 out.println("</tr>");
						 }
						
 						%>
						
					</table>
				</div>
			</div>
		</div>
	</body>
</html>