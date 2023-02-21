-- 账户表 --
CREATE TABLE `user` (
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
   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`pk_id`),
   UNIQUE KEY (`user_name`),
   UNIQUE KEY (`did`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 机构证书表 --
CREATE TABLE `cert` (
   `pk_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
   `did` varchar(255) NOT NULL,
   `cert_type` int(4) null comment '证件类型',
   `cert_hash` VARCHAR(255) NOT NULL DEFAULT '' COMMENT '证件指纹',
   `cert_content` text COMMENT '证件详情',
   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
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
   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`pk_id`),
   UNIQUE KEY (`did`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 标签表 --
CREATE TABLE `tag` (
   `pk_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
   `tag` varchar(64) NOT NULL DEFAULT '' COMMENT '标签名',
   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`pk_id`),
   UNIQUE KEY (`tag`)
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
   `tag_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '标签ID',
   `type` int(4) NOT NULL DEFAULT 0 COMMENT '类型，json-0，xml-1，doc-2，pic-3...',
   `protocol` int(4) NOT NULL DEFAULT 0 COMMENT '类型，HTTP-0，HTTPS-1，SFTP-2...',
   `schema` text  COMMENT '数据schema,格式为Json',
   `condition` text  COMMENT '数据的查询条件定义',
   `uri` varchar(64) NOT NULL DEFAULT '' COMMENT '数据访问连接',
   `effect_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '生效时间',
   `expire_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '失效时间',
   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`pk_id`),
   UNIQUE KEY (`schema_id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;