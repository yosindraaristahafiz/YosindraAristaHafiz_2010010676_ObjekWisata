-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 15, 2023 at 05:40 AM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 7.4.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rentalmobilyosi`
--

-- --------------------------------------------------------

--
-- Table structure for table `mobil`
--

CREATE TABLE `mobil` (
  `ID_Mobil` varchar(11) NOT NULL,
  `Jenis_Mobil` varchar(50) NOT NULL,
  `Plat_Nomor` varchar(10) NOT NULL,
  `Tahun_Pembuatan` varchar(30) NOT NULL,
  `Kapasitas` int(50) NOT NULL,
  `Harga_Sewa` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `mobil`
--

INSERT INTO `mobil` (`ID_Mobil`, `Jenis_Mobil`, `Plat_Nomor`, `Tahun_Pembuatan`, `Kapasitas`, `Harga_Sewa`) VALUES
('MBL-0001', 'JEEP', 'DA 123 AD', '2020', 4, 150000),
('MBL-0002', 'JIMMY', 'DA 654 AD', '2022', 4, 150000);

-- --------------------------------------------------------

--
-- Table structure for table `objek`
--

CREATE TABLE `objek` (
  `ID_Objek` varchar(11) NOT NULL,
  `Nama_Objek` varchar(50) NOT NULL,
  `Lokasi` varchar(200) NOT NULL,
  `Deskripsi` text NOT NULL,
  `Harga_Tiket` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `objek`
--

INSERT INTO `objek` (`ID_Objek`, `Nama_Objek`, `Lokasi`, `Deskripsi`, `Harga_Tiket`) VALUES
('OWS-0001', 'Pantai Kutai', 'Bali', 'Pantai kutai yang terletak di Bali', 200000),
('OWS-0002', 'Borneo Park', 'Banjarbaru', 'Borneo park yang terletak di Banjarbaru', 150000);

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `ID_Transaksi` varchar(11) NOT NULL,
  `ID_Mobil` varchar(11) NOT NULL,
  `ID_Objek` varchar(11) NOT NULL,
  `Nama_Pelanggan` varchar(100) NOT NULL,
  `Tanggal_Sewa` date NOT NULL,
  `Durasi_Sewa` int(100) NOT NULL,
  `Total_Harga` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`ID_Transaksi`, `ID_Mobil`, `ID_Objek`, `Nama_Pelanggan`, `Tanggal_Sewa`, `Durasi_Sewa`, `Total_Harga`) VALUES
('PSN-0001', 'MBL-0001', 'OWS-0001', 'Yoasindra', '2023-01-01', 2, 500000),
('PSN-0002', 'MBL-0002', 'OWS-0002', 'Indra', '2023-01-03', 2, 450000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `mobil`
--
ALTER TABLE `mobil`
  ADD PRIMARY KEY (`ID_Mobil`);

--
-- Indexes for table `objek`
--
ALTER TABLE `objek`
  ADD PRIMARY KEY (`ID_Objek`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`ID_Transaksi`),
  ADD KEY `ID_Mobil` (`ID_Mobil`,`ID_Objek`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
