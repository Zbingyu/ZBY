package com.pojo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;

public class Power implements Serializable {
	
	@TableId
	private Integer id;
	private String name;
	private Integer pid;
	private String url;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
