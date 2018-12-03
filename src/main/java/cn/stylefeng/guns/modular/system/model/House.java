package cn.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.sql.Date;

/**
 * <p>
 * 房屋租赁
 * </p>
 *
 * @author corey
 * @since 2018-12-03
 */
@TableName("sys_house")
public class House extends Model<House> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("house_user")
    private String houseUser;
    @TableField("house_date")
    @JsonFormat( pattern = "yyyy-MM-dd" )
    private Date houseDate;
    @TableField("house_desc")
    private String houseDesc;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHouseUser() {
        return houseUser;
    }

    public void setHouseUser(String houseUser) {
        this.houseUser = houseUser;
    }

    public Date getHouseDate() {
        return houseDate;
    }

    public void setHouseDate(Date houseDate) {
        this.houseDate = houseDate;
    }

    public String getHouseDesc() {
        return houseDesc;
    }

    public void setHouseDesc(String houseDesc) {
        this.houseDesc = houseDesc;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "House{" +
        ", id=" + id +
        ", houseUser=" + houseUser +
        ", houseDate=" + houseDate +
        ", houseDesc=" + houseDesc +
        "}";
    }
}
