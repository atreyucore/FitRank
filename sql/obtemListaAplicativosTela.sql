SELECT pf.id_app,
       a.nome,
       COUNT(pf.id_publicacao) atividades
  FROM post_fitness pf,							
       pessoa p,									
       aplicativo a
 WHERE p.id_usuario = '877571842254965'
   AND p.id_usuario = pf.id_pessoa
   AND pf.id_app = a.id_aplicativo
   AND pf.modalidade = 'R'

   AND (str_to_date(pf.data_publicacao, '%d/%m/%Y') 
                    BETWEEN str_to_date('06/04/2016', '%d/%m/%Y') 
                        AND str_to_date('12/04/2016', '%d/%m/%Y'))
GROUP BY pf.id_app