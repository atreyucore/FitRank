<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Escolha a modalidade</title>
<style>
	img {
		border: 3px solid;
		border-color: blue;
		border-radius: 50%;
		-webkit-border-radius: 50%;
    	-moz-border-radius: 50%;
 		padding: 10px;
	}
</style>
<script>

	function goToEscolhaRanking() {
		window.location = 'CarregaEscolhaRanking?token=' + '<%=(String) request.getAttribute("token")%>';
		
	}
</script>
</head>
<body>
<img src="imagem/bike11.png" onClick="goToEscolhaRanking()">
<img src="imagem/sprint.png" onClick="goToEscolhaRanking()">
<img src="imagem/man-silhouette1.png" onClick="goToEscolhaRanking()">
<img src="imagem/star212.png" onClick="goToEscolhaRanking()">
</body>
</html>