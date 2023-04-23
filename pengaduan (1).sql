-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 23, 2023 at 07:58 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.0.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pengaduan`
--

-- --------------------------------------------------------

--
-- Table structure for table `kecamatan`
--

CREATE TABLE `kecamatan` (
  `id_kecamatan` int(11) NOT NULL,
  `kecamatan` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `kecamatan`
--

INSERT INTO `kecamatan` (`id_kecamatan`, `kecamatan`) VALUES
(27, 'Brati'),
(28, 'Gabus'),
(29, 'Geyer'),
(30, 'Godong'),
(31, 'Grobokan'),
(32, 'Gubuk'),
(33, 'Karangayung'),
(35, 'Klambu'),
(36, 'Kradenan'),
(37, 'Ngaringan'),
(38, 'Penawangan'),
(39, 'Pulokulon'),
(40, 'Purwodadi'),
(41, 'Tanggungharjo'),
(42, 'Tawangharjo'),
(43, 'Tegowanu'),
(44, 'Toroh'),
(45, 'Wirosari');

-- --------------------------------------------------------

--
-- Table structure for table `kelurahan`
--

CREATE TABLE `kelurahan` (
  `id_kelurahan` int(11) NOT NULL,
  `kelurahan` varchar(100) NOT NULL,
  `id_kecamatan` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `kelurahan`
--

INSERT INTO `kelurahan` (`id_kelurahan`, `kelurahan`, `id_kecamatan`) VALUES
(1953, 'Jangkungharjoo', 30),
(1954, 'Karangsari', 27),
(1955, 'Katekan', 27),
(1956, 'Kronggen', 27),
(1957, 'Lemahputih', 27),
(1958, 'Menduran', 27),
(1959, 'Tegalsumur', 27),
(1960, 'Temon', 27),
(1961, 'Tirem	', 27),
(1962, 'Banjarejo', 28),
(1963, 'Bendoharjo', 28),
(1964, 'Gabus', 28),
(1965, 'Kalipang', 28),
(1966, 'Karangrejo', 28),
(1967, 'Keyongan', 28),
(1968, 'Nglinduk', 28),
(1969, 'Pandanharum', 28),
(1970, 'Pelem', 28),
(1971, 'Sulursari', 28),
(1972, 'Suwatu', 28),
(1973, 'Tahunan', 28),
(1974, 'Tlogotirto', 28),
(1975, 'Tunggulrejo', 28),
(1976, 'Asemrudung', 29),
(1977, 'Bangsri', 29),
(1978, 'Geyer', 29),
(1979, 'Jambangan', 29),
(1980, 'Juworo', 29),
(1981, 'Kalangbancar', 29),
(1982, 'Karang', 29),
(1983, 'Anyar', 29),
(1984, 'Ledokdawan', 29),
(1985, 'Monggot', 29),
(1986, 'Ngrandu', 29),
(1987, 'Rambat', 29),
(1988, 'Sobo', 29),
(1989, 'Suru', 29),
(1990, 'Anggaswangi', 30),
(1991, 'Bringin', 30),
(1992, 'Bugel', 30),
(1993, 'Dorolegi', 30),
(1994, 'Godong', 30),
(1995, 'Guci', 30),
(1996, 'Gundi', 30),
(1997, 'Guyangan', 30),
(1998, 'Harjowinangun', 30),
(1999, 'Jatilor', 30),
(2000, 'Karanggeneng', 30),
(2001, 'Kemloko', 30),
(2002, 'Ketangirejo', 30),
(2003, 'Ketitang', 30),
(2004, 'Klampok', 30),
(2005, 'Kopek', 30),
(2006, 'Latak', 30),
(2007, 'Manggarmas', 30),
(2008, 'Manggarwetan', 30),
(2009, 'Pahesan', 30),
(2010, 'Rajek', 30),
(2011, 'Sambung', 30),
(2012, 'Sumberagung', 30),
(2013, 'Sumurgede', 30),
(2014, 'Tinanding', 30),
(2015, 'Ungu', 30),
(2016, 'Wanutunggal', 30),
(2017, 'Werdoyo', 30),
(2018, 'Getasrejo', 31),
(2019, 'Karangrejo', 31),
(2020, 'Lebak', 31),
(2021, 'Lebengjumuk', 31),
(2022, 'Ngabenrejo', 31),
(2023, 'Putatsari', 31),
(2024, 'Rejosari', 31),
(2025, 'Sedayu', 31),
(2026, 'SumberJatipohon', 31),
(2027, 'Tanggungharjo', 31),
(2028, 'Grobogan', 32),
(2029, 'Baturagung', 32),
(2030, 'Gelapan', 32),
(2031, 'Ginggangtani', 32),
(2032, 'Gubug', 32),
(2033, 'Jatipecaron', 32),
(2034, 'Jeketro', 32),
(2035, 'Kemiri', 32),
(2036, 'Kunjeng', 32),
(2037, 'Kuwaron', 32),
(2038, 'Mlilir', 32),
(2039, 'Ngroto', 32),
(2040, 'Papanrejo', 32),
(2041, 'Penadaran', 32),
(2042, 'Pranten', 32),
(2043, 'Ringinharjo', 32),
(2044, 'Ringinkidul', 32),
(2045, 'Rowosari', 32),
(2046, 'Saban', 32),
(2047, 'Tambakan', 32),
(2048, 'Tlogomulyo', 32),
(2049, 'Cekel', 33),
(2050, 'Dempel', 33),
(2051, 'Gunungtumpeng', 33),
(2052, 'Jetis', 33),
(2053, 'Karanganyar', 33),
(2054, 'Karangsono', 33),
(2055, 'Ketro', 33),
(2056, 'Mangin', 33),
(2057, 'Mojoagung', 33),
(2058, 'Nampu', 33),
(2059, 'Pangkalan', 33),
(2060, 'Parakan', 33),
(2061, 'Putatnganten', 33),
(2062, 'Rawoh', 33),
(2063, 'Sendangharjo', 33),
(2064, 'Sumberejosari', 33),
(2065, 'Telawah', 33),
(2066, 'Temurejo', 33),
(2067, 'Termas', 33),
(2068, 'Deras', 34),
(2069, 'Jumo', 34),
(2070, 'Kalimaro', 34),
(2071, 'Karanglangu', 34),
(2072, 'Kedungjati', 34),
(2073, 'Kentengsari', 34),
(2074, 'Klitikan', 34),
(2075, 'Ngombak', 34),
(2076, 'Padas', 34),
(2077, 'Panimbo', 34),
(2078, 'Prigi', 34),
(2079, 'Wates', 34),
(2080, 'Jenengan', 35),
(2081, 'Kandangrejo', 35),
(2082, 'Klambu', 35),
(2083, 'Menawan', 35),
(2084, 'Penganten', 35),
(2085, 'Selojari', 35),
(2086, 'Taruman', 35),
(2087, 'Terkesi', 35),
(2088, 'Wandankemiri', 35),
(2089, 'Bago', 36),
(2090, 'Banjardowo', 36),
(2091, 'Banjarsari', 36),
(2092, 'Crewek', 36),
(2093, 'Grabagan', 36),
(2094, 'Kalisari', 36),
(2095, 'Kradenan', 36),
(2096, 'Kuwu', 36),
(2097, 'Pakis', 36),
(2098, 'Rejosari', 36),
(2099, 'Sambongbangi', 36),
(2100, 'Sengonwetan', 36),
(2101, 'Simo', 36),
(2102, 'Tanjungsari', 36),
(2103, 'Bandungsari', 37),
(2104, 'Belor', 37),
(2105, 'Kalangdosari', 37),
(2106, 'Kalanglundo', 37),
(2107, 'Ngaraparap', 37),
(2108, 'Ngaringan', 37),
(2109, 'Pendem', 37),
(2110, 'Sarirejo', 37),
(2111, 'Sendangrejo', 37),
(2112, 'Sumberagung', 37),
(2113, 'Tanjungharjo', 37),
(2114, 'Truwolu', 37),
(2115, 'Bologarang', 38),
(2116, 'Curut', 38),
(2117, 'Jipang', 38),
(2118, 'Karangawader', 38),
(2119, 'Karangpaing', 38),
(2120, 'Kluwan', 38),
(2121, 'Kramat', 38),
(2122, 'Lajer', 38),
(2123, 'Leyangan', 38),
(2124, 'Ngeluk', 38),
(2125, 'Penawangan', 38),
(2126, 'Pengkol', 38),
(2127, 'Pulutan', 38),
(2128, 'Sedadi', 38),
(2129, 'Toko', 38),
(2130, 'Tunggu', 38),
(2131, 'WatuPawon', 38),
(2132, 'Wedoro', 38),
(2133, 'Winong', 38),
(2134, 'Wolo', 38),
(2135, 'Jambon', 39),
(2136, 'Jatiharjo', 39),
(2137, 'Jetaksari', 39),
(2138, 'Karangharjo', 39),
(2139, 'Mangunrejo', 39),
(2140, 'Mlowokarangtalun', 39),
(2141, 'Panunggalan', 39),
(2142, 'Pojok', 39),
(2143, 'Pulokulon', 39),
(2144, 'Randurejo', 39),
(2145, 'Sembungharjo', 39),
(2146, 'Sidorejo', 39),
(2147, 'Tuko', 39),
(2148, 'Candisari', 40),
(2149, 'Cingkrong', 40),
(2150, 'Genuksuran', 40),
(2151, 'Kandangan', 40),
(2152, 'Karanganyar', 40),
(2153, 'Kedungrejo', 40),
(2154, 'Nambuhan', 40),
(2155, 'Ngembak', 40),
(2156, 'Nglobar', 40),
(2157, 'Ngraji', 40),
(2158, 'Pulorejo', 40),
(2159, 'Putat', 40),
(2160, 'Warukaranganyar', 40),
(2161, 'Danyang', 40),
(2162, 'Kalongan', 40),
(2163, 'Kuripan', 40),
(2164, 'Purwodadi', 40),
(2165, 'Brabo', 41),
(2166, 'Kaliwenang', 41),
(2167, 'Kapung', 41),
(2168, 'Mrisi', 41),
(2169, 'Ngambakrejo', 41),
(2170, 'Padang', 41),
(2171, 'Ringinpitu', 41),
(2172, 'Sugihmanik', 41),
(2173, 'Tanggungharjo', 41),
(2174, 'Godan', 42),
(2175, 'Jono', 42),
(2176, 'Kemaduhbatur', 42),
(2177, 'Mayahan', 42),
(2178, 'Plosorejo', 42),
(2179, 'Pojok', 42),
(2180, 'Pulongrambe', 42),
(2181, 'Selo', 42),
(2182, 'Tarub', 42),
(2183, 'Tawangharjo', 42),
(2184, 'Cangkring', 43),
(2185, 'Curug', 43),
(2186, 'Gaji', 43),
(2187, 'Gebangan', 43),
(2188, 'Karangpasar', 43),
(2189, 'Kebonagung', 43),
(2190, 'Kedungwungu', 43),
(2191, 'Kejawan', 43),
(2192, 'Mangunsari', 43),
(2193, 'Medani', 43),
(2194, 'Pepe', 43),
(2195, 'Sukorejo', 43),
(2196, 'Tajemsari', 43),
(2197, 'Tanggirejo', 43),
(2198, 'TegowanuKulon', 43),
(2199, 'TegowanuWetan', 43),
(2200, 'Tlogorejo', 43),
(2201, 'Tunjungharjo', 43),
(2202, 'Bandungharjo', 44),
(2203, 'Boloh', 44),
(2204, 'Depok', 44),
(2205, 'Dimoro', 44),
(2206, 'Genengadal', 44),
(2207, 'Genengsari', 44),
(2208, 'Katong', 44),
(2209, 'Kenteng', 44),
(2210, 'Krangganharjo', 44),
(2211, 'Ngrandah', 44),
(2212, 'Pilangpayung', 44),
(2213, 'Plosoharjo', 44),
(2214, 'Sindurejo', 44),
(2215, 'Sugihan', 44),
(2216, 'Tambirejo', 44),
(2217, 'Tunggak', 44),
(2218, 'Dapurno', 45),
(2219, 'Dokoro', 45),
(2220, 'Gedangan', 45),
(2221, 'Kalirejo', 45),
(2222, 'Karangasem', 45),
(2223, 'Kropak', 45),
(2224, 'Mojorebo', 45),
(2225, 'Sambirejo', 45),
(2226, 'Tambahrejo', 45),
(2227, 'Tambakselo', 45),
(2228, 'Tanjungrejo', 45),
(2229, 'Tegalrejo', 45),
(2230, 'Kunden', 45),
(2231, 'Wirosari', 45);

-- --------------------------------------------------------

--
-- Table structure for table `log`
--

CREATE TABLE `log` (
  `id_log` int(11) NOT NULL,
  `isi_log` text NOT NULL,
  `tgl_log` datetime NOT NULL,
  `id_user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `log`
