CREATE DATABASE  IF NOT EXISTS `movieplanet` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `movieplanet`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: movieplanet
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
  `actorID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`actorID`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor`
--

LOCK TABLES `actor` WRITE;
/*!40000 ALTER TABLE `actor` DISABLE KEYS */;
INSERT INTO `actor` VALUES (1,'Johnny Depp','1970-09-12','US'),(2,'Benedict Cumberbatch','1976-07-17','UK'),(3,'Leonardo DiCaprio','1974-11-11','US'),(4,'Jennifer Lawrence','1990-08-15','US'),(5,'Karl Urban','1972-06-07','New Zeland'),(6,'Angelina Jolie','1975-06-04','US'),(7,'Scarlett Johansson','1984-11-22','US'),(8,'Robbie Margot','1990-07-02','Australia'),(9,'Bradley Cooper','1975-01-05','US'),(10,'Sir Ian McKellen','1939-05-25','UK'),(11,'Daisy Ridley','1985-11-05','US'),(12,'Tom Hardy','1977-07-01','UK'),(13,'Travis Fimmel','1985-05-11','Australia'),(14,'Jeremy Piven','1965-11-05','US'),(15,'Orlando Bloom','1975-10-31','UK'),(16,'Keira Knightley','1985-03-26','UK'),(17,'Joseph Gordon-Levitt','1981-02-17','US'),(18,'Ellen Page','1987-02-21','Canada'),(19,'Hugh Jackman','1968-10-12','Australia'),(20,'Patrick Stewart','1940-06-13','UK'),(21,'Charlize Theron','1975-08-07','South Africa'),(22,'Viggo Mortensen','1958-10-20','US'),(23,'Elijah Wood','1981-01-28','US'),(24,'Billy Boyd','1968-08-28','UK'),(25,'Kevin Connoly','1974-03-05','US'),(26,'Kevin Dilon','1965-08-19','US'),(27,'Ben Foster','1980-10-29','US'),(28,'Harrison Ford','1942-06-13','US'),(29,'Mark Hamil','1951-09-25','US'),(30,'Carrie Fisher','1956-10-21','US'),(31,'Robert Downey Jr.','1965-04-04','US'),(32,'Mark Ruffalo','1967-09-22','US'),(33,'Chris Evans','1981-06-13','US'),(34,'Samuel L. Jackson','1948-12-21','US'),(35,'Kurt Rusell','1951-04-17','US'),(36,'Jennifer Jason Leigh','1962-02-05','US');
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
  KEY `fk_Actor_has_Movie_Actor1_idx` (`Actor_actorID`),
  KEY `fk_Movie_movieID_idx` (`Movie_movieID`),
  CONSTRAINT `fk_Actor_actorID` FOREIGN KEY (`Actor_actorID`) REFERENCES `actor` (`actorID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Movie_movieID` FOREIGN KEY (`Movie_movieID`) REFERENCES `movie` (`movieID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor_has_movie`
--

LOCK TABLES `actor_has_movie` WRITE;
/*!40000 ALTER TABLE `actor_has_movie` DISABLE KEYS */;
INSERT INTO `actor_has_movie` VALUES (1,1),(2,2),(3,3),(3,12),(4,4),(5,9),(6,7),(7,8),(8,6),(9,10),(10,5),(10,9),(11,11),(12,3),(12,12),(12,13),(13,14),(14,15),(15,1),(15,9),(16,1),(17,3),(18,3),(19,5),(20,5),(21,13),(22,9),(23,9),(24,9),(25,15),(26,15),(27,14),(28,11),(29,11),(30,11),(31,8),(32,8),(33,8),(34,8),(34,16),(35,16),(36,16);
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
  `directorID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`directorID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `director`
--

LOCK TABLES `director` WRITE;
/*!40000 ALTER TABLE `director` DISABLE KEYS */;
INSERT INTO `director` VALUES (1,'Gob Verbinski','1970-01-01','US'),(2,'Morten Tyldum','1972-02-02','Australia'),(3,'Christopher Nolan','1974-03-03','UK'),(4,'Francis Lawrence','1976-04-04','US'),(5,'Peter Jackson','1978-05-05','New Zeland'),(6,'Simon West','1980-06-06','UK'),(7,'Joss Whedon','1982-07-07','Australia'),(8,'David Ayer','1984-07-07','Australia'),(9,'Clint Eastwood','1970-08-08','US'),(10,'Bryan Singer','1972-09-09','US'),(11,'J.J. Abrams','1975-01-01','UK'),(12,'Alejandro G. Inarritu','1972-08-21','Mexico'),(13,'George Miller','1970-03-31','US'),(14,'Duncan Jones','1979-02-14','US'),(15,'Doug Ellin','1980-04-25','US'),(16,'Quentin Tarantino','1963-03-27','US');
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
  KEY `fk_Director_has_Movie_Director1_idx` (`Director_directorID`),
  KEY `fk_Movie_movieID_idx` (`Movie_movieID`),
  CONSTRAINT `fk_Director_directorID` FOREIGN KEY (`Director_directorID`) REFERENCES `director` (`directorID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `director_has_movie`
--

LOCK TABLES `director_has_movie` WRITE;
/*!40000 ALTER TABLE `director_has_movie` DISABLE KEYS */;
INSERT INTO `director_has_movie` VALUES (1,1),(2,2),(3,3),(4,4),(5,9),(6,7),(7,8),(8,6),(9,10),(10,5),(11,11),(12,12),(13,13),(14,14),(15,15),(16,16);
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
INSERT INTO `genre` VALUES (1,'Action'),(2,'Adventure'),(3,'Fantasy'),(4,'Biography'),(5,'Drama'),(6,'Thriller'),(7,'Sci-Fi'),(8,'Comedy'),(9,'Crime'),(10,'Mystery');
/*!40000 ALTER TABLE `genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `movie` (
  `movieID` int(11) NOT NULL AUTO_INCREMENT,
  `ratingID` int(11) NOT NULL,
  `name` varchar(200) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `score` decimal(10,2) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  `screenTime` int(11) DEFAULT NULL,
  `image` varchar(150) DEFAULT NULL,
  `trailer` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`movieID`),
  KEY `fk_Movie_Rating1_idx` (`ratingID`),
  CONSTRAINT `fk_Movie_Rating1` FOREIGN KEY (`ratingID`) REFERENCES `rating` (`ratingID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie`
--

LOCK TABLES `movie` WRITE;
/*!40000 ALTER TABLE `movie` DISABLE KEYS */;
INSERT INTO `movie` VALUES (1,3,'Pirates of the Caribbean: The Curse of the Black Pearl','Capt. Jack Sparrow (Johnny Depp) arrives at Port Royal in the Caribbean without a ship or crew. His timing is inopportune, however, because later that evening the town is besieged by a pirate ship. The pirates kidnap the governor\'s daughter, Elizabeth (Keira Knightley), who\'s in possession of a valuable coin that is linked to a curse that has transformed the pirates into the undead. A gallant blacksmith (Orlando Bloom) in love with Elizabeth allies with Sparrow in pursuit of the pirates.',0.00,2003,143,'pirates.jpg','https://www.youtube.com/embed/naQr0uTrH_s'),(2,3,'The Imitation Game','In 1939, newly created British intelligence agency MI6 recruits Cambridge mathematics alumnus Alan Turing (Benedict Cumberbatch) to crack Nazi codes, including Enigma -- which cryptanalysts had thought unbreakable. Turing\'s team, including Joan Clarke (Keira Knightley), analyze Enigma messages while he builds a machine to decipher them. Turing and team finally succeed and become heroes, but in 1952, the quiet genius encounters disgrace when authorities reveal he is gay and send him to prison.',0.00,2014,114,'imitation.jpg','https://www.youtube.com/embed/nuPZUUED5uk'),(3,3,'Inception','Dom Cobb (Leonardo DiCaprio) is a thief with the rare ability to enter people\'s dreams and steal their secrets from their subconscious. His skill has made him a hot commodity in the world of corporate espionage but has also cost him everything he loves. Cobb gets a chance at redemption when he is offered a seemingly impossible task: Plant an idea in someone\'s mind. If he succeeds, it will be the perfect crime, but a dangerous enemy anticipates Cobb\'s every move.',8.00,2010,148,'inception.jpg','https://www.youtube.com/embed/YoHD9XEInc0'),(4,3,'The Hunger Games: Mockingjay - Part 2','Realizing the stakes are no longer just for survival, Katniss Everdeen (Jennifer Lawrence) teams up with her closest friends, including Peeta (Josh Hutcherson), Gale (Liam Hemsworth) and Finnick for the ultimate mission. Together, they leave District 13 to liberate the citizens of war-torn Panem and assassinate President Snow, who\'s obsessed with destroying Katniss. What lies ahead are mortal traps, dangerous enemies and moral choices that will ultimately determine the future of millions.',6.60,2015,137,'thg.jpg','https://www.youtube.com/embed/n-7K_OjsDCQ'),(5,3,'X-Men','They are children of the atom, homo superior, the next link in the chain of evolution. Each was born with a unique genetic mutation, which at puberty manifested itself in extraordinary powers. In a world filled with hate and prejudice, they are feared by those who cannot accept their differences. Led by Xavier the X-Men fight to protect a world that fears them. They are locked in a battle with former colleague and friend, Magneto who believes humans and mutants should never co-exist.',7.40,2000,104,'xmen.jpg','https://www.youtube.com/embed/Iy5R5_T243w'),(6,3,'Suicide Squad','Figuring they\'re all expendable, a U.S. intelligence officer decides to assemble a team of dangerous, incarcerated supervillains for a top-secret mission. Now armed with government weapons, Deadshot (Will Smith), Harley Quinn (Margot Robbie), Captain Boomerang, Killer Croc and other despicable inmates must learn to work together. Dubbed Task Force X, the criminals unite to battle a mysterious and powerful entity, while the diabolical Joker (Jared Leto) launches an evil agenda of his own.',6.50,2016,123,'ss.jpg','https://www.youtube.com/embed/CmRih_VtVAs'),(7,3,'Lara Croft: Tomb Raider','This live action feature is inspired by the most successful interactive video-game character in history -- Lara Croft. Beautiful and brainy, Lara (Angelina Jolie) is the heroine of Eidos Interactive\'s phenomenally successful \"Tomb Raider\" game series. It is one woman\'s fearless quest criss-crossing the globe, in an amazing attempt to save the world.',5.70,2001,100,'lctr.jpg','https://www.youtube.com/embed/cnNBqNb3taw'),(8,3,'Avengers: The Age of Ultron','When Tony Stark (Robert Downey Jr.) jump-starts a dormant peacekeeping program, things go terribly awry, forcing him, Thor (Chris Hemsworth), the Incredible Hulk (Mark Ruffalo) and the rest of the Avengers to reassemble. As the fate of Earth hangs in the balance, the team is put to the ultimate test as they battle Ultron, a technological terror hell-bent on human extinction. Along the way, they encounter two mysterious and powerful newcomers, Pietro and Wanda Maximoff.',7.60,2015,141,'ultron.jpg','https://www.youtube.com/embed/tmeOjFno6Do'),(9,3,'Lord of the Rings: The Two Towers','The sequel to the Golden Globe-nominated and AFI Award-winning \"The Lord of the Rings: The Fellowship of the Ring,\" \"The Two Towers\" follows the continuing quest of Frodo (Elijah Wood) and the Fellowship to destroy the One Ring. Frodo and Sam (Sean Astin) discover they are being followed by the mysterious Gollum. Aragorn (Viggo Mortensen), the Elf archer Legolas and Gimli the Dwarf encounter the besieged Rohan kingdom, whose once great King Theoden has fallen under Saruman\'s deadly spell.',10.00,2002,179,'lotr.jpg','https://www.youtube.com/embed/LbfMDwc4azU'),(10,4,'American Sniper','U.S. Navy SEAL Chris Kyle (Bradley Cooper) takes his sole mission -- protect his comrades -- to heart and becomes one of the most lethal snipers in American history. His pinpoint accuracy not only saves countless lives but also makes him a prime target of insurgents. Despite grave danger and his struggle to be a good husband and father to his family back in the States, Kyle serves four tours of duty in Iraq. However, when he finally returns home, he finds that he cannot leave the war behind.',7.30,2014,133,'sniper.jpg','https://www.youtube.com/embed/99k3u9ay1gs'),(11,3,'Star Wars: The Force Awakens','30 years after the defeat of Darth Vader and the Empire, Rey, a scavenger from the planet Jakku, finds a BB-8 droid that knows the whereabouts of the long lost Luke Skywalker. Rey, as well as a rogue stormtrooper and two smugglers, are thrown into the middle of a battle between the Resistance and the daunting legions of the First Order.',0.00,2015,136,'sw.jpg','https://www.youtube.com/embed/sGbxmsDFVnE'),(12,4,'The Revenant','While exploring the uncharted wilderness in 1823, frontiersman Hugh Glass (Leonardo DiCaprio) sustains life-threatening injuries from a brutal bear attack. When a member (Tom Hardy) of his hunting team kills his young son (Forrest Goodluck) and leaves him for dead, Glass must utilize his survival skills to find a way back to civilization. Grief-stricken and fueled by vengeance, the legendary fur trapper treks through the snowy terrain to track down the man who betrayed him.',0.00,2015,156,'rev.jpg','https://www.youtube.com/embed/LoebZZ8K5N0'),(13,4,'Mad Max: The Fury Road','Years after the collapse of civilization, the tyrannical Immortan Joe enslaves apocalypse survivors inside the desert fortress the Citadel. When the warrior Imperator Furiosa (Charlize Theron) leads the despot\'s five wives in a daring escape, she forges an alliance with Max Rockatansky (Tom Hardy), a loner and former captive. Fortified in the massive, armored truck the War Rig, they try to outrun the ruthless warlord and his henchmen in a deadly high-speed chase through the Wasteland.',8.10,2015,120,'max.jpg','https://www.youtube.com/embed/vjBb4SZ0F6Q'),(14,3,'Warcraft: The Beginning','Looking to escape from his dying world, the orc shaman Gul\'dan utilizes dark magic to open a portal to the human realm of Azeroth. Supported by the fierce fighter Blackhand, Gul\'dan organizes the orc clans into a conquering army called the Horde. Uniting to protect Azeroth from these hulking invaders are King Llane, the mighty warrior Anduin Lothar (Travis Fimmel) and the powerful wizard Medivh. As the two races collide, leaders from each side start to question if war is the only answer.',7.00,2016,123,'wow.jpg','https://www.youtube.com/embed/u3jVet3ZWPw'),(15,4,'Entourage','While hanging with pals Eric (Kevin Connolly), Turtle (Jerry Ferrara) and Johnny Drama (Kevin Dillon), movie star Vince Chase (Adrian Grenier) gets a phone call from Ari Gold (Jeremy Piven), his former agent, who now runs a major studio. Ari offers Vince the leading role in his first production, but the actor insists on also directing the film. Things get out of hand when the $100 million flick goes over budget, leaving Ari, Vince and the boys at the mercy of the cutthroat world of Hollywood.',0.00,2015,104,'entourage.jpg','https://www.youtube.com/embed/SGSE_XPF4_g'),(16,4,'The Hateful Eight','While racing toward the town of Red Rock in post-Civil War Wyoming, bounty hunter John \"The Hangman\" Ruth (Kurt Russell) and his fugitive prisoner (Jennifer Jason Leigh) encounter another bounty hunter (Samuel L. Jackson) and a man who claims to be a sheriff. Hoping to find shelter from a blizzard, the group travels to a stagecoach stopover located on a mountain pass. Greeted there by four strangers, the eight travelers soon learn that they may not make it to their destination after all.',0.00,2015,187,'hate.jpg','https://www.youtube.com/embed/gnRbXn4-Yis');
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
  KEY `fk_Movie_has_Awards_Movie1_idx` (`Movie_movieID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_has_awards`
--

LOCK TABLES `movie_has_awards` WRITE;
/*!40000 ALTER TABLE `movie_has_awards` DISABLE KEYS */;
INSERT INTO `movie_has_awards` VALUES (1,3),(1,4),(1,5),(2,1),(3,1),(3,3),(3,5),(4,4),(5,3),(6,4),(8,3),(8,4),(9,1),(9,3),(9,5),(10,1),(11,3),(11,4),(11,5),(12,1),(12,2),(12,4),(12,5),(13,1),(13,3),(13,5),(16,1),(16,2),(16,5);
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
  KEY `fk_Movie_has_Genre_Movie1_idx` (`Movie_movieID`),
  KEY `fk_Genre_genreID_idx` (`Genre_genreID`),
  CONSTRAINT `fk_Genre_genreID` FOREIGN KEY (`Genre_genreID`) REFERENCES `genre` (`genreID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_has_genre`
--

LOCK TABLES `movie_has_genre` WRITE;
/*!40000 ALTER TABLE `movie_has_genre` DISABLE KEYS */;
INSERT INTO `movie_has_genre` VALUES (1,1),(1,2),(1,3),(2,4),(2,5),(2,6),(3,1),(3,2),(3,7),(4,1),(4,2),(4,7),(5,1),(5,2),(5,7),(6,1),(6,2),(6,3),(7,1),(7,2),(7,3),(8,1),(8,2),(8,7),(9,2),(9,3),(9,5),(10,1),(10,4),(10,5),(11,1),(11,2),(11,3),(12,2),(12,5),(12,6),(13,1),(13,2),(13,7),(14,1),(14,2),(14,3),(15,8),(16,5),(16,9),(16,10);
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
  KEY `fk_Producer_producerID_idx` (`Producer_producerID`),
  CONSTRAINT `fk_Producer_producerID` FOREIGN KEY (`Producer_producerID`) REFERENCES `producer` (`producerID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_has_producer`
--

LOCK TABLES `movie_has_producer` WRITE;
/*!40000 ALTER TABLE `movie_has_producer` DISABLE KEYS */;
INSERT INTO `movie_has_producer` VALUES (1,1),(2,2),(3,3),(4,4),(5,9),(6,7),(7,8),(8,6),(9,10),(10,5),(11,11),(12,12),(13,13),(14,14),(15,15),(16,16);
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
  `Writer_writerID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movie_has_writer`
--

LOCK TABLES `movie_has_writer` WRITE;
/*!40000 ALTER TABLE `movie_has_writer` DISABLE KEYS */;
INSERT INTO `movie_has_writer` VALUES (2,2),(3,3),(4,4),(5,10),(6,8),(7,6),(8,7),(9,5),(10,9),(11,11),(12,12),(13,13),(14,14),(15,15),(16,16),(1,1);
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
  `name` varchar(45) DEFAULT NULL,
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
INSERT INTO `producer` VALUES (1,'Jerry Bruckheimer','1976-11-09','US'),(2,'Nora Grossman','1978-10-10','UK'),(3,'Christopher Nolan','1974-03-03','US'),(4,'Jon Kilik','1978-02-18','UK'),(5,'Peter Jackson','1978-05-05','New Zeland'),(6,'Lawrence Gordon','1972-07-12','US'),(7,'Kevin Feige','1975-09-30','UK'),(8,'Zack Snyder','1974-03-26','Australia'),(9,'Clint Eastwood','1975-04-20','US'),(10,'Ralph Winter','1980-01-08','US'),(11,'J.J. Abrams','1975-01-01','US'),(12,'Steve Golin','1960-09-11','US'),(13,'George Miller','1970-03-31','US'),(14,'Stuart Fenegan','1973-10-17','UK'),(15,'Doug Ellin','1980-04-25','US'),(16,'Shannon McIntosh','1974-07-07','UK');
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
-- Table structure for table `screeningtime`
--

DROP TABLE IF EXISTS `screeningtime`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `screeningtime` (
  `screeningTimeID` int(11) NOT NULL,
  `hour` int(11) DEFAULT NULL,
  `minute` int(11) DEFAULT NULL,
  `second` int(11) DEFAULT NULL,
  PRIMARY KEY (`screeningTimeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `screeningtime`
--

LOCK TABLES `screeningtime` WRITE;
/*!40000 ALTER TABLE `screeningtime` DISABLE KEYS */;
/*!40000 ALTER TABLE `screeningtime` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `accessLevel` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`userID`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'root','toor','admin'),(2,'lbebic','nowin','user'),(3,'smiterino','liga','admin');
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
  KEY `fk_User_has_Movie_User1_idx` (`User_userID`),
  KEY `fk_Movie_movieID_idx` (`Movie_movieID`),
  CONSTRAINT `fk_User_userID` FOREIGN KEY (`User_userID`) REFERENCES `user` (`userID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_has_movie`
--

LOCK TABLES `user_has_movie` WRITE;
/*!40000 ALTER TABLE `user_has_movie` DISABLE KEYS */;
INSERT INTO `user_has_movie` VALUES (1,1,NULL),(1,2,NULL),(1,3,8.00),(1,4,NULL),(1,8,NULL),(1,9,10.00),(1,10,NULL),(1,13,NULL),(1,14,7.00);
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
  `name` varchar(45) DEFAULT NULL,
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
INSERT INTO `writer` VALUES (1,'Ted Elliott','1970-01-01','US'),(2,'Graham Moore','1972-02-02','US'),(3,'Christopher Nolan','1974-03-03','UK'),(4,'Peter Craig','1976-04-04','UK'),(5,'J.R.R. Tolkien','1892-01-03','South Africa'),(6,'Sara B. Cooper','1978-05-05','Australia'),(7,'Stan Lee','1980-06-06','US'),(8,'David Ayer','1982-07-07','Australia'),(9,'Chris Kyle','1980-08-08','US'),(10,'Tom DeSanto','1982-09-09','UK'),(11,'Lawrence Kasdan','1980-07-07','US'),(12,'Mark L. Smith','1968-11-14','US'),(13,'George Miller','1970-03-31','US'),(14,'Charles Leavitt','1985-11-06','UK'),(15,'Doug Ellin','1980-04-25','US'),(16,'Quentin Tarantino','1963-03-27','US');
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

-- Dump completed on 2016-12-04 21:24:25
