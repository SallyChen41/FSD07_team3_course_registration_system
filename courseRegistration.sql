-- MySQL dump 10.13  Distrib 8.0.31, for macos12 (x86_64)
--
-- Host: crouseregistration.cs0dafexieny.us-east-2.rds.amazonaws.com    Database: courseRegistration
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '';

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `courses` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `available` int NOT NULL,
  `course_description` varchar(1000) DEFAULT NULL,
  `day_of_week` varchar(255) DEFAULT NULL,
  `end_date` date NOT NULL,
  `end_time` varchar(255) DEFAULT NULL,
  `start_date` date NOT NULL,
  `start_time` varchar(255) DEFAULT NULL,
  `student_limit` int NOT NULL,
  `title` varchar(300) DEFAULT NULL,
  `instructor` bigint NOT NULL,
  `semester` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcbo74en07goxby31l53781gn9` (`instructor`),
  KEY `FKaad841awxigbkcb53qbfgf132` (`semester`)
) ENGINE=MyISAM AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES (1,1,'This course is an introduction to computer concepts, tools for managing software development and documentation, and the foundation of web development. The course is organized into three sections. The first section introduces students to computer concepts and explains the major parts of a computer and how computers work. The second section provides basic information on file management and the applications for creating, organizing, and sharing design documents and business proposals. The third section covers fundamental HTML and CSS commands to produce a basic website.','M','2023-12-22','10:30','2023-09-04','09:00',100,'Foundations of Web Development',3,1),(2,0,'This course begins with an introduction to flow charts used to model simple algorithms and decision-making structures. These are then implemented using the Java programming language, where the student learns about program structure, variable types, passing parameters, control structures (if/else, switch, do-while, while, for) as well as the creation and use of subroutines (methods) and arrays. The student also gains a basic understanding of computer program structure, algorithm design, debugging strategies, unit testing, and source control.','M','2023-12-22','12:30','2023-09-04','11:00',100,'Programming I',4,1),(3,1,'This course focuses on introducing the student to basic principles of Object-Oriented Programming, such as classes, their design and implementation, constructors, try-catch clauses, and exceptions. It also ventures deeper into the standard Java library by exposing the student to Java Collection classes, for-each loop, text file input-output and network connectivity. Students will learn about the principles of encapsulation, inheritance, the concept of “static”, interfaces, abstract classes, and polymorphism. Emphasis is put on practical application of the skill to create a solid foundation for following programming courses.','M','2023-12-22','14:30','2023-09-04','13:00',100,'Programming II',5,1),(4,2,'This course focuses on how to design, create, update, and query a relational database. This course covers the key concepts of database management systems, including normalization, table creation, relationships and query construction. Students will practice writing SQL queries, including JOINs and sub-queries. The student will gain an understanding of views and how they are used, as well as the advantages in the use of stored procedures along with guidelines on creating them. The student will learn what triggers are and how they enforce data integrity. Finally, the student will learn how to save and restore a database.','T','2023-12-22','10:30','2023-09-04','09:00',100,'Database',6,1),(5,1,'This Web Design course leads the student through the entire Web site creation process, while developing and enhancing their HTML, CSS, and creative design skills along the way. This course introduces the concept of responsive web design which allows you to control a websites’ appearances on multiple screen sizes. Students will explore design strategies for laying out content, graphics, and navigation for multi-page websites. A wire-framing tool, such as Adobe XD, will be used to create mock-ups and user interface designs.','T','2023-12-22','12:30','2023-09-04','11:00',100,'Website Design',7,1),(6,0,'This course continues developing website design skills by utilizing JavaScript scripting language that enables Web Designers to develop highly interactive Web-based user interfaces. Students will learn how to use JavaScript for modifying the Document Object Model, event handling, controlling animation, forms verification, timers and counters. The course will then focus on additional libraries. The jQuery library will be used to add GUI widgets, apply effects, make server-side requests, and handle incoming data. A JavaScript framework with be introduced to assist in building user interfaces.','T','2023-12-22','14:30','2023-09-04','13:00',100,'User Interfaces',8,1),(7,1,'In this course the student will learn how to create and consume a variety of platform-independent web services, such as SOAP, and REST. The student will also learn to consume existing web services with public APIs (Application Programming Interface), such as Facebook, Twitter, GoogleAPI, and Yahoo Finances. Clients and servers for such services may be written using C#, Java, JavaScript, NodeJS as well as mobile platforms.','W','2023-12-22','10:30','2023-09-04','09:00',100,'Web Services',9,1),(8,0,'In the first part of this course, the student will learn about the syntax and control structures of the PHP language, as well as its web-context-specific features that make it a powerful tool for dynamic web pages and web app development. The student will use PHP language and external libraries to implement web pages using the MVC (Model-View-Controller) principles, with a micro-framework, database access library, and templating library. The student will also learn about best industry practices, such as PHP IDEs, visual debugging, and the use of Composer. The second part of this course focus on a full-stack project, to be done in the PHP language, individually or in teams of 2 to 3 students.','W','2023-12-22','12:30','2023-09-04','11:00',100,'Web Development I',10,1),(9,1,'This course introduces students to the requirements of application and data security. Students will configure a development and production environment, apply authentication and authorization measures, and deploy a secure Web application. A scripting language such as Python will be used for system administration tasks. Deployment options on AWS and Azure will be examined.','W','2023-12-22','14:30','2023-09-04','13:00',100,'Cloud Administration and Security',11,1),(10,0,'In this course, students will develop their capacity to interpret, select and adapt algorithms to solve problems in a given situation. They will begin by learning about the importance of data structures in order to manage large amounts of data efficiently. The student will then apply data structures to produce efficient algorithms. The course will include topics such as stacks, queues, lists, and trees. There will be an emphasis on cultivating an attitude and approach to problem solving.','TH','2023-12-22','10:30','2023-09-04','09:00',100,'Data Structures and Algorithms',12,1),(11,0,'In this course the student will learn how and when to use the advanced Object-Oriented Programming features of the Java language such as encapsulation, inheritance, the concept of “static”, interfaces, abstract classes, and polymorphism. This course will also expose the student to event-driven programming and writing unit and integration tests for an application written in Java’s Spring framework. Emphasis is put on integration of the\n36\ncontent learned in this course and the previous two programming courses. Students will focus on a project using Java Spring.','TH','2023-12-22','12:30','2023-09-04','11:00',100,'Programming III',3,1),(12,0,'This is a project-based course to provide students the opportunity to use their skills in web development to develop and deploy an application using a JavaScript framework, such as Angular or Vue. Working in teams, students will examine user requirements of a SME and design and develop an application. Emphasis will be on deployment and creating effective documentation of the project with the goal of producing maintainable code.','TH','2023-12-22','14:30','2023-09-04','13:00',100,'Web Development II',4,1),(13,0,'This course introduces students to the various ways to prepare for their career as a Full-Stack Developer and integrate into the labour market. Interview skills, CV writing, preparing an online profile using Linkedin, and researching job boards are essential skills for the job search. Additionally, students will prepare for an interview by researching a company and researching a technology, to develop their research skills and develop an aptitude for continuous learning. Students will research companies, contact companies, submit their cv, and arrange interviews for their internship during this course.','F','2023-12-22','10:30','2023-09-04','09:00',100,'Integration into the Labour Market',5,1),(14,0,'This course introduces software engineering using C# and covers all introductory aspects of .Net programming, including control structures, functions, arrays, and GUIs. Using C#, the students will define classes and apply the principles of encapsulation, inheritance and polymorphism. The emphasis throughout the course is on using sound software engineering principles and concepts. Students will be introduced to a set of fundamental skills in constructing system components and to a system design that will be useful in the construction of large systems as well as small components. Different GUIs will be explored such as Windows Presentation Foundation (WPF) in order to create rich, interactive client applications. Using the .NET Framework students will create and deploy fully functional, accessible, and secure Windows applications.','F','2023-12-22','12:30','2023-09-04','11:00',100,'Application Development I',6,1),(15,0,'This course will teach the fundamentals of Web application site implementation by using Microsoft ASP.NET and Microsoft C# .NET. Using the Microsoft Visual Studio® .NET environment and the Microsoft .NET platform, the goal is to create an ASP.NET Web application that delivers dynamic content to a Web site. This course introduces students to the ASP.NET Model-View-Controller (MVC) web development framework. MVC is a powerful, patterns-based way to build dynamic websites that enables a clean separation of concerns and that provides full control over markup for an effective Agile development. ASP.NET MVC includes many features that enable rapid, test-driven development. This course will cover many web development related concepts such as databases, servers, configuration files, LINQ, ADO.NET Entity Framework, page validation and hosting. These concepts will be applied to the development of ASP.NET MVC Web applications.','F','2023-12-22','14:30','2023-09-04','13:00',100,'Application Development II',7,1),(16,0,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.','M','2024-04-26','10:30','2024-01-08','09:00',100,'Infographic elements for mobile applications',8,2),(17,0,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.','M','2024-04-26','12:30','2024-01-08','11:00',100,'Client-side web programming',9,2),(18,0,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.','M','2024-04-26','14:30','2024-01-08','13:00',100,'Server-side web programming',10,2),(19,0,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.','T','2024-04-26','10:30','2024-01-08','09:00',100,'Native Applications with Android 1',11,2),(20,0,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.','T','2024-04-26','12:30','2024-01-08','11:00',100,'Native Applications with Android 2',12,2),(21,0,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.','T','2024-04-26','14:30','2024-01-08','13:00',100,'Development of Progressive Web Applications',3,2),(22,0,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.','W','2024-04-26','10:30','2024-01-08','09:00',100,'Mobile Application Development with iOS 1',4,2),(23,0,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.','W','2024-04-26','12:30','2024-01-08','11:00',100,'Mobile Application Development with iOS 2',5,2),(24,0,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.','W','2024-04-26','14:30','2024-01-08','13:00',100,'Related Technologies for Multiplatform Applications',6,2),(25,0,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.','TH','2024-04-26','10:30','2024-01-08','09:00',100,'Mobile Application design',7,2),(26,0,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.','TH','2024-04-26','12:30','2024-01-08','11:00',100,'Multi-platform Applications',8,2),(27,0,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.','TH','2024-04-26','14:30','2024-01-08','13:00',100,'Web Design I',9,2),(28,0,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.','F','2024-04-26','10:30','2024-01-08','09:00',100,'Web Design II',10,2),(29,0,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.','F','2024-04-26','12:30','2024-01-08','11:00',100,'Web Design III',11,2),(30,0,'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.','F','2024-04-26','14:30','2024-01-08','13:00',100,'Illustration',12,2);
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `semesters`
--

DROP TABLE IF EXISTS `semesters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `semesters` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `quarter` varchar(255) NOT NULL,
  `year` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `semesters`
