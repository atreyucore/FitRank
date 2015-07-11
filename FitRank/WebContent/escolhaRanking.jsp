<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Escolher a configuração do Ranking</title>
<style>
	div.periodos {
		border: 1px solid;
		border-color: blue;
		width: 280px;
		
		border-radius: 7px;
 		-webkit-border-radius: 7px; 
     	-moz-border-radius: 7px; 
	}
	
	img{
		margin-bottom: 20px;
		margin-right: 50px;
	}
	
	img.rank {
		margin-left: 200px; 
	}
	
	span.fav {
		margin-left: 250px; 
	}
</style>
</head>

<body>
<div>
	<img src="imagem/speedometer14.png">
	<img src="imagem/races.png">
	<img class="rank" src="imagem/game44.png">
</div>

<div class="timeAndFav">
	<img src="imagem/sun95.png">
	<img src="imagem/camera70.png">
	<span class="fav">
		<img class="fav" src="imagem/tick11.png">
		<img class="fav" src="imagem/star212.png">
	</span>
</div>
<div class="calendario">
	<img src="imagem/calendar157.png">
	<div class="periodos">
		<input type="radio" name="periodo"/> Semana
		<input type="radio" name="periodo"/> Mês
		<input type="radio" name="periodo"/> Ano
		<input type="radio" name="periodo"/> Sempre
	</div>
</div>

</body>
</html>