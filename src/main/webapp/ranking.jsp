<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Escolher a configuração do Ranking</title>
		<script type="text/javascript" src="js/jquery-1.11.2.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
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
							<th>Velocidade Máx.</th>
						</tr>

						<tr class="rankingLine">
							<td>
								<img align="middle" src="http://static.comicvine.com/uploads/square_tiny/14/149672/2868907-fake_facebook_profile_picture_funny_batman_pic_150x150.jpg">
								<span class="profileName">Bruce Wayne</span>
							</td>
							<td class="measure"><span>100 km/h</span></td>
						</tr>
						<tr class="rankingLine">
							<td>
								<img align="middle" src="http://static.comicvine.com/uploads/square_tiny/13/132352/3523035-superman_avatar.jpeg">
								<span class="profileName">Clark Kent</span>
<!-- 								JR Cefet -->
							</td>
							<td class="measure">
								<span>99 km/h</span>
							</td>
						</tr>
						<tr class="rankingLine">
							<td>
<!-- 							<div> -->
								<img align="middle" src="http://www.artecapas.com/images/blank_avtar.gif">
	<!-- 							</div> -->
								<span class="profileName">Anônimo</span>
<!-- 								JR Cefet -->
							</td>
							<td class="measure">
								<span>20 km/h</span>
							</td>
						</tr>						
					</table>
				</div>
			</div>
		</div>
	</body>
</html>