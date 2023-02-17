-- 账户表 --
CREATE TABLE `user_info` (
   `pk_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
   `user_name` varchar(255) NOT NULL,
   `user_type` int(4) NOT NULL,
   `did` varchar(255) NOT NULL,
   `salt` varchar(255) NOT NULL,
   `pwdhash` varchar(255) NOT NULL,
   `contact` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '联系方式',
   `location` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '联系地址',
   `email` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '邮箱',
   `review_state` int(4) NOT NULL,
   `review_time` timestamp NOT NULL,
   `creat_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`pk_id`),
   UNIQUE KEY (`user_name`),
   UNIQUE KEY (`did`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 机构证书表 --
CREATE TABLE `cert_info` (
   `pk_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
   `did` varchar(255) NOT NULL,
   `cert_type` int(4) null comment '证件类型',
   `cert_no `varchar(128) default '' not null comment '证件号码',
   `cert_hash` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '证件指纹',
   `creat_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`pk_id`),
   UNIQUE KEY (`did`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 产品表 --
CREATE TABLE `product` (
   `pk_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
   `product_id` bigint(20) NOT NULL,
   `did` varchar(255) NOT NULL,
   `product_name` varchar(255) NOT NULL,
   `enterprise_id` varchar(255) NOT NULL,
   `information` text NOT NULL,
   `review_state` int(4) NOT NULL,
   `review_time` timestamp NOT NULL,
   `creat_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`pk_id`),
   UNIQUE KEY (`did`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 目录表 --
CREATE TABLE `schema` (
   `pk_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
   `schema_id` bigint(20) NOT NULL,
   `provider_id` bigint(20) NOT NULL,
   `product_id` bigint(20) NOT NULL,
   `version` int(4) NOT NULL,
   `visible` int(4) NOT NULL,
   `description` text NOT NULL,
   `usage` varchar(64) NOT NULL,
   `price` int(32) NOT NULL,
   `tags` varchar(64) NOT NULL,
   `visitInfo` text NOT NULL,
   `creat_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`pk_id`),
   UNIQUE KEY (`schema_id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;