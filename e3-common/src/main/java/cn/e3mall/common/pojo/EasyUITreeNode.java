package cn.e3mall.common.pojo;

import java.io.Serializable;
/**
 * 封装EasyUI Tree所需的json
 * @author wking
 *
 */
public class EasyUITreeNode implements Serializable{

	private Long id;
	private String text;
	private String state;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
}
