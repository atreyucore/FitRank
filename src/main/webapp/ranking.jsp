<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import="br.com.fitrank.modelo.RankingPessoa" %>
	<%@ page import="java.util.List" %>
	<%@ page import="java.util.ArrayList" %>
	<%@ page import="java.text.DecimalFormat" %>
	
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
// 					if ($(this).attr("src") == "" || $(this).attr("src") == "null" ) {
// 						$(this).attr("src" , "imagem/default_photo.png");
// 					}
// 					buscaInformacoesPerfil(this);
// 					buscaNomePerfil(this);
				}).each(function() {
// 				  if(this.complete) $(this).load();
				});
				
// 				$(".rankingLine>td>span").one("load", function() {
					
// 				}).each(function() {
// 				  if(this.complete) $(this).load();
// 				});
				
				
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
					case "V":
						$(".velocidade").css("display","");
						break;
					case "D":
						$(".distancia").css("display","");
						break;
					default:
						break;
				}
				
<%-- 				switch("<%=(String) request.getAttribute("turno")%>") { --%>
// 					case "dia":
// 						$(".dia").css("display","");
// 						break;
// 					case "noite":
// 						$(".noite").css("display","");
// 						break;
// 					default:
// 						break;
// 				}
				
				$(".calendario").css("display","");
				
				switch("<%=(String) request.getAttribute("periodo")%>") {
					//Fluxo padrão
					case "0":
						$(".chosenPeriod").text("Dia");
						break;
					case "1":
						$(".chosenPeriod").text("Semana");
						break;
					case "2":
						$(".chosenPeriod").text("Mês");
						break;
					case "3":
						$(".chosenPeriod").text("Ano");
						break;
					//Vindo do favorito	
					case "D":
						$(".chosenPeriod").text("Dia");
						break;
					case "S":
						$(".chosenPeriod").text("Semana");
						break;
					case "M":
						$(".chosenPeriod").text("Mês");
						break;
					case "A":
						$(".chosenPeriod").text("Ano");
						break;
					default:
						break;
				}
			} 
			
// 			function buscaInformacoesPerfil(element) {
// 				var idUsuario = $(element).attr("data-id_pessoa");
				
// 				FB.api(
// 					  '/' + idUsuario + '/picture',
// 					  'GET',
// 					  { "type" : "normal", 
<%-- 						"access_token" : '<%=(String) request.getParameter("token")%>'}, --%>
// 					  function(response) {
// 						  $(element).attr("src",response.data.url)
// 					  }
// 					);
				
// 				FB.api(
// 					  '/' + idUsuario ,
// 					  'GET',
// 					  { "fieds" : "name", 
<%-- 						"access_token" : '<%=(String) request.getParameter("token")%>'}, --%>
// 					  function(response) {
// 						  $($(element).next("span")[0]).text(response.name);
// 					  }
// 					);
// 			}
			
// 			function buscaNomePerfil(element) {
// 				var elemento = $(element).parent().next().children()[0];
// 				var idUsuario =  $(elemento).attr("data-id_pessoa");
				
// 				FB.api(
// 					  '/' + idUsuario ,
// 					  'GET',
// 					  { "fieds" : "name", 
<%-- 						"access_token" : '<%=(String) request.getParameter("token")%>'}, --%>
// 					  function(response) {
// 						  $(elemento).text(response.name);
// 					  }
// 					);
// 			}
			
			function compartilhar() {
				window.open('https://www.facebook.com/dialog/share?app_id=749336888463283&display=popup&href=http://eic.cefet-rj.br/app/FitRank/&redirect_uri=http://eic.cefet-rj.br/app/FitRank/',"fb_share", "width=500, height=500");
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
								FitRank
							</span>
							<div class="fav" onclick="compartilhar()"> 
								<img class="fav" src="imagem/social24.png" style="border-radius: 50%;background-color: rgb(191, 230, 231);" />
							</div>
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
							<th></th>
							<th></th>
							<th>Perfil</th>
							<th></th>
							<%
							String medida = "";
							String atributo = "";
							
							if ( ( (String) request.getAttribute("modo") ).equals("velocidade") || ( (String) request.getAttribute("modo") ).equals("V") ) { 
								atributo = "Velocidade Média";
								medida = "km/h";
// 								out.println("<th>Velocidade Média</th>");
							} else if ( ( (String) request.getAttribute("modo") ).equals("distancia") || ( (String) request.getAttribute("modo") ).equals("D") )  {
								atributo = "Distância";
								medida = "km";
// 								out.println("<th>Distância</th>");
							}%>
						</tr>
						
 						<% 
						ArrayList<RankingPessoa> listaRankingPessoa = (ArrayList<RankingPessoa>) request.getAttribute("listaRanking");
 						DecimalFormat df = new DecimalFormat();
						df.setMaximumFractionDigits(2);
						
						 for(RankingPessoa competidor : listaRankingPessoa){ 
							 out.println("<tr class='rankingLine'>");
							 out.println("<td class='colocacao'>" + competidor.getColocacao() + "</td>");
							 out.println("<td>");
							 out.println("<img align='middle' data-id_pessoa='" + competidor.getId_pessoa() + "' src='" + ((competidor.getPessoa().getUrl_foto() != null) ? competidor.getPessoa().getUrl_foto() : "imagem/default_photo.png") + "' />");
							 out.println("</td>");
							 out.println("<td class='profileName'>");
							 out.println("<span data-id_pessoa='" + competidor.getId_pessoa() + "' >"+ competidor.getPessoa().getNome() +"</span>");
							 out.println("</td>");
							 out.println("<td class='measure'><span>" + atributo + ": " + df.format(competidor.getResultado()) + " " + medida + "</span><br />");
							 
							 if ( ( (String) request.getAttribute("modo") ).equals("velocidade") || ( (String) request.getAttribute("modo") ).equals("V") ) {
								 out.println("<span class='not_emphasized'><div class='circle distancia'><img src='imagem/races.png'></div> " + df.format( competidor.getDistancia_percorrida() ) + " km" + "</span><br />");
							 } else if ( ( (String) request.getAttribute("modo") ).equals("distancia") || ( (String) request.getAttribute("modo") ).equals("D") )  {
								 out.println("<span class='not_emphasized'><div class='circle velocidade'><img src='imagem/speedometer14.png'></div> " + df.format( competidor.getVelocidade_media() ) + " km/h" + "</span><br />");
							 }
							 
							 out.println("<span class='not_emphasized'>Corridas: " + competidor.getQuantidade_corridas() + " " + "</span></td>");
							 
							 
							 out.println("</tr>");
						 }
						
 						%>
						
					</table>
				</div>
			</div>
		</div>
	</body>
</html>