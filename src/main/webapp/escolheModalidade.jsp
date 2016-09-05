<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Escolha a modalidade</title>
<link rel="stylesheet" type="text/css" href="./style/css/FitRank.css">
<script>
	
	<%if ((String) request.getAttribute("erro") != null) {%>
		alert('<%=(String) request.getAttribute("erro")%>');
	<%}%>
	function goToEscolhaRanking(modalidade) {
		window.location = 'CarregaEscolhaRanking?modalidade=' + modalidade + '&token=' + '<%=(String) request.getAttribute("token")%>';
	}
	
	function goToRankingFavorito() {
		window.location = 'CarregaEscolhaRanking?fav=S&token=' + '<%=(String) request.getAttribute("token")%>';
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
						<div class="logo">
							FitRank
						</div>
					</div>
					<div class="circles">
						<div class="circleWrapper">
							<div class="circle walk" onClick="goToEscolhaRanking('W')">
								<img src="imagem/man-silhouette1.png">
							</div>
						</div>
						<div class="circleWrapper">
							<div class="circle run" onClick="goToEscolhaRanking('R')">
								<img src="imagem/sprint.png">
							</div>
						</div>
						<div class="circleWrapper">
							<div class="circle bike" onClick="goToEscolhaRanking('B')">
								<img src="imagem/bike11.png">
							</div>
						</div>
					</div>
					<div class="circles">
<!-- 						<div class="circleWrapper"> -->
<!-- 							<div class="circle union" > -->
<!-- 								<img src="imagem/interface52.png"> -->
<!-- 							</div> -->
<!-- 						</div> -->
						<div class="circleWrapper">
							<div class="circle favorite"  onClick="goToRankingFavorito()" >
								<img src="imagem/favourites7.png">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="footer"></div>
	</div>
</body>
</html>