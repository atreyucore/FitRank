<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="java.util.ResourceBundle" %>
<%@ page import="java.io.*,java.util.Locale" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="theme-color" content="#6f3d94" />
		
		<title>FitRank</title>
		
		<link rel="stylesheet" type="text/css" href="./style/css/FitRank.css">
		<link rel="icon" type="image/png" sizes="16x16" href="/favicon-16x16.png">
		
		<script src="./js/jquery-1.11.2.js"></script>
		<script src="http://connect.facebook.net/pt_BR/all.js"></script>
		<script>
			$(document).ready(function() {
					FB.init({
						appId : '749336888463283', //Id do aplicativo ()
						status : true, // verifica status do login
						cookie : true, // habilita cookies para permitir acesso via servidor
						xfbml : true, // habilita parser XFBML
						version : 'v2.5'
					});
		
					$('#entra').on("click",
						function entra() {
							// 				FB.login(function(){
			
							/*FB.login(function(response) {
							alert(response.status);
							}, {scope: 'email,user_likes,user_actions.fitness'} );*/
							var token = "";
							FB.getLoginStatus(function(response) {
										
								if (response.status === 'connected') {
			
									token = response.authResponse.accessToken;
		// 							window.location = location.origin
		// 									+ location.pathname
		// 									+ "InitUser?token="
		// 									+ token;
									
									$("#token").val(token);
									
									$("#formSubmit").submit();
		
									// 					        }else if(response.status === 'not_authorized'){
		
									// 					          alert("Não autorizado");
		
								} else {
		
									FB.login(function(response) {
											if (response.authResponse) {
												token = response.authResponse.accessToken;
												$("#token").val(token);
												
												$("#formSubmit").submit();
											
		// 										alert(response.session);
		// 										alert("entrou");
		// 										$(
		// 												'#fb_login_form')
		// 												.submit();
												
													token = response.authResponse.accessToken;
													 
		// 											window.location = location.origin
		// 													+ location.pathname
		// 													+ "InitUser?token="
		// 													+ token;
													
													$("#token").val(token);
													
													$("#formSubmit").submit();
		
												
											} else {
												console
														.log("O usuário não permitiu acesso aos dados!");
											}
		
		// 									if (response.status == "connected"
		// 											&& response.authResponse) {
		// 										token = response.authResponse.accessToken;
		// 										window.location = location.origin
		// 												+ location.pathname
		// 												+ "InitUser?token="
		// 												+ token;
		
		// 										$("#token").val(token);
												
		// 										$("#formSubmit").submit();
		// 									}
		
										},
										{
											scope : 'email,user_friends,user_actions.fitness'
										});//user_birthday
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
		<% ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n.Bundle", request.getLocale()); 
		%>
		
		<%
		   //Get the client's Locale
		   Locale locale = request.getLocale();
		   String language = locale.getLanguage();
		   String country = locale.getCountry();
		%>
		<div class="wrapper">
			<div class="preheader"></div>
			<div class="content">
				<div class="headerContent">
					<div class="siteHeader">
						<div>
							<div class="logo">FitRank</div>
						</div>
						<div>
							<p class="text" style="text-align: center;"><%=resourceBundle.getString("MESSAGE_1")%></p>
							<p class="text" style="text-align: center;"><%=resourceBundle.getString("MESSAGE_2")%></p>
							<input type="button" id="entra" class="facebookLogin"/>
							
							<p align="center">
							<% 
							   out.println("Language : " + language  + "<br />");
							   out.println("Country  : " + country   + "<br />");
							%>
						</div>
					</div>
				</div>
			</div>
			<div class="footer"></div>
		</div>
		<div id="fb-root"></div>
		<form action="Main" id="formSubmit" style="display: none;" method="post">
	    	<input type="hidden" id="token" name="token" />
		</form>
	</body>
</html>
