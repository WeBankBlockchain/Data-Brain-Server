-- 账户表 --
CREATE TABLE `user_info` (
   `pk_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
   `user_name` varchar(255) NOT NULL DEFAULT '' COMMENT '用户名',
   `user_type` int(4) NOT NULL DEFAULT 0 COMMENT '用户类型',
   `did` varchar(255) NOT NULL DEFAULT '' COMMENT 'DID',
   `salt` varchar(255) NOT NULL,
   `pwdhash` varchar(255) NOT NULL,
   `contact` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '联系方式',
   `location` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '联系地址',
   `email` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '邮箱',
   `review_state` int(4) NOT NULL DEFAULT 0 COMMENT '审核状态',
   `review_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '审核时间',
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
   `cert_hash` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '证件指纹',
   `cert_content` text COMMENT '证件详情',
   `creat_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`pk_id`),
   UNIQUE KEY (`did`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 产品表 --
CREATE TABLE `product` (
   `pk_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
   `product_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '产品id',
   `did` varchar(255) NOT NULL,
   `product_name` varchar(255) NOT NULL DEFAULT '' COMMENT '产品名称',
   `provider_id` varchar(255) NOT NULL DEFAULT '' COMMENT '提供方id',
   `information` text COMMENT '产品详情',
   `review_state` int(4) NOT NULL DEFAULT 0 COMMENT '审核状态',
   `review_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '审核时间',
   `creat_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`pk_id`),
   UNIQUE KEY (`did`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 目录表 --
CREATE TABLE `schema` (
   `pk_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
   `schema_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '目录id',
   `provider_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '提供方id',
   `product_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '产品id',
   `version` int(4) NOT NULL DEFAULT 0 COMMENT '版本号',
   `visible` int(4) NOT NULL DEFAULT 0 COMMENT '是否可见',
   `description` text COMMENT '描述',
   `usage` varchar(64) NOT NULL DEFAULT '' COMMENT '用途',
   `price` int(32) NOT NULL DEFAULT 0 COMMENT '价格',
   `tags` varchar(64) NOT NULL DEFAULT '' COMMENT '标签',
   `visitInfo` text  COMMENT '详细访问信息,格式为Json',
   `creat_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`pk_id`),
   UNIQUE KEY (`schema_id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;