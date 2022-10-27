-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : jeu. 27 oct. 2022 à 14:40
-- Version du serveur : 5.7.36
-- Version de PHP : 8.0.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `docteurm2i`
--

-- --------------------------------------------------------

--
-- Structure de la table `parametre`
--

DROP TABLE IF EXISTS `parametre`;
CREATE TABLE IF NOT EXISTS `parametre` (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `nom` varchar(256) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `parametre`
--

INSERT INTO `parametre` (`id`, `nom`) VALUES
(1, 'Température '),
(2, 'Saturation O² '),
(3, 'Fréquence cardiaque ');

-- --------------------------------------------------------

--
-- Structure de la table `personne`
--

DROP TABLE IF EXISTS `personne`;
CREATE TABLE IF NOT EXISTS `personne` (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `nom` varchar(256) NOT NULL,
  `prenom` varchar(256) NOT NULL,
  `isMedecin` int(11) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `personne`
--

INSERT INTO `personne` (`id`, `nom`, `prenom`, `isMedecin`, `date`) VALUES
(1, 'Valjean', 'Jean', 1, '2020-03-09'),
(2, 'Granger', 'Hermione', 1, '2022-10-09'),
(3, 'Sacquet', 'Frodon', 0, '1990-04-01'),
(4, 'Gamegie', 'Sam', 0, '1990-02-03'),
(5, 'Touque', 'Pippin', 0, '1990-11-30'),
(6, 'Brandebouc', 'Meri', 0, '1990-11-30'),
(13, 'Sacquet', 'Bilbon', 0, '1960-09-02');

-- --------------------------------------------------------

--
-- Structure de la table `releve`
--

DROP TABLE IF EXISTS `releve`;
CREATE TABLE IF NOT EXISTS `releve` (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `dateCreation` datetime NOT NULL,
  `valeur` float NOT NULL,
  `patientId` bigint(20) NOT NULL,
  `medecinId` bigint(20) NOT NULL,
  `parametreId` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `releve`
--

INSERT INTO `releve` (`id`, `dateCreation`, `valeur`, `patientId`, `medecinId`, `parametreId`) VALUES
(2, '2022-10-19 14:25:15', 40.5, 4, 1, 1),
(3, '2022-10-21 15:08:16', 92, 5, 1, 2),
(5, '2022-10-25 15:10:16', 89, 6, 2, 3),
(11, '2022-10-02 14:22:00', 92, 3, 2, 2),
(12, '2021-06-02 15:50:00', 34, 13, 2, 1);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
