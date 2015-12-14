<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Escolher a configuração do Ranking</title>
		<link rel="stylesheet" type="text/css" href="./style/css/FitRank.css">
		<script type="text/javascript" src="js/jquery-1.11.2.js"></script>
		<script src="js/jquery-ui-1.11.4/jquery-ui.js"></script>
		<script src="plugins/slider/js/jquery-ui-slider-pips.js"></script>
		<link rel="stylesheet" href="plugins/slider/css/jquery-ui-slider-pips.css" >                
		<link rel="stylesheet" href="https://code.jquery.com/ui/1.10.4/themes/flick/jquery-ui.css">
		
		<script type="text/javascript">
		
			$(document).ready(function(){
				preparaSlider();
				preparaEscolhas(".velocidade,.distancia", "selectedMode");
				preparaEscolhas(".dia,.noite", "selectedShift");
				
				preparaConfiguracaoPadrao();
			});
			
			
			function validaPreenchimento() {
				
				if ( $(".selectedMode")[0] == undefined ) {
					alert('Preencha o modo do Ranking (Velocidade Média ou Distância).')
					return false;
				}
				
				return true;
			}
			
			function preparaConfiguracaoPadrao() {
				if ("<%=(String) request.getAttribute("default")%>" === "S") {
					switch("<%=(String) request.getAttribute("modo")%>") {
						case "V":
							$(".velocidade").click();
							break;
						case "D":
							$(".distancia").click();
							break;
						default:
							break;
					}
					
					switch("<%=(String) request.getAttribute("turno")%>") {
						case "D":
							$(".dia").click();
							break;
						case "N":
							$(".noite").click();
							break;
						default:
							break;
					}
					
					switch("<%=(String) request.getAttribute("periodo")%>") {
						case "D":
							$(".slider").slider({value: 0});
							break;
						case "S":
							$(".slider").slider({value: 1});
							break;
						case "M":
							$(".slider").slider({value: 2});
							break;
						case "A":
							$(".slider").slider({value: 3});
							break;
						default:
							break;
					}
				}
			}
			
			function preparaSlider() {
				var time = ["Dia", "Semana", "M&ecirc;s", "Ano"];
				
				$(".slider").slider({ 
					min: 0, 
			        max: time.length-1
// 			        , 
// 			        orientation: "vertical"
			    }).slider("pips", {
			        rest: "label",
			        labels: time
			    });
				
				$(".ui-slider-handle.ui-state-default.ui-corner-all").append("<div class='circle calendario calendarioEscolhe'><img src='imagem/calendar157.png'></div>");
				
// 				$(".ui-slider-pip").each(function() {
// 					var bottomInitial = $(this).css("bottom");
// 					bottomInitial.replace("%","");
// 					bottomInitial = parseInt(bottomInitial) -40;
// 					bottomInitial = bottomInitial + "%";
// 					$(this).css("bottom", bottomInitial); 
// 				});				
			}
			
			function preparaEscolhas(classesEscolhas, classeModificadora) {
				$(classesEscolhas).on("click", function() {
					
					if(this.className.indexOf(classeModificadora) >= 0){
						$(this).removeClass(classeModificadora);
					} else {
						$(".circle").removeClass(classeModificadora);
						$(this).addClass(classeModificadora);
					}
		
				});
			}
			
			
			function handleConfiguracao(botao) {
				
				if ($(".selectedMode").size() != 0){
					var modo = $(".selectedMode").attr("class").split(" ")[1];
				}
				
				if ($(".selectedShift").size() != 0){
					var turno = $(".selectedShift").attr("class").split(" ")[1];
				}
				
				var periodo = $(".ui-slider-pip-selected>.ui-slider-label").attr("data-value");
				
				switch(botao) {
					case "favorito":
						salvarFavorito(modo, turno, periodo);
						break;
					case "padrao":
						salvarPadrao(modo, turno, periodo);
						break;
					case "ranking":
						gerarRanking(modo, turno, periodo);
						break;
					default:
						break;
				}
			} 
			
			function salvarFavorito(modo, turno, periodo){
				if(validaPreenchimento()) {
					var jqxhr = $.ajax( "SalvaConfiguracao?fav=S&default=N&modalidade=" + 
							'<%=(String) request.getAttribute("modalidade")%>' + "&modo=" + modo + 
							/*"&turno=" + turno + */"&periodo=" + periodo + "&token="
							+ '<%=(String) request.getAttribute("token")%>');
					jqxhr.done(function() {
						alert('Favorito salvo com sucesso');
					});
				}
			}
			
			function salvarPadrao(modo, turno, periodo){
				if(validaPreenchimento()) {
					var jqxhr = $.ajax("SalvaConfiguracao?fav=N&default=S&modalidade=" + 
							'<%=(String) request.getAttribute("modalidade")%>' + "&modo=" + modo + 
							/*"&turno=" + turno + */"&periodo=" + periodo + "&token="
							+ '<%=(String) request.getAttribute("token")%>');
					jqxhr.done(function() {
						alert('Padrao salvo com sucesso');
					});
				}
			}
			
			function gerarRanking(modo, turno, periodo){
				if ( validaPreenchimento() == true ) {
					
					window.location = "CarregaRanking?modalidade=" + 
						'<%=(String) request.getAttribute("modalidade")%>' + "&modo=" + modo + "&turno=" + turno + "&periodo=" + periodo + "&token="
						+ '<%=(String) request.getAttribute("token")%>';
				}
			}
		</script>
	</head>

	<body>
		<div class="wrapper">
			<div class="preheader"></div>
			<div class="content">
				<div class="headerContent">
					<div class="siteHeader">
						<div class="fitHeader">
							<span class="logo"> 
								FitRank
							</span> 
							<div class="fav"> 
