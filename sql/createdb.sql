CREATE TABLE `books` (
`id` int(11) NOT NULL,
`isbn` varchar(45) DEFAULT NULL, 
`title` varchar(45) DEFAULT NULL,
`author` varchar(45) DEFAULT NULL,
`publishDate` varchar(45) DEFAULT NULL,
PRIMARY KEY (`id`) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;   

CREATE TABLE `reservations` (   
`id` int(11) NOT NULL,   
`bookId` int(11) DEFAULT NULL,   
`startDate` varchar(45) DEFAULT NULL,   
`endDate` varchar(45) DEFAULT NULL,   
`out` tinyint(4) DEFAULT NULL,   
PRIMARY KEY (`id`),   
KEY `bookId_idx` (`bookId`),   
KEY `id_idx` (`bookId`),   
CONSTRAINT `id` FOREIGN KEY (`bookId`) REFERENCES `books` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
