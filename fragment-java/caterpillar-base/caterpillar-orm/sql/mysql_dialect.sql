

###############表操作###############
#采取查询比对只增不删的策略


#查询所有的表
select TABLE_NAME from information_schema.tables where table_schema='caterpillar' and table_type='BASE TABLE' and TABLE_NAME='t_app_user';


#删除表
drop table IF EXISTS t_app_user;

#创建表
create table if not EXISTS t_app_user(
	id BIGINT(32) PRIMARY key,
  username VARCHAR(32),
	num SMALLINT(4) UNSIGNED,
  blob1 blob(255),
	enum1 enum('1', '2', '33') DEFAULT '1',
	date1 date,
	datetime1 datetime,
	timestamp1 timestamp,
  time1 time,
  year1 year	
)ENGINE=INNODB;

#删除表
drop table if EXISTS t_app_aa;

#创建表带外键
create table if not EXISTS t_app_aa(
	id BIGINT(32) PRIMARY key,
	aa VARCHAR(32),
	userId BIGINT(32)
)ENGINE=INNODB;


#################字段操作##################
#采取查询比对只增不删的策略


#获取表的结构
select COLUMN_NAME, column_type, IS_NULLABLE  from information_schema.`COLUMNS` where TABLE_SCHEMA='caterpillar' and TABLE_NAME='t_app_user';

#添加字段
alter table t_app_aa add column bb VARCHAR(32);

#删除字段
alter table t_app_aa drop COLUMN bb;

#修改字段
#--change修改必须带列名
alter table t_app_aa change aa aaa CHAR(32) not null;

#--modify修改类型、约束
alter table t_app_aa modify aaa CHAR(32) not null;


#################外键操作##################
#采取查询比对只增不删的策略


#获取一张表的所有外键
select CONSTRAINT_NAME, COLUMN_NAME, REFERENCED_TABLE_NAME, REFERENCED_COLUMN_NAME from information_schema.KEY_COLUMN_USAGE where CONSTRAINT_SCHEMA='caterpillar' and table_name='t_app_aa' and REFERENCED_TABLE_NAME is not null and REFERENCED_COLUMN_NAME is not null;

#获取一张表的外键信息
select COLUMN_NAME, REFERENCED_TABLE_NAME, REFERENCED_COLUMN_NAME from information_schema.KEY_COLUMN_USAGE where CONSTRAINT_SCHEMA='caterpillar' and table_name='t_app_aa' and CONSTRAINT_NAME='fk_t_app_aa_t_app_user';

#删除外键
alter table t_app_aa drop foreign key fk_t_app_aa_t_app_user;

#添加外键
alter table t_app_aa add CONSTRAINT fk_t_app_aa_t_app_user foreign key (userId) references t_app_user(id) on update CASCADE on delete CASCADE;


#################主键操作##################
#采取查询修改的策略


#获取一张表的主键信息
select COLUMN_NAME from information_schema.KEY_COLUMN_USAGE where CONSTRAINT_SCHEMA='caterpillar' and table_name='t_app_aa' and CONSTRAINT_NAME='PRIMARY';

#删除主键
alter table t_app_aa drop PRIMARY key;

#添加主键
alter table t_app_aa add CONSTRAINT PRIMARY key (id, aa);


#################唯一键操作##################
#采取查询比对只增不删的策略


#获取一张表的unique key信息
select CONSTRAINT_NAME, COLUMN_NAME from information_schema.KEY_COLUMN_USAGE where CONSTRAINT_SCHEMA='caterpillar' and table_name='t_app_aa' and CONSTRAINT_NAME<>'PRIMARY' and REFERENCED_COLUMN_NAME is null and REFERENCED_TABLE_NAME is null;

#获取一个unique KEY
select COLUMN_NAME from information_schema.KEY_COLUMN_USAGE where CONSTRAINT_SCHEMA='caterpillar' and table_name='t_app_aa' and CONSTRAINT_NAME='uk_aa_bb';

#--添加unique key
ALTER TABLE t_app_aa add UNIQUE key uk_aa_bb (aa, bb);

#--删除unique KEY
ALTER TABLE t_app_aa DROP INDEX uk_aa_bb;



#######视图操作########


#查询所有的视图
#select * from information_schema.views where table_schema='caterpillar';
#select * from information_schema.tables where table_schema='caterpillar' and table_type='VIEW';
#视图采取每次删掉重新创建的策略


#删除视图
drop view if EXISTS t_app_user_view;

#创建视图
create view if not EXISTS t_app_user_view as select * from t_app_user;


#######索引操作########
#采取查询比对只增不删的策略


##普通索引

#--获取所有的索引
select COLUMN_NAME, SEQ_IN_INDEX from information_schema.STATISTICS where TABLE_SCHEMA='caterpillar' and index_name='in_aa_bb' ORDER BY SEQ_IN_INDEX; 

#--添加一个索引
alter table t_app_aa add INDEX in_aa_bb (aa, bb);

#--删除一个索引
alter table t_app_aa drop index in_aa_bb;

