-- 账户表 --
 CREATE TABLE `t_account_info` (
        `pk_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
        `username` varchar(255) NOT NULL DEFAULT '' COMMENT '账号',
        `did` varchar(255) NOT NULL DEFAULT '' COMMENT 'DID',
        `account_type` int(4) NOT NULL DEFAULT 0 COMMENT '用户类型',
        `private_key` varchar(66) NOT NULL DEFAULT '' COMMENT '私钥',
        `salt` varchar(255) NOT NULL,
        `pwdhash` varchar(255) NOT NULL,
        `status` int(4) NOT NULL DEFAULT 0 COMMENT '账户状态',
        `review_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '审核时间',
        `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
        `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
        PRIMARY KEY (`pk_id`),
        UNIQUE KEY (`did`),
        UNIQUE KEY(`username`)
        ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 个人用户表 --
 CREATE TABLE `t_person_info` (
   `pk_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
   `person_id` varchar(255) NOT NULL DEFAULT '' COMMENT 'DID',
   `person_name` varchar(32) NOT NULL DEFAULT '' COMMENT '姓名',
   `person_contact` varchar(32) NOT NULL DEFAULT '' COMMENT '个人联系方式',
   `person_email` varchar(128) NOT NULL DEFAULT '' COMMENT '个人邮箱',
   `person_cert_type` int(4) NOT NULL DEFAULT 0 comment '个人证件类型',
   `person_cert_no` varchar(128) NOT NULL DEFAULT '' COMMENT '个人证件号',
   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`pk_id`),
   UNIQUE KEY (`person_id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 机构表 --
 CREATE TABLE `t_company_info` (
   `pk_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
   `company_id` varchar(255) NOT NULL DEFAULT '' COMMENT 'DID',
   `company_name` varchar(255) NOT NULL COMMENT '公司名称',
   `company_cert_type` int(4) NOT NULL DEFAULT 0 comment '法人证件类型',
   `company_cert_content` text COMMENT '公司证件内容',
   `company_contact` varchar(32) NOT NULL DEFAULT '' COMMENT '公司联系方式',
   `company_image` varchar(255) NOT NULL DEFAULT '' COMMENT '证件影像',
   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`pk_id`),
   UNIQUE KEY (`company_id`),
   UNIQUE KEY (`company_name`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 产品表 --
 CREATE TABLE `t_product_info` (
   `pk_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
   `product_id` varchar(255) NOT NULL DEFAULT '' COMMENT '产品id',
   `product_name` varchar(255) NOT NULL DEFAULT '' COMMENT '产品名称',
   `provider_id` varchar(255) NOT NULL DEFAULT '' COMMENT '提供方id',
   `product_desc` text COMMENT '产品详情',
   `status` int(4) NOT NULL DEFAULT 0 COMMENT '审核状态',
   `review_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '审核时间',
   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`pk_id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 标签表 --
 CREATE TABLE `t_tag_info` (
   `pk_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
   `tag_name` varchar(64) NOT NULL DEFAULT '' COMMENT '标签名',
   `heat` int(32) NOT NULL DEFAULT 0 COMMENT '热度',
   `schema_id_list` varchar(255) NOT NULL DEFAULT '' COMMENT 'schemaId列表',
   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`pk_id`),
   UNIQUE KEY (`tag_name`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据目录表 --
 CREATE TABLE `t_data_schema_info` (
   `pk_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
   `data_schema_id` varchar(255) NOT NULL DEFAULT '' COMMENT '数据目录id',
   `data_schema_name` varchar(255) NOT NULL DEFAULT '' COMMENT '数据目录名称',
   `tag_id` varchar(64) NOT NULL DEFAULT '' COMMENT '标签',
   `provider_id` varchar(255) NOT NULL DEFAULT 0 COMMENT '提供方id',
   `product_id` varchar(255) NOT NULL DEFAULT '' COMMENT '产品id',
   `version` int(4) NOT NULL DEFAULT 0 COMMENT '版本号',
   `visible` int(4) NOT NULL DEFAULT 0 COMMENT '是否可见',
   `data_schema_desc` text COMMENT '描述',
   `data_schema_usage` varchar(64) NOT NULL DEFAULT '' COMMENT '用途',
   `price` int(32) NOT NULL DEFAULT 0 COMMENT '价格',
   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`pk_id`),
   UNIQUE KEY (`schema_id`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 数据目录访问详情--
 CREATE TABLE `t_data_schema_access_info` (
    `pk_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `data_schema_id` varchar(255) NOT NULL DEFAULT '' COMMENT '目录id',
    `data_format` int(4) NOT NULL DEFAULT 0 COMMENT '类型，json-0，xml-1，doc-2，pic-3...',
    `data_protocol` int(4) NOT NULL DEFAULT 0 COMMENT '类型，HTTP-0，HTTPS-1，SFTP-2...',
--    `schema` text  COMMENT '数据schema,格式为Json',
    `access_condition` text  COMMENT '数据的查询条件定义',
    `uri` varchar(64) NOT NULL DEFAULT '' COMMENT '数据访问连接',
    `effect_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '生效时间',
    `expire_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '失效时间',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`pk_id`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--用户token--
  CREATE TABLE `t_session_info` (
    `pk_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `token` varchar(255) NOT NULL DEFAULT '' COMMENT 'token',
    `did`  varchar(255) NOT NULL DEFAULT '' COMMENT 'did',
    `expiredAt` timestamp NOT NULL COMMENT '过期时间',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`pk_id`),
    UNIQUE KEY (`token`),
    UNIQUE KEY (`did`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;