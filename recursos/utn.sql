-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 29-11-2023 a las 23:25:44
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `utn`
--
CREATE DATABASE IF NOT EXISTS `utn` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `utn`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `campeonato`
--

CREATE TABLE `campeonato` (
  `ID` int(11) NOT NULL,
  `NOMBRE` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `campeonato`
--

INSERT INTO `campeonato` (`ID`, `NOMBRE`) VALUES
(1, 'Liga de Estrellas'),
(2, 'Copa Brillante'),
(3, 'Torneo Dorado'),
(4, 'Copa de Campeones'),
(5, 'Liga Premier'),
(6, 'Torneo Supremo'),
(7, 'Copa de Plata'),
(8, 'Liga de Titanes'),
(9, 'Copa Relámpago'),
(10, 'Torneo Épico'),
(23, 'Copa de Oro'),
(24, 'Torneo Brillante'),
(25, 'Liga Magnífica'),
(50, 'Torneo Legendario');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `campeonato_equipo`
--

CREATE TABLE `campeonato_equipo` (
  `campeonato_id` int(11) NOT NULL,
  `equipo_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `equipo`
--

CREATE TABLE `equipo` (
  `ID` int(11) NOT NULL,
  `DIRECTORTECNICO` varchar(255) DEFAULT NULL,
  `NOMBRE` varchar(255) DEFAULT NULL,
  `PARTIDOSJUGADOS` int(11) DEFAULT NULL,
  `PUNTOS` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `equipo`
--

INSERT INTO `equipo` (`ID`, `DIRECTORTECNICO`, `NOMBRE`, `PARTIDOSJUGADOS`, `PUNTOS`) VALUES
(1, 'González', 'Equipo A', 10, 25),
(2, 'Rodríguez', 'Equipo B', 9, 20),
(3, 'López', 'Equipo C', 11, 28),
(4, 'Martínez', 'Equipo D', 12, 30),
(5, 'Hernández', 'Equipo E', 10, 24),
(6, 'Pérez', 'Equipo F', 11, 27),
(7, 'Gómez', 'Equipo G', 9, 21),
(8, 'Fernández', 'Equipo H', 12, 31),
(9, 'Sánchez', 'Equipo I', 10, 26),
(10, 'Romero', 'Equipo J', 11, 29),
(23, 'Martínez', 'Equipo X', 10, 26),
(24, 'Díaz', 'Equipo Y', 9, 22),
(25, 'Suárez', 'Equipo Z', 11, 28),
(50, 'González', 'Equipo AA', 12, 32);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `equipo_jugadores`
--

CREATE TABLE `equipo_jugadores` (
  `equipo_id` int(11) NOT NULL,
  `jugador_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `jugador`
--

CREATE TABLE `jugador` (
  `ID` int(11) NOT NULL,
  `APELLIDO` varchar(255) DEFAULT NULL,
  `DOCUMENTO` int(11) DEFAULT NULL,
  `ESTADO` tinyint(1) DEFAULT 0,
  `fecha_nacimiento` date NOT NULL,
  `NOMBRE` varchar(255) DEFAULT NULL,
  `POSICION` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `jugador`
--

INSERT INTO `jugador` (`ID`, `APELLIDO`, `DOCUMENTO`, `ESTADO`, `fecha_nacimiento`, `NOMBRE`, `POSICION`) VALUES
(1, 'Martínez', 12345678, 1, '1990-05-15', 'Juan', 'Delantero'),
(2, 'González', 23456789, 0, '1993-10-20', 'Luis', 'Defensa'),
(3, 'Hernández', 34567890, 1, '1988-07-12', 'María', 'Centrocampista'),
(4, 'Pérez', 45678901, 0, '1995-03-25', 'Carlos', 'Portero'),
(5, 'López', 56789012, 1, '1992-09-08', 'Laura', 'Delantero'),
(6, 'García', 67890123, 0, '1994-11-30', 'Ana', 'Defensa'),
(7, 'Rodríguez', 78901234, 1, '1991-02-18', 'Pedro', 'Centrocampista'),
(8, 'Fernández', 89012345, 0, '1996-08-05', 'Sofía', 'Portero'),
(9, 'Sánchez', 90123456, 1, '1989-12-10', 'Diego', 'Delantero'),
(10, 'Romero', 1234567, 0, '1997-06-22', 'Valentina', 'Defensa'),
(23, 'Torres', 34567012, 1, '1990-04-28', 'Julia', 'Centrocampista'),
(24, 'Díaz', 45670123, 0, '1993-11-17', 'Manuel', 'Portero'),
(25, 'Suárez', 56701234, 1, '1992-07-03', 'Lucía', 'Delantero'),
(50, 'Mendoza', 98765432, 0, '1998-01-09', 'Alejandra', 'Defensa');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `campeonato`
--
ALTER TABLE `campeonato`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `campeonato_equipo`
--
ALTER TABLE `campeonato_equipo`
  ADD PRIMARY KEY (`campeonato_id`,`equipo_id`),
  ADD KEY `FK_campeonato_equipo_equipo_id` (`equipo_id`);

--
-- Indices de la tabla `equipo`
--
ALTER TABLE `equipo`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `equipo_jugadores`
--
ALTER TABLE `equipo_jugadores`
  ADD PRIMARY KEY (`equipo_id`,`jugador_id`),
  ADD KEY `FK_equipo_jugadores_jugador_id` (`jugador_id`);

--
-- Indices de la tabla `jugador`
--
ALTER TABLE `jugador`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `DOCUMENTO` (`DOCUMENTO`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `campeonato`
--
ALTER TABLE `campeonato`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT de la tabla `equipo`
--
ALTER TABLE `equipo`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- AUTO_INCREMENT de la tabla `jugador`
--
ALTER TABLE `jugador`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `campeonato_equipo`
--
ALTER TABLE `campeonato_equipo`
  ADD CONSTRAINT `FK_campeonato_equipo_campeonato_id` FOREIGN KEY (`campeonato_id`) REFERENCES `campeonato` (`ID`),
  ADD CONSTRAINT `FK_campeonato_equipo_equipo_id` FOREIGN KEY (`equipo_id`) REFERENCES `equipo` (`ID`);

--
-- Filtros para la tabla `equipo_jugadores`
--
ALTER TABLE `equipo_jugadores`
  ADD CONSTRAINT `FK_equipo_jugadores_equipo_id` FOREIGN KEY (`equipo_id`) REFERENCES `equipo` (`ID`),
  ADD CONSTRAINT `FK_equipo_jugadores_jugador_id` FOREIGN KEY (`jugador_id`) REFERENCES `jugador` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
