package com.tjl.work.core.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 功能描述 用户信息传递包装类
 * @author tjl
 * @Type UserVO
 * @date 2020/5/28 11:48
 * @Version 1.0
 */
public class UserVO implements Serializable {
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

    /**
     * 创建时间
     */
    private Date loginDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", username='" + username + '\'' +
                ", realName='" + realName + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", valid='" + valid + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public String getToken(){
        return String.valueOf(this.toString().hashCode());
    }
}
