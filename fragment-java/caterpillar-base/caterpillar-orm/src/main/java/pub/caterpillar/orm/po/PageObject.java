package pub.caterpillar.orm.po;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class PageObject implements Serializable {
	private static final long serialVersionUID = 1L;
	private int pageSize = 50;
	private int pageNo = 1;
	private int rowStart = 0;
	private Map<String, String> orderMap = new LinkedHashMap<String, String>();
	private transient int totalRows = 0;
	private boolean haveRepeat = false;
	private transient int pageCount = 0;
	
	public PageObject(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}


	public int getRowStart() {
		return this.rowStart;
	}


	public Map<String,String> getOrderMap() {
		return this.orderMap;
	}

	public boolean getHaveRepeat() {
		return haveRepeat;
	}

	public void setHaveRepeat(boolean haveRepeat) {
		this.haveRepeat = haveRepeat;
	}
}