<!-- 								salva padrao -->
								<img class="fav" src="imagem/tick11_big.png" style="border-radius: 50%;background-color: rgb(101,166,133);"
									onclick = "handleConfiguracao('padrao')"/>
<!-- 								salva favorito -->
								<img class="fav" src="imagem/star212_big.png" style="border-radius: 50%;background-color: rgb(241,239,169);" 
									onclick = "handleConfiguracao('favorito')"/>
<!-- 								<img class="fav" src="imagem/social24.png" style="border-radius: 50%;background-color: rgb(191, 230, 231);" /> -->
								<img class="fav" src="imagem/medal52.png" style="border-radius: 50%;background-color: rgb(193, 74, 74);" 
									onclick="handleConfiguracao('ranking')"/>
							</div>
						</div>
					</div>
	<!-- 				<div> -->
	<!-- 					<img class="rank" src="imagem/game44.png" /> -->
						
	
	<!-- 				</div> -->
					<div class="circles">
						<div class="circleWrapper rankChoose">
							<div class="circle velocidade" >
								<img src="imagem/speedometer14.png"> 
							</div>
						</div>
						
<!-- 						<div class="circleWrapper rankChoose"> -->
<!-- 							<div class="circle dia" > -->
<!-- 								<img src="imagem/sun95.png">  -->
<!-- 							</div> -->
<!-- 						</div> -->
						<div class="circleWrapper rankChoose">
							<div class="circle distancia" >
								<img src="imagem/races.png">
							</div>
						</div>
<!-- 						<div class="circleWrapper rankChoose slider"> -->
<!-- 							<div class="circle calendario"> -->
<!-- 	  	 						<img src="imagem/calendar157.png"> -->
<!-- 	  	 					</div> -->
<!-- 						</div> -->
<!-- 						<div class="circleWrapper rankChoose" style="position: relative;top: 200px;"> -->
	<!-- 						<div class="circle bestRank"> -->
<!-- 	  	 						<img src="imagem/game44.png"> -->
	<!--   	 						<div class="circle global" > -->
<!-- 									<img class="global" src="imagem/world91.png"> -->
	<!-- 							</div> -->
	<!--   	 					</div> -->
<!-- 						</div> -->
					</div>
					<div class="circles">
						<div class="circleWrapper rankChoose slider">
						</div>
						
<!-- 						<div class="circleWrapper rankChoose"> -->
<!-- 							<div class="circle noite" > -->
<!-- 								<img src="imagem/camera70.png"> -->
<!-- 							</div> -->
<!-- 						</div> -->
					</div>	
	<!-- 					<div class="periodos"> -->
	<!-- 						<input type="radio" name="periodo" /> Semana  -->
	<!-- 						<input type="radio" name="periodo" /> Mês  -->
	<!-- 						<input type="radio" name="periodo" /> Ano -->
	<!-- 						<input type="radio" name="periodo" /> Sempre -->
				</div>
			</div>
			<div class="footer"></div>
		</div>
	
	
	
	</body>
</html>