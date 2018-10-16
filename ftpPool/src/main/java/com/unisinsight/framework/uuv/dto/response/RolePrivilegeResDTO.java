package com.unisinsight.framework.uuv.dto.response;

/**
 * 数据响应对象
 *
 * @author DengXiangtian
 * @date 2018/9/8 10:53
 * @since 1.0
 */
public class RolePrivilegeResDTO {

    private Integer roleId;

    private Integer privilegeId;

    private Float displayOrder;

    public Integer getPrivilegeId() {
        return privilegeId;
    }

    public void setPrivilegeId(Integer privilegeId) {
        this.privilegeId = privilegeId;
    }

    public Float getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Float displayOrder) {
        this.displayOrder = displayOrder;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
