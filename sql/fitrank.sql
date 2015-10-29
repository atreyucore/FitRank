-- --------------------------------------------------------
-- Servidor:                     eic.cefet-rj.br
-- Versão do servidor:           5.6.19-0ubuntu0.14.04.1 - (Ubuntu)
-- OS do Servidor:               debian-linux-gnu
-- HeidiSQL Versão:              9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Copiando estrutura do banco de dados para fitrank
CREATE DATABASE IF NOT EXISTS `fitrank` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `fitrank`;


-- Copiando estrutura para tabela fitrank.amizade
CREATE TABLE IF NOT EXISTS `amizade` (
  `id_pessoa` varchar(50) NOT NULL,
  `id_amigo` varchar(50) NOT NULL,
  `data_amizade` varchar(10) NOT NULL,
  PRIMARY KEY (`id_pessoa`,`id_amigo`),
  KEY `FK_ID_PESSOA_AMIZADE` (`id_pessoa`),
  KEY `FK_ID_AMIGO_AMIZADE` (`id_amigo`),
  CONSTRAINT `FK_ID_AMIGO_AMIZADE` FOREIGN KEY (`id_amigo`) REFERENCES `pessoa` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_ID_PESSOA_AMIZADE` FOREIGN KEY (`id_pessoa`) REFERENCES `pessoa` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Exportação de dados foi desmarcado.


-- Copiando estrutura para tabela fitrank.aplicativo
CREATE TABLE IF NOT EXISTS `aplicativo` (
  `id_aplicativo` varchar(50) NOT NULL,
  `nome` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_aplicativo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Exportação de dados foi desmarcado.


-- Copiando estrutura para tabela fitrank.configuracao
CREATE TABLE IF NOT EXISTS `configuracao` (
  `id_configuracao` int(11) NOT NULL AUTO_INCREMENT,
  `modalidade` char(1) DEFAULT NULL COMMENT 'R, W ou B',
  `modo` char(1) DEFAULT NULL COMMENT 'V ou D (Velocidade ou Distancia)',
  `dia_noite` char(1) DEFAULT NULL COMMENT 'D ou N',
  `intervalo_data` varchar(10) DEFAULT NULL COMMENT 'D, S, M ou A',
  `favorito` varchar(1) NOT NULL DEFAULT 'N' COMMENT 'S ou N',
  `padrao_modalidade` varchar(1) NOT NULL DEFAULT 'N' COMMENT 'S ou N',
  `id_pessoa` varchar(50) NOT NULL,
  PRIMARY KEY (`id_configuracao`),
  KEY `FK1_PESSOA_CONF` (`id_pessoa`),
  CONSTRAINT `FK_CONFIGURACAO_PESSOA` FOREIGN KEY (`id_pessoa`) REFERENCES `pessoa` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Exportação de dados foi desmarcado.


-- Copiando estrutura para tabela fitrank.course
CREATE TABLE IF NOT EXISTS `course` (
  `id_course` varchar(32) NOT NULL,
  `distancia` float NOT NULL,
  `calorias` float DEFAULT NULL,
  `ritmo` float DEFAULT NULL,
  `id_post` varchar(32) NOT NULL,
  PRIMARY KEY (`id_course`),
  KEY `FK_course_post_fitness` (`id_post`),
  CONSTRAINT `FK_COURSE_POST` FOREIGN KEY (`id_post`) REFERENCES `post_fitness` (`id_publicacao`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Exportação de dados foi desmarcado.


-- Copiando estrutura para tabela fitrank.jsoup
CREATE TABLE IF NOT EXISTS `jsoup` (
  `id_jsoup` int(11) NOT NULL AUTO_INCREMENT,
  `distance` float DEFAULT NULL,
  `duration` float DEFAULT NULL,
  `avg_pace` float DEFAULT NULL,
  `elevation_gain` float DEFAULT NULL,
  `calories` float DEFAULT NULL,
  `heart_rate` float DEFAULT NULL,
  `max_heart_rate` float DEFAULT NULL,
  `weather` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `celsius_degrees` float DEFAULT NULL,
  `place_kind` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `evaluation` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `avg_speed` float DEFAULT NULL,
  `red_line_heart_rate` float DEFAULT NULL,
  `red_line_heart_rate_duration` float DEFAULT NULL,
  `anaerobic_heart_rate` float DEFAULT NULL,
  `anaerobic_heart_rate_duration` float DEFAULT NULL,
  `aerobic_heart_rate` float DEFAULT NULL,
  `aerobic_heart_rate_duration` float DEFAULT NULL,
  `fat_burning_heart_rate` float DEFAULT NULL,
  `fat_burning_heart_rate_duration` float DEFAULT NULL,
  `easy_heart_rate` float DEFAULT NULL,
  `easy_heart_rate_duration` float DEFAULT NULL,
  `no_zone_heart_rate` float DEFAULT NULL,
  `no_zone_heart_rate_duration` float DEFAULT NULL,
  `json_course` mediumblob,
  `url` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `id_course` varchar(32),
  PRIMARY KEY (`id_jsoup`),
  KEY `FK_JSOUP_COURSE` (`id_course`),
  CONSTRAINT `FK_JSOUP_COURSE` FOREIGN KEY (`id_course`) REFERENCES `course` (`id_course`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Exportação de dados foi desmarcado.


-- Copiando estrutura para tabela fitrank.localizacao
CREATE TABLE IF NOT EXISTS `localizacao` (
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `altitude` double DEFAULT NULL,
  `id_course` varchar(32) NOT NULL,
  `id_localizacao` int(32) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id_localizacao`),
  KEY `id_course` (`id_course`),
  CONSTRAINT `FK_LOCALIZACAO_COURSE` FOREIGN KEY (`id_course`) REFERENCES `course` (`id_course`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Exportação de dados foi desmarcado.


-- Copiando estrutura para tabela fitrank.pessoa
CREATE TABLE IF NOT EXISTS `pessoa` (
  `id_usuario` varchar(50) NOT NULL,
  `data_cadastro` varchar(10) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `data_ultimo_login` varchar(10) NOT NULL,
  `data_ultima_atualizacao_runs` varchar(10) DEFAULT NULL,
  `data_ultima_atualizacao_walks` varchar(10) DEFAULT NULL,
  `data_ultima_atualizacao_bikes` varchar(10) DEFAULT NULL,
  `rank_anual` varchar(1) DEFAULT 'N' COMMENT '''S'' ou ''N''',
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Exportação de dados foi desmarcado.


-- Copiando estrutura para tabela fitrank.post_fitness
CREATE TABLE IF NOT EXISTS `post_fitness` (
  `id_publicacao` varchar(32) NOT NULL,
  `id_pessoa` varchar(50) NOT NULL,
  `data_inicio_corrida` varchar(10) DEFAULT NULL,
  `data_fim_corrida` varchar(10) DEFAULT NULL,
  `modalidade` char(1) DEFAULT NULL,
  `id_app` varchar(50) NOT NULL,
  `distancia_percorrida` double DEFAULT NULL,
  `duracao` double DEFAULT NULL,
  `data_publicacao` varchar(10) DEFAULT NULL,
  `url` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_publicacao`),
  KEY `FK1_ID_APLICATIVO` (`id_app`),
  KEY `FK2_ID_PESSOA` (`id_pessoa`),
  CONSTRAINT `FK_POST_PESSOA` FOREIGN KEY (`id_pessoa`) REFERENCES `pessoa` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_POST_APLICATIVO` FOREIGN KEY (`id_app`) REFERENCES `aplicativo` (`id_aplicativo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Exportação de dados foi desmarcado.


-- Copiando estrutura para tabela fitrank.ranking
CREATE TABLE IF NOT EXISTS `ranking` (
  `id_ranking` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(50) DEFAULT NULL,
  `id_configuracao` int(11) NOT NULL,
  `data_ranking` int(11) NOT NULL,
  PRIMARY KEY (`id_ranking`),
  KEY `FK1_ID_CONFIGURACAO` (`id_configuracao`),
  CONSTRAINT `FK_RANKING_CONFIGURACAO` FOREIGN KEY (`id_configuracao`) REFERENCES `configuracao` (`id_configuracao`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Exportação de dados foi desmarcado.


-- Copiando estrutura para tabela fitrank.ranking_pessoa
CREATE TABLE IF NOT EXISTS `ranking_pessoa` (
  `id_ranking` int(11) NOT NULL,
  `id_pessoa` varchar(50) NOT NULL,
  `colocacao` int(10) NOT NULL,
  `resultado` float DEFAULT NULL,
  PRIMARY KEY (`id_ranking`,`id_pessoa`),
  KEY `FK_PESSOA_RANKING` (`id_ranking`),
  KEY `FK_RANKING_PESSOA` (`id_pessoa`),
  CONSTRAINT `FK_PESSOA_RANKING` FOREIGN KEY (`id_ranking`) REFERENCES `ranking` (`id_ranking`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_RANKING_PESSOA` FOREIGN KEY (`id_pessoa`) REFERENCES `pessoa` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Exportação de dados foi desmarcado.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
