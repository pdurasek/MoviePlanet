CREATE DATABASE  IF NOT EXISTS `movieplanet` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `movieplanet`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: movieplanet
-- ------------------------------------------------------
-- Server version	5.7.12-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `actor`
--

DROP TABLE IF EXISTS `actor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor` (
  `actorID` int(11) NOT NULL,
  `firstName` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`actorID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor`
--

LOCK TABLES `actor` WRITE;
/*!40000 ALTER TABLE `actor` DISABLE KEYS */;
INSERT INTO `actor` VALUES (1,'Johnny','Depp','1970-09-11','US'),(2,'Benedict','Cumberbatch','1976-07-17','UK'),(3,'Leonardo','DiCaprio','1974-11-11','US'),(4,'Jennifer','Lawrence','1990-08-15','US'),(5,'Karl','Urban','1972-06-07','New Zeland'),(6,'Angelina','Jolie','1975-06-04','US'),(7,'Scarlett','Johansson','1984-11-22','US'),(8,'Robbie','Margot','1990-07-02','Australia'),(9,'Bradley','Cooper','1975-01-05','US'),(10,'Sir Ian','McKellen','1939-05-25','UK');
/*!40000 ALTER TABLE `actor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actor_has_movie`
--

DROP TABLE IF EXISTS `actor_has_movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor_has_movie` (
  `Actor_actorID` int(11) NOT NULL,
  `Movie_movieID` int(11) NOT NULL,
  PRIMARY KEY (`Actor_actorID`,`Movie_movieID`),
  KEY `fk_Actor_has_Movie_Movie1_idx` (`Movie_movieID`),
  KEY `fk_Actor_has_Movie_Actor1_idx` (`Actor_actorID`),
  CONSTRAINT `fk_Actor_has_Movie_Actor1` FOREIGN KEY (`Actor_actorID`) REFERENCES `actor` (`actorID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Actor_has_Movie_Movie1` FOREIGN KEY (`Movie_movieID`) REFERENCES `movie` (`movieID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor_has_movie`
--

LOCK TABLES `actor_has_movie` WRITE;
/*!40000 ALTER TABLE `actor_has_movie` DISABLE KEYS */;
INSERT INTO `actor_has_movie` VALUES (1,1),(2,2),(3,3),(4,4),(10,5),(8,6),(6,7),(7,8),(5,9),(9,10);
/*!40000 ALTER TABLE `actor_has_movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `awards`
--

DROP TABLE IF EXISTS `awards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `awards` (
  `awardsID` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`awardsID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `awards`
--

LOCK TABLES `awards` WRITE;
/*!40000 ALTER TABLE `awards` DISABLE KEYS */;
INSERT INTO `awards` VALUES (1,'Oscar'),(2,'Golden Globe'),(3,'Saturn Award'),(4,'Teen Choice Awards'),(5,'BAFTA Film Award');
/*!40000 ALTER TABLE `awards` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `director`
--

DROP TABLE IF EXISTS `director`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `director` (
  `directorID` int(11) NOT NULL,
  `firstName` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`directorID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `director`
--

LOCK TABLES `director` WRITE;
/*!40000 ALTER TABLE `director` DISABLE KEYS */;
INSERT INTO `director` VALUES (1,'Gob','Verbinski','1970-01-01','US'),(2,'Morten','Tyldum','1972-02-02','Australia'),(3,'Christopher','Nolan','1974-03-03','UK'),(4,'Francis','Lawrence','1976-04-04','US'),(5,'Peter','Jackson','1978-05-05','New Zeland'),(6,'Simon','West','1980-06-06','UK'),(7,'Joss','Whedon','1982-07-07','Australia'),(8,'David','Ayer','1984-07-07','Australia'),(9,'Clint','Eastwood','1970-08-08','US'),(10,'Bryan','Singer','1972-09-09','US');
/*!40000 ALTER TABLE `director` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `director_has_movie`
--

DROP TABLE IF EXISTS `director_has_movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `director_has_movie` (
  `Director_directorID` int(11) NOT NULL,
  `Movie_movieID` int(11) NOT NULL,
  PRIMARY KEY (`Director_directorID`,`Movie_movieID`),
  KEY `fk_Director_has_Movie_Movie1_idx` (`Movie_movieID`),
  KEY `fk_Director_has_Movie_Director1_idx` (`Director_directorID`),
  CONSTRAINT `fk_Director_has_Movie_Director1` FOREIGN KEY (`Director_directorID`) REFERENCES `director` (`directorID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Director_has_Movie_Movie1` FOREIGN KEY (`Movie_movieID`) REFERENCES `movie` (`movieID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `director_has_movie`
--

LOCK TABLES `director_has_movie` WRITE;
/*!40000 ALTER TABLE `director_has_movie` DISABLE KEYS */;
INSERT INTO `director_has_movie` VALUES (1,1),(2,2),(3,3),(4,4),(10,5),(8,6),(6,7),(7,8),(5,9),(9,10);
/*!40000 ALTER TABLE `director_has_movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genre`
--

DROP TABLE IF EXISTS `genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `genre` (
  `genreID` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`genreID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genre`
--

LOCK TABLES `genre` WRITE;
/*!40000 ALTER TABLE `genre` DISABLE KEYS */;
INSERT INTO `genre` VALUES (1,'Action'),(2,'Adventure'),(3,'Fantasy'),(4,'Biography'),(5,'Drama'),(6,'Thriller'),(7,'Sci-Fi');
/*!40000 ALTER TABLE `genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movie` (
  `movieID` int(11) NOT NULL,
  `ratingID` int(11) NOT NULL,
  `name` varchar(200) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `score` varchar(45) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `screenTime` int(11) DEFAULT NULL,
  PRIMARY KEY (`movieID`),
  KEY `fk_Movie_Rating1_idx` (`ratingID`),
  CONSTRAINT `fk_Movie_Rating1` FOREIGN KEY (`ratingID`) REFERENCES `rating` (`ratingID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` VALUES (1,3,'Pirates of the Caribbean: The Curse of the Black Pearl','Text Text Text Text Text Text Text Text Text Text Text ','8.1',2003,143),(2,3,'The Imitation Game','Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text ','8.1',2014,114),(3,3,'Inception','Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text ','8.8',2010,148),(4,3,'The Hunger Games: Mockingjay - Part 2','Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text ','6.6',2015,137),(5,3,'X-Men','Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text ','7.4',2000,104),(6,3,'Suicide Squad','Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text ','6.5',2016,123),(7,3,'Lara Croft: Tomb Raider','Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text ','5.7',2001,100),(8,3,'Avengers: The Age of Ultron','Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text ','7.5',2015,141),(9,3,'Lord of the Rings: The Two Towers','Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text ','8.7',2002,179),(10,4,'American Sniper','Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text Text ','7.3',2014,133);
/*!40000 ALTER TABLE `movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_has_awards`
--

DROP TABLE IF EXISTS `movie_has_awards`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movie_has_awards` (
  `Movie_movieID` int(11) NOT NULL,
  `Awards_awardsID` int(11) NOT NULL,
  PRIMARY KEY (`Movie_movieID`,`Awards_awardsID`),
  KEY `fk_Movie_has_Awards_Awards1_idx` (`Awards_awardsID`),
  KEY `fk_Movie_has_Awards_Movie1_idx` (`Movie_movieID`),
  CONSTRAINT `fk_Movie_has_Awards_Awards1` FOREIGN KEY (`Awards_awardsID`) REFERENCES `awards` (`awardsID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Movie_has_Awards_Movie1` FOREIGN KEY (`Movie_movieID`) REFERENCES `movie` (`movieID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_has_awards`
--

LOCK TABLES `movie_has_awards` WRITE;
/*!40000 ALTER TABLE `movie_has_awards` DISABLE KEYS */;
INSERT INTO `movie_has_awards` VALUES (2,1),(3,1),(9,1),(10,1),(1,3),(3,3),(5,3),(8,3),(9,3),(1,4),(4,4),(6,4),(8,4),(1,5),(3,5),(9,5);
/*!40000 ALTER TABLE `movie_has_awards` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_has_genre`
--

DROP TABLE IF EXISTS `movie_has_genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movie_has_genre` (
  `Movie_movieID` int(11) NOT NULL,
  `Genre_genreID` int(11) NOT NULL,
  PRIMARY KEY (`Movie_movieID`,`Genre_genreID`),
  KEY `fk_Movie_has_Genre_Genre1_idx` (`Genre_genreID`),
  KEY `fk_Movie_has_Genre_Movie1_idx` (`Movie_movieID`),
  CONSTRAINT `fk_Movie_has_Genre_Genre1` FOREIGN KEY (`Genre_genreID`) REFERENCES `genre` (`genreID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Movie_has_Genre_Movie1` FOREIGN KEY (`Movie_movieID`) REFERENCES `movie` (`movieID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_has_genre`
--

LOCK TABLES `movie_has_genre` WRITE;
/*!40000 ALTER TABLE `movie_has_genre` DISABLE KEYS */;
INSERT INTO `movie_has_genre` VALUES (1,1),(3,1),(4,1),(5,1),(6,1),(7,1),(8,1),(10,1),(1,2),(3,2),(4,2),(5,2),(6,2),(7,2),(8,2),(9,2),(1,3),(6,3),(7,3),(9,3),(2,4),(10,4),(2,5),(9,5),(10,5),(2,6),(3,7),(4,7),(5,7),(8,7);
/*!40000 ALTER TABLE `movie_has_genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_has_producer`
--

DROP TABLE IF EXISTS `movie_has_producer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movie_has_producer` (
  `Movie_movieID` int(11) NOT NULL,
  `Producer_producerID` int(11) NOT NULL,
  PRIMARY KEY (`Movie_movieID`,`Producer_producerID`),
  KEY `fk_Movie_has_Producer_Producer1_idx` (`Producer_producerID`),
  KEY `fk_Movie_has_Producer_Movie1_idx` (`Movie_movieID`),
  CONSTRAINT `fk_Movie_has_Producer_Movie1` FOREIGN KEY (`Movie_movieID`) REFERENCES `movie` (`movieID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Movie_has_Producer_Producer1` FOREIGN KEY (`Producer_producerID`) REFERENCES `producer` (`producerID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_has_producer`
--

LOCK TABLES `movie_has_producer` WRITE;
/*!40000 ALTER TABLE `movie_has_producer` DISABLE KEYS */;
/*!40000 ALTER TABLE `movie_has_producer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie_has_writer`
--

DROP TABLE IF EXISTS `movie_has_writer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movie_has_writer` (
  `Movie_movieID` int(11) NOT NULL,
  `Writer_writerID` int(11) NOT NULL,
  PRIMARY KEY (`Movie_movieID`,`Writer_writerID`),
  KEY `fk_Movie_has_Writer_Writer1_idx` (`Writer_writerID`),
  KEY `fk_Movie_has_Writer_Movie1_idx` (`Movie_movieID`),
  CONSTRAINT `fk_Movie_has_Writer_Movie1` FOREIGN KEY (`Movie_movieID`) REFERENCES `movie` (`movieID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Movie_has_Writer_Writer1` FOREIGN KEY (`Writer_writerID`) REFERENCES `writer` (`writerID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_has_writer`
--

LOCK TABLES `movie_has_writer` WRITE;
/*!40000 ALTER TABLE `movie_has_writer` DISABLE KEYS */;
INSERT INTO `movie_has_writer` VALUES (1,1),(2,2),(3,3),(4,4),(9,5),(7,6),(8,7),(6,8),(10,9),(5,10);
/*!40000 ALTER TABLE `movie_has_writer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producer`
--

DROP TABLE IF EXISTS `producer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `producer` (
  `producerID` int(11) NOT NULL,
  `firstName` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`producerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producer`
--

LOCK TABLES `producer` WRITE;
/*!40000 ALTER TABLE `producer` DISABLE KEYS */;
INSERT INTO `producer` VALUES (1,'Jerry','Bruckheimer','1976-11-09','US'),(2,'Nora','Grossman','1978-10-10','UK'),(3,'Christopher','Nolan','1974-03-03','US'),(4,'Jon','Kilik','1978-02-18','UK'),(5,'Peter','Jackson','1978-05-05','New Zeland'),(6,'Lawrence','Gordon','1972-07-12','US'),(7,'Kevin','Feige','1975-09-30','UK'),(8,'Zack','Snyder','1974-03-26','Australia'),(9,'Clint','Eastwood','1975-04-20','US'),(10,'Ralph','Winter','1980-01-08','US');
/*!40000 ALTER TABLE `producer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rating`
--

DROP TABLE IF EXISTS `rating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rating` (
  `ratingID` int(11) NOT NULL,
  `type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ratingID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rating`
--

LOCK TABLES `rating` WRITE;
/*!40000 ALTER TABLE `rating` DISABLE KEYS */;
INSERT INTO `rating` VALUES (1,'G'),(2,'PG'),(3,'PG-13'),(4,'R'),(5,'NC-17');
/*!40000 ALTER TABLE `rating` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `userID` int(11) NOT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `accessLevel` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'datboi','harambe','admin'),(2,'lbebic','nowin','public'),(3,'smite','liga','user');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_has_movie`
--

DROP TABLE IF EXISTS `user_has_movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_has_movie` (
  `User_userID` int(11) NOT NULL,
  `Movie_movieID` int(11) NOT NULL,
  `score` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`User_userID`,`Movie_movieID`),
  KEY `fk_User_has_Movie_Movie1_idx` (`Movie_movieID`),
  KEY `fk_User_has_Movie_User1_idx` (`User_userID`),
  CONSTRAINT `fk_User_has_Movie_Movie1` FOREIGN KEY (`Movie_movieID`) REFERENCES `movie` (`movieID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_Movie_User1` FOREIGN KEY (`User_userID`) REFERENCES `user` (`userID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_has_movie`
--

LOCK TABLES `user_has_movie` WRITE;
/*!40000 ALTER TABLE `user_has_movie` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_has_movie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `writer`
--

DROP TABLE IF EXISTS `writer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `writer` (
  `writerID` int(11) NOT NULL,
  `firstName` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`writerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `writer`
--

LOCK TABLES `writer` WRITE;
/*!40000 ALTER TABLE `writer` DISABLE KEYS */;
INSERT INTO `writer` VALUES (1,'Ted','Elliott','1970-01-01','US'),(2,'Graham','Moore','1972-02-02','US'),(3,'Christopher','Nolan','1974-03-03','UK'),(4,'Peter','Craig','1976-04-04','UK'),(5,'J.R.R.','Tolkien','1892-01-03','South Africa'),(6,'Sara B.','Cooper','1978-05-05','Australia'),(7,'Stan','Lee','1980-06-06','US'),(8,'David','Ayer','1982-07-07','Australia'),(9,'Chris','Kyle','1980-08-08','US'),(10,'Tom','DeSanto','1982-09-09','UK');
/*!40000 ALTER TABLE `writer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-24 12:14:55
