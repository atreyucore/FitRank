<head>
<html>
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
		 
		 FB.getLoginStatus(function(response) {
		        if (response.status === 'connected') {
		        	
		        	var token = response.authResponse.accessToken;
         		    window.location="http://localhost:8080/FitRank/RecuperaUsuarioFacebook?token="+token;

		        }else if(response.status === 'not_authorized'){
   		         
		          alert("Não autorizado");
		          
		        }else{
		        	
		        FB.login(function(response) {
		            if(response.session)
		            {
		            	 alert("entrou");
		            	 $('#fb_login_form').submit();
		            	 if(response.authResponse){
		            		 window.location="http://localhost:8080/truefriends/RecuperaUsuarioFacebook?token="+response.authResponse.accessToken;
		            		 
		            	 }else{
		            		 console.log("O usuário não aceitou o acesso aos seus Dados!");
		            	 }
		            }
		            
		          });
		         }
		 });
	 }; 
		        	           
	
	 
</script>
</body>
</head>
</html>
 