package com.einssnc.model;
// Generated 2019. 6. 7 ���� 9:32:56 by Hibernate Tools 4.3.5.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * JejuBusStop generated by hbm2java
 */
@Entity
@Table(name = "jeju_bus_stop", catalog = "simulation")
public class JejuBusStop implements java.io.Serializable {

	private int id;
	private String name;
	private double lon;
	private double lat;
	private String remark;

	public JejuBusStop() {
	}

	public JejuBusStop(int id, String name, double lon, double lat) {
		this.id = id;
		this.name = name;
		this.lon = lon;
		this.lat = lat;
	}

	public JejuBusStop(int id, String name, double lon, double lat, String remark) {
		this.id = id;
		this.name = name;
		this.lon = lon;
		this.lat = lat;
		this.remark = remark;
	}

	@Id

	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "lon", nullable = false, precision = 22, scale = 0)
	public double getLon() {
		return this.lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	@Column(name = "lat", nullable = false, precision = 22, scale = 0)
	public double getLat() {
		return this.lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	@Column(name = "remark", length = 65535)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
