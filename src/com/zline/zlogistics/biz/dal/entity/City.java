package com.zline.zlogistics.biz.dal.entity;

public class City extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private Long cityId;
	private String cityName;
	private String cityCode;
	public Long getCityId() {
		return cityId;
	}
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
}
