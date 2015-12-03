<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Escolha a modalidade</title>
		<link rel="stylesheet" type="text/css" href="./style/css/FitRank.css">
		<script src="./js/jquery-1.11.2.js"></script>
		<script src="http://connect.facebook.net/pt_BR/all.js"></script>
			<script>
        	$(document).ready( function(){
					 FB.init({
						  appId  : '749336888463283', //Id do aplicativo ()
						  status : true, // verifica status do login
						  cookie : true, // habilita cookies para permitir acesso via servidor
						  xfbml  : true  // habilita parser XFBML
						});
					 
				$('#entra').on("click",function entra() {
					 
					 
					 
					 /*FB.login(function(response) {
						alert(response.status);
					 }, {scope: 'email,user_likes,user_actions.fitness'} );*/   
					 var token = "";
					 FB.getLoginStatus(function(response) {
					        if (response.status === 'connected') {
					        	
					        	token = response.authResponse.accessToken;
			         		    window.location = location.origin + location.pathname + "InitUser?token=" + token;
			
// 					        }else if(response.status === 'not_authorized'){
			   		         
// 					          alert("Não autorizado");
					          
					        }else{
					        	var popup = 					        	
			        			FB.login(function(response) {
						            if(response.session)
						            {
						            	alert(response.session);
						            	 alert("entrou");
						            	 $('#fb_login_form').submit();
						            	 if(response.authResponse){
						            		 token = response.authResponse.accessToken;
						            		 window.location = location.origin + location.pathname + "InitUser?token=" + token;
						            		 
						            	 }else{
						            		 console.log("O usuário não permitiu acesso aos dados!");
						            	 }
						            } 
						            
						            if (response.status == "connected" && response.authResponse) {
						            	token = response.authResponse.accessToken;
						            	window.location = location.origin + location.pathname + "InitUser?token=" + token;
						            }
						            
						          }, {scope: 'email,user_birthday,user_friends,user_actions.fitness'});
// 					        	if(!popup) { 
// 									   //an alert in this example
// 									   alert('Parece que seu navegador está bloqueando o popup para autorizar a nossa conexão com o Facebook. \nPara continuar será necessário desabilitar o bloqueio.');
// 									}
					         }
					 });
					 
					 
				 }); 
        	});   
				
				 
			</script>
	</head>
	<body>
<!-- 	onload="entra()" -->
		
		<div class="wrapper">
		<div class="preheader"></div>
		<div class="content">
			<div class="headerContent">
				<div class="siteHeader">
					<div>
						<div class="logo">
							FitRank<sup class="supCopy">&copy;</sup>
						</div>
					</div>
					<div>
						<p class="text" style="text-align:center;"> At the moment we only work with Facebook fitness extracted data.</p> 
						<p class="text" style="text-align:center;" >Facebook login is mandatory.</p>
						<p class="text" style="text-align:center;" >We won't post to Facebook without your permission.</p>
						<input type="button" id="entra"
								style="cursor: pointer; margin: 0 auto;display: block;border:none;width:288px;height:62px;background-image: url('https://fbcdn-dragon-a.akamaihd.net/hphotos-ak-xaf1/t39.2178-6/851579_209602122530903_1060396115_n.png')" />
<!-- 						<img style="cursor: pointer; margin: 0 auto;display: block; position: relative;top: 20px;" src="https://fbcdn-dragon-a.akamaihd.net/hphotos-ak-xaf1/t39.2178-6/851579_209602122530903_1060396115_n.png" onclick="entra()"/> -->
					</div>
				</div>
			</div>
		</div>
		<div class="footer"></div>
	</div>
	<div id="fb-root"></div>
			<!--a href="#" onclick="entra()">
			< img src="imagem/download.jpg" />
			</a-->
	</body>
</html>
 