package com.cqx.excel.model;

public class Bsp {
    private Integer cityId;

    private String province;

    private Integer provinceNum;

    private String name;

    private Boolean availableFlag;

    private String code;

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public Integer getProvinceNum() {
        return provinceNum;
    }

    public void setProvinceNum(Integer provinceNum) {
        this.provinceNum = provinceNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Boolean getAvailableFlag() {
        return availableFlag;
    }

    public void setAvailableFlag(Boolean availableFlag) {
        this.availableFlag = availableFlag;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }
}