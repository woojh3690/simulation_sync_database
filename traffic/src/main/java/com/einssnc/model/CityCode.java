package com.einssnc.model;
// Generated 2019. 5. 20 ���� 4:14:02 by Hibernate Tools 5.2.12.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * CityCode generated by hbm2java
 */
@Entity
@Table(name = "city_code", catalog = "simulation")
public class CityCode implements java.io.Serializable {

	private int citycode;
	private String cityname;
	private Set<BusStop> busStops = new HashSet<BusStop>(0);

	public CityCode() {
	}

	public CityCode(int citycode, String cityname) {
		this.citycode = citycode;
		this.cityname = cityname;
	}

	public CityCode(int citycode, String cityname, Set<BusStop> busStops) {
		this.citycode = citycode;
		this.cityname = cityname;
		this.busStops = busStops;
	}

	@Id

	@Column(name = "citycode", unique = true, nullable = false)
	public int getCitycode() {
		return this.citycode;
	}

	public void setCitycode(int citycode) {
		this.citycode = citycode;
	}

	@Column(name = "cityname", nullable = false, length = 100)
	public String getCityname() {
		return this.cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cityCode")
	public Set<BusStop> getBusStops() {
		return this.busStops;
	}

	public void setBusStops(Set<BusStop> busStops) {
		this.busStops = busStops;
	}

}
