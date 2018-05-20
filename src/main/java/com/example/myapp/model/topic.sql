CREATE TABLE `topic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `lesson_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKie3l7jgy01eohf5lyafc6qj1b` (`lesson_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