--

INSERT INTO `log` (`id_log`, `isi_log`, `tgl_log`, `id_user`) VALUES
(16, 'Tanggapan sedang dalam proses berhasil ditambahkan', '2022-10-10 14:11:44', 1),
(17, 'Tanggapan otw berhasil ditambahkan', '2022-10-10 14:12:30', 1),
(18, 'Tanggapan selesai boss berhasil ditambahkan', '2022-10-10 14:13:17', 1),
(19, 'Tanggapan dah lah berhasil ditambahkan', '2022-10-10 14:14:01', 1),
(20, 'Masyarakat azzaura56 berhasil dihapus', '2022-10-10 17:09:31', 1),
(21, 'Masyarakat ableza24 berhasil dihapus', '2022-10-10 17:09:34', 1),
(22, 'Masyarakat andre123 berhasil dihapus', '2022-10-10 17:09:38', 1),
(23, 'Masyarakat annisa98 berhasil dihapus', '2022-10-10 17:09:42', 1),
(24, 'Masyarakat irgi5 berhasil dihapus', '2022-10-10 17:09:47', 1),
(25, 'Pengaduan jalan bocor berhasil dihapus', '2022-10-10 17:12:01', 1),
(26, 'Kecamatan Ciputat berhasil dihapus', '2022-10-10 17:12:10', 1),
(27, 'Kecamatan Ciputat Timur berhasil dihapus', '2022-10-10 17:12:14', 1),
(28, 'Kecamatan Pamulang berhasil dihapus', '2022-10-10 17:12:18', 1),
(29, 'Kecamatan Pondok Aren berhasil dihapus', '2022-10-10 17:12:22', 1),
(30, 'Kecamatan Serpong berhasil dihapus', '2022-10-10 17:12:25', 1),
(31, 'Kecamatan Serpong Utara berhasil dihapus', '2022-10-10 17:12:31', 1),
(32, 'Kecamatan Setu berhasil dihapus', '2022-10-10 17:12:34', 1),
(33, 'Kecamatan Banyumanik berhasil ditambahkan', '2022-10-10 17:12:51', 1),
(34, 'Kecamatan Candi Sari berhasil ditambahkan', '2022-10-10 17:13:03', 1),
(35, 'Kecamatan Candisari berhasil diubah', '2022-10-10 17:13:15', 1),
(36, 'Kecamatan Gajah Mungkur berhasil ditambahkan', '2022-10-10 17:13:26', 1),
(37, 'Kecamatan Gayamsari berhasil ditambahkan', '2022-10-10 17:13:52', 1),
(38, 'Kecamatan Genuk berhasil ditambahkan', '2022-10-10 17:14:02', 1),
(39, 'Kecamatan Gunug Pati berhasil ditambahkan', '2022-10-10 17:14:11', 1),
(40, 'Kecamatan Gunung Pati berhasil diubah', '2022-10-10 17:14:22', 1),
(41, 'Kecamatan Mijen berhasil ditambahkan', '2022-10-10 17:14:36', 1),
(42, 'Kecamatan Ngaliyan berhasil ditambahkan', '2022-10-10 17:14:45', 1),
(43, 'Kecamatan Pedurungan berhasil ditambahkan', '2022-10-10 17:15:13', 1),
(44, 'Kecamatan Semarang Barat berhasil ditambahkan', '2022-10-10 17:15:25', 1),
(45, 'Kecamatan Semarang Selatan berhasil ditambahkan', '2022-10-10 17:15:47', 1),
(46, 'Kecamatan Semarang Selatan berhasil ditambahkan', '2022-10-10 17:15:47', 1),
(47, 'Kecamatan Semarang Tengah berhasil ditambahkan', '2022-10-10 17:15:59', 1),
(48, 'Kecamatan Semarang Timur berhasil ditambahkan', '2022-10-10 17:16:17', 1),
(49, 'Kecamatan Semarang Utara berhasil ditambahkan', '2022-10-10 17:16:26', 1),
(50, 'Kecamatan Semarang Selatan berhasil dihapus', '2022-10-10 17:17:02', 1),
(51, 'Kecamatan Tembalang Tugu berhasil ditambahkan', '2022-10-10 17:17:21', 1),
(52, 'Kecamatan Tugu berhasil ditambahkan', '2022-10-10 17:17:43', 1),
(53, 'Kecamatan Tembalang berhasil diubah', '2022-10-10 17:17:54', 1),
(54, 'Kelurahan Ngesrep berhasil ditambahkan', '2022-10-10 17:18:34', 1),
(55, 'Kelurahan Tinjomoyo berhasil ditambahkan', '2022-10-10 17:20:21', 1),
(56, 'Kelurahan Srondol Kulon berhasil ditambahkan', '2022-10-10 17:20:37', 1),
(57, 'Kelurahan Srondol Wetan berhasil ditambahkan', '2022-10-10 17:20:49', 1),
(58, 'Kelurahan Banyumanik\' berhasil ditambahkan', '2022-10-10 17:21:54', 1),
(59, 'Kelurahan Banyumanik berhasil diubah', '2022-10-10 17:22:01', 1),
(60, 'Kelurahan Pudak Payung berhasil ditambahkan', '2022-10-10 17:22:11', 1),
(61, 'Kelurahan Pudakpayung berhasil diubah', '2022-10-10 17:22:24', 1),
(62, 'Kelurahan Gedawang berhasil ditambahkan', '2022-10-10 17:22:37', 1),
(63, 'Kelurahan Jabungan berhasil ditambahkan', '2022-10-10 17:22:51', 1),
(64, 'Kelurahan Padangsari berhasil ditambahkan', '2022-10-10 17:23:07', 1),
(65, 'Kelurahan Padalangan berhasil ditambahkan', '2022-10-10 17:23:15', 1),
(66, 'Kelurahan Sumurboto berhasil ditambahkan', '2022-10-10 17:23:29', 1),
(67, 'Kelurahan Wonotingal berhasil ditambahkan', '2022-10-10 17:24:06', 1),
(68, 'Kelurahan Kaliwiru berhasil ditambahkan', '2022-10-10 17:24:24', 1),
(69, 'Kelurahan Jatingaleh berhasil ditambahkan', '2022-10-10 17:24:49', 1),
(70, 'Kelurahan Karanganyar Gunung berhasil ditambahkan', '2022-10-10 17:25:06', 1),
(71, 'Kelurahan Njomblang berhasil ditambahkan', '2022-10-10 17:25:21', 1),
(72, 'Kelurahan Candi berhasil ditambahkan', '2022-10-10 17:25:31', 1),
(73, 'Kelurahan Candi berhasil diubah', '2022-10-10 17:25:40', 1),
(74, 'Kelurahan Tegalsari berhasil ditambahkan', '2022-10-10 17:25:58', 1),
(75, 'Kelurahan Jomblang berhasil diubah', '2022-10-10 17:26:58', 1),
(76, 'Tanggapan baik akan segera kami proses berhasil ditambahkan', '2022-10-10 19:17:35', 1),
(77, 'Tanggapan baik akan segera kami proses berhasil diubah', '2022-10-10 19:18:45', 1),
(78, 'Tanggapan oke berhasil ditambahkan', '2022-10-10 19:19:54', 1),
(79, 'Tanggapan proses berhasil ditambahkan', '2022-10-10 19:22:09', 1),
(80, 'Kecamatan Brati berhasil ditambahkan', '2022-10-14 02:12:25', 1),
(81, 'Kecamatan Gabus berhasil ditambahkan', '2022-10-14 02:12:39', 1),
(82, 'Kecamatan Geyer berhasil ditambahkan', '2022-10-14 02:12:48', 1),
(83, 'Kecamatan Godong berhasil ditambahkan', '2022-10-14 02:13:01', 1),
(84, 'Kecamatan Grobokan berhasil ditambahkan', '2022-10-14 02:13:09', 1),
(85, 'Kecamatan Gubuk berhasil ditambahkan', '2022-10-14 02:13:24', 1),
(86, 'Kecamatan Karangayung berhasil ditambahkan', '2022-10-14 02:13:39', 1),
(87, 'Kecamatan Kedungjati berhasil ditambahkan', '2022-10-14 02:13:52', 1),
(88, 'Kecamatan Klambu berhasil ditambahkan', '2022-10-14 02:14:00', 1),
(89, 'Kecamatan Kradenan berhasil ditambahkan', '2022-10-14 02:14:18', 1),
(90, 'Kecamatan Ngaringan berhasil ditambahkan', '2022-10-14 02:14:29', 1),
(91, 'Kecamatan Penawangan berhasil ditambahkan', '2022-10-14 02:14:43', 1),
(92, 'Kecamatan Pulokulon berhasil ditambahkan', '2022-10-14 02:14:54', 1),
(93, 'Kecamatan Purwodadi berhasil ditambahkan', '2022-10-14 02:15:06', 1),
(94, 'Kecamatan Tanggungharjo berhasil ditambahkan', '2022-10-14 02:15:17', 1),
(95, 'Kecamatan Tawangharjo berhasil ditambahkan', '2022-10-14 02:15:31', 1),
(96, 'Kecamatan Tegowanu berhasil ditambahkan', '2022-10-14 02:15:41', 1),
(97, 'Kecamatan Toroh berhasil ditambahkan', '2022-10-14 02:15:52', 1),
(98, 'Kecamatan Wirosari berhasil ditambahkan', '2022-10-14 02:16:01', 1),
(99, 'Kelurahan Jangkungharjo berhasil ditambahkan', '2022-10-14 02:18:06', 1),
(100, 'Kelurahan Karangsari berhasil ditambahkan', '2022-10-14 02:19:12', 1),
(101, 'Tanggapan oke segera kami proses berhasil ditambahkan', '2022-10-14 08:43:24', 1),
(102, 'Tanggapan sedang dalam pengerjaan berhasil ditambahkan', '2022-10-14 08:43:53', 1),
(103, 'Tanggapan sedang dalam pengerjaan berhasil ditambahkan', '2022-10-14 08:44:03', 1),
(104, 'Tanggapan selesai bos berhasil ditambahkan', '2022-10-14 08:45:17', 1),
(105, 'Masyarakat yu berhasil diubah', '2022-12-28 22:20:38', 1),
(106, 'Pengguna petugas dengan jabatan operator berhasil ditambahkan', '2023-01-09 23:05:43', 1),
(107, 'Pengguna andri123 berhasil dihapus', '2023-01-09 23:08:27', 1),
(108, 'Pengaduan  berhasil dihapus', '2023-01-10 00:08:46', 1),
(109, 'Pengaduan operator berhasil dihapus', '2023-01-10 00:13:42', 1),
(110, 'Tanggapan oki berhasil ditambahkan', '2023-01-10 00:15:31', 1),
(111, 'Tanggapan okiiuuu berhasil ditambahkan', '2023-01-10 00:15:55', 1),
(112, 'Pengaduan  berhasil dihapus', '2023-01-10 00:22:30', 1),
(113, 'Pengaduan Retak berhasil dihapus', '2023-01-10 02:23:16', 1),
(114, 'Pengaduan Biasa berhasil dihapus', '2023-01-10 02:23:20', 1),
(115, 'Pengaduan Retak berhasil dihapus', '2023-01-10 02:23:24', 1),
(116, 'Pengaduan Retak berhasil dihapus', '2023-01-10 02:23:28', 1),
(117, 'Pengaduan Berlubang berhasil dihapus', '2023-01-10 02:23:31', 1),
(118, 'Tanggapan bisa diatur berhasil ditambahkan', '2023-01-10 02:29:06', 3),
(119, 'Tanggapan bisa diatur berhasil diubah', '2023-01-10 02:29:32', 3),
(120, 'Tanggapan dada berhasil ditambahkan', '2023-03-28 18:31:16', 1),
(121, 'Tanggapan sad berhasil ditambahkan', '2023-03-28 18:31:33', 1),
(122, 'Tanggapan ada berhasil ditambahkan', '2023-03-29 23:42:43', 1),
(123, 'Tanggapan sdad berhasil ditambahkan', '2023-03-31 17:33:48', 1),
(124, 'Tanggapan d berhasil ditambahkan', '2023-03-31 17:34:41', 1),
(125, 'Tanggapan sdsad berhasil ditambahkan', '2023-03-31 17:34:52', 1),
(126, 'Tanggapan lklk berhasil ditambahkan', '2023-03-31 17:35:58', 1),
(127, 'Tanggapan sedang dalam proses berhasil ditambahkan', '2023-04-01 01:35:15', 1),
(128, 'Tanggapan baik berhasil ditambahkan', '2023-04-01 01:36:07', 1),
(129, 'Tanggapan sad dengan status Valid berhasil dihapus', '2023-04-01 01:51:23', 1),
(130, 'Masyarakat kevinxxx berhasil dihapus', '2023-04-02 13:40:17', 1),
(131, 'Pengguna kevin dengan jabatan operator berhasil ditambahkan', '2023-04-03 08:47:21', 1),
(132, 'Tanggapan czxcxczx berhasil ditambahkan', '2023-04-03 08:58:41', 4),
(133, 'Tanggapan lklk dengan status Selesai berhasil dihapus', '2023-04-04 16:46:17', 1),
(134, 'Tanggapan sdsad dengan status Pengerjaan berhasil dihapus', '2023-04-04 16:49:39', 1),
(135, 'Tanggapan d dengan status Valid berhasil dihapus', '2023-04-04 16:49:43', 1),
(136, 'Tanggapan sdad dengan status Proses berhasil dihapus', '2023-04-04 16:49:46', 1),
(137, 'Tanggapan x berhasil ditambahkan', '2023-04-04 21:43:55', 1),
(138, 'Tanggapan dadasd berhasil ditambahkan', '2023-04-04 22:42:22', 1),
(139, 'Tanggapan dadasd dengan status Tidak Valid berhasil dihapus', '2023-04-04 23:25:20', 1),
(140, 'Tanggapan one selessad dengan status Selesai berhasil dihapus', '2023-04-05 02:05:37', 1),
(141, 'Tanggapan sdadsd dengan status Pengerjaan berhasil dihapus', '2023-04-05 02:05:55', 1),
(142, 'Tanggapan one saddsaas dengan status Valid berhasil dihapus', '2023-04-05 02:06:05', 1),
(143, 'Tanggapan TIDAK BENAR INI dengan status Tidak Valid berhasil dihapus', '2023-04-05 02:06:37', 1),
(144, 'Tanggapan x dengan status Proses berhasil dihapus', '2023-04-05 02:06:42', 1),
(145, 'Tanggapan bjbj berhasil ditambahkan', '2023-04-05 02:06:58', 1);

