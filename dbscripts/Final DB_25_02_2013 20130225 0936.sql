-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.5.27


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema ceylon_journey_db
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ ceylon_journey_db;
USE ceylon_journey_db;

--
-- Table structure for table `ceylon_journey_db`.`admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `adminid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL DEFAULT '',
  `first_name` varchar(45) NOT NULL DEFAULT '',
  `last_name` varchar(45) NOT NULL DEFAULT '',
  `office_telephone` varchar(45) NOT NULL DEFAULT '',
  `mobile_number` varchar(45) NOT NULL DEFAULT '',
  `emaill_address` varchar(45) NOT NULL DEFAULT '',
  PRIMARY KEY (`adminid`),
  KEY `FK_admin_1` (`username`),
  CONSTRAINT `FK_admin_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ceylon_journey_db`.`admin`
--

/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` (`adminid`,`username`,`first_name`,`last_name`,`office_telephone`,`mobile_number`,`emaill_address`) VALUES 
 (1,'a','Duminda ','Hettiarachchi','2908605','0778322434','duminda.nirosh@gmail.com'),
 (2,'admin','Admin','Admin','+94-123456789','+94-123456789','admin@ceylonjourney.com');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;


--
-- Table structure for table `ceylon_journey_db`.`city_detail`
--

DROP TABLE IF EXISTS `city_detail`;
CREATE TABLE `city_detail` (
  `city_id` int(11) NOT NULL AUTO_INCREMENT,
  `city_name` varchar(45) NOT NULL,
  `district_id` int(11) NOT NULL,
  `city_status` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`city_id`),
  KEY `FK_city_detail_1` (`district_id`),
  CONSTRAINT `FK_city_detail_1` FOREIGN KEY (`district_id`) REFERENCES `district_detail` (`district_id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ceylon_journey_db`.`city_detail`
--

/*!40000 ALTER TABLE `city_detail` DISABLE KEYS */;
INSERT INTO `city_detail` (`city_id`,`city_name`,`district_id`,`city_status`) VALUES 
 (29,'Sigiriya',16,1),
 (30,'Anuradhapura',2,1),
 (31,'Polonnaruwa',21,1),
 (32,'Galle',6,1),
 (33,'Kollupittiya',5,1),
 (34,'Kandy',11,1),
 (35,'Jaffna',9,1),
 (37,'Dehiwala',5,1),
 (38,'Kokuvil',9,1),
 (39,'Yala',18,1),
 (40,'Pinnawala',12,1),
 (41,'Peradeniya',11,1),
 (42,'Ratnapura',23,1),
 (43,'Nuwara Eliya',20,1),
 (44,'Beruwala',10,1),
 (45,'Welimada',20,1),
 (46,'Dambulla',16,1);
/*!40000 ALTER TABLE `city_detail` ENABLE KEYS */;


--
-- Table structure for table `ceylon_journey_db`.`district_detail`
--

DROP TABLE IF EXISTS `district_detail`;
CREATE TABLE `district_detail` (
  `district_id` int(11) NOT NULL AUTO_INCREMENT,
  `district_name` varchar(45) DEFAULT NULL,
  `district_status` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`district_id`),
  UNIQUE KEY `district_name_UNIQUE` (`district_name`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ceylon_journey_db`.`district_detail`
--

/*!40000 ALTER TABLE `district_detail` DISABLE KEYS */;
INSERT INTO `district_detail` (`district_id`,`district_name`,`district_status`) VALUES 
 (1,'Ampara',1),
 (2,'Anuradhapura',1),
 (3,'Badulla',1),
 (4,'Batticaloa',1),
 (5,'Colombo',1),
 (6,'Galle',1),
 (7,'Gampaha',1),
 (8,'Hambantota',1),
 (9,'Jaffna',1),
 (10,'Kalutara',1),
 (11,'Kandy',1),
 (12,'Kegalle',1),
 (13,'Kilinochchi',1),
 (14,'Kurunegala',1),
 (15,'Mannar',1),
 (16,'Matale',1),
 (17,'Matara',1),
 (18,'Moneragala',1),
 (19,'Mullaitivu',1),
 (20,'Nuwara Eliya',1),
 (21,'Polonnaruwa',1),
 (22,'Puttalam',1),
 (23,'Ratnapura',1),
 (24,'Trincomalee',1),
 (25,'Vavuniya',1);
/*!40000 ALTER TABLE `district_detail` ENABLE KEYS */;


--
-- Table structure for table `ceylon_journey_db`.`hot_location`
--

