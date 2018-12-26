package com.pojo;

import java.io.Serializable;


public class Role_Power implements Serializable {
	private Integer rid;
	private Integer pid;

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

}
