package com.einssnc.model;
// Generated 2019. 6. 5 ���� 3:07:26 by Hibernate Tools 4.3.5.Final

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
 * Turninfo generated by hbm2java
 */
@Entity
@Table(name = "turninfo", catalog = "simulation")
public class Turninfo implements java.io.Serializable {

	private TurninfoId id;
	private Node node;
	private String stLink;
	private String edLink;
	private String turnType;
	private Character turnOper;
	private String remark;

	public Turninfo() {
	}

	public Turninfo(TurninfoId id, Node node) {
		this.id = id;
		this.node = node;
	}

	public Turninfo(TurninfoId id, Node node, String stLink, String edLink, String turnType, Character turnOper,
			String remark) {
		this.id = id;
		this.node = node;
		this.stLink = stLink;
		this.edLink = edLink;
		this.turnType = turnType;
		this.turnOper = turnOper;
		this.remark = remark;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "nodeId", column = @Column(name = "node_id", nullable = false, length = 10)),
			@AttributeOverride(name = "turnId", column = @Column(name = "turn_id", nullable = false)) })
	public TurninfoId getId() {
		return this.id;
	}

	public void setId(TurninfoId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "node_id", nullable = false, insertable = false, updatable = false)
	public Node getNode() {
		return this.node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	@Column(name = "st_link", length = 10)
	public String getStLink() {
		return this.stLink;
	}

	public void setStLink(String stLink) {
		this.stLink = stLink;
	}

	@Column(name = "ed_link", length = 10)
	public String getEdLink() {
		return this.edLink;
	}

	public void setEdLink(String edLink) {
		this.edLink = edLink;
	}

	@Column(name = "turn_type", length = 3)
	public String getTurnType() {
		return this.turnType;
	}

	public void setTurnType(String turnType) {
		this.turnType = turnType;
	}

	@Column(name = "turn_oper", length = 1)
	public Character getTurnOper() {
		return this.turnOper;
	}

	public void setTurnOper(Character turnOper) {
		this.turnOper = turnOper;
	}

	@Column(name = "remark", length = 100)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
