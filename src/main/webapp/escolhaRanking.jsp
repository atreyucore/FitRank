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
				var time = ["Dia", "Semana", "Mês"];
				
				$(".slider").slider({ 
					min: 0, 
			        max: time.length-1, 
			        orientation: "vertical"
			    }).slider("pips", {
			        rest: "label",
			        labels: time
			    });
				
				$(".ui-slider-handle.ui-state-default.ui-corner-all").append("<div class='circle calendario'><img src='imagem/calendar157.png'></div>");
				
				$(".ui-slider-pip").each(function() {
					var bottomInitial = $(this).css("bottom");
					bottomInitial.replace("%","");
					bottomInitial = parseInt(bottomInitial) -40;
					bottomInitial = bottomInitial + "%";
					$(this).css("bottom", bottomInitial); 
				});
				
				preparaEscolhas(".velocimeter,.races", "selectedMode");
				preparaEscolhas(".sun,.moon", "selectedShift");
			})	
						
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
			
			function salvarFavorito(){
				//TODO Fazer aqui a chamada do servlet com os parametros de configuracao favorita
			}
			
			function salvarPadrao(){
				//TODO Fazer aqui a chamada do servlet com os parametros de configuracao padrao
			}
		</script>
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
							<div class="fav"> 
								<img class="fav" src="imagem/tick11_big.png" style="border-radius: 50%;background-color: rgb(101,166,133);"/>
								<img class="fav" src="imagem/star212_big.png" style="border-radius: 50%;background-color: rgb(241,239,169);" />
								<img class="fav" src="imagem/social24.png" style="border-radius: 50%;background-color: rgb(191, 230, 231);" />
								<img class="fav" src="imagem/medal52.png" style="border-radius: 50%;background-color: rgb(193, 74, 74);" 
									onclick="window.location = 'ranking.jsp';"/>
							</div>
						</div>
					</div>
	<!-- 				<div> -->
	<!-- 					<img class="rank" src="imagem/game44.png" /> -->
						
	
	<!-- 				</div> -->
					<div class="circles">
						<div class="circleWrapper rankChoose">
							<div class="circle velocimeter" >
								<img src="imagem/speedometer14.png"> 
							</div>
						</div>
						
						<div class="circleWrapper rankChoose">
							<div class="circle sun" >
								<img src="imagem/sun95.png"> 
							</div>
						</div>
						
						<div class="circleWrapper rankChoose slider">
<!-- 							<div class="circle calendario"> -->
<!-- 	  	 						<img src="imagem/calendar157.png"> -->
<!-- 	  	 					</div> -->
						</div>
						<div class="circleWrapper rankChoose" style="position: relative;top: 200px;">
	<!-- 						<div class="circle bestRank"> -->
	  	 						<img src="imagem/game44.png">
	<!--   	 						<div class="circle global" > -->
									<img class="global" src="imagem/world91.png">
	<!-- 							</div> -->
	<!--   	 					</div> -->
						</div>
					</div>
					<div class="circles">
						<div class="circleWrapper rankChoose">
							<div class="circle races" >
								<img src="imagem/races.png">
							</div>
						</div>
						<div class="circleWrapper rankChoose">
							<div class="circle moon" >
								<img src="imagem/camera70.png">
							</div>
						</div>
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