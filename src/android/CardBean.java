package org.apache.cordova.youprinter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hangeng on 2021/3/24.
 */

public class CardBean implements Serializable {
    private List<BomBean> consumeMaterialList;
    private String flowCardNumber;
    private String productionOrderNumber;
    private String productName;

    public String getPlanQuantity() {
        return planQuantity;
    }

    public void setPlanQuantity(String planQuantity) {
        this.planQuantity = planQuantity;
    }

    //订单总数量 add by lzw 20220805
    private String planQuantity;

    public String getProductVersion() {
        return productVersion;
    }

    public void setProductVersion(String productVersion) {
        this.productVersion = productVersion;
    }

    private String productVersion;

    public List<BomBean> getConsumeMaterialList() {
        return consumeMaterialList;
    }

    public void setConsumeMaterialList(List<BomBean> consumeMaterialList) {
        this.consumeMaterialList = consumeMaterialList;
    }

    public String getFlowCardNumber() {
        return flowCardNumber;
    }

    public void setFlowCardNumber(String flowCardNumber) {
        this.flowCardNumber = flowCardNumber;
    }

    public String getProductionOrderNumber() {
        return productionOrderNumber;
    }

    public void setProductionOrderNumber(String productionOrderNumber) {
        this.productionOrderNumber = productionOrderNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getOperationNumber() {
        return operationNumber;
    }

    public void setOperationNumber(String operationNumber) {
        this.operationNumber = operationNumber;
    }

    public String getActualQuantity() {
        return actualQuantity;
    }

    public void setActualQuantity(String actualQuantity) {
        this.actualQuantity = actualQuantity;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    private String operationNumber;
    private String actualQuantity;
    private String operator;

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    private String operateTime;

    public String getEquipmentNumber() {
        return equipmentNumber;
    }

    public void setEquipmentNumber(String equipmentNumber) {
        this.equipmentNumber = equipmentNumber;
    }

    private String equipmentNumber;

    public String getWenyan() {
        return wenyan;
    }

    public void setWenyan(String wenyan) {
        this.wenyan = wenyan;
    }

    //订单总数量 add by lzw 20220805
    private String wenyan;
}
