-- 账户表 --
 CREATE TABLE `account` (
        `pk_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
        `username` varchar(255) NOT NULL DEFAULT '' COMMENT '账号',
        `account_type` int(4) NOT NULL DEFAULT 0 COMMENT '用户类型',
        `did` varchar(255) NOT NULL DEFAULT '' COMMENT 'DID',
        `private_key` varchar(66) NOT NULL DEFAULT '' COMMENT '私钥',
        `salt` varchar(255) NOT NULL,
        `pwdhash` varchar(255) NOT NULL,
        `review_state` int(4) NOT NULL DEFAULT 0 COMMENT '审核状态',
        `review_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '审核时间',
        `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
        `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
        PRIMARY KEY (`pk_id`),
        UNIQUE KEY (`did`)
        ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
-- 用户表 --
CREATE TABLE `user_info` (
   `pk_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
   `user_id` varchar(255) NOT NULL DEFAULT '' COMMENT 'DID',
   `name` varchar(32) NOT NULL DEFAULT '' COMMENT '姓名',
   `contact` varchar(32) NOT NULL DEFAULT '' COMMENT '联系方式',
   `location` varchar(128) NOT NULL DEFAULT '' COMMENT '联系地址',
   `email` varchar(128) NOT NULL DEFAULT '' COMMENT '邮箱',
   `cert_type` int(4) NOT NULL DEFAULT 0 comment '证件类型',
   `cert_num` varchar(128) NOT NULL DEFAULT '' COMMENT '证件号',
   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`pk_id`),
   UNIQUE KEY (`user_id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 机构表 --
CREATE TABLE `org_info` (
   `pk_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
   `org_id` varchar(255) NOT NULL DEFAULT '' COMMENT 'DID',
   `org_name` varchar(255) NOT NULL DEFAULT '' COMMENT '机构名称',
   `cert_type` int(4) NOT NULL DEFAULT 0 comment '证件类型',
   `cert_content` text COMMENT '证件内容',
   `contact` varchar(32) NOT NULL DEFAULT '' COMMENT '联系方式',
   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`pk_id`),
   UNIQUE KEY (`org_id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 产品表 --
CREATE TABLE `product` (
   `pk_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
   `product_id` varchar(255) NOT NULL DEFAULT '' COMMENT '产品id',
   `product_name` varchar(255) NOT NULL DEFAULT '' COMMENT '产品名称',
   `provider_id` varchar(255) NOT NULL DEFAULT '' COMMENT '提供方id',
   `information` text COMMENT '产品详情',
   `review_state` int(4) NOT NULL DEFAULT 0 COMMENT '审核状态',
   `review_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '审核时间',
   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`pk_id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 标签表 --
CREATE TABLE `tag` (
   `pk_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
   `tag` varchar(64) NOT NULL DEFAULT '' COMMENT '标签名',
   `heat` int(32) NOT NULL DEFAULT 0 COMMENT '热度',
   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`pk_id`),
   UNIQUE KEY (`tag`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 目录表 --
CREATE TABLE `schema_info` (
   `pk_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
   `schema_id` varchar(255) NOT NULL DEFAULT '' COMMENT '目录id',
   `tag_id` bigint(20) NOT NULL DEFAULT 0 COMMENT 'tagId',
   `provider_id` varchar(255) NOT NULL DEFAULT 0 COMMENT '提供方id',
   `product_id` varchar(255) NOT NULL DEFAULT '' COMMENT '产品id',
   `product_name` varchar(255) NOT NULL DEFAULT '' COMMENT '产品名称',
   `provider_name` varchar(255) NOT NULL DEFAULT '' COMMENT '机构名称',
   `version` int(4) NOT NULL DEFAULT 0 COMMENT '版本号',
   `visible` int(4) NOT NULL DEFAULT 0 COMMENT '是否可见',
   `description` text COMMENT '描述',
   `usage` varchar(64) NOT NULL DEFAULT '' COMMENT '用途',
   `price` int(32) NOT NULL DEFAULT 0 COMMENT '价格',
   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`pk_id`),
   UNIQUE KEY (`schema_id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 CREATE TABLE `visit_info` (
    `pk_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `schema_id` varchar(255) NOT NULL DEFAULT '' COMMENT '目录id',
    `type` int(4) NOT NULL DEFAULT 0 COMMENT '类型，json-0，xml-1，doc-2，pic-3...',
    `protocol` int(4) NOT NULL DEFAULT 0 COMMENT '类型，HTTP-0，HTTPS-1，SFTP-2...',
    `schema` text  COMMENT '数据schema,格式为Json',
    `condition` text  COMMENT '数据的查询条件定义',
    `uri` varchar(64) NOT NULL DEFAULT '' COMMENT '数据访问连接',
    `effect_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '生效时间',
    `expire_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '失效时间',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`pk_id`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;