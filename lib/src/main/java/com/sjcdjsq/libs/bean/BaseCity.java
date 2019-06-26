package com.sjcdjsq.libs.bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BaseCity implements Serializable {
	private String areaId;
	private String areaName;
	private String latitude;
	private String custommoduleList;
	private boolean isSelect;
	private String longitude;
	private String isArea;
	private String Areas;
	public String getAreas() {
		return Areas;
	}

	public void setAreas(String areas) {
		Areas = areas;
	}

	public String getIsArea() {
		return isArea;
	}

	public void setIsArea(String isArea) {
		this.isArea = isArea;
	}

	public boolean isSelect() {
		return isSelect;
	}

	public void setSelect(boolean isSelect) {
		this.isSelect = isSelect;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getCustommoduleList() {
		return custommoduleList;
	}

	public void setCustommoduleList(String custommoduleList) {
		this.custommoduleList = custommoduleList;
	}

	@Override
	public String toString() {
		return "BaseCity [areaId=" + areaId + ", areaName=" + areaName
				+ ", latitude=" + latitude + ", isSelect=" + isSelect
				+ ", longitude=" + longitude + ", isArea=" + isArea
				+ ", Areas=" + Areas + "]";
	}
	
	

	
}
