<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Escolher a configuração do Ranking</title>
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
	<!-- 				<div> -->
	<!-- 					<img class="rank" src="imagem/game44.png" /> -->
						
	
	<!-- 				</div> -->
					<div class="circles">
						<div class="circle run ranking" onClick="goToEscolhaRanking('runs')">
							<img src="imagem/sprint.png">
						</div>
						
						<div class="circle velocimeter ranking" >
							<img src="imagem/speedometer14.png"> 
						</div>
						
						
						<div class="circle sun ranking smallTile" >
							<img src="imagem/sun95.png"> 
						</div>
						
						<div class="circle calendario ranking smallTile">
  	 						<img src="imagem/calendar157.png">
  	 						<p class="capsula chosenPeriod">Mês</p>
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