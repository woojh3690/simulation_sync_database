package com.einssnc.model;
// Generated 2019. 5. 23 ���� 10:52:08 by Hibernate Tools 4.3.5.Final

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

	public NationWideSpeedId() {
	}

	public NationWideSpeedId(Date time, String linkId) {
		this.time = time;
		this.linkId = linkId;
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
						&& castOther.getLinkId() != null && this.getLinkId().equals(castOther.getLinkId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getTime() == null ? 0 : this.getTime().hashCode());
		result = 37 * result + (getLinkId() == null ? 0 : this.getLinkId().hashCode());
		return result;
	}

}