--

LOCK TABLES `semesters` WRITE;
/*!40000 ALTER TABLE `semesters` DISABLE KEYS */;
INSERT INTO `semesters` VALUES (1,'Fall','2023'),(2,'Winter','2023'),(3,'Spring','2024'),(4,'Summer','2024');
/*!40000 ALTER TABLE `semesters` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_registrations`
--

DROP TABLE IF EXISTS `student_registrations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_registrations` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `status` varchar(255) DEFAULT NULL,
  `course_id` bigint NOT NULL,
  `student_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6f4ibcin3jj9epo224i0h8int` (`course_id`),
  KEY `FKtola8r2ffufk3o781bg1myttt` (`student_id`)
) ENGINE=MyISAM AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_registrations`
--

LOCK TABLES `student_registrations` WRITE;
/*!40000 ALTER TABLE `student_registrations` DISABLE KEYS */;
INSERT INTO `student_registrations` VALUES (1,'Dropped',1,13),(2,'Dropped',7,13),(3,'Registered',2,13),(4,'Registered',1,13),(5,'Dropped',3,13),(6,'Registered',20,14),(7,'Registered',4,14),(8,'Registered',30,14),(9,'Registered',27,14),(10,'Registered',29,15),(11,'Registered',24,15),(12,'Registered',23,15),(13,'Registered',6,15),(14,'Registered',27,16),(15,'Registered',9,16),(16,'Registered',3,16),(17,'Registered',28,16),(18,'Dropped',4,13),(19,'Registered',2,14),(20,'Dropped',5,13),(21,'Registered',1,17),(22,'Registered',2,17),(23,'Dropped',9,17),(24,'Registered',1,13),(25,'Registered',1,18),(26,'Dropped',4,18);
/*!40000 ALTER TABLE `student_registrations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(100) NOT NULL,
  `city` varchar(50) NOT NULL,
  `dob` date NOT NULL,
  `email` varchar(150) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `phone_num` varchar(10) NOT NULL,
  `postal_code` varchar(20) NOT NULL,
  `profession` varchar(50) DEFAULT NULL,
  `role` varchar(255) NOT NULL DEFAULT 'STUDENT',
  `username` varchar(25) NOT NULL,
  `reset_password_token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`),
  UNIQUE KEY `UK_f5fgr310aucvqex8djp780h1x` (`reset_password_token`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'6666 St','Montréal','1966-06-06','Siyi.Chen@johnabbottcollege.net','Siyi','Chen','$2a$10$mC2k2EUFtLs.z51L2Zvwxee0i9jgpPmhZqarKptIlcHaWYsf2ZcvC','1234567890','H8N 1T8','null','ADMIN','admin1',NULL),(2,'8888 Av','Montréal','1955-05-05','6213129@johnabbottcollege.net','ChihYin','Chen','$2a$10$.xN4OC2qFijohigDGGDMce2SQyaadG4Rb/MeSNi8mRnupTJGbhd.y','1234567890','H2Y 1T3','null','ADMIN','admin2',NULL),(3,'0101 St','Montréal','1963-01-01','leia@email.com','Leia','Andrews','$2a$10$fE5FJiPgOY0rYMSeJBuPJu6DkI8XdavB8cghlJCY84lfB5qpE1eRO','1234567890','H8N 1T8','null','INSTRUCTOR','prof1',NULL),(4,'2222 Av','Montréal','1973-02-02','conner@email.com','Conner','Chambers','$2a$10$Kc/aQWi89kec20KdUnV5yOs3xPxwBVNTaZ8B/2zdrjKQLlXin2AJm','1234567890','H2Y 1T3','null','INSTRUCTOR','prof2',NULL),(5,'3333 St','Montréal','1983-03-03','sadie@email.com','Sadie','Stewart','$2a$10$MfZqJGlgBPnG5yD7sqIHge7TW.1b0rR3AbJsr5zUU0gxcP1P.JlxG','1234567890','H2K 2A1 ','null','INSTRUCTOR','prof3',NULL),(6,'4444 Av','Montréal','1965-04-04','erik@email.com','Erik','Belanger','$2a$10$ypm5PrvErTm.Ip7cnF4Q9e52GFEX8pLV/EoZbtiAy3qdQlF1o0G5W','1234567890','H8N 1T8','null','INSTRUCTOR','prof4',NULL),(7,'5555 St','Montréal','1975-05-05','jose@email.com','Jose','Marquez','$2a$10$334ZOOMsmVSGf2VgKNtDvOfB9FAFOr51RN5In.4gyD/uhuZlHemvW','1234567890','H2Y 1T3','null','INSTRUCTOR','prof5',NULL),(8,'6666 Av','Montréal','1985-06-06','charles@email.com','Charles','James','$2a$10$umXxjPGlQ56sElESyu15I.6s4M3EPEYagyEKO4zxVrWsuRNhaGsFu','1234567890','H2K 2A1','null','INSTRUCTOR','prof6',NULL),(9,'7777 St','Montréal','1967-07-07','sara@email.com','Sara','Lowe','$2a$10$RyHW7VbW0jAVSbthzZlsNu5CzFqpeUQvJehKsF5kC/nqZpDzch4zO','1234567890','H8N 1T8','null','INSTRUCTOR','prof7',NULL),(10,'8888 Av','Montréal','1977-08-08','alan@email.com','Alan','Doyle','$2a$10$FBUUdOIu..0Ejw8k37FjSunDgzL8lQ.uk0r6Xi46Hx.RSMbOxJCM.','1234567890','H2Y 1T3','null','INSTRUCTOR','prof8',NULL),(11,'9999 St','Montréal','1987-09-09','ray@email.com','Ray','Hopkins','$2a$10$XSmIRsoVUb0JkX4kuQJqgOt1INoOPEvpqLzkdonDMj.SZ/m83GVVq','1234567890','H2K 2A1','null','INSTRUCTOR','prof9',NULL),(12,'1010 Av','Montréal','1960-10-10','henry@email.com','Henry','Kent','$2a$10$0DAvlNvlmj5YQ3pE6h8pdOsczogp05N12k7YBTUZzN8bbPMNp1vJS','1234567890','H2J 2M4','null','INSTRUCTOR','prof10',NULL),(13,'11 tree','Montreal','2001-11-11','hello@gmail.com','Hello','Kitty','$2a$10$Uwazd4YfuopCl66mqHE.bOcQ7pbsaYZ8CYd4Z1gF4Odm7ILxVeLka','1234567890','d2d2d2','null','STUDENT','hellokitty',NULL),(14,'Wizard St','Hogwarts','2000-01-01','harrypotter@email.com','Harry','Potter','$2a$10$sK2zEaicbCYXCOPK8vuyYunMnlenIUfMZ7UAZTHRMYA7BOtkDHEyK','1234567890','123 456','null','STUDENT','harrypotter',NULL),(15,'Puppy Av','Continental','1979-07-09','johnwick@email.com','John','Wick','$2a$10$ri34L.Bg.YaQAB5E.hbgj.zIXxcyGsdEibuu3OQzy19ynlNq9zRji','1234567890','K9P X8M','null','STUDENT','johnwick',NULL),(16,'Let It Go Av','Disney','2013-12-22','cold@email.com','Elsa','Frozen','$2a$10$J7rxCIttvtOX3M8RCUTZrOIM.nQ0JKE4eH.b1cNRhUPDh2Bm67ysi','1234567890','B9H D5Y','null','STUDENT','elsa',NULL),(17,'111 rock st','Montreal','2001-12-12','super@email.com','Super','Mario','$2a$10$0j0qZtPz./Zww6O1h69WuusE2NV3VAnKxwR782I1WR6e7YRrgfjsq','1234567890','f1f1f1','null','STUDENT','supermario',NULL),(18,'11 capcom st','Montreal','2001-11-11','hunter@mail.com','Monster','Hunter','$2a$10$lT4tTSY2hth4CLfCbslkfORWM7aKlonS9fmi2BXDd8iagdjRnmLkq','1234567890','c1c1c1','null','STUDENT','monsterhunter',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-12 10:56:33
