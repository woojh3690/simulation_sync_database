package com.einssnc.model;
// Generated 2019. 6. 4 ���� 4:06:44 by Hibernate Tools 4.3.5.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * NationWideSpeed generated by hbm2java
 */
@Entity
@Table(name = "nation_wide_speed", catalog = "simulation")
public class NationWideSpeed implements java.io.Serializable {

	private NationWideSpeedId id;

	public NationWideSpeed() {
	}

	public NationWideSpeed(NationWideSpeedId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "time", column = @Column(name = "time", nullable = false, length = 26)),
			@AttributeOverride(name = "linkId", column = @Column(name = "link_id", nullable = false, length = 10)),
			@AttributeOverride(name = "speed", column = @Column(name = "speed")),
			@AttributeOverride(name = "trafficVolume", column = @Column(name = "traffic_volume")),
			@AttributeOverride(name = "density", column = @Column(name = "density")),
			@AttributeOverride(name = "travelTime", column = @Column(name = "travel_time")),
			@AttributeOverride(name = "delayTime", column = @Column(name = "delay_time")),
			@AttributeOverride(name = "vehicleLength", column = @Column(name = "vehicle_length")),
			@AttributeOverride(name = "sensorShare", column = @Column(name = "sensor_share")) })
	public NationWideSpeedId getId() {
		return this.id;
	}

	public void setId(NationWideSpeedId id) {
		this.id = id;
	}

}
