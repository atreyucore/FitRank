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
		<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
		<script src="http://connect.facebook.net/pt_BR/all.js"></script>
		<script type="text/javascript">
		
			var periodo = {
					//normalizar para ter somente 4 opcoes
				"0":"Dia", 
				"1":"Semana", 
				"2":"Mês", 
				"3":"Ano",
				"D":"Dia", 
				"S":"Semana",
				"M":"Mês", 
				"A":"Ano"
			};
			
			var modo = {
				"D" : "Distância",
				"V" : "Velocidade"
			};
			
			var modoMedidas = {
				"D" : "km",
				"V" : "km/h"
			};
			
			var modoDescricao = {
				"D" : "Distância",
				"V" : "Velocidade Média"
			};
			
			var modalidade = {
				"R": "runs",
				"W": "walks",
				"B": "bikes"
			};
			
			var modalidadeDescricao = {
				"R": "Corrida",
				"W": "Caminhada",
				"B": "Ciclismo"
			};
			
			var dadosAjax = {};
			
			var token = '<%=(String) request.getParameter("token")%>';
			var json =  JSON.parse('<%=(String) response.getHeader("json")%>');
			
			$(document).ready(function(){
			    $(document).ajaxStart(function () {
			        $("#loading").show();
			    }).ajaxStop(function () {
			        $("#loading").hide();
			    });

				preparaConfiguracao();
			    preparaRanking();
			    var clickDisabled = false;
			   			    	
			   	$(".menu").click(function(e) {
			   		if (clickDisabled)
			            return;
			   		
			   		clickDisabled = true;
			   		
			   		var itemClicado = $(this);
			   		
			   		var toHide = 0; 
			   		
			   		$(".menu").not($(this)).children(".opcao").each(function(index, el) {
			    		
			    		if($(el).css("display") !== 'none') {
			    			toHide++;
			    		}
					});
			   		
			   		var espera = toHide * (125 + 120) + ($(this).children(".opcao").length) * (125 + 120);
			   		
			   		setTimeout(function() {
			   			clickDisabled = false;
			   		}, espera);
			   		
			   		var time = 125;
			   		
			   		//Esconde as opcoes que estão aparecendo
		   			$(".menu").not($(this)).children(".opcao").each(function(index, el){
			    		
			    		if($(el).css("display") !== 'none') {
							setTimeout(function() {
								$(el).toggle('slide', {direction : 'down', duration: 120});
							}, time);
							
							time += 125;
			    		}
					});
			   		
		   			var mainDescriptionButtons = $(".menu").children(":not(.opcao)").children("[class*='chosen']");//.not(".chosenPeriod");
			    	
		   			var spanDescChosen = $(this).children(":not(.opcao)").children("[class*='chosen']");//.not(".chosenPeriod");
// 		   			var esconder = false;
		   			
		   			//Esconde as descricoes que estão aparecendo
		   			$(mainDescriptionButtons).each(function(index, el){
		   				var isElEqualChosen =  $(spanDescChosen).attr('class') === $(el).attr('class'); 
		   				var isPeriod = $(el).hasClass("chosenPeriod");
		   				
		   				if ( !isElEqualChosen && $(el).css("display") !== "none" && !isPeriod) {
		   					
		   					$(this).toggle('slide', {direction : 'down', duration: 120});
// 		   					esconder = true;
		   				} else if (isElEqualChosen && isPeriod) {
		   					$(".headerContent").animate({
								marginBottom : "0"
							},120);
		   				} else if( isElEqualChosen && $(spanDescChosen).css("display") !== "none") {
		   				
		   					$(spanDescChosen).toggle('slide', {direction : 'down', duration: 120});
		   					
		   					$(".headerContent").animate({
								marginBottom : "0"
							},120);
		   				} else if(isElEqualChosen && $(spanDescChosen).css("display") === "none") {
		   					$(spanDescChosen).toggle('slide', {direction : 'down', duration: 120});
		   					
		   					$(".headerContent").animate({
								marginBottom : "20px"
							},120);
		   				}
						
		   			});
		   			
// 		   			$(spanClassesChosen).toggle('slide', {direction : 'down', duration: 120});
		   			
// 		   			setTimeout(function() {
						
// //							if( !$(spanTextChosen).hasClass("chosenPeriod")) {
							
// //							}
						
// 						if($(".headerContent").css("margin-bottom") == 0 || $(".headerContent").css("margin-bottom") == '0px') { 
						
// 							$(".headerContent").animate({
// 								marginBottom : "20px"
// 							},120);
							
							
// 						} else {
// 							$(".headerContent").animate({
// 								marginBottom : "0"
// 							},120);
// 						}
						
// 					}, time);
					
// 					time += 125;
			   		
// 					if(!$(this).hasClass("periodoWrapper")){
						
// 						var spanTextChosen = $(this).children(":not(.opcao)").children("[class*='chosen']");
						
// 						if (spanTextChosen.css("display") === 'none' ) {
// 							spanTextChosen
// 							hiddened = $(this).children(".opcao");
							
// 						} else {
							
// 							hiddened = $(this).children(".opcao").get().reverse();
							
// 						}
// 					}
					
					//circulos de opcoes
			   		var hiddened;
					
					if ($(this).children(".opcao").css("display") === 'none' ) {
						hiddened = $(this).children(".opcao");
					} else {
						hiddened = $(this).children(".opcao").get().reverse();
					}
					
					
					$(hiddened).each(function(index, el){
						
						
						setTimeout(function() {
							$(el).toggle('slide', {direction : 'down', duration: 120});
							
						}, time);
						
						time += 125;
					});
					
				});
			    
			   	$(".opcao").click(function() {
			   		var element = $(this);
			   		
			   		dadosAjax['token'] = token;
			   		dadosAjax[element.attr('data-ref')] = element.children().attr('data-ref');
			   		dadosAjax[element.parent().siblings(".menu").first().children(":not(.opcao)").attr('data-ref')] = element.parent().siblings(".menu").first().children(":not(.opcao)").children(".bgSmall").attr('data-ref');
			   		dadosAjax[element.parent().siblings(".menu").eq(1).children(":not(.opcao)").attr('data-ref')] = element.parent().siblings(".menu").eq(1).children(":not(.opcao)").children(".bgSmall").attr('data-ref');
			   		
			   		var menu = element.siblings(":not(.opcao)").children(".bgSmall");
			   		
			   		var opcao = element.children();
			   		
			   		var menuDataRef = menu.attr("data-ref");
			   		
			   		var menuText = menu.siblings(".capsula").text();
			   		
			   		var opcaoDataRef = opcao.attr("data-ref");
			   		
			   		var opcaoText= opcao.children(".capsula").text();
// 			   		var opcaoText= opcao.text();
			   		menu.removeClass(menuDataRef);
			   		
			   		opcao.removeClass(opcaoDataRef);
			   		
			   		menu.addClass(opcaoDataRef);
			   		
			   		menu.siblings(".capsula").text(opcaoText);
			   		
			   		menu.attr("data-ref", opcaoDataRef);
			   		
			   		opcao.addClass(menuDataRef);
			   		
			   		opcao.children(".capsula").text(menuText);
			   		
			   		opcao.attr("data-ref", menuDataRef);

			   		//para o caso de mudança de periodo
			   		menu.siblings(".chosenPeriod").text(opcaoDataRef);
			   		
			   		opcao.children(".descOpcao").text(menuDataRef);
			   		
			   		
					if (opcaoDataRef == 'Semana') {
						menu.siblings(".chosenPeriod").css("right", "-56px");
			   		} else {
			   			menu.siblings(".chosenPeriod").css("right", "-25px");
			   		}
					
			   		//Troca os valores das propriedades de dados para os aceitos pelo controller.
			   		dadosAjax = prepareProperties(dadosAjax);
			   		
			   		$.ajax({
			   			url: location.origin + location.pathname.substring(0, location.pathname.lastIndexOf("/") + 1) + "CarregaRanking",
			   			data: dadosAjax,
			   			method: 'get',
			   			success: function( data, textStatus, jqXHR){
			   				$(".tableRank>tbody>.rankingLine").remove();
			   				
			   				json = JSON.parse(jqXHR.getResponseHeader('json'));
			   				
			   				competidores = json["@items"];
							
			   				for(index in competidores){
			   					var competidor = competidores[index];
			   					
			   					$(".tableRank>tbody").append("<tr class='rankingLine'></tr>");

			   					var rankingLine = $( $(".rankingLine")[index] );
			   					
			   					rankingLine.append("<td class='colocacao'></td>");
			   					rankingLine.append("<td class='profileImg'><img align='middle' ></td>");
			   					
			   					rankingLine.append("<td class='profileName'></td>");
			   					rankingLine.children(".profileName").append("<span></span>");
			   					
			   					rankingLine.append("<td class='measure'></td>");
			   					
			   					rankingLine.children(".measure").append("<span class='modoSpanEmphasized'></span><br>");
			   					rankingLine.children(".measure").append("<span class='not_emphasized modoSpan'><div class='circle bgTiny'></div></span><br>");
			   					rankingLine.children(".measure").append("<span class='not_emphasized qtdSpan'></span>");
			   					
			   					rankingLine.children(".colocacao").text(competidor.colocacao);
			   					rankingLine.children(".profileImg").children("img").attr("data-id_pessoa", competidor.id_pessoa).attr("src", competidor.pessoa.url_foto === null ? 'imagem/default_photo.png' : competidor.pessoa.url_foto );
			   					rankingLine.children(".profileName").children("span").attr("data-id_pessoa", competidor.id_pessoa).text(competidor.pessoa.nome);
			   					
			   					//Resultado			   					
			   					var resultadoLine = rankingLine.children(".measure").children("span");
			   					
			   					resultadoLine.first().text(modoDescricao[dadosAjax.modo] + " : " + competidor.resultado.toFixed(2) + " " + modoMedidas[dadosAjax.modo] );
			   					
			   					switch(dadosAjax.modo){
			   						case "V":
			   							$(".modoSpan>div").addClass(modo["D"]);
					   					$( resultadoLine[1] ).children("div").first().after(modoDescricao["D"] + " : " + competidor.distancia_percorrida.toFixed(2) + " " + modoMedidas["D"] );
					   					break;
			   						case "D" :
			   							$(".modoSpan>div").addClass(modo["V"]);
			   							$( resultadoLine[1] ).children("div").first().after(modoDescricao["V"] + " : " + competidor.velocidade_media.toFixed(2) + " " + modoMedidas["V"] );
					   					break;
			   					}
			   					
			   					$( resultadoLine[2] ).text("Corridas : " + competidor.quantidade_corridas);
			   				}	
			   			}
			   				
			   		});
			   		
			   	});
			    
			    
			});
			
			function prepareProperties(obj) {

				for (prop in obj) {
					
					switch(prop) {
						case 'modalidade' :
							for(mod in modalidade) {
								if( obj[prop] == modalidade[mod]) {
									obj[prop] = mod;
								}
							}
							break;
						case 'modo' :
							for(m in modo) {
								if( obj[prop] == modo[m]) {
									obj[prop] = m;
								}
							}
							break;
						case 'periodo' :
							for(per in periodo) {
								if( obj[prop] == periodo[per] && isNaN(per)) {
									obj[prop] = per;
								}
							}
							break;
							
					}
					
				}
				
				
				return obj;
			}
			
			function preparaConfiguracao() {
				
				//modalidade
				$("<div></div>").appendTo(".modalidade").addClass(modalidade['<%=(String) request.getAttribute("modalidade")%>'].valueOf())
									.addClass("bgSmall")
									.attr("data-ref", modalidade["<%=(String) request.getAttribute("modalidade")%>"].valueOf());
				
				var alturaModalidade = $(".modalidade").height() * 2 - 10;
				
				$.map(modalidade, function(value, index) { 
					if( value != modalidade['<%=(String) request.getAttribute("modalidade")%>']) {
						var currModalidadeDescricao = modalidadeDescricao[index];
						
						//Adiciona as opcoes
						$("<span class='capsula descOpcaoModalidade'></span>").text(currModalidadeDescricao).appendTo(
								$("<div></div>").addClass(value).addClass('bgTiny').attr('data-ref', value).appendTo( 
									$("<div></div>").appendTo(".modalidadeWrapper").addClass("circle modalidade ranking smallTile opcao").attr('data-ref', 'modalidade').css("display", "none").css("bottom" , alturaModalidade).css("left", $(".modalidade").height())
								)
						);
						
						alturaModalidade += $(".opcao").height() * 2 + 15; 
					
					} else {
						$(".chosenModalidade").text(modalidadeDescricao['<%=(String) request.getAttribute("modalidade")%>']);
					}
				});
				
				//modo
				$("<div></div>").appendTo(".modo").addClass(modo['<%=(String) request.getAttribute("modo")%>'].valueOf())
									.addClass("bgSmall")
									.attr("data-ref", modo["<%=(String) request.getAttribute("modo")%>"].valueOf());
				
				var alturaModo = $(".modo").height() * 2 - 10;
				
				$.map(modo, function(value, index) { 
					if( value != modo['<%=(String) request.getAttribute("modo")%>']) {
						var currModoDescricao = modoDescricao[index]; 
						//Adiciona as opcoes
						$("<span class='capsula descOpcaoModo'></span>").text(currModoDescricao).appendTo(
							$("<div></div>").addClass(value).addClass('bgTiny').attr('data-ref', value).appendTo( 
								$("<div></div>").appendTo(".modoWrapper").addClass("circle modo ranking smallTile opcao").attr('data-ref', 'modo').css("display", "none").css("bottom" , alturaModo).css("left", $(".modo").height())
							)
						);
					
						alturaModo += $(".opcao").height() * 2 + 15;
					} else {
						$(".chosenModo").text(modoDescricao['<%=(String) request.getAttribute("modo")%>']);
					}
				});
				
				//periodo
				$("<div></div>").prependTo(".periodo").addClass("calendario").addClass("bgSmall")
							.attr("data-ref", periodo["<%=(String) request.getAttribute("periodo")%>"].valueOf());
				
				var alturaPeriodo = $(".periodo").height() * 2 - 10;
				
				$(".chosenPeriod").text(periodo['<%=(String) request.getAttribute("periodo")%>']);

				if (periodo['<%=(String) request.getAttribute("periodo")%>'] == 'Semana') {
					$(".chosenPeriod").css("right", "-56px");
		   		} else {
		   			$(".chosenPeriod").css("right", "-25px");
		   		}
				
				
				$.map(periodo, function(value, index) { 
					var currPeriodo = periodo[index];	
				
					if(isNaN(index) && value !== periodo['<%=(String) request.getAttribute("periodo")%>'] ) {
						
						//Adiciona as opcoes
						$("<span class='capsula chosenPeriod descOpcao'></span>").text(currPeriodo).appendTo(
								$("<div></div>").addClass(value).addClass('calendario bgTiny').attr('data-ref', value).appendTo( 
									$("<div></div>").appendTo(".periodoWrapper").addClass("circle periodo ranking smallTile opcao").attr('data-ref', 'periodo').css("display", "none").css("bottom" , alturaPeriodo).css("left", $(".periodo").height())
							)
						);
						
						alturaPeriodo += $(".opcao").height() * 2 + 15;
					}
				});				
				
			} 
			
			//primeira execucao
			function preparaRanking() {		
				
				competidores = json["@items"];
				
   				for(index in competidores) {
   					
   					var competidor = competidores[index];
   					
   					
   					$(".tableRank>tbody").append("<tr class='rankingLine'></tr>");
   					
   					$( $(".rankingLine")[index] ).append("<td class='colocacao'></td>");
   					$( $(".rankingLine")[index] ).append("<td class='profileImg'><img align='middle' ></td>");
   					
   					$( $(".rankingLine")[index] ).append("<td class='profileName'></td>");
   					$( $(".rankingLine")[index] ).children(".profileName").append("<span></span>");
   					
   					$( $(".rankingLine")[index] ).append("<td class='measure'></td>");
   					
   					$( $(".rankingLine")[index] ).children(".measure").append("<span class='modoSpanEmphasized'></span><br>");
   					$( $(".rankingLine")[index] ).children(".measure").append("<span class='not_emphasized modoSpan'><div class='circle bgTiny'></div></span><br>");
   					$( $(".rankingLine")[index] ).children(".measure").append("<span class='not_emphasized qtdSpan'></span>");
   					
   					$( $(".rankingLine")[index] ).children(".colocacao").text(competidor.colocacao);
   					$( $(".rankingLine")[index] ).children(".profileImg").children("img").attr("data-id_pessoa", competidor.id_pessoa).attr("src", competidor.pessoa.url_foto === null ? 'imagem/default_photo.png' : competidor.pessoa.url_foto );
   					$( $(".rankingLine")[index] ).children(".profileName").children("span").attr("data-id_pessoa", competidor.id_pessoa).text(competidor.pessoa.nome);
   					
   					//Resultado			   					
   					var resultadoLine = $( $(".rankingLine")[index] ).children(".measure").children("span");
   					
//    					var modoString = $(".modoWrapper").children(":not(.opcao)").children().attr('data-ref');
   					var modoString = $(".modoWrapper").children(":not(.opcao)").children(".bgSmall").attr('data-ref');
   					
   					resultadoLine.first().text(modoString + " : " + competidor.resultado.toFixed(2) + " " + modoMedidas[modoString.substring(0,1)] );
   					
   					switch(modoString){
						case modo["V"]:
							$(".modoSpan>div").addClass(modo["D"]);
	   						$( resultadoLine[1] ).children("div").first().after(modoDescricao["D"] + " : " + competidor.distancia_percorrida.toFixed(2) + " " + modoMedidas["D"] );
	   					break;
						case modo["D"] :
							$(".modoSpan>div").addClass(modo["V"]);
							$( resultadoLine[1] ).children("div").first().after(modoDescricao["V"] + " : " + competidor.velocidade_media.toFixed(2) + " " + modoMedidas["V"] );
	   					break;
					}
					
					$( resultadoLine[2] ).text("Corridas : " + competidor.quantidade_corridas);
   					
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
				<div class="headerContent rankingHeader">
					<div class="siteHeader">
						<div>
							<span class="logo"> 
								FitRank
							</span>
							<div class="fav" onclick="compartilhar()"> 
								<img class="fav" src="imagem/social24.png" style="border-radius: 50%;background-color: rgb(191, 230, 231);" />
							</div>
						<div class="modalidadeWrapper menu">
							<div class="circle modalidade ranking smallTile" data-ref="modalidade">
								<span class="capsula chosenModalidade"></span>
							</div>
						</div>
						
						<div class="modoWrapper menu">
							<div class="circle modo ranking smallTile " data-ref="modo">
								<span class="capsula chosenModo"></span>
							</div>
						</div>
	
						<div class="periodoWrapper menu">
							<div class="circle periodo ranking smallTile" data-ref="periodo">
	  	 						<span class="capsula chosenPeriod"></span>
	  	 					</div>
						</div>		
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
					</table>
				</div>
			</div>
		</div>
		<div id="loading" style="display:none;background-image:"></div>
	</body>
</html>