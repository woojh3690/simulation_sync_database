package com.einssnc.model;
// Generated 2019. 6. 5 ���� 3:07:26 by Hibernate Tools 4.3.5.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * TurninfoId generated by hbm2java
 */
@Embeddable
public class TurninfoId implements java.io.Serializable {

	private String nodeId;
	private int turnId;

	public TurninfoId() {
	}

	public TurninfoId(String nodeId, int turnId) {
		this.nodeId = nodeId;
		this.turnId = turnId;
	}

	@Column(name = "node_id", nullable = false, length = 10)
	public String getNodeId() {
		return this.nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	@Column(name = "turn_id", nullable = false)
	public int getTurnId() {
		return this.turnId;
	}

	public void setTurnId(int turnId) {
		this.turnId = turnId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TurninfoId))
			return false;
		TurninfoId castOther = (TurninfoId) other;

		return ((this.getNodeId() == castOther.getNodeId()) || (this.getNodeId() != null
				&& castOther.getNodeId() != null && this.getNodeId().equals(castOther.getNodeId())))
				&& (this.getTurnId() == castOther.getTurnId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getNodeId() == null ? 0 : this.getNodeId().hashCode());
		result = 37 * result + this.getTurnId();
		return result;
	}

}
