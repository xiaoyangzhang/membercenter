CREATE TABLE `draft` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `draft_name` varchar(50) DEFAULT NULL COMMENT '草稿名称',
  `user_id` bigint(20) DEFAULT NULL COMMENT '对应的员工id',
  `main_type` int(11) DEFAULT NULL COMMENT '主类型分类',
  `sub_type` int(11) DEFAULT NULL COMMENT '子类型分类',
  `data` text COMMENT 'JSON草稿数据',
  `gmt_created` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  `domain_id` int(11) DEFAULT NULL COMMENT '区分系统的id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=253 DEFAULT CHARSET=utf8 COMMENT='草稿信息表'

INSERT INTO `membercenter`.`ha_menu`
(
`name`,
`url`,
`req_type`,
`type`,
`level`,
`leaf`,
`parent_id`,
`domain`,
`gmt_created`,
`gmt_modified`,
`status`)
VALUES
(
'商品草稿箱',
'/draft/list',
1,
1,
2,
1,
120,
1200,
current_time(),
current_time(),
1);

INSERT INTO `membercenter`.`ha_menu`
(
`name`,
`url`,
`req_type`,
`type`,
`level`,
`leaf`,
`parent_id`,
`domain`,
`gmt_created`,
`gmt_modified`,
`status`)
VALUES
(
'编辑草稿箱',
'/draft/edit/{id}/{mainType}/{subType}',
1,
1,
2,
1,
120,
1200,
current_time(),
current_time(),
1);