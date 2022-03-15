-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 21, 2021 at 11:42 PM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 7.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_antrian`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_antrian`
--

CREATE TABLE `tbl_antrian` (
  `id` int(11) NOT NULL,
  `id_nasabah` varchar(20) NOT NULL,
  `no_antrian` varchar(6) NOT NULL,
  `no_rekening` varchar(16) NOT NULL,
  `nama_lengkap` varchar(255) NOT NULL,
  `no_handphone` varchar(15) NOT NULL,
  `id_user` varchar(20) NOT NULL,
  `status_antrian` varchar(100) NOT NULL,
  `tanggal` date NOT NULL DEFAULT current_timestamp(),
  `waktu` time NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_nasabah`
--

CREATE TABLE `tbl_nasabah` (
  `id_nasabah` varchar(20) NOT NULL,
  `no_rekening` varchar(16) NOT NULL,
  `nama_lengkap` varchar(255) NOT NULL,
  `no_handphone` varchar(16) NOT NULL,
  `regist_date` date NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_nasabah`
--

INSERT INTO `tbl_nasabah` (`id_nasabah`, `no_rekening`, `nama_lengkap`, `no_handphone`, `regist_date`) VALUES
('USR-1234567890123456', '1234567890123456', 'Febriana Safitri', '087878907866', '2021-05-03');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_user`
--

CREATE TABLE `tbl_user` (
  `id_user` varchar(20) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(255) NOT NULL,
  `level_akses` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tbl_user`
--

INSERT INTO `tbl_user` (`id_user`, `nama`, `username`, `password`, `level_akses`) VALUES
('Admin-01', 'Admin Satu', 'Admin 01', 'admin', 'Admin'),
('Admin-02', 'Admin Dua', 'Admin 02', 'admin', 'Admin'),
('USR-1234567890123456', 'Febriana Safitri', 'Febri', 'febri', 'User');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_antrian`
--
ALTER TABLE `tbl_antrian`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_antrian`
--
ALTER TABLE `tbl_antrian`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
