-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 26, 2024 at 06:45 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `game_rush`
--

-- --------------------------------------------------------

--
-- Table structure for table `game`
--

CREATE TABLE `game` (
  `gameID` int(10) NOT NULL,
  `gameTitle` varchar(60) NOT NULL,
  `gameGenre` varchar(10) NOT NULL,
  `releaseDate` date NOT NULL,
  `gamePrice` float NOT NULL,
  `description` varchar(400) NOT NULL,
  `gameImage` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `game`
--

INSERT INTO `game` (`gameID`, `gameTitle`, `gameGenre`, `releaseDate`, `gamePrice`, `description`, `gameImage`) VALUES
(1, 'Adventures in Kasukabe', 'Action', '2024-05-09', 3000, 'The peaceful town of Kasukabe is in turmoil as mysterious disturbances begin to disrupt daily life. Shinchan and his friends suspect that an evil force is at work, threatening the harmony of their beloved home. As the town\'s last line of defense, they set out on a quest to unravel the mystery behind these disturbances and restore peace to Kasukabe.', 'SLUG HOUSE.jpg'),
(3, 'Galactic Heroes', 'Simulation', '2024-05-09', 2000, 'Venture into the uncharted depths of the galaxy as a member of the Star Explorers, an elite group of spacefaring adventurers. Pilot your starship, discover new planets, and encounter alien civilizations as you unravel the mysteries of the cosmos. ', 'silmeow.jpg'),
(4, 'Chrono Odyssey', 'Other', '2024-05-09', 3400, 'Embark on a journey through time and space in this mesmerizing RPG adventure. As a time traveler gifted with extraordinary abilities, you must navigate the echoes of history to uncover hidden truths and alter the course of destiny. ', 'SpongeBob Bob Esponja.jpg'),
(5, 'Legends of Ghibi', 'Adventure', '2024-05-03', 2000, 'Uncover the forgotten lore of Ghibli, a world steeped in legend and myth. As a chosen hero, you must embark on a quest to fulfill the ancient prophecy and prevent the rise of darkness.', 'Silly Spongebob.jpg'),
(6, 'Frostfall Saga', 'Other', '2024-05-09', 2100, 'Brave the frozen tundra of Frostfall, a land locked in eternal winter. As a courageous adventurer, you must unravel the mysteries of the Winter\'s Veil, battling fierce creatures and confronting ancient powers', 'download (1).jpg'),
(7, 'Nebula Odyssey', 'Horror', '2024-05-09', 5200, 'Embark on a cosmic journey through the Nebula, a realm of swirling stardust and celestial wonders. As a Guardian of the Starlight, you must protect the Nebula from dark forces and restore balance to the cosmos.', 'chicken.jpg'),
(8, 'Cheesy cheese', 'Action', '2024-05-09', 1200, 'Welcome to Cheesy Cheese, the ultimate game for cheese enthusiasts and lovers of all things dairy! Dive into a world where you\'ll embark on a tantalizing adventure through cheese-filled landscapes, gooey challenges, and creamy conquests.', 'download (2).jpg'),
(9, 'Kitty', 'Adventure', '2024-05-13', 2000, 'Kitty', 'KittyCat.jpg'),
(10, 'Milka', 'Action', '2024-05-13', 4500, 'milka', 'default_product.jpg'),
(11, 'Jelly', 'Simulation', '2024-05-13', 1200, 'Jelly', 'default_product.jpg'),
(12, 'hello', 'Adventure', '2024-04-30', 1200, 'hello', 'Whoops.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `userName` varchar(10) NOT NULL,
  `email` varchar(40) NOT NULL,
  `firstName` varchar(20) NOT NULL,
  `lastName` varchar(20) NOT NULL,
  `DOB` date NOT NULL,
  `phoneNumber` varchar(14) NOT NULL,
  `role` varchar(10) NOT NULL DEFAULT 'user',
  `password` varchar(200) NOT NULL,
  `profileImage` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userName`, `email`, `firstName`, `lastName`, `DOB`, `phoneNumber`, `role`, `password`, `profileImage`) VALUES
('aa', 'shooaaye@gmail.com', 'aa', 'aa', '2002-01-01', '+9888767656787', 'user', 'mcYtSpxkVoiaIIwbn4A0ZOYH4syUBUVvCJEU4o95k1CYMH+2tfkIf8tWLtv6cPjL', 'default_user.jpg'),
('aayesha', 'aayeshanakarmi@gmail.com', 'Aayesha', 'Nakarmi', '2004-02-09', '+9779876543212', 'user', '+j1LxqLfE5KIxEYFHVlQkEDRC4We/KqdSrvknMXOkNWp7cKSE9C6/QEi4FzxRtwHQXwIYaQ=', 'default_user.jpg'),
('admin', 'admin@gmail.com', 'spongebob', 'squarepants', '2005-04-09', '+9779886905908', 'admin', 'YemuZM2THImieZcZsW/KXa+illk/eqHhqZL0Y3fOdE+Y91Mgn4ySDOj7M428IP45Cb/a', 'Silly Spongebob.jpg'),
('blabla', 'blabla@gmail.com', 'blabla', 'blabla', '2005-07-07', '+9887876767699', 'user', 'yY+L8UqWq5vJcZz08Vhvbc1ItHQUlB5gYngtF0NCnI3UV1rVSmQDTX7zOmV7aA6d9T1fgw==', 'default_user.jpg'),
('doggy', 'doggy@gmail.com', 'doggy', 'doggy', '2024-03-12', '+977875647889', 'user', 'l2x3VRcPe1uny5qeOvmwgjmqexqYf6B3/eHV4o1J+6O6/juImmAP0nngagt00WtMRw==', 'vadim-kaipov-8ZELrodSvTc-unsplash.jpg'),
('donald', 'donald@gmail.com', 'donald', 'duck', '2005-01-08', '+9887876767656', 'user', 'CESaUCotJ7eiLuHKjORdPTTnyeE+Ipk5TGTfhmYl3kDO1eXE9CuYJ7kn3eUh0k+1jy41XA==', 'default_user.jpg'),
('golden', 'goldengolden@gmail.com', 'golden', 'golden', '2024-04-22', '+9779869059080', 'user', '5WMvyhsMO7ro5lYJQuXeQpKlYARduyH0777D80wjhnvkdWMjld6U8bmNJwJl0SWsbw8=', 'Screenshot 2023-08-11 200907.png'),
('jaaaaaaa', 'dracula@gmail.com', 'dracula', 'dracula', '2024-04-29', '+9778756478390', 'user', 'oA5BTekuHmFE9/M0i/53vokvx7Rxz/0cGRSQgYkNF5W6URfi6J9xsi03lm3PCcWKrrEKsg==', 'default_user.jpg'),
('jelly', 'jelly@gmail.com', 'jelly', 'jelly', '1999-06-08', '+9779869059098', 'user', '7NT1uOlrEztMT0d+jyaN6S2b4qAJCQrkPAO7wkJOgss84TCly8hLJGS5peTkU4QnJkSZ', 'default_user.jpg'),
('kungfu', 'kungfu@gmail.com', 'Kungfuu', 'Pandaa', '1998-02-13', '+9779886905906', 'user', '+HRCfZXH7ng8yMp8Kiy8LL9hqXCU37oqDWvSRF8DOqw6JXe/prQph4OZ45Ye6rxDnn2zvw==', 'kungFu.jpg'),
('meow', 'meow@gmail.com', 'Aayesha', 'Nakarmi', '2000-04-05', '+9887876565454', 'user', '1BCF+dC05nB0IHMq7D9iGpS0MHKi4w0jO3FWKKUCpLpCOdpBPy/3S/5MHozKm5PIg5o=', 'default_user.jpg'),
('sheep', 'sheep@gmail.com', 'sheep', 'sheep', '2005-09-05', '+9779886905999', 'user', 'Q0Kotd2R11Q6Ti1X5kSebe7TBf52jY7JCXucCIOa6UsTlqKV3o5U1V8AJK3bnwkAfLMN', 'chicken.jpg'),
('sheepy', 'sheepy@gmail.com', 'sheepy', 'sheepy', '2000-04-05', '+9779886905777', 'user', 'oOf7eKpX1URnNfCTTBF770+UTL43MGQ/yqLNS07b/zcdQMdZzQ9njH42TqprUbNWLA3OtA==', 'default_user.jpg'),
('yes', 'yes@gmail.com', 'yesyes', 'yesyes', '2000-07-08', '+9887876565488', 'user', 'dQ+ZgRAEDbRJPD0wNMv40rOnO1Yo5D2FaxdX0pHI880RUxUGcOiEqIKgBAdm8MTjRA==', 'Whoops.jpg');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `game`
--
ALTER TABLE `game`
  ADD PRIMARY KEY (`gameID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userName`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `phoneNumber` (`phoneNumber`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
