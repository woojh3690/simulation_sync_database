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
 * BusRoute generated by hbm2java
 */
@Entity
@Table(name = "bus_route", catalog = "simulation")
public class BusRoute implements java.io.Serializable {

	private String routeid;
	private CityCode cityCode;
	private String routeno;
	private String routetp;
	private String startnodenm;
	private String endnodenm;

	public BusRoute() {
	}

	public BusRoute(String routeid, CityCode cityCode) {
		this.routeid = routeid;
		this.cityCode = cityCode;
	}

	public BusRoute(String routeid, CityCode cityCode, String routeno, String routetp, String startnodenm,
			String endnodenm) {
		this.routeid = routeid;
		this.cityCode = cityCode;
		this.routeno = routeno;
		this.routetp = routetp;
		this.startnodenm = startnodenm;
		this.endnodenm = endnodenm;
	}

	@Id

	@Column(name = "routeid", unique = true, nullable = false, length = 30)
	public String getRouteid() {
		return this.routeid;
	}

	public void setRouteid(String routeid) {
		this.routeid = routeid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "citycode", nullable = false)
	public CityCode getCityCode() {
		return this.cityCode;
	}

	public void setCityCode(CityCode cityCode) {
		this.cityCode = cityCode;
	}

	@Column(name = "routeno", length = 100)
	public String getRouteno() {
		return this.routeno;
	}

	public void setRouteno(String routeno) {
		this.routeno = routeno;
	}

	@Column(name = "routetp", length = 20)
	public String getRoutetp() {
		return this.routetp;
	}

	public void setRoutetp(String routetp) {
		this.routetp = routetp;
	}

	@Column(name = "startnodenm", length = 50)
	public String getStartnodenm() {
		return this.startnodenm;
	}

	public void setStartnodenm(String startnodenm) {
		this.startnodenm = startnodenm;
	}

	@Column(name = "endnodenm", length = 50)
	public String getEndnodenm() {
		return this.endnodenm;
	}

	public void setEndnodenm(String endnodenm) {
		this.endnodenm = endnodenm;
	}

}
