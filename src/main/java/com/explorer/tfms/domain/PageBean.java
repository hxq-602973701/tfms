package com.explorer.tfms.domain;
import java.util.List;
@SuppressWarnings("serial")
public class PageBean<T> implements java.io.Serializable{
	/**
	 * 每页的条数
	 */
	private int pageSize = 20;
	/**
	 * 当前页
	 */
	private int currentPage = 1;
	/**
	 * 总页数
	 */
	private int pageCount;
	/**
	 * 总记录数
	 */
	private Long recordCount;
	/**
	 * 记录
	 */
	private List<T> records;
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public Long getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(Long recordCount) {
		this.recordCount = recordCount;
	}
	public List<T> getRecords() {
		return records;
	}
	public void setRecords(List<T> records) {
		this.records = records;
	}
}