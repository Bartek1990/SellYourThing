-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Czas wygenerowania: 30 Maj 2013, 14:58
-- Wersja serwera: 5.5.27
-- Wersja PHP: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Baza danych: `sellyourthing`
--
CREATE DATABASE `sellyourthing`;
USE `sellyourthing`;
-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `address`
--

CREATE TABLE IF NOT EXISTS `address` (
  `address_id` int(10) NOT NULL AUTO_INCREMENT,
  `city` varchar(50) NOT NULL,
  `post_code` varchar(20) NOT NULL,
  `street` varchar(100) NOT NULL,
  `country` varchar(30) NOT NULL,
  `state` varchar(30) NOT NULL,
  `building_nr` int(10) NOT NULL,
  PRIMARY KEY (`address_id`),
  UNIQUE KEY `address_id` (`address_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Zrzut danych tabeli `address`
--

INSERT INTO `address` (`address_id`, `city`, `post_code`, `street`, `country`, `state`, `building_nr`) VALUES
(1, 'Wolbrom', '32-340', 'Brzozówka 105', 'Polska', 'Ma?opolskie', 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `auction`
--

CREATE TABLE IF NOT EXISTS `auction` (
  `auction_id` int(10) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `type` varchar(30) NOT NULL,
  `status` varchar(20) NOT NULL,
  `exp_date` date NOT NULL,
  `Subcategoriessub_id` int(10) NOT NULL,
  PRIMARY KEY (`auction_id`),
  UNIQUE KEY `auction_id` (`auction_id`),
  KEY `FKAuction815237` (`Subcategoriessub_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `auction_bot`
--

CREATE TABLE IF NOT EXISTS `auction_bot` (
  `bot_id` int(10) NOT NULL AUTO_INCREMENT,
  `interval_b` double NOT NULL DEFAULT '1',
  `step` double NOT NULL DEFAULT '5',
  `limit_b` float DEFAULT NULL,
  PRIMARY KEY (`bot_id`),
  UNIQUE KEY `bot_id` (`bot_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `auction_user`
--

CREATE TABLE IF NOT EXISTS `auction_user` (
  `Auctionauction_id` int(10) NOT NULL,
  `Useruser_id` int(10) NOT NULL,
  PRIMARY KEY (`Auctionauction_id`,`Useruser_id`),
  KEY `FKAuction_Us679642` (`Auctionauction_id`),
  KEY `FKAuction_Us289869` (`Useruser_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `biding`
--

CREATE TABLE IF NOT EXISTS `biding` (
  `biding_id` int(10) NOT NULL AUTO_INCREMENT,
  `Useruser_id` int(10) NOT NULL,
  `Auction_botbot_id` int(10) NOT NULL,
  `Auctionauction_id` int(10) NOT NULL,
  `current_price` double NOT NULL,
  PRIMARY KEY (`biding_id`),
  UNIQUE KEY `biding_id` (`biding_id`),
  KEY `FKBiding307546` (`Useruser_id`),
  KEY `FKBiding694532` (`Auctionauction_id`),
  KEY `FKBiding6115` (`Auction_botbot_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `categories`
--

CREATE TABLE IF NOT EXISTS `categories` (
  `cat_id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  PRIMARY KEY (`cat_id`),
  UNIQUE KEY `cat_id` (`cat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
  `comment_id` int(10) NOT NULL AUTO_INCREMENT,
  `Auctionauction_id` int(10) NOT NULL,
  `content` varchar(255) NOT NULL,
  PRIMARY KEY (`comment_id`),
  UNIQUE KEY `content` (`content`),
  UNIQUE KEY `comment_id` (`comment_id`),
  KEY `FKComment780742` (`Auctionauction_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `grade`
--

CREATE TABLE IF NOT EXISTS `grade` (
  `grade_id` int(10) NOT NULL AUTO_INCREMENT,
  `Auctionauction_id` int(10) NOT NULL,
  `grade_lvl` int(10) NOT NULL,
  PRIMARY KEY (`grade_id`),
  UNIQUE KEY `grade_id` (`grade_id`),
  KEY `FKGrade760531` (`Auctionauction_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `groups`
--

CREATE TABLE IF NOT EXISTS `groups` (
  `group_id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `acc_level` int(10) NOT NULL,
  PRIMARY KEY (`group_id`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `group_id` (`group_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Zrzut danych tabeli `groups`
--

INSERT INTO `groups` (`group_id`, `name`, `acc_level`) VALUES
(1, 'admin', 3);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `product_images`
--

CREATE TABLE IF NOT EXISTS `product_images` (
  `img_id` int(10) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) NOT NULL,
  `title` varchar(50) DEFAULT NULL,
  `Auctionauction_id` int(10) NOT NULL,
  PRIMARY KEY (`img_id`),
  UNIQUE KEY `url` (`url`),
  UNIQUE KEY `img_id` (`img_id`),
  KEY `FKProduct_im458306` (`Auctionauction_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `subcategories`
--

CREATE TABLE IF NOT EXISTS `subcategories` (
  `sub_id` int(10) NOT NULL AUTO_INCREMENT,
  `sub_name` varchar(50) NOT NULL,
  `Categoriescat_id` int(10) NOT NULL,
  PRIMARY KEY (`sub_id`),
  UNIQUE KEY `sub_id` (`sub_id`),
  KEY `FKSubcategor72335` (`Categoriescat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `forname` varchar(30) NOT NULL,
  `password` varchar(64) NOT NULL,
  `pesel` varchar(11) NOT NULL,
  `email` varchar(40) NOT NULL,
  `date_of_birth` date NOT NULL,
  `register_date` date NOT NULL,
  `Groupgroup_id` int(10) NOT NULL,
  `Addressaddress_id` int(10) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `pesel` (`pesel`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `user_id` (`user_id`),
  KEY `FKUser709988` (`Groupgroup_id`),
  KEY `FKUser645989` (`Addressaddress_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Zrzut danych tabeli `user`
--

INSERT INTO `user` (`user_id`, `name`, `forname`, `password`, `pesel`, `email`, `date_of_birth`, `register_date`, `Groupgroup_id`, `Addressaddress_id`) VALUES
(1, 'Bart?omiej', 'Grzebinoga', '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', '90082703498', 'bartek.grzybek@gmail.com', '2013-06-01', '2013-05-30', 1, 1);

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `auction`
--
ALTER TABLE `auction`
  ADD CONSTRAINT `FKAuction815237` FOREIGN KEY (`Subcategoriessub_id`) REFERENCES `subcategories` (`sub_id`);

--
-- Ograniczenia dla tabeli `auction_user`
--
ALTER TABLE `auction_user`
  ADD CONSTRAINT `FKAuction_Us289869` FOREIGN KEY (`Useruser_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `FKAuction_Us679642` FOREIGN KEY (`Auctionauction_id`) REFERENCES `auction` (`auction_id`);

--
-- Ograniczenia dla tabeli `biding`
--
ALTER TABLE `biding`
  ADD CONSTRAINT `FKBiding6115` FOREIGN KEY (`Auction_botbot_id`) REFERENCES `auction_bot` (`bot_id`),
  ADD CONSTRAINT `FKBiding307546` FOREIGN KEY (`Useruser_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `FKBiding694532` FOREIGN KEY (`Auctionauction_id`) REFERENCES `auction` (`auction_id`);

--
-- Ograniczenia dla tabeli `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `FKComment780742` FOREIGN KEY (`Auctionauction_id`) REFERENCES `auction` (`auction_id`);

--
-- Ograniczenia dla tabeli `grade`
--
ALTER TABLE `grade`
  ADD CONSTRAINT `FKGrade760531` FOREIGN KEY (`Auctionauction_id`) REFERENCES `auction` (`auction_id`);

--
-- Ograniczenia dla tabeli `product_images`
--
ALTER TABLE `product_images`
  ADD CONSTRAINT `FKProduct_im458306` FOREIGN KEY (`Auctionauction_id`) REFERENCES `auction` (`auction_id`);

--
-- Ograniczenia dla tabeli `subcategories`
--
ALTER TABLE `subcategories`
  ADD CONSTRAINT `FKSubcategor72335` FOREIGN KEY (`Categoriescat_id`) REFERENCES `categories` (`cat_id`);

--
-- Ograniczenia dla tabeli `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FKUser645989` FOREIGN KEY (`Addressaddress_id`) REFERENCES `address` (`address_id`),
  ADD CONSTRAINT `FKUser709988` FOREIGN KEY (`Groupgroup_id`) REFERENCES `groups` (`group_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
