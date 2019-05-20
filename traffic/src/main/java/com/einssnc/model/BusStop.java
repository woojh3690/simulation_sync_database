package com.einssnc.model;
// Generated 2019. 5. 20 ���� 4:14:02 by Hibernate Tools 5.2.12.Final

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

	private String nodeno;
	private CityCode cityCode;
	private String nodeid;
	private String nodenm;
	private int gpslati;
	private int gpslong;

	public BusStop() {
	}

	public BusStop(String nodeno, CityCode cityCode, String nodeid, String nodenm, int gpslati, int gpslong) {
		this.nodeno = nodeno;
		this.cityCode = cityCode;
		this.nodeid = nodeid;
		this.nodenm = nodenm;
		this.gpslati = gpslati;
		this.gpslong = gpslong;
	}

	@Id

	@Column(name = "nodeno", unique = true, nullable = false, length = 30)
	public String getNodeno() {
		return this.nodeno;
	}

	public void setNodeno(String nodeno) {
		this.nodeno = nodeno;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "citycode", nullable = false)
	public CityCode getCityCode() {
		return this.cityCode;
	}

	public void setCityCode(CityCode cityCode) {
		this.cityCode = cityCode;
	}

	@Column(name = "nodeid", nullable = false, length = 30)
	public String getNodeid() {
		return this.nodeid;
	}

	public void setNodeid(String nodeid) {
		this.nodeid = nodeid;
	}

	@Column(name = "nodenm", nullable = false, length = 30)
	public String getNodenm() {
		return this.nodenm;
	}

	public void setNodenm(String nodenm) {
		this.nodenm = nodenm;
	}

	@Column(name = "gpslati", nullable = false)
	public int getGpslati() {
		return this.gpslati;
	}

	public void setGpslati(int gpslati) {
		this.gpslati = gpslati;
	}

	@Column(name = "gpslong", nullable = false)
	public int getGpslong() {
		return this.gpslong;
	}

	public void setGpslong(int gpslong) {
		this.gpslong = gpslong;
	}

}
