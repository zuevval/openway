-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3308
-- Время создания: Июн 02 2019 г., 20:59
-- Версия сервера: 10.3.13-MariaDB
-- Версия PHP: 7.1.22

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `openway`
--

-- --------------------------------------------------------

--
-- Структура таблицы `applications`
--

CREATE TABLE `applications` (
  `appl_id` int(11) NOT NULL,
  `appl_name` varchar(45) NOT NULL,
  `appl_name2` varchar(45) DEFAULT NULL,
  `appl_surn` varchar(45) NOT NULL,
  `appl_email` varchar(45) NOT NULL,
  `appl_how` varchar(45) DEFAULT 'NULL',
  `appl_about` text DEFAULT NULL,
  `appl_comment` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `applications`
--

INSERT INTO `applications` (`appl_id`, `appl_name`, `appl_name2`, `appl_surn`, `appl_email`, `appl_how`, `appl_about`, `appl_comment`) VALUES
(1, 'Jane', NULL, 'Doe', 'jane.doe@example.com', NULL, NULL, NULL),
(8, 'Igor', 'Vasilyevich', 'Kurchatov', 'nuclear.king@gmail.com', NULL, NULL, NULL),
(9, 'Valerii', NULL, 'Zuev', 'valera.zuev.zva@gmail.com', NULL, NULL, NULL),
(11, 'Alex', 'A.', 'Burkov', 'bf@mail.ru', 'not sure', 'Im a human', ''),
(12, 'Alex', 'F', 'Putikov', 'oput@mail.ru', 'difficult question', 'Im a man', ')'),
(13, 'Susanna', '', 'Martin', 'smart@gmail.com', 'I have been told in my colledge', 'Ohh', 'No comments');

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

CREATE TABLE `users` (
  `login` varchar(45) NOT NULL,
  `pass` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `users`
--

INSERT INTO `users` (`login`, `pass`) VALUES
('admin', 'qwerty');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `applications`
--
ALTER TABLE `applications`
  ADD PRIMARY KEY (`appl_id`),
  ADD UNIQUE KEY `appl_id` (`appl_id`),
  ADD UNIQUE KEY `secKey1` (`appl_name`,`appl_name2`,`appl_surn`);

--
-- Индексы таблицы `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`login`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `applications`
--
ALTER TABLE `applications`
  MODIFY `appl_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
