SELECT @rownum := @rownum + 1 AS colocacao,						
		consulta.id_pessoa id_pessoa,							
		(consulta.distancia/consulta.duracao) velocidade_media,	
		consulta.distancia distancia_percorrida,				
		consulta.corridas quantidade_corrida					
  FROM (SELECT @rownum := 0) r,									
		(SELECT pf.id_pessoa,	
				SUM(pf.distancia_percorrida) distancia,			
				SUM(pf.duracao) duracao,						
				COUNT(pf.id_publicacao) corridas,				
				SUM(pf.distancia_percorrida) resultado			
				FROM post_fitness pf,							
					 pessoa p									
		  WHERE (p.id_usuario IN (SELECT a.id_amigo				
									FROM amizade a				
								   WHERE a.id_pessoa = '962249043802227')
				 OR p.id_usuario = '962249043802227')
				 AND p.id_usuario = pf.id_pessoa

	AND pf.modalidade = 'R'

# AND (str_to_date(pf.data_publicacao, '%d/%m/%Y') 
#					BETWEEN str_to_date('06/04/2016', '%d/%m/%Y') 
#						AND str_to_date('12/04/2016', '%d/%m/%Y'))
		 GROUP BY pf.id_pessoa
		 ) consulta
ORDER BY resultado DESC