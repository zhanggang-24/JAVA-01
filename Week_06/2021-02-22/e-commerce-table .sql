
-- 用户账户表
CREATE TABLE IF NOT EXISTS t_user_account(
user_id VARCHAR(64) NOT NULL COMMENT '用户id',
username VARCHAR(20) NOT NULL DEFAULT '' COMMENT '用户名',
password VARCHAR(64) NOT NULL DEFAULT '' COMMENT '密码',
avatar VARCHAR(100) NOT NULL DEFAULT '' COMMENT '头像路径',
nickname VARCHAR(20) NOT NULL DEFAULT '' COMMENT '昵称',
gender TINYINT NOT NULL DEFAULT 0 COMMENT '性别 1=男，2=女',
birthday VARCHAR(10) NOT NULL DEFAULT '0000-00-00' COMMENT '生日',
state TINYINT NOT NULL DEFAULT 0  COMMENT '状态 0=使用中，1=注销',
create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
PRIMARY KEY(user_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT '用户账户表';


-- 用户地址表
CREATE TABLE IF NOT EXISTS t_user_addr(
addr_id VARCHAR(64) NOT NULL COMMENT '地址id',
user_id VARCHAR(64) NOT NULL COMMENT '用户id',
name VARCHAR(20) NOT NULL DEFAULT '' COMMENT '收货人姓名',
phone_number VARCHAR(11) NOT NULL DEFAULT '' COMMENT '手机号',
location VARCHAR(64) NOT NULL DEFAULT '' COMMENT '所在地区',
addr_detail VARCHAR(100) NOT NULL DEFAULT '' COMMENT '地址详情',
state TINYINT NOT NULL DEFAULT 0  COMMENT '状态 0=使用中，1=注销',
create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
PRIMARY KEY(addr_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT '用户地址表';


-- 用户订单表
CREATE TABLE IF NOT EXISTS t_user_order(
order_id VARCHAR(64) NOT NULL COMMENT '订单id',
user_id  VARCHAR(64) NOT NULL COMMENT '用户id',
addr_id  VARCHAR(64) NOT NULL COMMENT '收货地址id',
order_type TINYINT NOT NULL DEFAULT 0 COMMENT '订单类型 1=代付款，2=待收货，3=已完成，4=已取消，5=已删除',
order_no VARCHAR(100) NOT NULL DEFAULT '' COMMENT '订单号',
order_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '下单时间',
pay_method TINYINT NOT NULL DEFAULT 0 COMMENT '支付方式 1=支付宝，2=微信',
total_amount DECIMAL(16,2) NOT NULL DEFAULT 0  COMMENT '商品总额',
create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
PRIMARY KEY(order_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT '用户订单表';


-- 订单商品表
CREATE TABLE IF NOT EXISTS t_order_goods(
order_goods_id VARCHAR(64) NOT NULL COMMENT 'id',
order_id VARCHAR(64) NOT NULL COMMENT '订单id',
goods_id VARCHAR(64) NOT NULL COMMENT '商品id',
goods_snapshot_id VARCHAR(64) NOT NULL COMMENT '商品快照id',
create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
PRIMARY KEY(order_goods_id)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT '订单商品表';


-- 商品快照表
CREATE TABLE IF NOT EXISTS t_goods_snapshot(
goods_snapshot_id VARCHAR(64) NOT NULL COMMENT '商品快照id',
goods_id VARCHAR(64) NOT NULL COMMENT '商品id',
goods_no VARCHAR(100) NOT NULL DEFAULT '' COMMENT '商品编码',
name VARCHAR(200) NOT NULL COMMENT '商品名称',
price DECIMAL(10,2) NOT NULL DEFAULT 0 COMMENT '商品价格',
goods_category_id VARCHAR(64) NOT NULL COMMENT '商品类别id',
create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
PRIMARY KEY(goods_snapshot_id)
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT '商品快照表';


-- 商品详情表
CREATE TABLE IF NOT EXISTS t_goods_details(
goods_id VARCHAR(64) NOT NULL COMMENT '商品id',
goods_no VARCHAR(100) NOT NULL DEFAULT '' COMMENT '商品编码',
name VARCHAR(200) NOT NULL COMMENT '商品名称',
price DECIMAL(10,2) NOT NULL DEFAULT 0 COMMENT '商品价格',
goods_category_id VARCHAR(64) NOT NULL COMMENT '商品类别id',
create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
PRIMARY KEY(goods_id)
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT '商品详情表';


-- 商品类别表
CREATE TABLE IF NOT EXISTS t_goods_category(
goods_category_id VARCHAR(64) NOT NULL COMMENT '商品类别id',
category_no VARCHAR(100) NOT NULL DEFAULT '' COMMENT '类别编码',
category_nanme VARCHAR(200) NOT NULL DEFAULT '' COMMENT '类别名称',
father_id VARCHAR(64) NOT NULL COMMENT '父类型id',
state TINYINT NOT NULL DEFAULT 0  COMMENT '状态 0=使用中，1=注销',
create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
update_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
PRIMARY KEY(goods_category_id)
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT '商品类别表';



