-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Apr 14, 2020 at 10:07 PM
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
(1, 1, 37.7858, -122.406, 0, '2020-03-31 22:01:01'),
(2, 1, 37.7866, -122.406, 0, '2020-03-31 22:01:01'),
(3, 2, 37.7867, -122.406, 0, '2020-03-31 22:01:01'),
(4, 2, 37.7868, -122.406, 0, '2020-03-31 22:01:14'),
(5, 2, 37.7858, -122.405, 0, '2020-03-31 22:01:14'),
(6, 3, 37.7858, -122.411, 0, '2020-03-31 22:01:15'),
(7, 1, 38.7858, -122.406, 0, '2020-03-31 22:01:32'),
(8, 3, 38.7858, -122.401, 0, '2020-03-31 22:01:33'),
(9, 2, 38.7858, -122.405, 0, '2020-03-31 22:01:33'),
(10, 4, 38.7858, -122.405, 0, '2020-03-31 22:01:58'),
(11, 4, 37.7858, -122.406, 0, '2020-03-31 22:01:58'),
(12, 5, 38.1015, -105.101, 0.1022, '2020-04-01 20:31:13');

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
(1, 'NID1001', '101994462', 'Alex', '164E92FC-D37A-4946-81CB-29DE7EE4B124'),
(2, 'NID0002', '120299281', 'Bob', 'EFBF47ED-FF42-4AE8-B7BC-6BD3158127B5'),
(3, 'NID0003', '101199281', 'Charlie', '3EBD4E7D-33AA-41C4-AE45-20886957E629'),
(4, 'NID0004', '101199281', 'Dave', '0193FF51-2F43-4D4A-BA5B-686CA6048F58'),
(5, 'NID0005', '123451122', 'Einstein', '3EBD4E7D-33AA-41C4-AE45-20886957E639');

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
  MODIFY `event_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
