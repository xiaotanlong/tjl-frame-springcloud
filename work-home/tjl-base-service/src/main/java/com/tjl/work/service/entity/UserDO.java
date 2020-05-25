package com.tjl.work.service.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.tjl.base.dao.mybatisplus.BaseModel;
import lombok.Data;
import lombok.experimental.Accessors;
import java.time.LocalDateTime;

/**
 * @author tjl
 * @since 2020-05-25
 */
@Data
@Accessors(chain = true)
@TableName("sys_user")
public class UserDO extends BaseModel {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 所属角色id
     */
    private Integer roleId;

    /**
     * 所属角色 (冗余设计)
     */
    private String roleName;

    /**
     * 系統用户名
     */
    private String username;

    /**
     * 系统用户真实姓名
     */
    private String realName;

    /**
     * 系统用户密码
     */
    private String password;

    /**
     * 系统用户手机号码
     */
    private String phone;

    /**
     * 帐号是否有效 0 无效 1有效
     */
    private String valid;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    @TableField(exist = false)
    private String testColumn;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(exist = false)
    private LocalDateTime endTime;


}