DROP TABLE IF EXISTS `hot_location`;
CREATE TABLE `hot_location` (
  `location_id` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`location_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ceylon_journey_db`.`hot_location`
--

/*!40000 ALTER TABLE `hot_location` DISABLE KEYS */;
INSERT INTO `hot_location` (`location_id`) VALUES 
 (95);
/*!40000 ALTER TABLE `hot_location` ENABLE KEYS */;


--
-- Table structure for table `ceylon_journey_db`.`hotel`
--

DROP TABLE IF EXISTS `hotel`;
CREATE TABLE `hotel` (
  `hotel_id` int(11) NOT NULL AUTO_INCREMENT,
  `hotel_name` varchar(45) DEFAULT NULL,
  `hotel_address` varchar(255) DEFAULT NULL,
  `email_address` varchar(45) DEFAULT NULL,
  `hotel_status` tinyint(4) DEFAULT NULL,
  `hotel_description` varchar(1000) DEFAULT NULL,
  `entered_by` varchar(45) DEFAULT NULL,
  `entered_date` datetime DEFAULT NULL,
  PRIMARY KEY (`hotel_id`),
  UNIQUE KEY `hotel_name_UNIQUE` (`hotel_name`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ceylon_journey_db`.`hotel`
--

/*!40000 ALTER TABLE `hotel` DISABLE KEYS */;
INSERT INTO `hotel` (`hotel_id`,`hotel_name`,`hotel_address`,`email_address`,`hotel_status`,`hotel_description`,`entered_by`,`entered_date`) VALUES 
 (5,'Taj Samudra','NÂº25 Galle Face Centre Road  Colombo 03                            ','tajsamudra@gmail.com',1,'The Taj Samudra is strategically based in Colombo\'s business hub and is also just a short stroll to the city centre for shopping and entertainment. Spread across 11 acres of beautifully landscaped gardens, the hotel also boasts a peerless view of the Indian Ocean and the Galle Face Green, a hallmark of Colombo.                           ','a','2013-02-19 16:31:45'),
 (6,'Cinnamon Citadel Kandy','124, Srimath Kuda Ratwatte Mw., Kandy, Sri Lanka.                         ','citadel@hotmail.com',1,'Located in the heart of Kandy, the Cinnamon Citadel Kandy (formerly Chaaya Citadel Hotel Kandy) is surrounded by awesome beauty of the Kandyan landscape of sweeping green hills, broken by the winding path of the Sri Lanka\'s longest river, the Mahaweli. Kandy, a 3-hour drive from Colombo, retains a traditional Sri Lankan lifestyle. The Temple of the Tooth, which houses the sacred tooth relic of the Lord Buddha is one of the many important sites to visit.                         ','a','2013-02-19 16:34:13'),
 (7,'No Image Hotel','No address                            ','no mail',1,'This hotel does not have any image for testing purpose.                           ','a','2013-02-20 15:58:31');
/*!40000 ALTER TABLE `hotel` ENABLE KEYS */;


--
-- Table structure for table `ceylon_journey_db`.`hotel_category_type`
--

DROP TABLE IF EXISTS `hotel_category_type`;
CREATE TABLE `hotel_category_type` (
  `hotel_id` int(11) NOT NULL DEFAULT '0',
  `hotel_type_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`hotel_id`,`hotel_type_id`),
  KEY `FK_hotel_category_type_2` (`hotel_type_id`),
  CONSTRAINT `FK_hotel_category_type_1` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`hotel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='InnoDB free: 10240 kB';

--
-- Dumping data for table `ceylon_journey_db`.`hotel_category_type`
--

/*!40000 ALTER TABLE `hotel_category_type` DISABLE KEYS */;
INSERT INTO `hotel_category_type` (`hotel_id`,`hotel_type_id`) VALUES 
 (5,6),
 (6,7),
 (7,8);
/*!40000 ALTER TABLE `hotel_category_type` ENABLE KEYS */;


--
-- Table structure for table `ceylon_journey_db`.`hotel_city`
--

DROP TABLE IF EXISTS `hotel_city`;
CREATE TABLE `hotel_city` (
  `hotel_id` int(11) NOT NULL,
  `city_id` int(11) NOT NULL,
  PRIMARY KEY (`hotel_id`,`city_id`),
  KEY `FK_hotel_city_2` (`city_id`),
  CONSTRAINT `FK_hotel_city_1` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`hotel_id`),
  CONSTRAINT `FK_hotel_city_2` FOREIGN KEY (`city_id`) REFERENCES `city_detail` (`city_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='InnoDB free: 10240 kB';

--
-- Dumping data for table `ceylon_journey_db`.`hotel_city`
--

/*!40000 ALTER TABLE `hotel_city` DISABLE KEYS */;
INSERT INTO `hotel_city` (`hotel_id`,`city_id`) VALUES 
 (7,30),
 (5,33),
 (6,34);
/*!40000 ALTER TABLE `hotel_city` ENABLE KEYS */;


--
-- Table structure for table `ceylon_journey_db`.`hotel_contact`
--

DROP TABLE IF EXISTS `hotel_contact`;
CREATE TABLE `hotel_contact` (
  `hotel_id` int(11) NOT NULL,
  `contact_number` varchar(45) NOT NULL,
  `contact_type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`hotel_id`,`contact_number`),
  CONSTRAINT `FK_hotel_contact_1` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`hotel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ceylon_journey_db`.`hotel_contact`
--

/*!40000 ALTER TABLE `hotel_contact` DISABLE KEYS */;
/*!40000 ALTER TABLE `hotel_contact` ENABLE KEYS */;


--
-- Table structure for table `ceylon_journey_db`.`hotel_contract`
--

DROP TABLE IF EXISTS `hotel_contract`;
CREATE TABLE `hotel_contract` (
  `hotel_id` int(10) unsigned NOT NULL DEFAULT '0',
  `start_date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `end_date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `entered_date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`hotel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ceylon_journey_db`.`hotel_contract`
--

/*!40000 ALTER TABLE `hotel_contract` DISABLE KEYS */;
INSERT INTO `hotel_contract` (`hotel_id`,`start_date`,`end_date`,`entered_date`) VALUES 
 (5,'2013-02-23 11:20:39','2014-02-23 11:20:39','2013-02-23 11:20:39');
/*!40000 ALTER TABLE `hotel_contract` ENABLE KEYS */;


--
-- Table structure for table `ceylon_journey_db`.`hotel_image`
--

DROP TABLE IF EXISTS `hotel_image`;
CREATE TABLE `hotel_image` (
  `hotel_id` int(11) NOT NULL,
  `image_name` varchar(45) NOT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `image_size` varchar(45) DEFAULT NULL,
  `image_type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`hotel_id`,`image_name`),
  KEY `hotel_id_idx` (`hotel_id`),
  CONSTRAINT `hotel_id` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`hotel_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ceylon_journey_db`.`hotel_image`
--

/*!40000 ALTER TABLE `hotel_image` DISABLE KEYS */;
INSERT INTO `hotel_image` (`hotel_id`,`image_name`,`image_url`,`image_size`,`image_type`) VALUES 
 (5,'5-TajSamudra.jpg','D:\\SoftwareDevelopment\\Project\\LocalEdition\\JSPServletEdition\\CeylonJourney\\web\\uploadImages\\hotel\\5\\','12242','image/jpeg'),
 (6,'6-chaaya_citadel_hotel.jpg','D:\\SoftwareDevelopment\\Project\\LocalEdition\\JSPServletEdition\\CeylonJourney\\web\\uploadImages\\hotel\\6\\','5385','image/jpeg');
/*!40000 ALTER TABLE `hotel_image` ENABLE KEYS */;


--
-- Table structure for table `ceylon_journey_db`.`hotel_location`
--

DROP TABLE IF EXISTS `hotel_location`;
CREATE TABLE `hotel_location` (
  `hotel_id` int(11) NOT NULL,
  `location_id` int(11) NOT NULL,
  PRIMARY KEY (`hotel_id`,`location_id`),
  KEY `FK_hotel_location_2` (`location_id`),
  CONSTRAINT `FK_hotel_location_1` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`hotel_id`),
  CONSTRAINT `FK_hotel_location_2` FOREIGN KEY (`location_id`) REFERENCES `location` (`location_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='InnoDB free: 10240 kB';

--
-- Dumping data for table `ceylon_journey_db`.`hotel_location`
--

/*!40000 ALTER TABLE `hotel_location` DISABLE KEYS */;
/*!40000 ALTER TABLE `hotel_location` ENABLE KEYS */;


--
-- Table structure for table `ceylon_journey_db`.`hotel_officer`
--

DROP TABLE IF EXISTS `hotel_officer`;
CREATE TABLE `hotel_officer` (
  `hotel_officer_id` int(4) NOT NULL AUTO_INCREMENT,
  `hotel_id` int(4) NOT NULL,
  `username` varchar(45) NOT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `office_telephone` varchar(45) DEFAULT NULL,
  `mobile_number` varchar(45) DEFAULT NULL,
  `email_address` varchar(155) DEFAULT NULL,
  PRIMARY KEY (`hotel_officer_id`),
  KEY `FK_hotel_officer_1` (`username`),
  KEY `FK_hotel_officer_2` (`hotel_id`),
  CONSTRAINT `FK_hotel_officer_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`),
  CONSTRAINT `FK_hotel_officer_2` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`hotel_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ceylon_journey_db`.`hotel_officer`
--

/*!40000 ALTER TABLE `hotel_officer` DISABLE KEYS */;
INSERT INTO `hotel_officer` (`hotel_officer_id`,`hotel_id`,`username`,`first_name`,`last_name`,`office_telephone`,`mobile_number`,`email_address`) VALUES 
 (1,5,'hotel','hotel','hotel','0771111112','456','hotel@gmail.com');
/*!40000 ALTER TABLE `hotel_officer` ENABLE KEYS */;


--
-- Table structure for table `ceylon_journey_db`.`hotel_type`
--

DROP TABLE IF EXISTS `hotel_type`;
CREATE TABLE `hotel_type` (
  `hotel_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(45) DEFAULT NULL,
  `hotel_type_status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`hotel_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ceylon_journey_db`.`hotel_type`
--

/*!40000 ALTER TABLE `hotel_type` DISABLE KEYS */;
INSERT INTO `hotel_type` (`hotel_type_id`,`type_name`,`hotel_type_status`) VALUES 
 (6,'Five Star',1),
 (7,'Three Star',1),
 (8,'One Star',1);
/*!40000 ALTER TABLE `hotel_type` ENABLE KEYS */;


--
-- Table structure for table `ceylon_journey_db`.`internal_officer`
--

DROP TABLE IF EXISTS `internal_officer`;
CREATE TABLE `internal_officer` (
  `internal_officer_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `office_telephone` varchar(45) DEFAULT NULL,
  `mobile_number` varchar(45) DEFAULT NULL,
  `email_address` varchar(155) NOT NULL DEFAULT '',
  PRIMARY KEY (`internal_officer_id`),
  KEY `username_idx` (`username`),
  CONSTRAINT `username` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ceylon_journey_db`.`internal_officer`
--

/*!40000 ALTER TABLE `internal_officer` DISABLE KEYS */;
INSERT INTO `internal_officer` (`internal_officer_id`,`username`,`first_name`,`last_name`,`office_telephone`,`mobile_number`,`email_address`) VALUES 
 (2,'internal','Internal','Internal','0772222222','0212226754','internal@gmail.com');
/*!40000 ALTER TABLE `internal_officer` ENABLE KEYS */;


--
-- Table structure for table `ceylon_journey_db`.`location`
--

DROP TABLE IF EXISTS `location`;
CREATE TABLE `location` (
  `location_id` int(11) NOT NULL AUTO_INCREMENT,
  `location_name` varchar(155) NOT NULL,
  `location_description` varchar(1000) DEFAULT NULL,
  `location_address` varchar(155) DEFAULT NULL,
  `location_status` tinyint(4) DEFAULT NULL,
  `welcome_image_name` varchar(45) DEFAULT NULL,
  `welcome_image_url` varchar(155) DEFAULT NULL,
  `entered_by` varchar(45) NOT NULL DEFAULT '',
  `entered_date` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`location_id`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=latin1 COMMENT='InnoDB free: 9216 kB; (`location_id`) REFER `ceylon_journey_';

--
-- Dumping data for table `ceylon_journey_db`.`location`
--

/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` (`location_id`,`location_name`,`location_description`,`location_address`,`location_status`,`welcome_image_name`,`welcome_image_url`,`entered_by`,`entered_date`) VALUES 
 (84,'Sigiriya','Sigiriya is a large stone and ancient rock fortress and palace ruin in the central Matale District of Central Province, Sri Lanka, surrounded by the remains of an extensive network of gardens, reservoirs, and other structures. A popular tourist destination, Sigiriya is also renowned for its ancient paintings which are reminiscent of the Ajanta Caves of India. It is one of the eight World Heritage Sites of Sri Lanka.','Central Matale District of Central Province, Sri Lanka                 ',1,'84-Sigiriya.jpg','D:\\SoftwareDevelopment\\Project\\LocalEdition\\JSPServletEdition\\CeylonJourney\\web\\uploadImages\\84\\','admin','2013-02-21 23:51:32'),
 (85,'Kuttam Pokuna','One of the best specimen of bathing tanks or pools in ancient Sri Lanka is the pair of pools known as Kuttam Pokuna (Twin Ponds/Pools). The said pair of pools were built by the Sinhalese in the ancient kingdom of Anuradhapura. These are considered one of the significant achievements in the field of hydrological engineering and outstanding architectural and artistic creations of the ancient Sinhalese.                            ','Anuradhapura                            ',1,'85-Kuttam-Pokuna.jpg','D:\\SoftwareDevelopment\\Project\\LocalEdition\\JSPServletEdition\\CeylonJourney\\web\\uploadImages\\85\\','a','2013-02-19 16:22:37'),
 (86,'Vatadage','The Polonnaruwa Vatadage is an ancient structure dating back to the Polonnaruwa period of Sri Lanka. It is believed to have been built during the reign of Parakramabahu I to hold the tooth relic of the Buddha, or during the reign of Nissanka Malla to hold the alms bowl used by the Buddha. Both these venerated relics would have given the structure a great significance and importance at the time. Located within the ancient city of Polonnaruwa, it is the best preserved example of a vatadage in the country, and has been described as the \"ultimate development\" of this type of architecture. Abandoned for several centuries, excavation work at the Polonnaruwa Vatadage began in 1903.                       ','Located in a quadrangular area known as the Dalada Maluva in the ancient city of Polonnaruwa.                     ',1,'86-Vatadage.jpg','D:\\SoftwareDevelopment\\Project\\LocalEdition\\JSPServletEdition\\CeylonJourney\\web\\uploadImages\\86\\','a','2013-02-19 16:25:49');
INSERT INTO `location` (`location_id`,`location_name`,`location_description`,`location_address`,`location_status`,`welcome_image_name`,`welcome_image_url`,`entered_by`,`entered_date`) VALUES 
 (87,'Galle Fort','Galle Fort, in the Bay of Galle on the southwest coast of Sri Lanka, was built first in 1588 by the Portuguese, then extensively fortified by the Dutch during the 17th century from 1649 onwards. It is a historical, archaeological and architectural heritage monument, which even after more than 423 years maintains a polished appearance, due to extensive reconstruction work done by Archaeological Department of Sri Lanka.                            ','Galle                            ',1,'87-GalleFort.JPG','D:\\SoftwareDevelopment\\Project\\LocalEdition\\JSPServletEdition\\CeylonJourney\\web\\uploadImages\\87\\','a','2013-02-19 16:29:09'),
 (89,'No Image','aaaa                        ','aaaa                            ',1,NULL,NULL,'a','2013-02-19 22:13:01'),
 (91,'Yala National Park','Yala National Park is the most visited and second largest national park in Sri Lanka. Actually it consists of five blocks, two of which are now open to the public; and also adjoining parks. The blocks have individual names also, like Ruhuna National Park for the (best known) block 1 and Kumana National Park or \'Yala East\' for the adjoining area. It is situated in the southeast region of the country, and lies in Southern Province and Uva Province. The park covers 979 square kilometres (378 sq mi) and is located about 300 kilometres (190 mi) from Colombo. Yala was designated as a wildlife sanctuary in 1900, and, along with Wilpattu it was one of the first two national parks in Sri Lanka, having been designated in 1938. The park is best known for its variety of wild animals. It is important for the conservation of Sri Lankan Elephants and aquatic birds.                            ','Southern and Uva Provinces, Sri Lanka                            ',1,'91-Yala.jpg','D:\\SoftwareDevelopment\\Project\\LocalEdition\\JSPServletEdition\\CeylonJourney\\web\\uploadImages\\91\\','admin','2013-02-22 00:24:28'),
 (92,'Pinnawala','The Pinnawela Elephant Orphanage is situated northwest of the town of Kegalle, halfway between the present commerciel capital Colombo and the ancient royal residence Kandy in the hills of central Sri Lanka. There are about 84 elephants under protection. The orphanage is visited daily by many Sri Lankan and foreign tourists.                            ','Karandupona Rambukkana Road, Pinnawela                            ',1,'92-Pinnawala.jpg','D:\\SoftwareDevelopment\\Project\\LocalEdition\\JSPServletEdition\\CeylonJourney\\web\\uploadImages\\92\\','admin','2013-02-22 01:28:02');
INSERT INTO `location` (`location_id`,`location_name`,`location_description`,`location_address`,`location_status`,`welcome_image_name`,`welcome_image_url`,`entered_by`,`entered_date`) VALUES 
 (93,'Royal Botanic Gardens','Royal Botanical Garden, Peradeniya is situated about 5.5 km to the west from the city of Kandy in the Central Province of Sri Lanka.and attracts 1.2 million visitors annually, It is renowned for its collection of a variety of orchids. It includes more than 300 varieties of orchids, spices, medicinal plants and palm trees. Attached to it is the National Herbarium. The total area of the botanical garden is 147 acres (0.59 km2), at 460 meters above sea level, and with a 200-day annual rainfall. It is managed by the Division of National Botanic Gardens of the Department of Agriculture.                            ','Peradeniya Rd, Peradeniya 20400                            ',1,'93-PeradeniyaBotanicalGarden.jpg','D:\\SoftwareDevelopment\\Project\\LocalEdition\\JSPServletEdition\\CeylonJourney\\web\\uploadImages\\93\\','admin','2013-02-22 00:30:20'),
 (94,'Sinharaja Forest','Sinharaja Forest is a world heritage site and major eco tourism destination, which can also be described as a Tropical Lowland Rainforest or Tropical Wet Evergreen Forest . Whatever its \'technical\' name, it is undoubtedly a rich treasure trove of nature with a great diversity of habitats and a vast repository of Sri Lanka\'s endemic species found no where else in the world. BIRD WATCHING in this ecosystem is particularly interesting because it is home to 95% of the endemic birds of Sri Lanka ! Named as a world heritage site in 1989, this lowland evergreen rain forest is steeped in deep legend and mystery. The word \'Sinharaja\' means, Lion (Sinha) King (Raja), and it is popular belief that the legendary origin of the Sinhala people is from the union between a princess and the lion king who once lived in the forest!                                       ','Southern Province                       ',1,'94-sinharaja.jpg','D:\\SoftwareDevelopment\\Project\\LocalEdition\\JSPServletEdition\\CeylonJourney\\web\\uploadImages\\94\\','admin','2013-02-22 09:22:50');
INSERT INTO `location` (`location_id`,`location_name`,`location_description`,`location_address`,`location_status`,`welcome_image_name`,`welcome_image_url`,`entered_by`,`entered_date`) VALUES 
 (95,'Sri Dalada Maligawa','Sri Dalada Maligawa or the Temple of the Sacred Tooth Relic is a Buddhist temple in the city of Kandy, Sri Lanka. It is located in the royal palace complex which houses the relic of the tooth of Buddha. Since ancient times, the relic has played an important role in local politics because it is believed that whoever holds the relic holds the governance of the country. Kandy was the last capital of the Sri Lankan kings and is a UNESCO world heritage site partly due to the temple.                            ','Sri Dalada Veediya, Kandy                            ',1,'95-SriDaladaMaligawa.jpg','D:\\SoftwareDevelopment\\Project\\LocalEdition\\JSPServletEdition\\CeylonJourney\\web\\uploadImages\\95\\','admin','2013-02-22 00:37:34'),
 (96,'Sri Pada','Sri Pada (also Adam\'s peak), is a 2,243 metres (7,359 ft) tall conical mountain located in central Sri Lanka. It is well known for the Sri Pada, i.e., \"sacred footprint\", a 1.8 metres (5 ft 11 in) rock formation near the summit, in Buddhist tradition it is held to be the footprint of the Buddha, in Hindu tradition that of Shiva and in Muslim and Christian tradition that of Adam, or that of St. Thomas.                            ','40 km northeast of the city of Ratnapura                            ',1,'96-SriPada.jpg','D:\\SoftwareDevelopment\\Project\\LocalEdition\\JSPServletEdition\\CeylonJourney\\web\\uploadImages\\96\\','admin','2013-02-22 00:44:49'),
 (97,'Horton Plains National Park','Horton Plains National Park is a protected area in the central highlands of Sri Lanka and is covered by montane grassland and cloud forest. This plateau at an altitude of 2,100â??2,300 metres (6,900â??7,500 ft) is rich in biodiversity and many species found here are endemic to the region. This region was designated a national park in 1988. It is also a popular tourist destination and is situated 32 kilometres (20 mi) from Nuwara Eliya and 8 kilometres (5.0 mi) from Ohiya                            ','Located on the southern plateau of the central highlands                       ',1,'97-horton.jpg','D:\\SoftwareDevelopment\\Project\\LocalEdition\\JSPServletEdition\\CeylonJourney\\web\\uploadImages\\97\\','admin','2013-02-22 00:46:31');
INSERT INTO `location` (`location_id`,`location_name`,`location_description`,`location_address`,`location_status`,`welcome_image_name`,`welcome_image_url`,`entered_by`,`entered_date`) VALUES 
 (98,'Beruwala Beach','Beruwela\'s broad and attractive beach called \"Golden Mile\" is a pristine beach of golden sand well sheltered by the palm groves that spread along the coastal stretch. The wide beach that basks in the glorious tropical sun from the direction of the ocean is well shaded with the lush greenery of the landside. Beruwela has excellent water sport facilities such as water scooter rides, wind surfing. para-sailing, water skiing. While the bay beach is safe for swimming in any month throughout the year, it also affords opportunity for Deep-sea Fishing and Wreck & Coral Reef Diving.                 ','Kalutara District, Western Province                            ',1,'98-beruwela.jpg','D:\\SoftwareDevelopment\\Project\\LocalEdition\\JSPServletEdition\\CeylonJourney\\web\\uploadImages\\98\\','admin','2013-02-22 00:52:15'),
 (99,'Ravan Ella Falls','Situated in the Dry zone off Welimada this beautiful waterfall is a continuous stream of life to the villagers. It provides much needed water for the cultivation of vegetables, their main income. The life-line role of this tiny, yet beautiful waterfall is seen when we first arrived the village. While the surrounding mountains were dirty and dry this particular mountain was greenish and cultivated. According to villages the water of this stream has to be protected from smugglers day and night and for that they have \"a shift duty\". \r\nIt is 40m (131ft) tall and in the Uduhawara village.                            ','Uduhawara village, Welimada.                            ',1,'99-RavanFalls.jpg','D:\\SoftwareDevelopment\\Project\\LocalEdition\\JSPServletEdition\\CeylonJourney\\web\\uploadImages\\99\\','admin','2013-02-22 00:55:09');
INSERT INTO `location` (`location_id`,`location_name`,`location_description`,`location_address`,`location_status`,`welcome_image_name`,`welcome_image_url`,`entered_by`,`entered_date`) VALUES 
 (100,'Dambulla Cave Temple','Dambulla cave temple also known as the Golden Temple of Dambulla is a World Heritage Site (1991) in Sri Lanka, situated in the central part of the country. It is the largest and best-preserved cave temple complex in Sri Lanka. The rock towers 160 m over the surrounding plains.There are more than 80 documented caves in the surrounding area. Major attractions are spread over 5 caves, which contain statues and paintings. These paintings and statues are related to Lord Buddha and his life. There total of 153 Buddha statues, 3 statues of Sri Lankan kings and 4 statues of gods and goddesses. The latter include two statues of Hindu gods, the god Vishnu and the god Ganesh. The murals cover an area of 2,100 square metres. Depictions on the walls of the caves include the temptation by the demon Mara, and Buddha\'s first sermon.                            ','This site is situated 148 km east of Colombo and 72 km north of Kandy.                        ',1,'100-DambullaTemple.jpg','D:\\SoftwareDevelopment\\Project\\LocalEdition\\JSPServletEdition\\CeylonJourney\\web\\uploadImages\\100\\','admin','2013-02-22 01:01:29'),
 (101,'Dehiwala Zoo','National Zoological Gardens of Sri Lanka (also called Colombo Zoo or Dehiwala Zoo) is a zoological garden in Dehiwala, Sri Lanka, founded in 1936. Its sprawling areas are host to a variety of animals and birds. The zoo exhibits animals but also places an emphasis on animal conservation and welfare, and education.\r\nVisions for the zoo include, \"To create one of the worldâ??s outstanding zoological institutions, that is a centre of the excellence for conservation, research and education\" and mission is \"Resourceful conservation of animals by means of a learning, achieved through the exhibition of species which were adopted with loving care\". The zoo has 3000 animals and 350 species as of 2005. The annual revenue is LKR 40 million.\r\nThe zoo exchanges its residents with other zoological gardens for breeding purposes.                            ','Kalubowlia, Colombo                            ',1,'101-DehiwalaZoo.jpg','D:\\SoftwareDevelopment\\Project\\LocalEdition\\JSPServletEdition\\CeylonJourney\\web\\uploadImages\\101\\','admin','2013-02-22 01:05:26');
/*!40000 ALTER TABLE `location` ENABLE KEYS */;


--
-- Table structure for table `ceylon_journey_db`.`location_city`
--

DROP TABLE IF EXISTS `location_city`;
CREATE TABLE `location_city` (
  `location_id` int(11) NOT NULL,
  `city_id` int(11) NOT NULL,
  PRIMARY KEY (`location_id`,`city_id`),
  KEY `FK_location_city_2` (`city_id`),
  CONSTRAINT `FK_location_city_1` FOREIGN KEY (`location_id`) REFERENCES `location` (`location_id`),
  CONSTRAINT `FK_location_city_2` FOREIGN KEY (`city_id`) REFERENCES `city_detail` (`city_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='InnoDB free: 10240 kB';

--
-- Dumping data for table `ceylon_journey_db`.`location_city`
--

/*!40000 ALTER TABLE `location_city` DISABLE KEYS */;
INSERT INTO `location_city` (`location_id`,`city_id`) VALUES 
 (84,29),
 (85,30),
 (86,31),
 (87,32),
 (95,34),
 (89,35),
 (101,37),
 (91,39),
 (92,40),
 (93,41),
 (94,42),
 (96,42),
 (97,43),
 (98,44),
 (99,45),
 (100,46);
/*!40000 ALTER TABLE `location_city` ENABLE KEYS */;


--
-- Table structure for table `ceylon_journey_db`.`location_comment`
--

DROP TABLE IF EXISTS `location_comment`;
CREATE TABLE `location_comment` (
  `comment_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `location_id` int(10) unsigned NOT NULL DEFAULT '0',
  `comment` varchar(100) NOT NULL DEFAULT '',
  `comment_status` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ceylon_journey_db`.`location_comment`
--

/*!40000 ALTER TABLE `location_comment` DISABLE KEYS */;
INSERT INTO `location_comment` (`comment_id`,`location_id`,`comment`,`comment_status`) VALUES 
 (15,96,'fgtfdgfdgdfgdg                   ',1),
 (17,95,'fgfdgfdg               ',1);
/*!40000 ALTER TABLE `location_comment` ENABLE KEYS */;


--
-- Table structure for table `ceylon_journey_db`.`location_contact`
--

DROP TABLE IF EXISTS `location_contact`;
CREATE TABLE `location_contact` (
  `location_id` int(11) NOT NULL,
  `contact_number` varchar(45) NOT NULL,
  `contact_type` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ceylon_journey_db`.`location_contact`
--

/*!40000 ALTER TABLE `location_contact` DISABLE KEYS */;
/*!40000 ALTER TABLE `location_contact` ENABLE KEYS */;


--
-- Table structure for table `ceylon_journey_db`.`location_image`
--

DROP TABLE IF EXISTS `location_image`;
CREATE TABLE `location_image` (
  `location_id` int(10) unsigned NOT NULL DEFAULT '0',
  `image_name` varchar(45) NOT NULL,
  `image_url` varchar(155) DEFAULT NULL,
  `image_type` varchar(45) DEFAULT NULL,
  `image_size` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`location_id`,`image_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ceylon_journey_db`.`location_image`
--

/*!40000 ALTER TABLE `location_image` DISABLE KEYS */;
INSERT INTO `location_image` (`location_id`,`image_name`,`image_url`,`image_type`,`image_size`) VALUES 
 (84,'84-LionsPawSigirya.jpg','D:\\SoftwareDevelopment\\Project\\LocalEdition\\JSPServletEdition\\CeylonJourney\\web\\uploadImages\\84\\','image/jpeg','14945');
/*!40000 ALTER TABLE `location_image` ENABLE KEYS */;


--
-- Table structure for table `ceylon_journey_db`.`location_keyword`
--

DROP TABLE IF EXISTS `location_keyword`;
CREATE TABLE `location_keyword` (
  `location_id` int(11) NOT NULL DEFAULT '0',
  `keyword_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`location_id`,`keyword_id`),
  KEY `FK_location_keyword_2` (`keyword_id`),
  CONSTRAINT `FK_location_keyword_1` FOREIGN KEY (`location_id`) REFERENCES `location` (`location_id`),
  CONSTRAINT `FK_location_keyword_2` FOREIGN KEY (`keyword_id`) REFERENCES `search_keyword` (`keyword_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ceylon_journey_db`.`location_keyword`
--

/*!40000 ALTER TABLE `location_keyword` DISABLE KEYS */;
/*!40000 ALTER TABLE `location_keyword` ENABLE KEYS */;


--
-- Table structure for table `ceylon_journey_db`.`location_search_type`
--

DROP TABLE IF EXISTS `location_search_type`;
CREATE TABLE `location_search_type` (
  `location_id` int(11) NOT NULL,
  `search_type_id` int(11) NOT NULL,
  `location_type_status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`location_id`,`search_type_id`),
  KEY `FK_location_search_type_2` (`search_type_id`),
  CONSTRAINT `FK_location_search_type_1` FOREIGN KEY (`location_id`) REFERENCES `location` (`location_id`),
  CONSTRAINT `FK_location_search_type_2` FOREIGN KEY (`search_type_id`) REFERENCES `search_type` (`search_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='InnoDB free: 10240 kB';

--
-- Dumping data for table `ceylon_journey_db`.`location_search_type`
--

/*!40000 ALTER TABLE `location_search_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `location_search_type` ENABLE KEYS */;


--
-- Table structure for table `ceylon_journey_db`.`location_summary`
--

DROP TABLE IF EXISTS `location_summary`;
CREATE TABLE `location_summary` (
  `location_id` int(11) NOT NULL,
  `location_introduced_by` varchar(45) DEFAULT NULL,
  `location_approved_by` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`location_id`),
  CONSTRAINT `FK_location_summary_1` FOREIGN KEY (`location_id`) REFERENCES `location` (`location_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ceylon_journey_db`.`location_summary`
--

/*!40000 ALTER TABLE `location_summary` DISABLE KEYS */;
/*!40000 ALTER TABLE `location_summary` ENABLE KEYS */;


--
-- Table structure for table `ceylon_journey_db`.`member`
--

DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `member_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email_address` varchar(45) DEFAULT NULL,
  `office_telephone` varchar(45) DEFAULT NULL,
  `mobile_nuermb` varchar(45) NOT NULL DEFAULT '',
  PRIMARY KEY (`member_id`),
  KEY `FK_member_1` (`username`),
  CONSTRAINT `FK_member_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ceylon_journey_db`.`member`
--

/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` (`member_id`,`username`,`first_name`,`last_name`,`email_address`,`office_telephone`,`mobile_nuermb`) VALUES 
 (14,'u','u','u','u','12345678','ufgf');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;


--
-- Table structure for table `ceylon_journey_db`.`search_keyword`
--

DROP TABLE IF EXISTS `search_keyword`;
CREATE TABLE `search_keyword` (
  `keyword_id` int(11) NOT NULL AUTO_INCREMENT,
  `keyword_name` varchar(155) DEFAULT NULL,
  `keyword_description` varchar(255) DEFAULT NULL,
  `keyword_type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`keyword_id`),
  UNIQUE KEY `keyword_UNIQUE` (`keyword_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ceylon_journey_db`.`search_keyword`
--

/*!40000 ALTER TABLE `search_keyword` DISABLE KEYS */;
/*!40000 ALTER TABLE `search_keyword` ENABLE KEYS */;


--
-- Table structure for table `ceylon_journey_db`.`search_type`
--

DROP TABLE IF EXISTS `search_type`;
CREATE TABLE `search_type` (
  `search_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(45) DEFAULT NULL,
  `type_status` tinyint(4) DEFAULT '1',
  `type_description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`search_type_id`),
  UNIQUE KEY `type_name_UNIQUE` (`type_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ceylon_journey_db`.`search_type`
--

/*!40000 ALTER TABLE `search_type` DISABLE KEYS */;
/*!40000 ALTER TABLE `search_type` ENABLE KEYS */;


--
-- Table structure for table `ceylon_journey_db`.`travel_guide`
--

DROP TABLE IF EXISTS `travel_guide`;
CREATE TABLE `travel_guide` (
  `travel_guide_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL DEFAULT '',
  `last_name` varchar(45) NOT NULL DEFAULT '',
  `email_address` varchar(45) NOT NULL DEFAULT '',
  `telephone_number` varchar(45) DEFAULT NULL,
  `mobile_number` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`travel_guide_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ceylon_journey_db`.`travel_guide`
--

/*!40000 ALTER TABLE `travel_guide` DISABLE KEYS */;
/*!40000 ALTER TABLE `travel_guide` ENABLE KEYS */;


--
-- Table structure for table `ceylon_journey_db`.`user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(45) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `user_status` tinyint(1) DEFAULT NULL,
  `user_type_id` int(11) NOT NULL,
  PRIMARY KEY (`username`),
  KEY `user_type_id_idx` (`user_type_id`),
  CONSTRAINT `user_type_id` FOREIGN KEY (`user_type_id`) REFERENCES `user_type` (`user_type_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ceylon_journey_db`.`user`
--

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`username`,`password`,`user_status`,`user_type_id`) VALUES 
 ('a','b',1,1),
 ('admin','admin',1,1),
 ('hotel','hotel',1,4),
 ('internal','internal',1,3),
 ('u','u',1,2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;


--
-- Table structure for table `ceylon_journey_db`.`user_type`
--

DROP TABLE IF EXISTS `user_type`;
CREATE TABLE `user_type` (
  `user_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(45) DEFAULT NULL,
  `login_type_status` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`user_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ceylon_journey_db`.`user_type`
--

/*!40000 ALTER TABLE `user_type` DISABLE KEYS */;
INSERT INTO `user_type` (`user_type_id`,`type_name`,`login_type_status`) VALUES 
 (1,'admin',1),
 (2,'member',1),
 (3,'internal_officer',1),
 (4,'hotel_officer',1);
/*!40000 ALTER TABLE `user_type` ENABLE KEYS */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
