package com.zline.zlogistics.web.common;

import java.util.List;


/**
 * 使用说明:如果有数据,一切ok
 * 如果没有数据: w
 * 			  1:DataTableReturnObject<LogisticsOrder> returnObject   returnObject 不可以为null
 *			  2: data 不可以为null 也就是 data至少要 等于 一个空的list
 *			 
 */
public class DataTableReturnObject<T> {
	private int draw; // Client request times
    private int recordsTotal; // Total records number without conditions
    private int recordsFiltered; // Total records number with conditions
    private List<T> data; // The data we should display on the page
	public int getDraw() {
		return draw;
	}
	public void setDraw(int draw) {
		this.draw = draw;
	}
	public int getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public int getRecordsFiltered() {
		return recordsFiltered;
	}
	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
    
}
