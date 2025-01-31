CREATE DATABASE  IF NOT EXISTS `biblioteca` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `biblioteca`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: biblioteca
-- ------------------------------------------------------
-- Server version	8.0.36

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

--
-- Table structure for table `libro`
--

DROP TABLE IF EXISTS `libro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `libro` (
  `id_libro` int NOT NULL AUTO_INCREMENT,
  `titulo` varchar(255) DEFAULT NULL,
  `autor` varchar(255) DEFAULT NULL,
  `genero` varchar(255) DEFAULT NULL,
  `fecha_publicacion` date NOT NULL,
  `disponibilidad` bit(1) NOT NULL,
  `urlImagen` varchar(255) DEFAULT NULL,
  `fechaPublicacion` varchar(255) DEFAULT NULL,
  `id_usuario` bigint DEFAULT NULL,
  PRIMARY KEY (`id_libro`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libro`
--

LOCK TABLES `libro` WRITE;
/*!40000 ALTER TABLE `libro` DISABLE KEYS */;
INSERT INTO `libro` VALUES (1,'Cien años de soledad','Gabriel García Márquez','Novela','1967-06-05',_binary '','https://images.ctfassets.net/4cd45et68cgf/2Ct3i2g818EuxwPDUFOyl5/82043f6b4636ecb08331b3c34e1e659a/es_mx_cads_main_main_teaser_key_art_-_coronel_vertical_27x40_rgb_pre_1.jpg',NULL,NULL),(2,'1984','George Orwell','Distopía','1949-06-08',_binary '','https://cdn.prod.website-files.com/6034d7d1f3e0f52c50b2adee/6254291caac6d1e42e8986df_62023ceb41cd1c2807b2841a_9788418933011.jpeg',NULL,NULL),(3,'Don Quijote de la Mancha','Miguel de Cervantes','Clásico','1605-01-16',_binary '','https://cdn.slidesharecdn.com/ss_thumbnails/donquijotedelamancha-lecturafacilparaninos-160128204504-thumbnail.jpg?width=640&height=640&fit=bounds',NULL,NULL),(4,'El principito','Antoine de Saint-Exupéry','Cuento','1943-04-06',_binary '','https://m.media-amazon.com/images/I/714Hvb52n-L._AC_UF894,1000_QL80_.jpg',NULL,NULL),(5,'Orgullo y prejuicio','Jane Austen','Romántico','1813-01-28',_binary '','https://pics.filmaffinity.com/Orgullo_y_prejuicio-629327318-large.jpg',NULL,NULL),(6,'Crimen y castigo','Fiódor Dostoyevski','Filosófico','1866-01-01',_binary '','https://cdn.prod.website-files.com/6034d7d1f3e0f52c50b2adee/6254541d8ae4df16d4e69bc8_6034d7d1f3e0f54529b2b1a1_Crimen-y-castigo-fiodor-dostoyevski-editorial-alma.jpeg',NULL,NULL),(7,'La sombra del viento','Carlos Ruiz Zafón','Misterio','2001-06-06',_binary '','https://m.media-amazon.com/images/I/71BS32NFrsL.jpg',NULL,NULL),(8,'El nombre de la rosa','Umberto Eco','Histórico','1980-10-01',_binary '','https://m.media-amazon.com/images/I/71EaXqj6NwL._UF1000,1000_QL80_.jpg',NULL,NULL),(9,'Harry Potter y la piedra filosofal','J.K. Rowling','Fantasía','1997-06-26',_binary '','https://pictures.abebooks.com/isbn/9789500419574-es.jpg',NULL,NULL),(10,'Los juegos del hambre','Suzanne Collins','Ciencia Ficción','2008-09-14',_binary '','https://imagessl0.casadellibro.com/a/l/s5/70/9782266260770.webp',NULL,NULL);
/*!40000 ALTER TABLE `libro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prestamo`
--

DROP TABLE IF EXISTS `prestamo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prestamo` (
  `id_prestamo` bigint NOT NULL AUTO_INCREMENT,
  `id_libro_prestamo` int DEFAULT NULL,
  `id_usuario_prestamo` int DEFAULT NULL,
  `fecha_prestamo` date NOT NULL,
  `fecha_devolucion` date DEFAULT NULL,
  `multa` float DEFAULT NULL,
  PRIMARY KEY (`id_prestamo`),
  KEY `id_usuario_prestamo_idx` (`id_usuario_prestamo`),
  KEY `id_libro_prestamo_idx` (`id_libro_prestamo`),
  CONSTRAINT `id_libro_prestamo` FOREIGN KEY (`id_libro_prestamo`) REFERENCES `libro` (`id_libro`),
  CONSTRAINT `id_usuario_prestamo` FOREIGN KEY (`id_usuario_prestamo`) REFERENCES `usuarios` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestamo`
--

LOCK TABLES `prestamo` WRITE;
/*!40000 ALTER TABLE `prestamo` DISABLE KEYS */;
INSERT INTO `prestamo` VALUES (1,1,20,'2025-01-30','2025-01-30',0),(2,1,20,'2025-01-30','2025-01-30',0),(3,2,20,'2025-01-30','2025-01-30',0),(4,8,20,'2025-01-30','2025-01-30',0);
/*!40000 ALTER TABLE `prestamo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserva`
--

DROP TABLE IF EXISTS `reserva`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reserva` (
  `id_reserva` bigint NOT NULL AUTO_INCREMENT,
  `id_libro_reserva` int DEFAULT NULL,
  `id_usuario_reserva` int DEFAULT NULL,
  `fecha_reserva` date NOT NULL,
  `estado` bit(1) DEFAULT NULL,
  `idLibroReserva` int NOT NULL,
  `idUsuarioReserva` int NOT NULL,
  PRIMARY KEY (`id_reserva`),
  KEY `id_libro_reserva_idx` (`id_libro_reserva`),
  KEY `id_usuario_reserva_idx` (`id_usuario_reserva`),
  CONSTRAINT `id_libro_reserva` FOREIGN KEY (`id_libro_reserva`) REFERENCES `libro` (`id_libro`),
  CONSTRAINT `id_usuario_reserva` FOREIGN KEY (`id_usuario_reserva`) REFERENCES `usuarios` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserva`
--

LOCK TABLES `reserva` WRITE;
/*!40000 ALTER TABLE `reserva` DISABLE KEYS */;
/*!40000 ALTER TABLE `reserva` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id_usuario` int NOT NULL AUTO_INCREMENT,
  `dni` int NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `apellidos` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `telefono` int NOT NULL,
  `rol` enum('administrador','usuario') NOT NULL,
  `contrasena` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (11,12345678,'Juan','Pérez García','juan.perez@example.com',600123456,'administrador','1'),(12,87654321,'María','López Fernández','maria.lopez@example.com',600654321,'usuario','1'),(13,11223344,'Luis','Gómez Ruiz','luis.gomez@example.com',600987654,'usuario','1'),(14,22334455,'Ana','Martínez Sánchez','ana.martinez@example.com',600111222,'usuario','1'),(15,33445566,'Carlos','Hernández Díaz','carlos.hernandez@example.com',600333444,'usuario','1'),(16,44556677,'Laura','Jiménez Torres','laura.jimenez@example.com',600555666,'usuario','1'),(17,55667788,'Sofía','Vargas Romero','sofia.vargas@example.com',600575666,'usuario','1'),(18,66778899,'Pedro','Castro González','pedro.castro@example.com',600777888,'usuario','1'),(19,77889900,'Elena','Morales Cano','elena.morales@example.com',600999000,'usuario','1'),(20,88990011,'Jorge','Reyes Navarro','jorge.reyes@example.com',612555666,'usuario','1'),(21,234234,'hola','hola','hola',1111111111,'administrador','hola'),(22,92345678,'Ignacio','Prueba','i@gmail.com',944944944,'usuario','ignacio'),(23,98123456,'Pep','Josep','j@gmail.com',981234564,'usuario','contrasena');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-31 20:53:00
