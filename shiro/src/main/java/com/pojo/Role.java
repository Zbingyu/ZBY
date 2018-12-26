package com.pojo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;

public class Role implements Serializable {

	@TableId
	private Integer id;
	private String name;

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

}
