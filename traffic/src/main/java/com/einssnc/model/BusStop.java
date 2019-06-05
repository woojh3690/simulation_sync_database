package com.einssnc.model;
// Generated 2019. 6. 5 ���� 5:12:42 by Hibernate Tools 4.3.5.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * BusStop generated by hbm2java
 */
@Entity
@Table(name = "bus_stop", catalog = "simulation")
public class BusStop implements java.io.Serializable {

	private String nodeid;
	private CityCode cityCode;
	private String nodenm;
	private double lon;
	private double lat;

	public BusStop() {
	}

	public BusStop(String nodeid, CityCode cityCode, String nodenm, double lon, double lat) {
		this.nodeid = nodeid;
		this.cityCode = cityCode;
		this.nodenm = nodenm;
		this.lon = lon;
		this.lat = lat;
	}

	@Id

	@Column(name = "nodeid", unique = true, nullable = false, length = 15)
	public String getNodeid() {
		return this.nodeid;
	}

	public void setNodeid(String nodeid) {
		this.nodeid = nodeid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "citycode", nullable = false)
	public CityCode getCityCode() {
		return this.cityCode;
	}

	public void setCityCode(CityCode cityCode) {
		this.cityCode = cityCode;
	}

	@Column(name = "nodenm", nullable = false, length = 100)
	public String getNodenm() {
		return this.nodenm;
	}

	public void setNodenm(String nodenm) {
		this.nodenm = nodenm;
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

}
