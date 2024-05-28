-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 16, 2024 at 07:03 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `vtravel_agency_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `accommodations`
--

CREATE TABLE `accommodations` (
  `accommodationid` bigint(20) NOT NULL,
  `DestinationID` int(11) DEFAULT NULL,
  `Name` varchar(100) DEFAULT NULL,
  `Description` text DEFAULT NULL,
  `price_per_night` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `accommodations`
--

INSERT INTO `accommodations` (`accommodationid`, `DestinationID`, `Name`, `Description`, `price_per_night`) VALUES
(1, 1, 'Hotel Paris', 'Luxurious hotel in the heart of Paris.', 300),
(2, 2, 'Tokyo Tower Hotel', 'Scenic views of Tokyo skyline.', 370),
(3, 3, 'Rome Palace', 'Elegant hotel with classic Italian architecture.', 400);

-- --------------------------------------------------------

--
-- Table structure for table `admins`
--

CREATE TABLE `admins` (
  `adminid` bigint(20) NOT NULL,
  `Username` varchar(50) DEFAULT NULL,
  `Password` varchar(255) DEFAULT NULL,
  `FirstName` varchar(50) DEFAULT NULL,
  `LastName` varchar(50) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Phone` varchar(20) DEFAULT NULL,
  `Role` varchar(100) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admins`
--

INSERT INTO `admins` (`adminid`, `Username`, `Password`, `FirstName`, `LastName`, `Email`, `Phone`, `Role`, `first_name`, `last_name`) VALUES
(1, 'youness', 'younessvistatravels1', 'Youness', 'Kasside', 'youness@example.com', '123-456-7890', 'Administrator', NULL, NULL),
(2, 'hiba', 'hibavistatravels2', 'Hiba', 'Arbel', 'hiba@example.com', '987-654-3210', 'Administrator', NULL, NULL),
(3, 'aya', 'ayavistatravels3', 'Aya', 'Elmghari', 'aya@example.com', '555-123-4567', 'Manager', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `bookings`
--

CREATE TABLE `bookings` (
  `BookingID` int(11) NOT NULL,
  `CustomerID` int(11) DEFAULT NULL,
  `VoyageID` int(11) DEFAULT NULL,
  `Status` enum('Confirmed','Pending','Cancelled') DEFAULT NULL,
  `booking_date` datetime(6) DEFAULT NULL,
  `total_price` decimal(38,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `bookings`
--

INSERT INTO `bookings` (`BookingID`, `CustomerID`, `VoyageID`, `Status`, `booking_date`, `total_price`) VALUES
(1, 1, 1, 'Confirmed', '2024-03-20 00:00:00.000000', 1500.00),
(2, 2, 2, 'Pending', '2024-03-25 00:00:00.000000', 2000.00),
(3, 3, 3, 'Confirmed', '2024-03-30 00:00:00.000000', 1800.00),
(7, 1, 2, 'Confirmed', '2024-05-11 01:00:00.000000', 1000.00),
(9, 14, 2, 'Pending', '2024-05-11 00:00:00.000000', 6000.00);

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `CustomerID` int(11) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Phone` varchar(20) DEFAULT NULL,
  `Address` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`CustomerID`, `first_name`, `last_name`, `Email`, `Phone`, `Address`, `password`) VALUES
(1, 'John', 'Doe', 'john.doe@example.com', '123-456-7890', '123 Main St, City', 'default_password'),
(2, 'Jane', 'Smith', 'jane.smith@example.com', '987-654-3210', '456 Elm St, Town', 'default_password'),
(3, 'Alice', 'Johnson', 'alice.johnson@example.com', '555-123-4567', '789 Oak Ave, Village', 'default_password'),
(14, 'YOUNESS', 'QASSID', 'younesqassid@outlook.com', NULL, NULL, '$2a$10$ZDdF..pAWvVyIM.WA5MF7.ZjxZOK7qfrsYVKwsnYR5OLM1J9u42QW'),
(24, 'YOUNES', 'QASSID', 'younesqassid7@gmail.com', NULL, NULL, '$2a$10$m0.rXwVb21VSVm1gA5fH2uhDBKt7hNpSIiKx6Mxcp0PsNm4rtbjeq');

