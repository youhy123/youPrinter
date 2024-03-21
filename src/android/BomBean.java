package org.apache.cordova.youprinter;

import java.io.Serializable;

/**
 * Created by hangeng on 2021/2/9.
 */

public class BomBean implements Serializable {
    private Long id;
    //WO编号
    private String productionOrderNumber;


    //图番编号
    private String productNumber;
    //图番代码
    private String productName;
    //物料编号
    private String materialNumber;
    //物料代码
    private String materialName;

    public String getMaterialDesc() {
        return materialDesc;
    }

    public void setMaterialDesc(String materialDesc) {
        this.materialDesc = materialDesc;
    }

    private String materialDesc;

    //物料规格
    private String specification;
    //图番版本
    private String versionNumber;

    //取付系数
    private String partsQty;
    //订单数量
    private String orderQuantity;

    //需求数量
    private String needsQuantity;
    //实际数量
    private String actualQuantity;
//材料批次
    private String batchNumber;
    private String usageCoefficient;

    private String processNumber;
    private String stockNumber;
    //领料时间
    private String pickingTime;

    //样品标识；0：非样品，1：样品
    private int isSample = 0;
    //BOM物料标识；0：BOM物料，1：非BOM物料
    private String noBom = "0";
    //BOM顺序
    private int seq;

    //BOM图番名称
    private String productDesc;

    //图番版本
    private String materialVersionNumber;
    //理论数
    private String theoryQuantity;
    //单位
    private String unitDict;

    //卷宽
    private String rollWidth;

    //截断宽
    private String cutSizeX;
    //截断长
    private String cutSizeY;

    //是否显示 20221129 add by lzw
    private String  isShow;

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    public String getCutSize() {
        return cutSize;
    }

    public void setCutSize(String cutSize) {
        this.cutSize = cutSize;
    }

    private String cutSize;//20220802 add by lzw

    private String materialCategoryCode;

    public String getStockUnitDict() {
        return stockUnitDict;
    }

    public void setStockUnitDict(String stockUnitDict) {
        this.stockUnitDict = stockUnitDict;
    }

    private String stockUnitDict;

    public String getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }

    public String getMaterialCategoryCode() {
        return materialCategoryCode;
    }

    public void setMaterialCategoryCode(String materialCategoryCode) {
        this.materialCategoryCode = materialCategoryCode;
    }

    public String getMaterialCategoryName() {
        return materialCategoryName;
    }

    public void setMaterialCategoryName(String materialCategoryName) {
        this.materialCategoryName = materialCategoryName;
    }

    private String materialCategoryName;





    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    private boolean isSelected = false;

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    private boolean isNew = false;

    public boolean isChanged() {
        return isChanged;
    }

    public void setChanged(boolean changed) {
        isChanged = changed;
    }

    private boolean isChanged = false;

    public String getProductionOrderNumber() {
        return productionOrderNumber;
    }

    public void setProductionOrderNumber(String productionOrderNumber) {
        this.productionOrderNumber = productionOrderNumber;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getPartsQty() {
        return partsQty;
    }

    public void setPartsQty(String partsQty) {
        this.partsQty = partsQty;
    }

    public String getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(String orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public String getNeedsQuantity() {
        return needsQuantity;
    }

    public void setNeedsQuantity(String needsQuantity) {
        this.needsQuantity = needsQuantity;
    }

    public String getActualQuantity() {
        return actualQuantity;
    }

    public void setActualQuantity(String actualQuantity) {
        this.actualQuantity = actualQuantity;
    }

    public String getUsageCoefficient() {
        return usageCoefficient;
    }

    public void setUsageCoefficient(String usageCoefficient) {
        this.usageCoefficient = usageCoefficient;
    }

    public String getProcessNumber() {
        return processNumber;
    }

    public void setProcessNumber(String processNumber) {
        this.processNumber = processNumber;
    }

    public String getStockNumber() {
        return stockNumber;
    }

    public void setStockNumber(String stockNumber) {
        this.stockNumber = stockNumber;
    }

    public String getPickingTime() {
        return pickingTime;
    }

    public void setPickingTime(String pickingTime) {
        this.pickingTime = pickingTime;
    }

    public String getNoBom() {
        return noBom;
    }

    public void setNoBom(String noBom) {
        this.noBom = noBom;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }
    public String getRollWidth() {
        return rollWidth;
    }

    public void setRollWidth(String rollWidth) {
        this.rollWidth = rollWidth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getTheoryQuantity() {
        return theoryQuantity;
    }

    public void setTheoryQuantity(String theoryQuantity) {
        this.theoryQuantity = theoryQuantity;
    }

    public String getUnitDict() {
        return unitDict;
    }

    public void setUnitDict(String unitDict) {
        this.unitDict = unitDict;
    }

    public String getCutSizeX() {
        return cutSizeX;
    }

    public void setCutSizeX(String cutSizeX) {
        this.cutSizeX = cutSizeX;
    }

    public String getCutSizeY() {
        return cutSizeY;
    }

    public void setCutSizeY(String cutSizeY) {
        this.cutSizeY = cutSizeY;
    }

    public int getIsSample() {
        return isSample;
    }

    public void setIsSample(int isSample) {
        this.isSample = isSample;
    }

    public String getMaterialVersionNumber() {
        return materialVersionNumber;
    }

    public void setMaterialVersionNumber(String materialVersionNumber) {
        this.materialVersionNumber = materialVersionNumber;
    }



//     "id": 5045422945905139815,
//             "productionOrderId": null,
//             "productionOrderNumber": "WOA00141188",
//             "productNumber": "0000014893",
//             "productName": "D3B04724C",
//             "productVersionNumber": "R01",
//    materialVersionNumber
//             "materialNumber": "0000006735",
//             "productDesc": "SBA001000/00.100/1000.0/000000",
//             "specification": null,
//             "materialVersionNumber": "R01",
//             "partsQty": 92.6,
//             "orderQuantity": 2000,
//             "theoryQuantity": 185.2,
//             "theoryQuantity": 185.2,
//             "actualQuantity": null,
//             "usageCoefficient": null,
//             "processNumber": "010",
//             "stockNumber": null,
//             "pickingTime": "2020-11-23 00:00:00",
//             "composeQuantity": null,
//             "baseQuantity": null,
//             "unitDict": "M",
//             "wasteRate": null,
//             "materialResourceDict": null,
//             "crtTime": null,
//             "crtName": null,
//             "crtHost": null,
//             "updTime": null,
//             "crtUserId": null,
//             "updUserId": null,
//             "updName": null,
//             "updHost": null,
//             "owner": null,
//             "delFlag": null,
//             "versionId": null,
//             "productVersionNumber": "R01",
//             "versionName": null,
//             "batchNumber": null,
//             "isSample": "0",
//             "noBom": "1",
//             "seq": 2
}
