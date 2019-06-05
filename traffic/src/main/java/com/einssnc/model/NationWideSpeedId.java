package com.einssnc.model;
// Generated 2019. 6. 5 ���� 12:36:18 by Hibernate Tools 4.3.5.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * NationWideSpeedId generated by hbm2java
 */
@Embeddable
public class NationWideSpeedId implements java.io.Serializable {

	private Date time;
	private String linkId;
	private Integer speed;
	private Integer trafficVolume;
	private Integer density;
	private Integer travelTime;
	private Integer delayTime;
	private Integer vehicleLength;
	private Integer sensorShare;

	public NationWideSpeedId() {
	}

	public NationWideSpeedId(Date time, String linkId) {
		this.time = time;
		this.linkId = linkId;
	}

	public NationWideSpeedId(Date time, String linkId, Integer speed, Integer trafficVolume, Integer density,
			Integer travelTime, Integer delayTime, Integer vehicleLength, Integer sensorShare) {
		this.time = time;
		this.linkId = linkId;
		this.speed = speed;
		this.trafficVolume = trafficVolume;
		this.density = density;
		this.travelTime = travelTime;
		this.delayTime = delayTime;
		this.vehicleLength = vehicleLength;
		this.sensorShare = sensorShare;
	}

	@Column(name = "time", nullable = false, length = 26)
	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Column(name = "link_id", nullable = false, length = 10)
	public String getLinkId() {
		return this.linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof NationWideSpeedId))
			return false;
		NationWideSpeedId castOther = (NationWideSpeedId) other;

		return ((this.getTime() == castOther.getTime()) || (this.getTime() != null && castOther.getTime() != null
				&& this.getTime().equals(castOther.getTime())))
				&& ((this.getLinkId() == castOther.getLinkId()) || (this.getLinkId() != null
						&& castOther.getLinkId() != null && this.getLinkId().equals(castOther.getLinkId())))
				&& ((this.getSpeed() == castOther.getSpeed()) || (this.getSpeed() != null
						&& castOther.getSpeed() != null && this.getSpeed().equals(castOther.getSpeed())))
				&& ((this.getTrafficVolume() == castOther.getTrafficVolume())
						|| (this.getTrafficVolume() != null && castOther.getTrafficVolume() != null
								&& this.getTrafficVolume().equals(castOther.getTrafficVolume())))
				&& ((this.getDensity() == castOther.getDensity()) || (this.getDensity() != null
						&& castOther.getDensity() != null && this.getDensity().equals(castOther.getDensity())))
				&& ((this.getTravelTime() == castOther.getTravelTime()) || (this.getTravelTime() != null
						&& castOther.getTravelTime() != null && this.getTravelTime().equals(castOther.getTravelTime())))
				&& ((this.getDelayTime() == castOther.getDelayTime()) || (this.getDelayTime() != null
						&& castOther.getDelayTime() != null && this.getDelayTime().equals(castOther.getDelayTime())))
				&& ((this.getVehicleLength() == castOther.getVehicleLength())
						|| (this.getVehicleLength() != null && castOther.getVehicleLength() != null
								&& this.getVehicleLength().equals(castOther.getVehicleLength())))
				&& ((this.getSensorShare() == castOther.getSensorShare())
						|| (this.getSensorShare() != null && castOther.getSensorShare() != null
								&& this.getSensorShare().equals(castOther.getSensorShare())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getTime() == null ? 0 : this.getTime().hashCode());
		result = 37 * result + (getLinkId() == null ? 0 : this.getLinkId().hashCode());
		result = 37 * result + (getSpeed() == null ? 0 : this.getSpeed().hashCode());
		result = 37 * result + (getTrafficVolume() == null ? 0 : this.getTrafficVolume().hashCode());
		result = 37 * result + (getDensity() == null ? 0 : this.getDensity().hashCode());
		result = 37 * result + (getTravelTime() == null ? 0 : this.getTravelTime().hashCode());
		result = 37 * result + (getDelayTime() == null ? 0 : this.getDelayTime().hashCode());
		result = 37 * result + (getVehicleLength() == null ? 0 : this.getVehicleLength().hashCode());
		result = 37 * result + (getSensorShare() == null ? 0 : this.getSensorShare().hashCode());
		return result;
	}

}
