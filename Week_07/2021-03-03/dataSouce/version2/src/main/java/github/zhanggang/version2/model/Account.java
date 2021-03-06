package github.zhanggang.version2.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Table(name = "account")
//@TableName("account")
@ToString
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @Column(name = "name")
//    @TableField("name")
    private String name;

    @Column(name = "phone")
//    @TableField("phone")
    private String phone;

    @Column(name = "money")
    private BigDecimal money;
}
