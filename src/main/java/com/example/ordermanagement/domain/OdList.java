package com.example.ordermanagement.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName od_list
 */
@TableName(value ="od_list")
@Data
public class OdList implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String equipmentName;

    /**
     * 
     */
    private String supplyName;

    /**
     * 
     */
    private Integer counts;

    /**
     * 
     */
    private Integer isDelete;

    /**
     * 
     */
    private String belongWho;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        OdList other = (OdList) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getEquipmentName() == null ? other.getEquipmentName() == null : this.getEquipmentName().equals(other.getEquipmentName()))
            && (this.getSupplyName() == null ? other.getSupplyName() == null : this.getSupplyName().equals(other.getSupplyName()))
            && (this.getCounts() == null ? other.getCounts() == null : this.getCounts().equals(other.getCounts()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
            && (this.getBelongWho() == null ? other.getBelongWho() == null : this.getBelongWho().equals(other.getBelongWho()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getEquipmentName() == null) ? 0 : getEquipmentName().hashCode());
        result = prime * result + ((getSupplyName() == null) ? 0 : getSupplyName().hashCode());
        result = prime * result + ((getCounts() == null) ? 0 : getCounts().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getBelongWho() == null) ? 0 : getBelongWho().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", equipmentName=").append(equipmentName);
        sb.append(", supplyName=").append(supplyName);
        sb.append(", counts=").append(counts);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", belongWho=").append(belongWho);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}