-- --------------------------------------------------------

--
-- Table structure for table `destinations`
--

CREATE TABLE `destinations` (
  `DestinationID` int(11) NOT NULL,
  `Description` text DEFAULT NULL,
  `ImageURL` varchar(255) DEFAULT NULL,
  `destination_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `destinations`
--

INSERT INTO `destinations` (`DestinationID`, `Description`, `ImageURL`, `destination_name`) VALUES
(1, 'Explore the enchanting \"City of Love\" with its iconic landmarks, charming streets, and romantic ambiance.', '/images/cities/paris.jpg', 'Paris'),
(2, 'Experience the vibrant energy of Tokyo, where tradition meets innovation amidst bustling streets and tranquil temples.', '/images/cities/tokyo.jpg', 'Tokyo'),
(3, 'Step back in time and discover the eternal city of Rome, home to ancient ruins, majestic monuments, and Renaissance art.', '/images/cities/rome.jpg', 'Rome'),
(4, 'Discover the bustling streets, iconic skyscrapers, and cultural diversity of the Big Apple, where every corner offers a unique experience.', '/images/cities/newyork.jpg', 'New York City'),
(5, 'Immerse yourself in the rich history and modern charm of London, home to iconic landmarks, world-class museums, and vibrant neighborhoods.', '/images/cities/london.jpg', 'London'),
(6, 'Experience the vibrant culture, stunning architecture, and lively atmosphere of Barcelona, where Gaudi\'s masterpieces and Mediterranean beaches await.', '/images/cities/barcelona.jpg', 'Barcelona'),
(7, 'Embark on an exotic journey to Marrakesh, where bustling souks, colorful palaces, and the vibrant Medina create an unforgettable atmosphere.', '/images/cities/marrakesh.jpg', 'Marrakesh'),
(8, 'Experience the dazzling luxury and modern marvels of Dubai, home to futuristic skyscrapers, luxury shopping malls, and golden desert landscapes.', '/images/cities/dubai.jpg', 'Dubai'),
(9, 'Explore the fascinating blend of East and West in Istanbul, where ancient mosques, bustling bazaars, and stunning palaces await around every corner.', '/images/cities/istanbul.jpg', 'Istanbul');

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE `employees` (
  `employeeid` bigint(20) NOT NULL,
  `FirstName` varchar(50) DEFAULT NULL,
  `LastName` varchar(50) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Phone` varchar(20) DEFAULT NULL,
  `Position` varchar(100) DEFAULT NULL,
  `Department` varchar(100) DEFAULT NULL,
  `Location` varchar(100) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`employeeid`, `FirstName`, `LastName`, `Email`, `Phone`, `Position`, `Department`, `Location`, `first_name`, `last_name`) VALUES