-- --------------------------------------------------------

--
-- Table structure for table `masyarakat`
--

CREATE TABLE `masyarakat` (
  `id_masyarakat` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `no_telepon` varchar(20) NOT NULL,
  `alamat` text NOT NULL,
  `NIK` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `masyarakat`
--

INSERT INTO `masyarakat` (`id_masyarakat`, `nama`, `username`, `password`, `no_telepon`, `alamat`, `NIK`) VALUES
(8, 'Ferdi', 'ferdi', '$2y$10$/.mB52G8/f4.sOdIt6snruSz6zq0XynGVOzv.i77b1AkMKGaV0DKa', '08542658745158', 'Ngaliyan', ''),
(13, 'Liaa', 'liaa', '$2y$10$32A.Val3TGZopyrGC4f3oOH.i0zwG5tuarCvwopG64zC12rhyfkhu', '0831651531651', 'adadfsgdnh', ''),
(16, 'Lo', 'lo', '$2y$10$7GruSx45qZ/2OjJjxqkELuf7HR9VX6k4qNFTWmc71FmqO5saK982y', '68465148', 'iohij', 'kjgh'),
(17, 'Aku', 'aku', '$2y$10$bA7o6YHypeBNSpTc9FufT.wJc6tM1Ez6LKo6qnVxeVJR/0fD77gCe', '015648513', 'kmjhbnfiojgrmk', '654897656489765'),
(22, 'nafisa khally', 'nafisa', '$2y$10$tVJ4ln4kq4cXP4j0gwAXge3eqlgGX3jBggxYYTYBp/DqBP67n71WC', '03819383', 'bulustalan', '121212'),
(26, 'nafisa khally', 'nafisa2478', '$2y$10$43S6prvCz5SjWg5rTo1w/ODbNzNfCUZhzUtCjaemevYYSmz.ckCgu', '03819383', 'bulustalan', '121212'),
(32, 'qkevinfsd', 'bsadasd', '$2y$10$9j0Z34D9rrd6.3DB86cx0un1f5DWjuvDAs4.mEehCZeR/m1kaCVX2', '4435345', 'ddadadad', '3433434'),
(33, 'kdakda', 'kadadksd', '$2y$10$REd8RIkWk5Imw6gP46njHOVDEudjSTm5QPTe1JX1zzXoSXcfsHtsS', '2323323', 'dsadasdasdd', '232323'),
(34, 'nafisaaallk', 'elak2d', '$2y$10$fASGdCy.NJeAzJnsNjQrZergFpIGBc65FtpyKUtlO/sTb9pm3oc8.', '022222', 'bulustalan no 2', '4342424'),
(35, 'Kevin toding', 'tasya', '$2y$10$DtaEUk8QM/Hx4f3Rj9aaxO3RHJMzQ7xDGRsiATOJ2P3mNKGvMpZam', '08232323', 'jl pahlawan', '898989'),
(36, 'kerenssadsssda', 'oiyadasds', '$2y$10$tNSBqNc.10BfPvVkO5qCe.Dq7xcOaqiSgT7RdFRoCxDFgufZXMjpu', '121212', 'pemnaikan', '2323232323'),
(37, 'gilto', 'giltosdds', '$2y$10$SQDhUPUXyElwGeeKmunSkeXf/kvWUYrVGdYilk6UcmBWMtb9EaQf2', '09203912', 'daddadaddd ', '8392382932');

-- --------------------------------------------------------

--
-- Table structure for table `pengaduan`
--

CREATE TABLE `pengaduan` (
  `id_pengaduan` int(11) NOT NULL,
  `isi_laporan` text NOT NULL,
  `jenis` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `tgl_pengaduan` datetime NOT NULL,
  `foto` varchar(500) DEFAULT 'default.png',
  `foto1` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `foto2` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `status_pengaduan` enum('belum_ditanggapi','proses','valid','pengerjaan','selesai','tidak_valid') NOT NULL DEFAULT 'belum_ditanggapi',
  `id_masyarakat` varchar(11) NOT NULL,
  `id_kelurahan` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pengaduan`
--

INSERT INTO `pengaduan` (`id_pengaduan`, `isi_laporan`, `jenis`, `tgl_pengaduan`, `foto`, `foto1`, `foto2`, `status_pengaduan`, `id_masyarakat`, `id_kelurahan`) VALUES
(20, 'rusak', 'Berlubang', '2023-01-10 20:05:46', '11.jpg', '21.jpg', '31.jpg', 'belum_ditanggapi', '19', '2135'),
(21, 'jalan rusak', 'Biasa', '2023-03-09 22:55:05', 'WhatsApp_Image_2023-03-06_at_18_53_00.jpeg', NULL, NULL, 'belum_ditanggapi', '20', '2089'),
(43, 'Jlaan berLUBANG TAK KUNJUNG DIPERBAIKI', 'Retak lelah dan deformasi pada semua lapisan perkerasan aspal', '2023-04-02 23:36:19', '088089800_1569394916-20190924-Gempa-Besar-Guncang-Pakistan-AP-710.jpg', 'images8.jpeg', 'bac4074b59513fef61d9f65efe66516c2.jpg', 'selesai', '35', '1953'),
(46, 'Terjadi gempa bumi dasyat', 'Retak lelah dan deformasi pada semua lapisan perkerasan aspal', '2023-04-02 22:52:28', '088089800_1569394916-20190924-Gempa-Besar-Guncang-Pakistan-AP-712.jpg', 'images9.jpeg', 'images10.jpeg', 'selesai', '35', '1953'),
(47, 'Tsunami di aceh pada tahun 2007 jjJJJHJH', 'Retak lelah dan deformasi pada semua lapisan perkerasan aspal', '2023-04-02 23:35:26', 'images12.jpeg', '088089800_1569394916-20190924-Gempa-Besar-Guncang-Pakistan-AP-713.jpg', 'images13.jpeg', 'tidak_valid', '35', '1953');

-- --------------------------------------------------------

--
-- Table structure for table `saran`
--

CREATE TABLE `saran` (
  `id_saran` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `no_telepon` varchar(20) NOT NULL,
  `alamat` text NOT NULL,
  `saran` text NOT NULL,
  `tgl_saran` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `saran`
--

INSERT INTO `saran` (`id_saran`, `nama`, `no_telepon`, `alamat`, `saran`, `tgl_saran`) VALUES
(3, 'Kevin Gilbert', '083723232', 'Jl. pembangunann', 'Sebaiknya perkerjaan di percepat', '2023-03-30 22:09:43'),
(4, 'Toding gilbert', '0838434', 'jl mugas 2', 'baiks ekaliiii', '2023-03-30 22:34:39'),
(5, 'SADDAD', '090394049', 'SDADSAD', 'DSADADASDASDAD', '2023-04-01 22:51:17');

-- --------------------------------------------------------

--
-- Table structure for table `tanggapan`
--

CREATE TABLE `tanggapan` (
  `id_tanggapan` int(11) NOT NULL,
  `isi_tanggapan` text NOT NULL,
  `tgl_tanggapan` datetime NOT NULL,
  `status_tanggapan` enum('proses','valid','pengerjaan','selesai','tidak_valid') NOT NULL,
  `foto_tanggapan` varchar(500) NOT NULL DEFAULT 'default.png',
  `id_pengaduan` int(11) NOT NULL,
  `id_user` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tanggapan`
--

INSERT INTO `tanggapan` (`id_tanggapan`, `isi_tanggapan`, `tgl_tanggapan`, `status_tanggapan`, `foto_tanggapan`, `id_pengaduan`, `id_user`) VALUES
(28, 'bisa diatur', '2023-01-10 02:29:32', 'proses', '2.jpg', 19, 3),
(65, 'ddsfdsf', '2023-04-05 06:48:13', 'selesai', 'images21.jpeg', 46, 1),
(66, 'uabh 2', '2023-04-05 07:32:09', 'proses', '01_jalan.jpg', 43, 1),
(40, 'tets tanggapan', '2023-04-04 21:31:52', 'valid', 'over-horizon-resto-semarang.jpg', 23, 23),
(67, 'test ubah', '2023-04-05 07:30:39', 'valid', 'images23.jpeg', 43, 1),
(68, 'sdfdsf', '2023-04-05 06:48:52', 'pengerjaan', 'images24.jpeg', 43, 1),
(36, 'sedang dalam proses', '2023-04-01 01:35:15', 'proses', 'Screenshot_2023-03-01_2133071.png', 45, 1),
(37, 'baik', '2023-04-01 01:36:07', 'valid', 'Screenshot_2023-03-04_133200.png', 45, 1),
(63, 'sdas', '2023-04-05 06:43:22', 'pengerjaan', 'images19.jpeg', 46, 1),
(59, 'dsa', '2023-04-05 06:42:21', 'proses', 'images15.jpeg', 47, 1),
(60, 'dad', '2023-04-05 06:42:32', 'tidak_valid', 'images16.jpeg', 47, 1),
(61, 'dsad', '2023-04-05 06:43:01', 'proses', 'images17.jpeg', 46, 1),
(62, 'sad', '2023-04-05 06:43:10', 'valid', 'images18.jpeg', 46, 1),
(69, 'd', '2023-04-05 06:49:00', 'selesai', 'images25.jpeg', 43, 1);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id_user` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `no_telepon` varchar(20) NOT NULL,
  `jabatan` enum('administrator','operator') NOT NULL,
  `ktp` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id_user`, `nama`, `username`, `password`, `no_telepon`, `jabatan`, `ktp`) VALUES
(1, 'Administrator', 'admin', '$2y$10$UvqdMtn7FHRCZ1Hug/fNS.PnDForJxEAhFUrtsiGa.Xj7hngFU946', '08123456789', 'administrator', ''),
(12, 'keren', 'fajarsad', '$2y$10$gB0WiTTKkKF.PUTcOZZiQ.A8KyWSyEM07I.pHAUEOGu.A8jwAY9sC', '121212', 'operator', ''),
(13, 'keren prameswari', 'fajarsadasdsa', '$2y$10$gB0WiTTKkKF.PUTcOZZiQ.A8KyWSyEM07I.pHAUEOGu.A8jwAY9sC', '121212344', 'operator', ''),
(14, 'kerenssad', 'oiyada', '$2y$10$c30XppDSx6cVUGua2dto/Ob3LP6v5pzIjySjGUdh4c.RnwgA9wb.G', '121212', '', ''),
(15, 'kerenssad', 'oiyadasd', '$2y$10$BTNBDzMrfyo8JNI8QpeO7uJ5lRzJ1lpFobWhWu3H72EOSrmLBa7mK', '121212', 'operator', ''),
(16, 'kerenssad', 'oiyadasd', '$2y$10$7NHFFSgQeHQLIE1j7kwR/OGMsQdZqDW10iZ155TG1dBr4cQ/YlikK', '121212', 'operator', ''),
(17, 'kerenssad', 'oiyadasd', '$2y$10$xyVBFfXRofJjYrXkrm5ruOtPX/C7f2xe3jlYmUAdnmCOllLMHy2Oi', '121212', 'operator', ''),
(18, 'kerenssadsssda', 'oiyadasds', '$2y$10$dAgtkxwA0Kok0I62yyhPPewAeLzP8jrzrR6skkAN/PoAcPmjZ1VJm', '121212', 'operator', ''),
(20, 'gilto', 'gilto', '$2y$10$GToTOM3EO0oyp.vQjH54iOdc9FBv3mRarJGQ9X.yI/pQTRw3DYCty', '4324', 'operator', '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `kecamatan`
--
ALTER TABLE `kecamatan`
  ADD PRIMARY KEY (`id_kecamatan`);

--
-- Indexes for table `kelurahan`
--
ALTER TABLE `kelurahan`
  ADD PRIMARY KEY (`id_kelurahan`),
  ADD KEY `id_kecamatan` (`id_kecamatan`);

--
-- Indexes for table `log`
--
ALTER TABLE `log`
  ADD PRIMARY KEY (`id_log`),
  ADD KEY `id_user` (`id_user`);

--
-- Indexes for table `masyarakat`
--
ALTER TABLE `masyarakat`
  ADD PRIMARY KEY (`id_masyarakat`);

--
-- Indexes for table `pengaduan`
--
ALTER TABLE `pengaduan`
  ADD PRIMARY KEY (`id_pengaduan`),
  ADD KEY `id_masyarakat` (`id_masyarakat`),
  ADD KEY `id_kelurahan` (`id_kelurahan`);

--
-- Indexes for table `saran`
--
ALTER TABLE `saran`
  ADD PRIMARY KEY (`id_saran`);

--
-- Indexes for table `tanggapan`
--
ALTER TABLE `tanggapan`
  ADD PRIMARY KEY (`id_tanggapan`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_pengaduan` (`id_pengaduan`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `kecamatan`
--
ALTER TABLE `kecamatan`
  MODIFY `id_kecamatan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- AUTO_INCREMENT for table `kelurahan`
--
ALTER TABLE `kelurahan`
  MODIFY `id_kelurahan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2235;

--
-- AUTO_INCREMENT for table `log`
--
ALTER TABLE `log`
  MODIFY `id_log` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=146;

--
-- AUTO_INCREMENT for table `masyarakat`
--
ALTER TABLE `masyarakat`
  MODIFY `id_masyarakat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT for table `pengaduan`
--
ALTER TABLE `pengaduan`
  MODIFY `id_pengaduan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- AUTO_INCREMENT for table `saran`
--
ALTER TABLE `saran`
  MODIFY `id_saran` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `tanggapan`
--
ALTER TABLE `tanggapan`
  MODIFY `id_tanggapan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=70;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
