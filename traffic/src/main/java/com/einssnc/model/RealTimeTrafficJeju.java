package com.einssnc.model;
// Generated 2019. 5. 31 ���� 9:03:40 by Hibernate Tools 4.3.5.Final

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
 * RealTimeTrafficJeju generated by hbm2java
 */
@Entity
@Table(name = "real_time_traffic_jeju", catalog = "simulation")
public class RealTimeTrafficJeju implements java.io.Serializable {

	private RealTimeTrafficJejuId id;
	private Link link;
	private int tfvl;
	private int sped;
	private int ocpyRate;
	private int trvlHh;

	public RealTimeTrafficJeju() {
	}

	public RealTimeTrafficJeju(RealTimeTrafficJejuId id, Link link, int tfvl, int sped, int ocpyRate, int trvlHh) {
		this.id = id;
		this.link = link;
		this.tfvl = tfvl;
		this.sped = sped;
		this.ocpyRate = ocpyRate;
		this.trvlHh = trvlHh;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "linkId", column = @Column(name = "link_id", nullable = false, length = 10)),
			@AttributeOverride(name = "prcnDt", column = @Column(name = "prcn_dt", nullable = false, length = 26)) })
	public RealTimeTrafficJejuId getId() {
		return this.id;
	}

	public void setId(RealTimeTrafficJejuId id) {
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

	@Column(name = "tfvl", nullable = false)
	public int getTfvl() {
		return this.tfvl;
	}

	public void setTfvl(int tfvl) {
		this.tfvl = tfvl;
	}

	@Column(name = "sped", nullable = false)
	public int getSped() {
		return this.sped;
	}

	public void setSped(int sped) {
		this.sped = sped;
	}

	@Column(name = "ocpy_rate", nullable = false)
	public int getOcpyRate() {
		return this.ocpyRate;
	}

	public void setOcpyRate(int ocpyRate) {
		this.ocpyRate = ocpyRate;
	}

	@Column(name = "trvl_hh", nullable = false)
	public int getTrvlHh() {
		return this.trvlHh;
	}

	public void setTrvlHh(int trvlHh) {
		this.trvlHh = trvlHh;
	}

}
