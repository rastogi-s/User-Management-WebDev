CREATE TABLE `lesson` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `module_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8ig5gt5ec58weexave6kdn2cv` (`module_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
