-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 23, 2018 at 06:37 PM
-- Server version: 10.1.30-MariaDB
-- PHP Version: 7.2.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bikeshop`
--

-- --------------------------------------------------------

--
-- Table structure for table `bicikl`
--

CREATE TABLE `bicikl` (
  `bicikl_id` int(11) NOT NULL,
  `brend` varchar(255) DEFAULT NULL,
  `cena` double NOT NULL,
  `godina_proizvodnje` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `naziv` varchar(255) DEFAULT NULL,
  `specifikacija` longtext,
  `velicina` varchar(255) DEFAULT NULL,
  `kategorija_id` int(11) DEFAULT NULL,
  `slika` text NOT NULL,
  `naslovna_slika` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bicikl`
--

INSERT INTO `bicikl` (`bicikl_id`, `brend`, `cena`, `godina_proizvodnje`, `model`, `naziv`, `specifikacija`, `velicina`, `kategorija_id`, `slika`, `naslovna_slika`) VALUES
(2, 'Cube', 1000, '2018', '15', 'two', 'sdsfsg', 'm', 2, '1.jpg,2.jpg,3.jpg,4.jpg,5.jpg,6.jpg,7.jpg,8.jpg,9.jpg,10.jpg,11.jpg,12.jpg,13.jpg,14.jpg,15.jpg,16.jpg,17.jpg,18.jpg,19.jpg,20.jpg', '13.jpg'),
(3, 'Specialized', 1200, '2018', 'remedy', 'ground controll', NULL, 'l', 1, '', ''),
(11, 'Kona', 1500, '2018', 'k1', 'Kona deluxe', 'fzdfzsgzddgfdzg', 'm', 1, 'homeroad.jpg', 'homeroad.jpg'),
(10, 'Cannondale', 1000, '2018', '1', 'jekyll', 'fdzfbzdbf', 'l', 2, '1.jpg,2.jpg,3.jpg,4.jpg,5.jpg,6.jpg,7.jpg,8.jpg,9.jpg,10.jpg,11.jpg,12.jpg,13.jpg,14.jpg,15.jpg,16.jpg,17.jpg,18.jpg,19.jpg,20.jpg,21.jpg,22.jpg,23.jpg,24.jpg', '2.jpg'),
(12, 'Kona', 1200, '2018', '12', 'process', 'hgk,hjvlujkb', 'l', 2, 'konaprocess_15.jpg', 'homemtb1.jpg'),
(13, 'Cannondale', 1230, '2018', '6', 'six', 'hyjkvflyvg.lukb', 'l', 2, 'cannon.png', 'homer.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `custom_snimi`
--

CREATE TABLE `custom_snimi` (
  `custom_snimi_id` int(11) NOT NULL,
  `delovi_id` varchar(255) DEFAULT NULL,
  `ime` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `delovi`
--

CREATE TABLE `delovi` (
  `delovi_id` int(11) NOT NULL,
  `brend` varchar(255) DEFAULT NULL,
  `cena` double NOT NULL,
  `korisnik_id` int(11) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `naziv` varchar(255) DEFAULT NULL,
  `opis` longtext,
  `slika` varchar(255) DEFAULT NULL,
  `velicina` varchar(255) DEFAULT NULL,
  `grupa_delova_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `grupa_delova`
--

CREATE TABLE `grupa_delova` (
  `grupa_delova_id` int(11) NOT NULL,
  `naziv` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `kategorija`
--

CREATE TABLE `kategorija` (
  `kategorija_id` int(11) NOT NULL,
  `naziv` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kategorija`
--

INSERT INTO `kategorija` (`kategorija_id`, `naziv`) VALUES
(1, 'ROAD'),
(2, 'MOUNTAIN');

-- --------------------------------------------------------

--
-- Table structure for table `korisnik`
--

CREATE TABLE `korisnik` (
  `id` int(11) NOT NULL,
  `adresa` varchar(255) DEFAULT NULL,
  `datum` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `ime` varchar(255) DEFAULT NULL,
  `korisnicko_ime` varchar(255) DEFAULT NULL,
  `prezime` varchar(255) DEFAULT NULL,
  `sifra` varchar(255) DEFAULT NULL,
  `telefon` varchar(255) DEFAULT NULL,
  `permisije_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `korisnik`
--

INSERT INTO `korisnik` (`id`, `adresa`, `datum`, `email`, `ime`, `korisnicko_ime`, `prezime`, `sifra`, `telefon`, `permisije_id`) VALUES
(1, 'Pirot', '2018-07-03', 'marija@gmail.com', 'marija', 'marija', 'Todorovic', '123', '123456', 1),
(5, 'Slavonska 7v/6', '2018-07-17', 'soul6reaver@gmail.com', 'kuna444', 'Marko Dzunic', 'Dzunic', '123456', '+38166321878', 2),
(7, 'Slavonska 7v/6', '2017-12-31', 'marja@gmail.com', 'kuna444', 'marija123', 'Dzunic', '$2a$10$3F/eMRICoHb7Z8nY3pfFNuIOYKYLPF3gZWUNGKxTicBIqPUI8BFZi', '+38166321878', 1);

-- --------------------------------------------------------

--
-- Table structure for table `korpa`
--

CREATE TABLE `korpa` (
  `korpa_id` int(11) NOT NULL,
  `cena_total` double DEFAULT NULL,
  `delovi_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `korpa_proizvodi`
--

CREATE TABLE `korpa_proizvodi` (
  `korpa_proizvodi_id` int(11) NOT NULL,
  `bicikl_id` int(11) DEFAULT NULL,
  `korpa_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `narudzbenica`
--

CREATE TABLE `narudzbenica` (
  `narudzbenica_id` int(11) NOT NULL,
  `datum` datetime DEFAULT NULL,
  `korisnik_id` int(11) DEFAULT NULL,
  `bicikle` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `narudzbenica`
--

INSERT INTO `narudzbenica` (`narudzbenica_id`, `datum`, `korisnik_id`, `bicikle`) VALUES
(2, '2018-07-18 06:09:11', 1, '2,3,4,');

-- --------------------------------------------------------

--
-- Table structure for table `permisije`
--

CREATE TABLE `permisije` (
  `id` int(11) NOT NULL,
  `naziv` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `permisije`
--

INSERT INTO `permisije` (`id`, `naziv`) VALUES
(1, 'admin'),
(2, 'user');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bicikl`
--
ALTER TABLE `bicikl`
  ADD PRIMARY KEY (`bicikl_id`),
  ADD KEY `FKdy8q1l9b4cndy8qi0yjf67tta` (`kategorija_id`);

--
-- Indexes for table `custom_snimi`
--
ALTER TABLE `custom_snimi`
  ADD PRIMARY KEY (`custom_snimi_id`),
  ADD KEY `FKpt0wa6ecjd0381nu35scc8p9y` (`user_id`);

--
-- Indexes for table `delovi`
--
ALTER TABLE `delovi`
  ADD PRIMARY KEY (`delovi_id`),
  ADD KEY `FKoqhngphxl84mp8fdxlgeualvc` (`grupa_delova_id`);

--
-- Indexes for table `grupa_delova`
--
ALTER TABLE `grupa_delova`
  ADD PRIMARY KEY (`grupa_delova_id`);

--
-- Indexes for table `kategorija`
--
ALTER TABLE `kategorija`
  ADD PRIMARY KEY (`kategorija_id`);

--
-- Indexes for table `korisnik`
--
ALTER TABLE `korisnik`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKaygqpqqt91745ml5kd9ew9vh8` (`permisije_id`);

--
-- Indexes for table `korpa`
--
ALTER TABLE `korpa`
  ADD PRIMARY KEY (`korpa_id`);

--
-- Indexes for table `korpa_proizvodi`
--
ALTER TABLE `korpa_proizvodi`
  ADD PRIMARY KEY (`korpa_proizvodi_id`),
  ADD KEY `FKj2a6gfdv5ilcewkcteevi56bc` (`bicikl_id`),
  ADD KEY `FKrpyaw0j7lhdwst2sflfcywyj9` (`korpa_id`);

--
-- Indexes for table `narudzbenica`
--
ALTER TABLE `narudzbenica`
  ADD PRIMARY KEY (`narudzbenica_id`),
  ADD KEY `FKeu62ufyoryfe5cqgxmbychtfh` (`korisnik_id`);

--
-- Indexes for table `permisije`
--
ALTER TABLE `permisije`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bicikl`
--
ALTER TABLE `bicikl`
  MODIFY `bicikl_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `delovi`
--
ALTER TABLE `delovi`
  MODIFY `delovi_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `kategorija`
--
ALTER TABLE `kategorija`
  MODIFY `kategorija_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `korisnik`
--
ALTER TABLE `korisnik`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `narudzbenica`
--
ALTER TABLE `narudzbenica`
  MODIFY `narudzbenica_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `permisije`
--
ALTER TABLE `permisije`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
