package com.einssnc.model;
// Generated 2019. 6. 5 ���� 3:07:26 by Hibernate Tools 4.3.5.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * RealTimeTrafficJejuId generated by hbm2java
 */
@Embeddable
public class RealTimeTrafficJejuId implements java.io.Serializable {

	private String linkId;
	private Date prcnDt;

	public RealTimeTrafficJejuId() {
	}

	public RealTimeTrafficJejuId(String linkId, Date prcnDt) {
		this.linkId = linkId;
		this.prcnDt = prcnDt;
	}

	@Column(name = "link_id", nullable = false, length = 10)
	public String getLinkId() {
		return this.linkId;
	}

	public void setLinkId(String linkId) {
		this.linkId = linkId;
	}

	@Column(name = "prcn_dt", nullable = false, length = 26)
	public Date getPrcnDt() {
		return this.prcnDt;
	}

	public void setPrcnDt(Date prcnDt) {
		this.prcnDt = prcnDt;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof RealTimeTrafficJejuId))
			return false;
		RealTimeTrafficJejuId castOther = (RealTimeTrafficJejuId) other;

		return ((this.getLinkId() == castOther.getLinkId()) || (this.getLinkId() != null
				&& castOther.getLinkId() != null && this.getLinkId().equals(castOther.getLinkId())))
				&& ((this.getPrcnDt() == castOther.getPrcnDt()) || (this.getPrcnDt() != null
						&& castOther.getPrcnDt() != null && this.getPrcnDt().equals(castOther.getPrcnDt())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getLinkId() == null ? 0 : this.getLinkId().hashCode());
		result = 37 * result + (getPrcnDt() == null ? 0 : this.getPrcnDt().hashCode());
		return result;
	}

}