(1, 'Ahmed', 'El Azzouzi', 'ahmed.elazzouzi@example.com', '212-123-456789', 'Travel Consultant', 'Sales', 'Marrakech', NULL, NULL),
(2, 'Fatima', 'Benali', 'fatima.benali@example.com', '212-987-654321', 'Sales Manager', 'Sales', 'Casablanca', NULL, NULL),
(3, 'Youssef', 'Hassani', 'youssef.hassani@example.com', '212-654-987321', 'Tour Guide', 'Customer Service', 'Marrakech', NULL, NULL),
(4, 'Sanaa', 'Amrani', 'sanaa.amrani@example.com', '212-567-123456', 'Customer Service Representative', 'Customer Service', 'Rabat', NULL, NULL),
(5, 'Hicham', 'Bouzid', 'hicham.bouzid@example.com', '212-789-456123', 'Marketing Specialist', 'Marketing', 'Casablanca', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `payments`
--

CREATE TABLE `payments` (
  `paymentid` bigint(20) NOT NULL,
  `BookingID` int(11) DEFAULT NULL,
  `PaymentDate` date DEFAULT NULL,
  `amount` decimal(38,2) DEFAULT NULL,
  `PaymentMethod` varchar(50) DEFAULT NULL,
  `payment_date` datetime(6) DEFAULT NULL,
  `payment_method` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `payments`
--

INSERT INTO `payments` (`paymentid`, `BookingID`, `PaymentDate`, `amount`, `PaymentMethod`, `payment_date`, `payment_method`) VALUES
(1, 1, '2024-03-21', 1500.00, 'Credit Card', NULL, NULL),
(2, 2, '2024-03-26', 1000.00, 'PayPal', NULL, NULL),
(3, 3, '2024-03-31', 1800.00, 'Credit Card', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `voyages`
--

CREATE TABLE `voyages` (
  `VoyageID` int(11) NOT NULL,
  `DestinationID` int(11) DEFAULT NULL,
  `price` decimal(38,2) DEFAULT NULL,
  `available_seats` int(11) DEFAULT NULL,
  `departure_date` datetime(6) DEFAULT NULL,
  `return_date` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `voyages`
--

INSERT INTO `voyages` (`VoyageID`, `DestinationID`, `price`, `available_seats`, `departure_date`, `return_date`) VALUES
(1, 1, 1500.00, 50, '2024-04-01 00:00:00.000000', '2024-04-22 00:00:00.000000'),
(2, 2, 2000.00, 40, '2024-05-12 00:00:00.000000', '2024-05-20 00:00:00.000000'),
(3, 3, 1800.00, 30, '2024-06-01 00:00:00.000000', '2024-06-10 00:00:00.000000');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `accommodations`
--
ALTER TABLE `accommodations`
  ADD PRIMARY KEY (`accommodationid`),
  ADD KEY `DestinationID` (`DestinationID`);

--
-- Indexes for table `admins`
--
ALTER TABLE `admins`
  ADD PRIMARY KEY (`adminid`),
  ADD UNIQUE KEY `Username` (`Username`);

--
-- Indexes for table `bookings`
--
ALTER TABLE `bookings`
  ADD PRIMARY KEY (`BookingID`),
  ADD KEY `CustomerID` (`CustomerID`),
  ADD KEY `VoyageID` (`VoyageID`);

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`CustomerID`),
  ADD UNIQUE KEY `unique_email` (`Email`);

--
-- Indexes for table `destinations`
--
ALTER TABLE `destinations`
  ADD PRIMARY KEY (`DestinationID`);

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`employeeid`);

--
-- Indexes for table `payments`
--
ALTER TABLE `payments`
  ADD PRIMARY KEY (`paymentid`),
  ADD KEY `BookingID` (`BookingID`);

--
-- Indexes for table `voyages`
--
ALTER TABLE `voyages`
  ADD PRIMARY KEY (`VoyageID`),
  ADD KEY `DestinationID` (`DestinationID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `accommodations`
--
ALTER TABLE `accommodations`
  MODIFY `accommodationid` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `admins`
--
ALTER TABLE `admins`
  MODIFY `adminid` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `bookings`
--
ALTER TABLE `bookings`
  MODIFY `BookingID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `CustomerID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `destinations`
--
ALTER TABLE `destinations`
  MODIFY `DestinationID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `employees`
--
ALTER TABLE `employees`
  MODIFY `employeeid` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `payments`
--
ALTER TABLE `payments`
  MODIFY `paymentid` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `voyages`
--
ALTER TABLE `voyages`
  MODIFY `VoyageID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `accommodations`
--
ALTER TABLE `accommodations`
  ADD CONSTRAINT `accommodations_ibfk_1` FOREIGN KEY (`DestinationID`) REFERENCES `destinations` (`DestinationID`);

--
-- Constraints for table `bookings`
--
ALTER TABLE `bookings`
  ADD CONSTRAINT `bookings_ibfk_1` FOREIGN KEY (`CustomerID`) REFERENCES `customers` (`CustomerID`),
  ADD CONSTRAINT `bookings_ibfk_2` FOREIGN KEY (`VoyageID`) REFERENCES `voyages` (`VoyageID`);

--
-- Constraints for table `payments`
--
ALTER TABLE `payments`
  ADD CONSTRAINT `payments_ibfk_1` FOREIGN KEY (`BookingID`) REFERENCES `bookings` (`BookingID`);

--
-- Constraints for table `voyages`
--
ALTER TABLE `voyages`
  ADD CONSTRAINT `voyages_ibfk_1` FOREIGN KEY (`DestinationID`) REFERENCES `destinations` (`DestinationID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
