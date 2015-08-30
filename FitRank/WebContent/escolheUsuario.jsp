<% 
	String[] profPicUrls= (String[]) request.getAttribute("profPicUrl");
	String[] ids = (String[]) request.getAttribute("ids");
	String token = (String) request.getAttribute("token");
	String alert = (String) request.getAttribute("alert");
%>

<html>
	<head>
	<script type="text/javascript" src="js/jquery-1.11.2.js"></script>
	<script type="text/javascript">
		var profile = [];
		
		function selectProfile(a) {
			$("img").css("border", "").css("border-style", "").css("border-width", "");
			$("#" + a.id).css("border", "blue").css("border-style", "solid").css("border-width", "thin");
			profile["htmlIdSelected"] = a.id;
			profile["fbIdSelected"] = a.getAttribute("fb-id");
		}
		
		function submitExtract() {
				window.location = "RecuperaFitness?" + "&categoria=" + $("#dropDownMenuFitnessCategory option:selected").val() + "&id=" + profile["fbIdSelected"] + "&token=<%=token%>";
		}
		
// 		extract=" + $("#dropDownMenuExtractMode option:selected").val()
	</script>
	</head>
	<body>
		<%			
			for(int i=0; i < profPicUrls.length;i++) {
		%> 
			
<%-- 			<a href="RecuperaCorridas?id=<%=ids[i]%>&token=<%=token%>&fase=inicio" > --%>
			<img src="<%=profPicUrls[i]%>" id="profile<%=i%>" fb-id="<%=ids[i]%>" onclick="selectProfile(this)"/><%-- </a> --%>
		<%
			}
		%>
		
<!-- 		<div> -->
<!-- 			<select id="dropDownMenuExtractMode"> -->
<!-- 				<option value="graphapi">Facebook Graph API</option> -->
<!-- 				<option value="jsoup" >JSOUP</option> -->
<!-- 			</select> -->
<!-- 		</div> -->
		
		<div>
			<select id="dropDownMenuFitnessCategory">
				<option value="runs">Corridas (Runs)</option>
				<option disabled="disabled">Caminhadas (Walks)</option>
				<option disabled="disabled">Pedaladas (Bikes)</option>
			</select>
		</div>
		
		<input type="button" value="Extrair" onclick="submitExtract()">
</html>
