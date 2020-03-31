-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Apr 01, 2020 at 12:02 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ctracker`
--

-- --------------------------------------------------------

--
-- Table structure for table `events`
--

CREATE TABLE `events` (
  `event_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `event_lat` float NOT NULL,
  `event_lon` float NOT NULL,
  `event_alt` float NOT NULL,
  `event_time` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `events`
--

INSERT INTO `events` (`event_id`, `user_id`, `event_lat`, `event_lon`, `event_alt`, `event_time`) VALUES
(7, 42, 37.7858, -122.406, 0, '2020-03-31 22:01:01'),
(8, 42, 37.7858, -122.406, 0, '2020-03-31 22:01:01'),
(9, 42, 37.7858, -122.406, 0, '2020-03-31 22:01:01'),
(10, 52, 37.7858, -122.406, 0, '2020-03-31 22:01:14'),
(11, 52, 37.7858, -122.406, 0, '2020-03-31 22:01:14'),
(12, 52, 37.7858, -122.406, 0, '2020-03-31 22:01:15'),
(13, 62, 37.7858, -122.406, 0, '2020-03-31 22:01:32'),
(14, 62, 37.7858, -122.406, 0, '2020-03-31 22:01:33'),
(15, 62, 37.7858, -122.406, 0, '2020-03-31 22:01:33'),
(16, 72, 37.7858, -122.406, 0, '2020-03-31 22:01:58'),
(17, 72, 37.7858, -122.406, 0, '2020-03-31 22:01:58'),
(18, 72, 37.7858, -122.406, 0, '2020-03-31 22:01:59');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `user_nid` varchar(50) NOT NULL,
  `user_phone_number` varchar(20) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `user_duid` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `user_nid`, `user_phone_number`, `user_name`, `user_duid`) VALUES
(24, '79989983', '100299289', 'Alex', '107042DF-6B12-4081-8ABC-DDBFAEA78E98'),
(25, '79989985', '100299281', 'Bob', '164E92FC-D37A-4946-81CB-29DE7EE4B124'),
(26, '88989985', '120299281', 'Charlie', 'EFBF47ED-FF42-4AE8-B7BC-6BD3158127B5'),
(27, '38989988', '101199281', 'Dave', '3EBD4E7D-33AA-41C4-AE45-20886957E629');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `events`
--
ALTER TABLE `events`
  ADD PRIMARY KEY (`event_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `user_nid` (`user_nid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `events`
--
ALTER TABLE `events`
  MODIFY `event_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
