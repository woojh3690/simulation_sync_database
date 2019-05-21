package com.einssnc.model;
// Generated 2019. 5. 21 ���� 10:28:14 by Hibernate Tools 5.2.12.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * NationWideSpeed generated by hbm2java
 */
@Entity
@Table(name = "nation_wide_speed", catalog = "simulation")
public class NationWideSpeed implements java.io.Serializable {

	private NationWideSpeedId id;
	private Link link;
	private Integer speed;
	private Integer trafficVolume;
	private Integer density;
	private Integer travelTime;
	private Integer delayTime;
	private Integer vehicleLength;
	private Integer sensorShare;

	public NationWideSpeed() {
	}

	public NationWideSpeed(NationWideSpeedId id, Link link) {
		this.id = id;
		this.link = link;
	}

	public NationWideSpeed(NationWideSpeedId id, Link link, Integer speed, Integer trafficVolume, Integer density,
			Integer travelTime, Integer delayTime, Integer vehicleLength, Integer sensorShare) {
		this.id = id;
		this.link = link;
		this.speed = speed;
		this.trafficVolume = trafficVolume;
		this.density = density;
		this.travelTime = travelTime;
		this.delayTime = delayTime;
		this.vehicleLength = vehicleLength;
		this.sensorShare = sensorShare;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "linkId", column = @Column(name = "link_id", nullable = false, length = 10)),
			@AttributeOverride(name = "time", column = @Column(name = "time", nullable = false, length = 26)) })
	public NationWideSpeedId getId() {
		return this.id;
	}

	public void setId(NationWideSpeedId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "link_id", nullable = false, insertable = false, updatable = false)
	public Link getLink() {
		return this.link;
	}

	public void setLink(Link link) {
		this.link = link;
	}

	@Column(name = "speed")
	public Integer getSpeed() {
		return this.speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}

	@Column(name = "traffic_volume")
	public Integer getTrafficVolume() {
		return this.trafficVolume;
	}

	public void setTrafficVolume(Integer trafficVolume) {
		this.trafficVolume = trafficVolume;
	}

	@Column(name = "density")
	public Integer getDensity() {
		return this.density;
	}

	public void setDensity(Integer density) {
		this.density = density;
	}

	@Column(name = "travel_time")
	public Integer getTravelTime() {
		return this.travelTime;
	}

	public void setTravelTime(Integer travelTime) {
		this.travelTime = travelTime;
	}

	@Column(name = "delay_time")
	public Integer getDelayTime() {
		return this.delayTime;
	}

	public void setDelayTime(Integer delayTime) {
		this.delayTime = delayTime;
	}

	@Column(name = "vehicle_length")
	public Integer getVehicleLength() {
		return this.vehicleLength;
	}

	public void setVehicleLength(Integer vehicleLength) {
		this.vehicleLength = vehicleLength;
	}

	@Column(name = "sensor_share")
	public Integer getSensorShare() {
		return this.sensorShare;
	}

	public void setSensorShare(Integer sensorShare) {
		this.sensorShare = sensorShare;
	}

}
