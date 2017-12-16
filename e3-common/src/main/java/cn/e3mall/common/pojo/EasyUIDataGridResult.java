package cn.e3mall.common.pojo;

import java.io.Serializable;
import java.util.List;
/**
 * 返回DataGrid所需json数据的pojo
 * @author wking
 *
 */
public class EasyUIDataGridResult implements Serializable{

	private Long total;
	private List rows;
	
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	
	
}
