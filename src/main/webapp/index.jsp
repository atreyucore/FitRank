<html>
	<head>
	</head>
	<body onload="entra()">
		<div id="fb-root"></div>
			<!--a href="#" onclick="entra()">
			< img src="imagem/download.jpg" />
			</a-->
			<script src="http://connect.facebook.net/pt_BR/all.js"></script>
			<script>
        
			 FB.init({
				  appId  : '749336888463283', //Id do aplicativo ()
				  status : true, // verifica status do login
				  cookie : true, // habilita cookies para permitir acesso via servidor
				  xfbml  : true  // habilita parser XFBML
				});
			
			
				 function entra() {
					 /*FB.login(function(response) {
						alert(response.status);
					 }, {scope: 'email,user_likes,user_actions.fitness'} );*/   
					 var token = "";
					 FB.getLoginStatus(function(response) {
					        if (response.status === 'connected') {
					        	
					        	token = response.authResponse.accessToken;
			         		    window.location = location.origin + location.pathname + "InitUser?token=" + token;
			
					        }else if(response.status === 'not_authorized'){
			   		         
					          alert("Não autorizado");
					          
					        }else{
					        	
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
					         }
					 });
				 }; 
					        	           
				
				 
			</script>
	</body>
</html>
 