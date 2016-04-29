SELECT str_to_date(pf.data_publicacao, '%d/%m/%Y') dat,
		  pf.data_publicacao
  FROM post_fitness pf
 WHERE pf.id_pessoa = '877571842254965'
   AND pf.modalidade = 'B'
 ORDER BY dat DESC
-- LIMIT 1