package com.muma.entity.base;

import java.io.Serializable;


public class IdEntity implements Serializable {

	private static final long serialVersionUID = 2718395762726794490L;

	/**
	 * 主键
	 */
	private Integer id;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "IdEntity{" +
				"id=" + id +
				'}';
	}
}
