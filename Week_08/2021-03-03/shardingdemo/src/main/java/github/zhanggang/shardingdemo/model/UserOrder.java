package github.zhanggang.shardingdemo.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.print.attribute.standard.MediaSize;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;

@Data
@Table(name = "t_user_order")
@ToString
public class UserOrder {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "order_id")
    private Long orderId;

//    @Column(name = "user_id")
    private Long userId;

//    @Column(name = "addr_id")
    private Long addrId;

//    @Column(name = "order_type")
    private Integer orderType;

//    @Column(name = "order_no")
    private String orderNo;

//    @Column(name = "order_time")
    private Timestamp orderTime;

//    @Column(name = "pay_method")
    private Integer payMethod;

//    @Column(name = "total_amount")
    private BigDecimal totalAmount;

//    @Column(name = "create_time")
    private Timestamp createTime;

//    @Column(name = "update_time")
    private Timestamp updateTime;
}
